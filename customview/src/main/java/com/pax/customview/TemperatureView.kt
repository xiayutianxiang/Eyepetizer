package com.pax.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.ColorUtils
import java.text.DecimalFormat
import java.util.*
import kotlin.properties.Delegates

class TemperatureView : View {

    private val mSmallRadius = 0
    private lateinit var mAnimTimer: Timer

    private var mWaveUpValue: Int = 0
    private lateinit var mCurTemperature: String
    private var targetAngle: Float = 0.0f
    private var totalAngle: Float = 0.0f
    private var centerPosition: Point = Point()
    private val mRecf = RectF()     //边界矩形
    private var radius by Delegates.notNull<Float>()  //半径
    private lateinit var mDegreeCirPaint: Paint
    private lateinit var mDegreeLineCirPaint: Paint

    private var isAnim = false
    private var mDigit = 0
    private var mBgCirColor //颜色
            = 0
    private var mBgCirWidth //圆环背景宽度
            = 0f
    private var mCirColor = 0
    private var mCirWidth //表示进度的圆环
            = 0f
    private var mAnimTime //动画时长，最长1000ms
            = 0
    private var mValue: String? = null
    private var mMaxValue = 0f
    private var mStartAngle //起始角度
            = 120f
    private var mSweepAngle //滑过的角度
            = 300f

    private var mTargetAngle = 300f //刻度的角度
    private var mValueSize = 0f
    private var mValueColor = 0
    private var mHint: String? = null
    private var mHintSize = 0f
    private var mHintColor = 0
    private var mUnit: String? = null
    private var mShadowColor = 0
    private var mShadowIsShow = false
    private var mShadowSize = 0f
    private var isGradient //颜色渐变
            = false
    private var mGradientColor = 0
    private var mGradientColors = intArrayOf(Color.RED, Color.GRAY, Color.BLUE)
    private val mSweepGradient: SweepGradient? = null

    //动画状态
    private var isAnimRunning = false
    private val slow = arrayOf(10, 10, 10, 8, 8, 8, 6, 6, 6, 4, 4, 4, 4, 2)

    //动画的下标
    private var goIndex = 0

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAttrs(context, attrs)
        initPaint()
    }

    private fun initAttrs(context: Context?, attrs: AttributeSet?) {
        //获取到自定义属性
        val typedArray = context!!.obtainStyledAttributes(attrs, R.styleable.TemperatureView)

        isAnim = typedArray.getBoolean(R.styleable.TemperatureView_isanim, true)
        mDigit = typedArray.getInt(R.styleable.TemperatureView_digit, 2)
        mBgCirColor = typedArray.getColor(R.styleable.TemperatureView_mBgCirColor, Color.GRAY)
        mBgCirWidth = typedArray.getDimension(R.styleable.TemperatureView_mBgCirWidth, 15f)
        mCirColor = typedArray.getColor(R.styleable.TemperatureView_mCirColor, Color.YELLOW)
        mCirWidth = typedArray.getDimension(R.styleable.TemperatureView_mCirWidth, 15f)
        mAnimTime = typedArray.getInt(R.styleable.TemperatureView_animTime, 1000)
        mValue = typedArray.getString(R.styleable.TemperatureView_value)
        mMaxValue = typedArray.getFloat(R.styleable.TemperatureView_maxvalue, 100f)
        mStartAngle = typedArray.getFloat(R.styleable.TemperatureView_startAngle, 270f)
        mSweepAngle = typedArray.getFloat(R.styleable.TemperatureView_sweepAngle, 360f)
        mValueSize = typedArray.getDimension(R.styleable.TemperatureView_valueSize, 15f)
        mValueColor = typedArray.getColor(R.styleable.TemperatureView_valueColor, Color.BLACK)
        mHint = typedArray.getString(R.styleable.TemperatureView_hint)
        mHintSize = typedArray.getDimension(R.styleable.TemperatureView_hintSize, 15f)
        mHintColor = typedArray.getColor(R.styleable.TemperatureView_hintColor, Color.GRAY)
        mUnit = typedArray.getString(R.styleable.TemperatureView_unit)
        mShadowColor = typedArray.getColor(R.styleable.TemperatureView_shadowColor, Color.BLACK)
        mShadowIsShow = typedArray.getBoolean(R.styleable.TemperatureView_shadowShow, false)
        mShadowSize = typedArray.getFloat(R.styleable.TemperatureView_shadowSize, 8f)
        isGradient = typedArray.getBoolean(R.styleable.TemperatureView_isGradient, false)
        mGradientColor = typedArray.getResourceId(R.styleable.TemperatureView_gradient, 0)
        if (mGradientColor != 0) {
            mGradientColors = context.resources.getIntArray(mGradientColor)
        }
        typedArray.recycle()
    }

    //初始化画笔
    private fun initPaint() {
        mDegreeCirPaint = Paint()
        mDegreeCirPaint.color = Color.WHITE
        mDegreeCirPaint.style = Paint.Style.STROKE
        mDegreeCirPaint.strokeCap = Paint.Cap.ROUND

        mDegreeLineCirPaint = Paint()
        mDegreeLineCirPaint.style = Paint.Style.STROKE
        mDegreeLineCirPaint.strokeCap = Paint.Cap.SQUARE
        mDegreeLineCirPaint.isAntiAlias = true
        mDegreeLineCirPaint
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        //获取控件的宽高
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var newWidthMeasureSpec = widthMeasureSpec

        //如果没有设置宽度，就设置默认宽度
        if (widthMode != MeasureSpec.EXACTLY) {
            newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(400, MeasureSpec.EXACTLY)
        }

        //获取最新的宽度
        val width = MeasureSpec.getSize(newWidthMeasureSpec) - paddingLeft - paddingRight

        //求圆心位置
        centerPosition.x = width / 2
        centerPosition.y = width / 2
        radius = width / 2f
        mRecf.set(0f, 0f, width.toFloat(), width.toFloat())

        super.onMeasure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //先画一个圆环
        canvas.drawArc(
            mRecf.left + 2f,
            mRecf.top + 2f,
            mRecf.right - 2f,
            mRecf.bottom - 2f,
            mStartAngle,
            mSweepAngle,
            false,
            mDegreeCirPaint
        )
        drawDegreeLine(canvas)
    }

    private fun drawDegreeLine(canvas: Canvas) {
        //先保存
        canvas.save()
        //移动到中心点开始绘制
        canvas.translate(radius, radius)
        //旋转坐标系，确定旋转的角度
        canvas.rotate(30f)

        //每次旋转的角度
        val rotateAngle = mSweepAngle / 100
        //累计叠加的角度
        var currentAngle = 0f
        for (i in 0..100) {
            if (currentAngle <= mTargetAngle && mTargetAngle != 0f) {
                //计算累计滑过的刻度百分比
                val percent = currentAngle / mSweepAngle
                //动态设置颜色
                mDegreeLineCirPaint.color = ColorUtils.blendARGB(Color.GREEN, Color.RED, percent)

                canvas.drawLine(0f, radius, 0f, radius - 20, mDegreeLineCirPaint)
                //画过的角度累加
                currentAngle += rotateAngle
            } else {
                mDegreeLineCirPaint.color = Color.WHITE
                canvas.drawLine(0f, radius, 0f, radius - 20, mDegreeLineCirPaint)
            }
            //画完一个刻度，旋转位置
            canvas.rotate(rotateAngle)
        }
        canvas.restore()
    }

    fun evaluateColor(percent: Float, colorStart: Int, colorEnd: Int): Int {
        var redCurrent = 0
        var blueCurrent = 0
        var greenCurrent = 0
        var alphaCurrent = 0

        val redStart = Color.red(colorStart)
        val blueStart = Color.blue(colorStart)
        val greenStart = Color.green(colorStart)
        val alphaStart = Color.alpha(colorStart)

        val redEnd = Color.red(colorEnd)
        val blueEnd = Color.blue(colorEnd)
        val greenEnd = Color.green(colorEnd)
        val alphaEnd = Color.alpha(colorEnd)

        val redDifference = redEnd - redStart
        val blueDifference = blueEnd - blueStart
        val greenDifference = greenEnd - greenStart
        val alphaDifference = alphaEnd - alphaStart

        redCurrent = (redStart + percent * redDifference).toInt()
        blueCurrent = (blueStart + percent * blueDifference).toInt()
        greenCurrent = (greenStart + percent * greenDifference).toInt()
        alphaCurrent = (alphaStart + percent * alphaDifference).toInt()

        return Color.argb(alphaCurrent, redCurrent, blueCurrent, greenCurrent)
    }

    private var mCurPercent by Delegates.notNull<Float>()

    //设置温度、入口的开始
    fun setupTemperature(temperature: Float) {
        mCurPercent = 0f
        totalAngle = (temperature / 100) * mSweepAngle
        targetAngle = 0f
        mCurPercent = 0f
        mCurTemperature = "0.0"
        mWaveUpValue = 0
    }

    //使用定时任务做动画
    fun startTimerAnim() {
        if (isAnimRunning) {
            return
        }
        mAnimTimer = Timer()
        mAnimTimer.schedule(object : TimerTask() {
            override fun run() {
                isAnimRunning = true
                targetAngle += slow[goIndex]
                goIndex++
                if (goIndex == slow.size) {
                    goIndex--;
                }
                if (targetAngle >= totalAngle) {
                    targetAngle = totalAngle
                    isAnimRunning = false
                    mAnimTimer.cancel()
                }

                //计算的温度
                mCurPercent = targetAngle / mSweepAngle
                mCurTemperature = DecimalFormat().format(mCurPercent * 100)
                //水波纹的高度
                mWaveUpValue = (mCurPercent * (mSmallRadius * 2)).toInt()

                postInvalidate()
            }
        }, 250, 30)
    }
}
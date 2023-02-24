package com.pax.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class TemperatureView : View {

    private var centerPosition: Point = Point()
    private val mRecf = RectF()     //边界矩形
    private var radius by Delegates.notNull<Float>()  //半径
    private lateinit var mDegreeCirPaint: Paint

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
            = 0f
    private var mSweepAngle //滑过的角度
            = 0f
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

        //移动到中心点开始绘制
        canvas.translate(radius, radius)
    }
}
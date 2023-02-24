package com.pax.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;

public class ProgressView extends View {

    private final static String TAG = "TemperatureView";

    private boolean isAnim;
    private int mDigit;
    private int mBgCirColor;    //颜色
    private float mBgCirWidth;  //圆环背景宽度
    private int mCirColor;
    private float mCirWidth;    //表示进度的圆环
    private int mAnimTime;      //动画时长，最长1000ms
    private String mValue;
    private float mMaxValue;
    private float mStartAngle; //起始角度
    private float mSweepAngle;  //滑过的角度
    private float mValueSize;
    private int mValueColor;
    private String mHint;
    private float mHintSize;
    private int mHintColor;
    private String mUnit;
    private int mShadowColor;
    private boolean mShadowIsShow;
    private float mShadowSize;
    private boolean isGradient; //颜色渐变
    private int mGradientColor;
    private int[] mGradientColors = new int[]{Color.RED, Color.GRAY, Color.BLUE};
    private SweepGradient mSweepGradient;
    //是否开启抗锯齿
    private boolean antiAlias = true;
    //背景圆画笔
    private Paint mBgCirPaint;
    //进度圆画笔
    private Paint mCirPaint;

    //进度数值
    private TextPaint mValuePaint;
    //提示文本
    private TextPaint mHintPaint;

    //圆心位置
    private Point centerPosition = new Point();
    //半径
    private float radius;
    //边界矩形
    private RectF mRectF = new RectF();
    //属性动画
    private ValueAnimator mValueAnimator;
    //动画进度
    private float mAnimPercent = 0f;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
    }

    //初始化画笔
    private void initPaint() {
        //圆画笔
        mCirPaint = new Paint();
        mCirPaint.setAntiAlias(antiAlias);
        mCirPaint.setStyle(Paint.Style.STROKE);
        mCirPaint.setStrokeCap(Paint.Cap.ROUND);
        mCirPaint.setStrokeWidth(mCirWidth);
        mCirPaint.setColor(mCirColor);
        //背景圆画笔
        mBgCirPaint = new Paint();
        mBgCirPaint.setAntiAlias(antiAlias);
        mBgCirPaint.setStyle(Paint.Style.STROKE);
        mBgCirPaint.setStrokeCap(Paint.Cap.ROUND);
        mBgCirPaint.setStrokeWidth(mBgCirWidth);
        mBgCirPaint.setColor(mBgCirColor);

        //文字主题的画笔
        mValuePaint = new TextPaint();
        mValuePaint.setAntiAlias(antiAlias);
        mValuePaint.setTextSize(mValueSize);
        mValuePaint.setColor(mValueColor);
        mValuePaint.setTextAlign(Paint.Align.CENTER);   //从中间向两侧绘制
        /*  mValuePaint.setTypeface(new Typeface.Builder(Typeface.DEFAULT));*/

        //提示文本的字体画笔
        mHintPaint = new TextPaint();
        mHintPaint.setAntiAlias(antiAlias);
        mHintPaint.setTextSize(mHintSize);
        mHintPaint.setColor(mHintColor);
        mHintPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        //获取到自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TemperatureView);

        isAnim = typedArray.getBoolean(R.styleable.TemperatureView_isanim, true);
        mDigit = typedArray.getInt(R.styleable.TemperatureView_digit, 2);
        mBgCirColor = typedArray.getColor(R.styleable.TemperatureView_mBgCirColor, Color.GRAY);
        mBgCirWidth = typedArray.getDimension(R.styleable.TemperatureView_mBgCirWidth, 15f);
        mCirColor = typedArray.getColor(R.styleable.TemperatureView_mCirColor, Color.YELLOW);
        mCirWidth = typedArray.getDimension(R.styleable.TemperatureView_mCirWidth, 15f);
        mAnimTime = typedArray.getInt(R.styleable.TemperatureView_animTime, 1000);
        mValue = typedArray.getString(R.styleable.TemperatureView_value);
        mMaxValue = typedArray.getFloat(R.styleable.TemperatureView_maxvalue, 100f);
        mStartAngle = typedArray.getFloat(R.styleable.TemperatureView_startAngle, 270f);
        mSweepAngle = typedArray.getFloat(R.styleable.TemperatureView_sweepAngle, 360f);
        mValueSize = typedArray.getDimension(R.styleable.TemperatureView_valueSize, 15f);
        mValueColor = typedArray.getColor(R.styleable.TemperatureView_valueColor, Color.BLACK);
        mHint = typedArray.getString(R.styleable.TemperatureView_hint);
        mHintSize = typedArray.getDimension(R.styleable.TemperatureView_hintSize, 15f);
        mHintColor = typedArray.getColor(R.styleable.TemperatureView_hintColor, Color.GRAY);
        mUnit = typedArray.getString(R.styleable.TemperatureView_unit);
        mShadowColor = typedArray.getColor(R.styleable.TemperatureView_shadowColor, Color.BLACK);
        mShadowIsShow = typedArray.getBoolean(R.styleable.TemperatureView_shadowShow, false);
        mShadowSize = typedArray.getFloat(R.styleable.TemperatureView_shadowSize, 8f);
        isGradient = typedArray.getBoolean(R.styleable.TemperatureView_isGradient, false);
        mGradientColor = typedArray.getResourceId(R.styleable.TemperatureView_gradient, 0);
        if (mGradientColor != 0) {
            mGradientColors = context.getResources().getIntArray(mGradientColor);
        }
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();

        //圆心位置
        centerPosition.x = width / 2;
        centerPosition.y = height / 2;

        //取最宽的圆环宽度
        float maxCirWidth = Math.max(mCirWidth, mBgCirWidth);
        //半径应为背景圆环和进度圆环的最小半径减去padding值，再减去最宽的圆环宽度 * 2
        float minWidth = Math.min(width, height) - getPaddingBottom() - getPaddingTop() - 2 * maxCirWidth;
        radius = minWidth / 2;

        //矩形坐标
        mRectF.left = centerPosition.x - radius - maxCirWidth / 2;
        mRectF.top = centerPosition.y - radius - maxCirWidth / 2;
        mRectF.right = centerPosition.x + radius + maxCirWidth / 2;
        mRectF.bottom = centerPosition.y + radius + maxCirWidth / 2;
        if (isGradient) {
            //设置圆环画笔渐变
            setupGradientCircle();
        }
    }

    /**
     * 设置圆形和矩阵的大小、圆心位置
     * onSizeChanged() 即将显示出来
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /*//圆心位置
        centerPosition.x = w / 2;
        centerPosition.y = h / 2;
        //半径
        float maxCirWidth = Math.max(mCirWidth, mBgCirWidth);
        float minWidth = Math.min(w - getPaddingLeft() - getPaddingRight() - 2 * maxCirWidth,
                h - getPaddingBottom() - getPaddingTop() - 2 * maxCirWidth);
        radius = minWidth / 2;

        //矩形坐标
        mRectF.left = centerPosition.x - radius - maxCirWidth / 2;
        mRectF.top = centerPosition.y - radius - maxCirWidth / 2;
        mRectF.right = centerPosition.x + radius + maxCirWidth / 2;
        mRectF.bottom = centerPosition.y + radius + maxCirWidth / 2;
        if (isGradient) {
            //设置圆环画笔渐变
            setupGradientCircle();
        }*/
    }

    private void setupGradientCircle() {
        mSweepGradient = new SweepGradient((float) centerPosition.x, (float) centerPosition.y, mGradientColors, null);

        mCirPaint.setShader(mSweepGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制文本和圆环
        drawText(canvas);
        drawCircle(canvas);
    }

    private void drawText(Canvas canvas) {
        //传入绘制的文本、x坐标、y坐标、画笔，文本在圆心处，所以要用到圆心坐标
        mValue = mValue == null ? "0.00" : mValue;
        canvas.drawText(mValue + mUnit, (float) centerPosition.x, (float) centerPosition.y, mValuePaint);
        if (mHint != null) {
            //ascent:根据当前字体和文本大小返回基线（上升）上方（负）的距离s
            canvas.drawText(mHint, (float) centerPosition.x, (float) centerPosition.y - mHintPaint.ascent() + 15, mHintPaint);
        }
    }

    private void drawCircle(Canvas canvas) {
        canvas.save();
        if (mShadowIsShow) {
            mCirPaint.setShadowLayer(mShadowSize, 0f, 0f, mShadowColor);
        }
        canvas.drawArc(mRectF, mStartAngle, mSweepAngle, false, mBgCirPaint);
        //画圆
        canvas.drawArc(mRectF, mStartAngle, mSweepAngle * mAnimPercent, false, mCirPaint);
        canvas.restore();
    }

    //TODO:动画时长应该由增长速率决定，最大1000ms，如果从0%涨到50%，那么就应该花费500ms，长到60%，就是600ms
    private void startAnim(float start, float end, String value, String oldValue) {
        mValueAnimator = ValueAnimator.ofFloat(start, end); //以浮点数的形式，过渡到结束值

        double valueI = Double.parseDouble(value);
        double oldValueI = Double.parseDouble(oldValue);
        int animTime = (int) Math.abs(valueI - oldValueI) * 10;
        mValueAnimator.setDuration(animTime);
        mValueAnimator.addUpdateListener(animation -> {
            mAnimPercent = (float) animation.getAnimatedValue();

            //根据当前动画得到当前值
            if (isAnim) {
                mValue = roundByScale((double) (mAnimPercent * mMaxValue), mDigit);
            } else {
                mValue = roundByScale(Double.parseDouble(mValue), mDigit);
            }
            //调用此方法重绘界面
            postInvalidate();
           // invalidate(); //只能在UI线程执行
        });
        mValueAnimator.start();
    }

    private String roundByScale(double v, int digit) {
        if (digit < 0) {
            throw new IllegalArgumentException("参数错误,必须设置参数大于0的数字");
        }
        if (digit == 0) {
            return new DecimalFormat("0").format(v);
        }
        StringBuilder formatStr = new StringBuilder("0.");
        for (int i = 0; i < digit; i++) {
            formatStr.append("0");
        }
        return new DecimalFormat(formatStr.toString()).format(v);
    }

    public void setValue(String value, float maxValue) {
        String oldValue = mValue;
        if (isNum(value)) {
            mValue = value;
            float start = mAnimPercent;
            float end = Float.parseFloat(value) / maxValue;
            startAnim(start, end, mValue, oldValue);
        } else {
            mValue = value;
        }
    }

    private boolean isNum(String str) {
        Double.parseDouble(str);
        return true;
    }
}

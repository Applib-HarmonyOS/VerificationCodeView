package com.zhangym.customview;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.text.Font;
import ohos.agp.utils.Color;
import ohos.agp.utils.Rect;
import ohos.app.Context;
import com.hmos.compact.utils.AttrUtils;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Verification code control
 * Created by zhangyongming on 2017/1/14.
 */
public class VerificationCodeView extends Component implements Component.DrawTask, Component.EstimateSizeListener {

    /**
     * Verification code text.
     */
    private String mVerificationText;

    /**
     * The color of the verification code text, the default is black.
     */
    private int mTextColor = Color.BLACK.getValue();

    /**
     * Verification code text size, the default is 16.
     */
    private int mTextSize = 16;

    /**
     * Is there an underline.
     */
    private boolean isUnderLine;

    /**
     * Is text bold.
     */
    private boolean isTextBold;

    /**
     * The slope of the text, positive numbers are left slanting, negative numbers are right slanting,
     * the default is 0f.
     */
    private float mTextSkewX = 0f;

    /**
     * TextWidth.
     */
    private float mStrokeWidth;

    /**
     * Whether to display interference lines, the default is true.
     */
    private boolean isShowInterferenceLines = true;

    /**
     * The number of interference lines, the default is 10.
     */
    private int mInterferenceLinesCount = 10;

    /**
     * The color of the interference line, the default is black.
     */
    private int mInterferenceLinesColor = Color.BLACK.getValue();

    /**
     * The width of the interference line, the default is 3.
     */
    private float mInterferenceLinesWidth = 3f;

    /**
     * Whether to display interference dots, the default is true.
     */
    private boolean isShowInterferenceCircles = true;

    /**
     * The number of interference dots, the default is 100.
     */
    private int mInterferenceCirclesCount = 100;

    /**
     * The color of the interference dots, the default is black.
     */
    private int mInterferenceCirclesColor = Color.BLACK.getValue();

    /**
     * The radius of the interference dot, the default is 5.
     */
    private float mInterferenceCirclesRadius = 5f;

    /**
     * The background color of the verification code.
     */
    private int mVerificationCodeBackground = Color.GRAY.getValue();

    /**
     * Whether the color of the interference circle is random,
     * if the value of mInterferenceCircleColor is manually set, the value is false.
     */
    private boolean isCircleColorRandom = true;

    /**
     * Whether the color of the interference line is random,
     * if the value of mInterferenceLinesColor is manually set,the value is false.
     */
    private boolean isLineColorRandom = true;

    /**
     * Paintbrush.
     */
    private final Paint mPaint = new Paint();

    /**
     * Random function.
     */
    private final Random mRandom = new SecureRandom();

    /**
     * The drawing area of ​​the text.
     */
    private final Rect mTextRect = new Rect();

    /**
     * Whether to call the requestLayout() method.
     *
     */
    private boolean isNeedRequesLayout = false;

    /**
     * Whether to display interference lines.
     *
     * @return Whether to display the boolean value of the interfering line.
     */
    public boolean isShowInterferenceLines() {
        return isShowInterferenceLines;
    }

    /**
     * Set whether to display interference lines.
     *
     * @param showInterferenceLines Whether to display the boolean value of interference lines.
     */
    public void setShowInterferenceLines(boolean showInterferenceLines) {
        isShowInterferenceLines = showInterferenceLines;
    }

    /**
     * Whether to display interference dots.
     *
     * @return Whether to display the boolean value of the interference dot.
     */
    public boolean isShowInterferenceCircles() {
        return isShowInterferenceCircles;
    }

    /**
     * Set whether to display interference dots.
     *
     * @param showInterferenceCircles Whether to display the boolean value of the interference circle.
     */
    public void setShowInterferenceCircles(boolean showInterferenceCircles) {
        isShowInterferenceCircles = showInterferenceCircles;
    }

    /**
     * Whether the text is bold.
     *
     * @return Whether the text is a bold boolean value.
     */
    public boolean isTextBold() {
        return isTextBold;
    }

    /**
     * Set whether the text is bold.
     *
     * @param textBold Whether the text is a bold boolean value.
     */
    public void setTextBold(boolean textBold) {
        isTextBold = textBold;
    }

    /**
     * Get whether the color of the interference dot is random.
     *
     * @return is a boolean value that interferes with whether the color of the dot is random.
     */
    public boolean isCircleColorRandom() {
        return isCircleColorRandom;
    }

    /**
     * Set whether the color of the interference dot is random.
     *
     * @param circleColorRandom is the boolean value of whether the color of the interference dot is random.
     */
    public void setCircleColorRandom(boolean circleColorRandom) {
        isCircleColorRandom = circleColorRandom;
    }

    /**
     * Get whether the line color is random.
     *
     * @return Whether the line color is random boolean.
     */
    public boolean isLineColorRandom() {
        return isLineColorRandom;
    }

    /**
     * Set whether the line color is random.
     *
     * @param lineColorRandom Whether the line color is random boolean.
     */
    public void setLineColorRandom(boolean lineColorRandom) {
        isLineColorRandom = lineColorRandom;
    }

    /**
     * Get the verification code text.
     *
     * @return String value of the verification code text.
     */
    public String getVerificationText() {
        return mVerificationText;
    }

    /**
     * Set the verification code text.
     *
     * @param verificationText String value of verification code text.
     */
    public void setVerificationText(String verificationText) {
        if (getVerificationText() == null || "".equals(getVerificationText())) {
            isNeedRequesLayout = true;
        }

        mVerificationText = verificationText;
        if (isNeedRequesLayout) {
            this.postLayout();
            isNeedRequesLayout = false;
        }
        invalidate();
    }

    /**
     * Get the background color of the control.
     *
     * @return int type color value.
     */
    public int getVerificationCodeBackground() {
        return mVerificationCodeBackground;
    }

    /**
     * Set the background color of the control.
     *
     * @param verificationCodeBackground int type color value.
     */
    public void setVerificationCodeBackground(int verificationCodeBackground) {
        mVerificationCodeBackground = verificationCodeBackground;
    }

    /**
     * Get the number of interference lines.
     *
     * @return The number of lines.
     */
    public int getInterferenceLinesCount() {
        return mInterferenceLinesCount;
    }

    /**
     * Set the number of interference lines.
     *
     * @param interferenceLinesCount The number of lines.
     */
    public void setInterferenceLinesCount(int interferenceLinesCount) {
        mInterferenceLinesCount = interferenceLinesCount;
    }

    /**
     * Set the color of interference lines.
     *
     * @param interferenceLinesColor The color int value of interference lines.
     */
    public void setInterferenceLinesColor(int interferenceLinesColor) {
        isLineColorRandom = false;
        mInterferenceLinesColor = interferenceLinesColor;
    }

    /**
     * Get the width of the interference line.
     *
     * @return The width value of the line.
     */
    public float getInterferenceLinesWidth() {
        return mInterferenceLinesWidth;
    }

    /**
     * Set the width of interference lines.
     *
     * @param interferenceLinesWidth The width value of the line.
     */
    public void setInterferenceLinesWidth(float interferenceLinesWidth) {
        mInterferenceLinesWidth = interferenceLinesWidth;
    }

    /**
     * Get the number of interference dots.
     *
     * @return The number of interference dots.
     */
    public int getInterferenceCirclesCount() {
        return mInterferenceCirclesCount;
    }

    /**
     * Set the number of interference dots.
     *
     * @param interferenceCirclesCount The number of interference circles.
     */
    public void setInterferenceCirclesCount(int interferenceCirclesCount) {
        mInterferenceCirclesCount = interferenceCirclesCount;
    }

    /**
     * Set the color of the interference dot.
     *
     * @param interferenceCirclesColor The color of interference circles.
     */
    public void setInterferenceCirclesColor(int interferenceCirclesColor) {
        isCircleColorRandom = false;
        mInterferenceCirclesColor = interferenceCirclesColor;
    }

    /**
     * Get the radius of the interference dot.
     *
     * @return The radius of the interference dot.
     */
    public float getInterferenceCirclesRadius() {
        return mInterferenceCirclesRadius;
    }

    /**
     * Set the radius of the interference dot.
     *
     * @param interferenceCirclesRadius The radius of the interference circle.
     */
    public void setInterferenceCirclesRadius(float interferenceCirclesRadius) {
        mInterferenceCirclesRadius = interferenceCirclesRadius;
    }

    /**
     * Get the text color of the verification code.
     *
     * @return Verification code text color.
     */
    public int getTextColor() {
        return mTextColor;
    }

    /**
     * Set the text color of the verification code.
     *
     * @param textColor Verification code text color.
     */
    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    /**
     * Get the text size of the verification code.
     *
     * @return Verification code text size.
     */
    public int getTextSize() {
        return mTextSize;
    }

    /**
     * Set the text size of the verification code.
     *
     * @param textSize Verification code text size.
     */
    public void setTextSize(int textSize) {
        mTextSize = textSize;
    }

    /**
     * Whether the verification code text is underlined.
     *
     * @return returns whether there is an underlined boolean value.
     */
    public boolean isUnderLine() {
        return isUnderLine;
    }

    /**
     * Set whether the verification code is underlined.
     *
     * @param underLine Is there an underline.
     */
    public void setUnderLine(boolean underLine) {
        isUnderLine = underLine;
    }

    /**
     * Get the slant value of the text.
     *
     * @return returns a tilt value of float type.
     */
    public float getTextSkewX() {
        return mTextSkewX;
    }

    /**
     * Set the slant value of the text.
     *
     * @param textSkewX The skew value of the text.
     */
    public void setTextSkewX(float textSkewX) {
        mTextSkewX = textSkewX;
    }

    /**
     * Get the width value of the text.
     *
     * @return returns a float type width value.
     */
    public float getStrokeWidth() {
        return mStrokeWidth;
    }

    /**
     * Set the width of the text.
     *
     * @param strokeWidth The width value of the text.
     */
    public void setStrokeWidth(float strokeWidth) {
        mStrokeWidth = strokeWidth;
    }

    public VerificationCodeView(Context context) {
        this(context, null);
    }

    /**
     * VerificationCode.
     *
     * @param context context.
     * @param attrs attrs.
     */
    public VerificationCodeView(Context context, AttrSet attrs) {
        super(context, attrs);
        mVerificationText = AttrUtils.getStringFromAttr(attrs, "verificationText", "");
        isUnderLine = AttrUtils.getBooleanFromAttr(attrs, "isUnderLine", false);
        isTextBold = AttrUtils.getBooleanFromAttr(attrs, "isTextBold", false);
        mStrokeWidth = AttrUtils.getFloatFromAttr(attrs, "strokeWidth", 0);
        mTextColor = AttrUtils.getColorFromAttr(attrs, "textColor", Color.BLACK.getValue());
        mTextSize = AttrUtils.getDimensionFromAttr(attrs, "textSize", 16);
        mTextSkewX = AttrUtils.getFloatFromAttr(attrs, "textSkewX", 0);
        isShowInterferenceCircles = AttrUtils.getBooleanFromAttr(attrs, "isShowInterferenceCircles", true);
        isCircleColorRandom = false;
        mInterferenceCirclesColor = AttrUtils.getColorFromAttr(attrs, "interferenceCirclesColor",
                Color.BLACK.getValue());
        mInterferenceCirclesRadius = AttrUtils.getFloatFromAttr(attrs, "interferenceCirclesRadius", 5f);
        mInterferenceCirclesCount = AttrUtils.getIntFromAttr(attrs, "interferenceCirclesCount", 100);
        isShowInterferenceLines = AttrUtils.getBooleanFromAttr(attrs, "isShowInterferenceLines", true);
        mInterferenceLinesColor = AttrUtils.getColorFromAttr(attrs, "interferenceLinesColor", Color.BLACK.getValue());
        mInterferenceLinesCount = AttrUtils.getIntFromAttr(attrs, "interferenceLinesCount", 10);
        mInterferenceLinesWidth = AttrUtils.getFloatFromAttr(attrs, "interferenceLinesWidth", 3f);
        mVerificationCodeBackground = AttrUtils.getColorFromAttr(attrs, "verificationCodeBackground",
                Color.GRAY.getValue());
        addDrawTask(this::onDraw);
        setEstimateSizeListener(this::onEstimateSize);
    }

    @Override
    public boolean onEstimateSize(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = EstimateSpec.getMode(widthMeasureSpec);
        int heightMode = EstimateSpec.getMode(heightMeasureSpec);
        int widthSize = EstimateSpec.getSize(widthMeasureSpec);
        int heightSize = EstimateSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        mPaint.setTextSize(mTextSize);
        if (widthMode == EstimateSpec.PRECISE) {
            width = widthSize;
        } else {
            if (mVerificationText != null) {
                mPaint.getTextBounds(mVerificationText);
                width = getPaddingLeft() + getPaddingRight() + mTextRect.getWidth();
            } else {
                width = 200;
            }
        }
        if (heightMode == EstimateSpec.PRECISE) {
            height = heightSize;
        } else {
            if (mVerificationText != null) {
                mPaint.getTextBounds(mVerificationText);
                height = getPaddingBottom() + getPaddingTop() + mTextRect.getHeight();
            } else {
                height = 100;
            }
        }
        setEstimatedSize(width, height);
        return false;
    }

    /**
     * Draw interference dots.
     *
     * @param canvas canvas.
     */
    public void showInterferenceCircle(Canvas canvas) {
        if (isShowInterferenceCircles) {
            mPaint.setStyle(Paint.Style.FILL_STYLE);
            if (mInterferenceCirclesCount > 0) {
                for (int i = 0; i < mInterferenceCirclesCount; i++) {
                    if (isCircleColorRandom) {
                        mPaint.setColor(new Color(Color.getIntColor(getRandomColor())));
                    } else {
                        mPaint.setColor(new Color(mInterferenceCirclesColor));
                    }
                    float pointX = mRandom.nextFloat() * getWidth();
                    float pointY = mRandom.nextFloat() * getHeight();
                    canvas.drawCircle(pointX, pointY, mInterferenceCirclesRadius, mPaint);
                }
            } else {
                throw new IllegalArgumentException("The number of interference dots must be greater than 0!");
            }
        }
    }

    /**
     * showInterferenceLines.
     *
     * @param canvas canvas.
     */
    public void showInterferenceLines(Canvas canvas) {
        if (isShowInterferenceLines) {
            mPaint.setStyle(Paint.Style.STROKE_STYLE);
            mPaint.setStrokeWidth(mInterferenceLinesWidth);
            if (mInterferenceLinesCount > 0) {
                for (int i = 0; i < mInterferenceLinesCount; i++) {
                    if (isLineColorRandom) {
                        mPaint.setColor(new Color(Color.getIntColor(getRandomColor())));
                    } else {
                        mPaint.setColor(new Color(mInterferenceLinesColor));
                    }
                    float startX = mRandom.nextFloat() * getWidth();
                    float startY = mRandom.nextFloat() * getHeight();
                    float stopX = mRandom.nextFloat() * getWidth();
                    float stopY = mRandom.nextFloat() * getHeight();
                    canvas.drawLine(startX, startY, stopX, stopY, mPaint);
                }
            } else {
                throw new IllegalArgumentException("The number of interference lines must be greater than 0!");
            }
        }
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        canvas.drawColor(mVerificationCodeBackground, Canvas.PorterDuffMode.SRC);
        if (mVerificationText != null && mVerificationText.length() > 0) {
            mPaint.setStyle(Paint.Style.FILLANDSTROKE_STYLE);
            if (isTextBold) {
                mPaint.setFont(Font.DEFAULT_BOLD);
            }
            mPaint.setColor(new Color(mTextColor));
            mPaint.setUnderLine(isUnderLine);
            canvas.drawText(mPaint, mVerificationText, getWidth() / 2 - mTextRect.getWidth() / 2
                    - mTextRect.left, getHeight()
                    / 2 + mTextRect.getHeight() / 2);
        }
        showInterferenceLines(canvas);
        showInterferenceCircle(canvas);
    }

    /**
     * Randomly generate a hexadecimal color value.
     *
     * @return The hexadecimal color value generated.
     */
    private String getRandomColor() {
        String r;
        String g;
        String b;
        r = Integer.toHexString(mRandom.nextInt(256)).toUpperCase();
        g = Integer.toHexString(mRandom.nextInt(256)).toUpperCase();
        b = Integer.toHexString(mRandom.nextInt(256)).toUpperCase();
        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;
        return "#" + r + g + b;
    }
}
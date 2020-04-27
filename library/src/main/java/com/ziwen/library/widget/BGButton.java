package com.ziwen.library.widget;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.ziwen.library.R;


/**
 * PackageName : com.library.widgets
 * Author : Ziwen Lan
 * Date : 2018/6/13
 * Time : 9:44
 * Introduction : 拥有按压效果的Button
 * 可直接设置圆角、描边尺寸、描边色及按压时填充色
 * 减少shape/selector布局文件的创建
 * 按压时填充的颜色为背景色的加深色
 */

public class BGButton extends AppCompatButton {
    private Context mContext;
    private AttributeSet mAttributeSet;
    private boolean mIsShowShadow;
    /**
     * 圆角
     */
    private float mRadius, mBottomLeftRadius, mBottomRightRadius, mTopLeftRadius, mTopRightRadius;
    /**
     * 填充色、按压时填充色、不可点击时填充色、渐变色初始值、结束值
     */
    private int mBackgroundColor, mPressedColor, mUnableColor, mBackgroundColorStart, mBackgroundColorEnd;
    /**
     * 正常状态下描边颜色、宽度
     */
    private int mStrokeColor;
    private float mStrokeWidth;
    /**
     * 按压时描边颜色、不可点击时描边颜色
     */
    private int mPressedStrokeColor, mUnableStrokeColor;
    /**
     * 正常、按压时的drawable
     */
    GradientDrawable mNormalDrawable, mPressedDrawable, mUnableDrawable;
    /**
     * 正常情况下的图片drawable
     */
    Drawable mImgDrawable;
    /**
     * 图片drawable的资源id
     */
    private int mImgDrawableResourceId;
    /**
     * 状态选择器
     */
    private StateListDrawable mStateListDrawable;

    public BGButton(Context context) {
        super(context);
    }

    public BGButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BGButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mAttributeSet = attrs;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BGButton, defStyleAttr, 0);

        mIsShowShadow = a.getBoolean(R.styleable.BGButton_showShadow, true);

        mBottomLeftRadius = a.getDimensionPixelSize(R.styleable.BGButton_bottomLeftRadius, 0);
        mBottomRightRadius = a.getDimensionPixelSize(R.styleable.BGButton_bottomRightRadius, 0);
        mTopLeftRadius = a.getDimensionPixelSize(R.styleable.BGButton_topLeftRadius, 0);
        mTopRightRadius = a.getDimensionPixelSize(R.styleable.BGButton_topRightRadius, 0);
        mRadius = a.getDimensionPixelSize(R.styleable.BGButton_radius, 0);

        mImgDrawableResourceId = a.getResourceId(R.styleable.BGButton_backgroundDrawable, -1);
        mBackgroundColor = a.getColor(R.styleable.BGButton_backgroundColor, Color.TRANSPARENT);
        mPressedColor = a.getColor(R.styleable.BGButton_pressedColor, mBackgroundColor == 0 || mBackgroundColor == -1 ? Color.TRANSPARENT : getBrighterColor(mBackgroundColor));
        mUnableColor = a.getColor(R.styleable.BGButton_unableColor, mPressedColor);
        mBackgroundColorStart = a.getColor(R.styleable.BGButton_backgroundColorStart, 0);
        mBackgroundColorEnd = a.getColor(R.styleable.BGButton_backgroundColorEnd, 0);
        if (mBackgroundColorStart != 0 && mBackgroundColorEnd != 0) {
            //设置了渐变色--按压颜色为渐变色变浅色
            mPressedColor = 0;
        }

        mStrokeColor = a.getColor(R.styleable.BGButton_strokeColor, Color.TRANSPARENT);
        mStrokeWidth = a.getDimensionPixelSize(R.styleable.BGButton_strokeWidth, -1);

        mPressedStrokeColor = a.getColor(R.styleable.BGButton_pressedStrokeColor, mStrokeColor);
        mUnableStrokeColor = a.getColor(R.styleable.BGButton_unableStrokeColor, mPressedStrokeColor);
        a.recycle();
        init();
    }

    private void init() {
        //按下时的drawable
        mPressedDrawable = new GradientDrawable();
        //不可点击时的drawable
        mUnableDrawable = new GradientDrawable();
        //正常状态下的drawable
        if (mImgDrawableResourceId != -1) {
            mImgDrawable = ContextCompat.getDrawable(mContext, mImgDrawableResourceId);
        } else if (mBackgroundColorStart != 0 && mBackgroundColorEnd != 0) {
            //设置成渐变色
            int[] colors = {mBackgroundColorStart, mBackgroundColorEnd};
            mNormalDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
            //按压渐变色
            int[] pressedColors = {getBrighterColor(mBackgroundColorStart), getBrighterColor(mBackgroundColorEnd)};
            mPressedDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            mPressedDrawable.setColors(pressedColors);
        } else {
            //非渐变色设置
            mNormalDrawable = new GradientDrawable();
            mNormalDrawable.setColor(mBackgroundColor);
            mPressedDrawable.setColor(mPressedColor);
        }

        if (mPressedColor != 0) {
            mPressedDrawable.setColor(mPressedColor);
        }

        mUnableDrawable.setColor(mUnableColor);
        //圆角值设置
        if (mRadius == 0) {
            if (mBottomLeftRadius != 0
                    || mBottomRightRadius != 0
                    || mTopLeftRadius != 0
                    || mTopRightRadius != 0) {
                //遵循左上，右上，右下，左下
                float[] radii = new float[]{mTopLeftRadius, mTopLeftRadius,
                        mTopRightRadius, mTopRightRadius,
                        mBottomRightRadius, mBottomRightRadius,
                        mBottomLeftRadius, mBottomLeftRadius};
                mNormalDrawable.setCornerRadii(radii);
                mPressedDrawable.setCornerRadii(radii);
                mUnableDrawable.setCornerRadii(radii);
            }
        } else {
            mNormalDrawable.setCornerRadius(mRadius);
            mPressedDrawable.setCornerRadius(mRadius);
            mUnableDrawable.setCornerRadius(mRadius);
        }
        //描边设置
        if (mStrokeWidth != -1 && mStrokeColor != Color.TRANSPARENT) {
            mNormalDrawable.setStroke((int) mStrokeWidth, mStrokeColor);
            mPressedDrawable.setStroke((int) mStrokeWidth, mPressedStrokeColor);
            mUnableDrawable.setStroke((int) mStrokeWidth, mUnableStrokeColor);
        }
        //状态选择器
        mStateListDrawable = new StateListDrawable();
        if (mImgDrawable != null) {
            Drawable imgPressedDrawable = getResources().getDrawable(mImgDrawableResourceId);
            imgPressedDrawable.setAlpha(155);
            mStateListDrawable.addState(new int[]{android.R.attr.state_pressed}, imgPressedDrawable);
            mImgDrawable.setAlpha(255);
            mStateListDrawable.addState(new int[]{}, mImgDrawable);
        } else {
            mStateListDrawable.addState(new int[]{android.R.attr.state_pressed}, mPressedDrawable);
            mStateListDrawable.addState(new int[]{}, mNormalDrawable);
        }
        //未设置权重时，默认内容居中
        int gravity = Gravity.TOP | Gravity.START;
        if (getGravity() == gravity) {
            setGravity(Gravity.CENTER);
        }
        //设置背景及动画
        setEnabledStyle(isEnabled());
    }


    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setEnabledStyle(enabled);
    }

    /**
     * 得到深色
     */
    private int getDarkerColor(int color) {
        float[] hsv = new float[3];
        //convert to hsv
        Color.colorToHSV(color, hsv);
        //make darker
        //more saturation
        hsv[1] = hsv[1] + 0.1f;
        //less brightness
        hsv[2] = hsv[2] - 0.2f;
        return Color.HSVToColor(hsv);
    }

    /**
     * 得到浅色
     */
    private int getBrighterColor(int color) {
        float[] hsv = new float[3];
        //convert to hsv
        Color.colorToHSV(color, hsv);
        //less saturation
        hsv[1] = hsv[1] - 0.1f;
        //more brightness
        hsv[2] = hsv[2] + 0.1f;
        return Color.HSVToColor(hsv);
    }


    /**
     * 初始化样式
     */
    public void reset() {
        mBottomLeftRadius = 0;
        mBottomRightRadius = 0;
        mTopLeftRadius = 0;
        mTopRightRadius = 0;
        mRadius = 0;
        mBackgroundColor = Color.TRANSPARENT;
        mPressedColor = Color.TRANSPARENT;
        //不可点击时填充色
        mUnableColor = Color.TRANSPARENT;
        //渐变色初始值与结束值
        mBackgroundColorStart = 0;
        mBackgroundColorEnd = 0;
        mImgDrawableResourceId = -1;
        //正常状态下线条颜色、宽度
        mStrokeColor = Color.TRANSPARENT;
        mStrokeWidth = -1;
        //按下时线条颜色
        mPressedStrokeColor = mStrokeColor;
        //不可点击时描边颜色
        mUnableStrokeColor = mPressedStrokeColor;
        requestLayout();
        invalidate();
    }

    /**
     * 根据布局恢复样式
     */
    public void resetByLayout() {
        TypedArray a = mContext.obtainStyledAttributes(mAttributeSet, R.styleable.BGButton, 0, 0);

        mBottomLeftRadius = a.getDimensionPixelSize(R.styleable.BGButton_bottomLeftRadius, 0);
        mBottomRightRadius = a.getDimensionPixelSize(R.styleable.BGButton_bottomRightRadius, 0);
        mTopLeftRadius = a.getDimensionPixelSize(R.styleable.BGButton_topLeftRadius, 0);
        mTopRightRadius = a.getDimensionPixelSize(R.styleable.BGButton_topRightRadius, 0);
        mRadius = a.getDimensionPixelSize(R.styleable.BGButton_radius, 0);

        mImgDrawableResourceId = a.getResourceId(R.styleable.BGButton_backgroundDrawable, -1);
        mBackgroundColor = a.getColor(R.styleable.BGButton_backgroundColor, Color.TRANSPARENT);
        mPressedColor = a.getColor(R.styleable.BGButton_pressedColor, mBackgroundColor == 0 || mBackgroundColor == -1 ? Color.TRANSPARENT : getBrighterColor(mBackgroundColor));
        mUnableColor = a.getColor(R.styleable.BGButton_unableColor, mPressedColor);
        mBackgroundColorStart = a.getColor(R.styleable.BGButton_backgroundColorStart, 0);
        mBackgroundColorEnd = a.getColor(R.styleable.BGButton_backgroundColorEnd, 0);
        if (mBackgroundColorStart != 0 && mBackgroundColorEnd != 0) {
            //设置了渐变色--按压颜色为渐变色变浅色
            mPressedColor = 0;
        }

        mStrokeColor = a.getColor(R.styleable.BGButton_strokeColor, Color.TRANSPARENT);
        mStrokeWidth = a.getDimensionPixelSize(R.styleable.BGButton_strokeWidth, -1);

        mPressedStrokeColor = a.getColor(R.styleable.BGButton_pressedStrokeColor, mStrokeColor);
        mUnableStrokeColor = a.getColor(R.styleable.BGButton_unableStrokeColor, mPressedStrokeColor);
        a.recycle();
        init();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private float dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }

    /**
     * 设置圆角
     *
     * @param radius dp
     */
    public void setRadius(float radius) {
        mRadius = dip2px(radius);
        init();
    }

    /**
     * 设置圆角
     */
    public void setRadius(float topLeftRadius, float topRightRadius, float bottomLeftRadius, float bottomRightRadius) {
        mRadius = 0;
        mTopLeftRadius = dip2px(topLeftRadius);
        mTopRightRadius = dip2px(topRightRadius);
        mBottomLeftRadius = dip2px(bottomLeftRadius);
        mBottomRightRadius = dip2px(bottomRightRadius);
        init();
    }

    /**
     * 设置背景色
     */
    public void setBGBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        mBackgroundColorStart = 0;
        mBackgroundColorEnd = 0;
        mPressedColor = mBackgroundColor == 0 || mBackgroundColor == -1 ? Color.parseColor("#00000000") : getBrighterColor(mBackgroundColor);
        init();
    }

    /**
     * 设置背景色 -- 渐变色
     */
    public void setBGBackgroundColor(int backgroundColorStart, int backgroundColorEnd) {
        mBackgroundColorStart = backgroundColorStart;
        mBackgroundColorEnd = backgroundColorEnd;
        mPressedColor = 0;
        init();
    }

    /**
     * 设置不可点击时背景色
     */
    public void setUnableColor(int unableColor) {
        mUnableColor = unableColor;
        init();
    }

    /**
     * 设置按压时背景色
     */
    public void setPressedColor(int pressedColor) {
        mPressedColor = pressedColor;
        init();
    }

    /**
     * 设置图片背景
     */
    public void setImgDrawableResource(@DrawableRes int imgDrawableResourceId) {
        mImgDrawableResourceId = imgDrawableResourceId;
        init();
    }

    /**
     * 设置描边
     */
    public void setStroke(float strokeWidth, @ColorInt int strokeColor, @ColorInt int pressedStrokeColor, @ColorInt int unableStrokeColor) {
        mStrokeWidth = dip2px(strokeWidth);
        mStrokeColor = strokeColor;
        mPressedStrokeColor = pressedStrokeColor;
        mUnableStrokeColor = unableStrokeColor;
        init();
    }

    /**
     * 设置为不可点击样式
     *
     * @param enabled true普通样式 false不可点击样式
     */
    public void setEnabledStyle(boolean enabled) {
        if (mStateListDrawable == null || mUnableDrawable == null) {
            return;
        }
        if (enabled) {
            setBackground(mStateListDrawable);
            /*
             * 设置点击动画效果
             * 因为使用代码进行setBackground的时候，Button的默认点击效果会消失，所以再次为button添加动画效果
             * 5.0上有效
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && mIsShowShadow) {
                setStateListAnimator(AnimatorInflater.loadStateListAnimator(mContext, R.animator.selector_bg_button_animator));
            }
        } else {
            //不可点击时样式
            setBackground(mUnableDrawable);
        }
    }
}

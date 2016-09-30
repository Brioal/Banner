package com.brioal.brioalbanner.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.brioal.brioalbanner.R;

/**
 * Created by Brioal on 2016/8/31.
 */

public class BottonLayout extends View {
    public static final int GRAVITY_CENTER = 0;
    public static final int GRAVITY_LEFT = 1;
    public static final int GRAVITY_RIGHT = 2;

    private int mDotSum; //圆点总数
    private int mCurrentIndex; //当前下标
    private float mProgress; //滑动进度
    private int mNormalColor;//未选中的颜色
    private int mSelectColor; //选中的颜色
    private int mRadius; //小圆点半径
    private int mMagin; //小圆点的Magin
    private int mStartX; //圆点开始的坐标
    private int mDotX; //移动的点的X坐标
    private int mWidth; //宽度
    private int mHeight; //高度

    private Paint mPaint;
    private int mGravity;


    public BottonLayout(Context context) {
        this(context, null);
    }

    public BottonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }

    //设置圆点总数
    public void setDotSum(int dotSum) {
        mDotSum = dotSum;
    }

    //设置滑动的进度
    public void setProgress(float progress) {
        mProgress = progress;
        invalidate();
    }


    private void init() {
        mDotSum = 5;
        mCurrentIndex = 0;
        mProgress = 0;
        mNormalColor = getResources().getColor(R.color.normal);
        mSelectColor = getResources().getColor(R.color.colorSelect);
        mRadius = 8;
        mMagin = 10;
        mGravity = GRAVITY_CENTER;
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = widthSize;
        mHeight = heightSize;
        setMeasuredDimension(widthSize, heightSize);
        switch (mGravity) {
            case GRAVITY_CENTER:
                mStartX = (mWidth - (mDotSum * mRadius + mDotSum * mMagin * 2)) / 2;
                break;
            case GRAVITY_LEFT:
                mStartX = mMagin;
                break;
            case GRAVITY_RIGHT:
                mStartX = mWidth - (mDotSum * mRadius + mDotSum * mMagin * 2);
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mNormalColor);
        for (int i = 0; i < mDotSum; i++) {
            canvas.drawCircle(mStartX + i * (mRadius + 2 * mMagin) + mMagin + mRadius / 2, mHeight / 2, mRadius, mPaint);
        }
        mPaint.setColor(mSelectColor);
        canvas.drawCircle(mStartX + mCurrentIndex * (mRadius + 2 * mMagin) + mMagin + mRadius / 2 + (mRadius + 2 * mMagin) * mProgress, mHeight / 2, mRadius, mPaint);
    }
}

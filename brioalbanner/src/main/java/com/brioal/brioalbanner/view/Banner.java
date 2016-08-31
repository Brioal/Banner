package com.brioal.brioalbanner.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brioal.brioalbanner.R;
import com.brioal.brioalbanner.adapter.ViewPagerAdapter;
import com.brioal.brioalbanner.entity.BannerEntity;
import com.brioal.brioalbanner.interfaces.OnPagerClickListener;
import com.brioal.brioalbanner.util.FixedSpeedScroller;
import com.bumptech.glide.Glide;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brioal on 2016/8/31.
 */

public class Banner extends RelativeLayout {
    private BottonLayout mBottonLayout;
    private ViewPager mViewPager;
    private List<BannerEntity> mList;
    private List<View> mViews;
    private ViewPagerAdapter mPagerAdapter;
    private OnPagerClickListener mClickListener;
    private int mAlpha = 70;
    private boolean isAutoMoving = true;
    private boolean isAuto = true;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private long mPauseDuration;
    private int mChangeDuration;


    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    //设置是否开启自动轮播
    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    //设置切换花费的时间
    public void setChangeDuration(int changeDuration) {
        mChangeDuration = changeDuration;
    }

    //设置停顿的时间
    public void setPauseDuration(long pauseDuration) {
        mPauseDuration = pauseDuration;
    }

    //设置提示及圆点的背景透明度
    public void setAlpha(int alpha) {
        mAlpha = alpha;
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        mPauseDuration = array.getInteger(R.styleable.Banner_banner_pause_duration, 2000);
        mChangeDuration = array.getInteger(R.styleable.Banner_banner_change_duration, 800);
        array.recycle();
        mViewPager = new ViewPager(getContext());
        LayoutParams pagerParams = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        addView(mViewPager, pagerParams);

        mBottonLayout = new BottonLayout(getContext());
        LayoutParams bottonParams = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 50);
        bottonParams.addRule(ALIGN_PARENT_BOTTOM);
        mBottonLayout.setBackgroundColor(Color.argb(mAlpha, 0, 0, 0));
        addView(mBottonLayout, bottonParams);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAutoMoving) {
                    int index = mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem((index + 1) % (mList.size()), true);
                    mHandler.postDelayed(this, mPauseDuration * 2 + mChangeDuration);
                } else {
                    mHandler.postDelayed(this, mPauseDuration * 4 + mChangeDuration);
                }
            }
        }, mPauseDuration);

    }


    public void setPagerClickListener(OnPagerClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void setList(List<BannerEntity> list) {
        mList = list;
        mViews = new ArrayList<>();
        mBottonLayout.setDotSum(list.size());
        for (int i = 0; i < mList.size(); i++) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.content, null, false);
            ImageView iv = (ImageView) itemView.findViewById(R.id.fra_iv_image);
            TextView tv = (TextView) itemView.findViewById(R.id.fra_tv_tip);
            tv.setBackgroundColor(Color.argb(mAlpha, 0, 0, 0));
            BannerEntity entity = mList.get(i);
            Glide.with(getContext()).load(entity.getImageUrl()).into(iv);
            tv.setText(entity.getTip());
            final int finalI = i;
            itemView.setClickable(true);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) {
                        mClickListener.onClick(mList.get(finalI), finalI);
                    }
                }
            });
            mViews.add(itemView);
        }
        mPagerAdapter = new ViewPagerAdapter(mViews);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setClickable(true);
        if (isAuto) {
            try {
                Field field = ViewPager.class.getDeclaredField("mScroller");
                field.setAccessible(true);
                FixedSpeedScroller scroller = new FixedSpeedScroller(mViewPager.getContext(),
                        new LinearInterpolator());
                field.set(mViewPager, scroller);
                scroller.setmDuration(mChangeDuration);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mViewPager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickListener != null) {
                    int index = mViewPager.getCurrentItem();
                    mClickListener.onClick(mList.get(index), index);
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mBottonLayout.setCurrentIndex(position);
                mBottonLayout.setProgress(positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        isAutoMoving = false;
                        break;
                    default:
                        isAutoMoving = true;
                        break;
                }
            }
        });
    }


}

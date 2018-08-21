package com.example.viewpager_indicator.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public abstract class AbsViewPagerIndicator extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private ViewPager pager;

    public AbsViewPagerIndicator(Context context) {
        this(context, null);
    }

    public AbsViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbsViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfig();
    }

    protected abstract void initConfig();


    public void setViewPager(ViewPager pager) {
        if (pager == null) {
            throw new RuntimeException("viewpager cannot be null");
        }
        if (pager.getAdapter() == null) {
            throw new RuntimeException("adapter in viewpager cannot be null");
        }
        this.pager = pager;
        this.pager.setOnPageChangeListener(this);
        initNewIndicators(pager.getAdapter().getCount());
    }

    protected abstract void initNewIndicators(int count);

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}

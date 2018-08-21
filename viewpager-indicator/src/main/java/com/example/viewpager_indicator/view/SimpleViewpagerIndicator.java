package com.example.viewpager_indicator.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.viewpager_indicator.R;
import com.example.viewpager_indicator.util.UIUtil;

public class SimpleViewpagerIndicator extends AbsViewPagerIndicator {

    public int SCREEN_WIDTH;
    /**
     * 当前View的大小
     */
    public int width;
    public int height;

    /**
     * 红点半径
     */
    public int dotRadius = 20;
    /**
     * 红点间距
     */
    public int dotSpan = 100;
    private LinearLayout layout;
    private ImageView mover;

    public SimpleViewpagerIndicator(Context context) {
        super(context);
    }

    public SimpleViewpagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleViewpagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initConfig() {
        SCREEN_WIDTH = UIUtil.getScreenWidth(getContext());

    }
    @Override
    protected void initNewIndicators(int count) {
        count++;
        layout = new LinearLayout(getContext());
        layout.setBackgroundColor(Color.BLACK);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        addView(layout, LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        for(int i=0;i<count;i++) {
            View child = generateChildView();

            layout.addView(child);
        }

        mover = new ImageView(getContext());
        mover.setBackgroundResource(R.drawable.dot);
        RelativeLayout.LayoutParams moverLayoutParams = new RelativeLayout.LayoutParams(dotSpan + dotRadius, dotRadius);
        moverLayoutParams.addRule(CENTER_VERTICAL);
        mover.setLayoutParams(moverLayoutParams);
        addView(mover);
    }

    private View generateChildView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.rdot);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dotRadius, dotRadius);
        params.gravity = Gravity.CENTER_VERTICAL;
        imageView.setLayoutParams(params);
        return imageView;
    }

//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        //width = 1079, height  = 53
//        width = getMeasuredWidth();
//        height = getMeasuredHeight();
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layotDots();
        super.onLayout(changed, l, t, r, b);
    }

    private void layotDots() {
        if (layout == null) {
            return;
        }
        if (dotSpan <  dotRadius) {  //数据没有意义
            return;
        }

        int dotPureSpan = dotSpan - dotRadius;
        int childCount = layout.getChildCount();
        for (int i=1;i<childCount;i++) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getChildAt(i).getLayoutParams();
            params.leftMargin = dotPureSpan;
        }
        layout.requestLayout();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (null == mover) {
            return;
        }

        RelativeLayout.LayoutParams moverParams = (LayoutParams) mover.getLayoutParams();
        moverParams.leftMargin = i * (dotSpan);
        mover.requestLayout();
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}

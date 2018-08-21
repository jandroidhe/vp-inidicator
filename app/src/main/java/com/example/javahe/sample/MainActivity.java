package com.example.javahe.sample;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpager_indicator.view.SimpleViewpagerIndicator;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    public final int PAGE_SIZE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * init view
     */
    private void initView() {
        viewPager = findViewById(R.id.vp);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return PAGE_SIZE;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TextView textView = new TextView(container.getContext());
                textView.setText("page = " + position);
                textView.setTextColor(Color.RED);
                container.addView(textView, -1, -1);
                return textView;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });

        SimpleViewpagerIndicator simpleViewpagerIndicator = findViewById(R.id.indicator);
        simpleViewpagerIndicator.setViewPager(viewPager);

    }
}

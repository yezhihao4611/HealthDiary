package com.tz.healthdiary;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tz.healthdiary.custom.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 西野七濑 on 2016/10/14.
 */

public class FirstActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TextView mTextView;
    private PagerAdapter mPagerAdapter;
    private int[] imageId = new int[]{R.mipmap.firstone,R.mipmap.firsttwo,R.mipmap.firstthree};
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    SharedPreferences mSharedPreferences = StartAnimaActivity.mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override//从滑动开始到结束前一直调用
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override//页面跳转完后调用
            public void onPageSelected(int position) {
            }

            @Override//状态改变时调用
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_s);
        mTextView = (TextView) findViewById(R.id.tv_into);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, InitializeActivity.class);
                startActivity(intent);
                mSharedPreferences = getSharedPreferences("firstSharedPreferences", 0);
                mSharedPreferences.edit().putBoolean("first", false).apply();
                finish();
            }
        });


        mPagerAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(FirstActivity.this);
                imageView.setImageResource(imageId[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mImageViews.add(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageViews.get(position));
            }

            @Override
            public int getCount() {
                return imageId.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setAdapter(mPagerAdapter);
    }
}

package com.tz.healthdiary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.tz.healthdiary.bean.MainAdapter;
import com.tz.healthdiary.fragment.BMIFragment;
import com.tz.healthdiary.fragment.CentreFragment;
import com.tz.healthdiary.fragment.NewsFragment;
import com.tz.healthdiary.fragment.OtherFragment;

import java.util.ArrayList;
import java.util.List;

import static com.tz.healthdiary.R.id.rg_main;


/**
 * Created by 西野七濑 on 2016/10/18.
 * 主页面
 * 使用了ViewPager滑动页面
 * 代码量相对少
 */
public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    RadioGroup mRadioGroup;

    List<Fragment> mList;
    MainAdapter mMainAdapter;

    Fragment mCentre;
    Fragment mBMI;
    Fragment mNews;
    Fragment mOther;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onChanged();
    }

    /**
     * 点击事件包装方法
     */
    private void onChanged() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.centre:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.bmi:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.news:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.other:
                        mViewPager.setCurrentItem(3);
                        break;
                }
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.centre);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.bmi);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.news);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.other);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 初始化方法
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main);

        mList = new ArrayList<>();

        mCentre = new CentreFragment();
        mBMI = new BMIFragment();
        mNews = new NewsFragment();
        mOther = new OtherFragment();

        mList.add(mCentre);
        mList.add(mBMI);
        mList.add(mNews);
        mList.add(mOther);

        mMainAdapter = new MainAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mMainAdapter);
    }

    /**
     * 点击事件
     * @param view
     */
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.ib_add:
                /*Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);*/
                break;
        }
    }
}
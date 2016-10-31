package com.tz.healthdiary;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.tz.healthdiary.custom.CircleIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.tz.healthdiary.R.id.bt_birthday;
import static com.tz.healthdiary.R.id.bt_start;
import static com.tz.healthdiary.R.id.bt_stature;
import static com.tz.healthdiary.R.id.bt_weight;
import static com.tz.healthdiary.R.id.civ_boy;
import static com.tz.healthdiary.R.id.civ_girl;
import static com.tz.healthdiary.R.id.et_birthday;

/**
 * Created by anzhuo on 2016/10/31.
 */

public class TestOneActivity extends AppCompatActivity {

    private List<View> viewList;
    private List<View> viewLists;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;

    View mViewOne;
    View mViewTwo;
    View mViewThree;
    View mViewFour;
    View mViewFive;

    EditText mEditTextBirthday;
    EditText mEditTextWeight;
    EditText mEditTextStature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_one);

        initData();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);

        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);
    }

    private void initData() {

        mViewOne = LayoutInflater.from(TestOneActivity.this).inflate(R.layout.activity_one, null);
        mViewTwo = LayoutInflater.from(TestOneActivity.this).inflate(R.layout.activity_two, null);
        mViewThree = LayoutInflater.from(TestOneActivity.this).inflate(R.layout.activity_three, null);
        mViewFour = LayoutInflater.from(TestOneActivity.this).inflate(R.layout.activity_four, null);
        mViewFive = LayoutInflater.from(TestOneActivity.this).inflate(R.layout.activity_five, null);

        viewLists = new ArrayList<View>();

        viewLists.add(mViewOne);
        viewLists.add(mViewTwo);
        viewLists.add(mViewThree);
        viewLists.add(mViewFour);
        viewLists.add(mViewFive);

        viewList = new ArrayList<View>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            View view = viewLists.get(i);
            if (i>0){
                view.setBackgroundColor(0xff000000 | random.nextInt(0x00ffffff));
            }
            viewList.add(view);
        }

        mEditTextBirthday = (EditText) mViewThree.findViewById(et_birthday);
        mEditTextWeight = (EditText) mViewFour.findViewById(R.id.et_weight);
        mEditTextStature = (EditText) mViewFive.findViewById(R.id.et_stature);

    }

    public void doClick(View view){
        switch (view.getId()) {
            case bt_start:
                Toast.makeText(TestOneActivity.this,"开始吧",Toast.LENGTH_SHORT).show();
                break;
            case civ_boy:
                Toast.makeText(TestOneActivity.this,"男孩",Toast.LENGTH_SHORT).show();
                break;
            case civ_girl:
                Toast.makeText(TestOneActivity.this,"女孩",Toast.LENGTH_SHORT).show();
                break;
            case et_birthday:
                Toast.makeText(TestOneActivity.this,"生日",Toast.LENGTH_SHORT).show();
                break;
            case bt_birthday:
                Toast.makeText(TestOneActivity.this,"生日",Toast.LENGTH_SHORT).show();
                break;
            case bt_weight:
                Toast.makeText(TestOneActivity.this,"体重",Toast.LENGTH_SHORT).show();
                break;
            case bt_stature:
                Toast.makeText(TestOneActivity.this,"身高",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public int getCount() {

            return viewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(viewList.get(position));

        }

        @Override
        public int getItemPosition(Object object) {

            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return "title";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));

            return viewList.get(position);
        }

    };

}

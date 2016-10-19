package com.tz.healthdiary;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.tz.healthdiary.fragment.BMIFragment;
import com.tz.healthdiary.fragment.CentreFragment;
import com.tz.healthdiary.fragment.NewsFragment;
import com.tz.healthdiary.fragment.OtherFragment;

/**
 * Created by 西野七濑 on 2016/10/19.
 * 测试类
 * 没有使用ViewPager滑动页面
 * 代码量比使用了ViewPager的大
 */

public class TestsActivity extends AppCompatActivity {
    private RadioButton centre;
    private RadioButton bmi;
    private RadioButton news;
    private RadioButton other;

    FragmentManager manager;
    FragmentTransaction transaction;

    CentreFragment mCentreFragment;
    BMIFragment mBMIFragment;
    NewsFragment mNewsFragment;
    OtherFragment mOtherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        initView();
        onCheckedChanged();
    }

    private void onCheckedChanged() {
        showFragment(0);
        centre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    showFragment(0);
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.fl_main, new CentreFragment());

                }
            }
        });
        bmi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    showFragment(1);
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.fl_main, new BMIFragment());

                }
            }
        });

        news.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    showFragment(2);
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.fl_main, new NewsFragment());

                }
            }
        });

        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    showFragment(3);
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.fl_main, new OtherFragment());

                }
            }
        });
    }

    private void initView() {
        centre = (RadioButton) findViewById(R.id.centre);
        bmi = (RadioButton) findViewById(R.id.bmi);
        news = (RadioButton) findViewById(R.id.news);
        other = (RadioButton) findViewById(R.id.other);

        centre.setChecked(true);
    }


    private void showFragment(int i) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //先把所有的fragment隐藏
        HideAllFragment(transaction);
        switch (i) {
            case 0:
                if (mCentreFragment == null) {
                    mCentreFragment = new CentreFragment();
                    transaction.add(R.id.fl_main, mCentreFragment);
                } else {
                    transaction.show(mCentreFragment);
                }
                break;
            case 1:
                if (mBMIFragment == null) {
                    mBMIFragment = new BMIFragment();
                    transaction.add(R.id.fl_main, mBMIFragment);
                } else {
                    transaction.show(mBMIFragment);
                }
                break;
            case 2:
                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                    transaction.add(R.id.fl_main, mNewsFragment);
                } else {
                    transaction.show(mNewsFragment);
                }
                break;
            case 3:
                if (mOtherFragment == null) {
                    mOtherFragment = new OtherFragment();
                    transaction.add(R.id.fl_main, mOtherFragment);
                } else {
                    transaction.show(mOtherFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void HideAllFragment(FragmentTransaction transaction) {
        if (mCentreFragment != null) {
            transaction.hide(mCentreFragment);
        }
        if (mBMIFragment != null) {
            transaction.hide(mBMIFragment);
        }
        if (mNewsFragment != null) {
            transaction.hide(mNewsFragment);
        }
        if (mOtherFragment != null) {
            transaction.hide(mOtherFragment);
        }
    }

}

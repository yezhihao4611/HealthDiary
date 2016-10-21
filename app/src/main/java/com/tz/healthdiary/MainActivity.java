package com.tz.healthdiary;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tz.healthdiary.fragment.BMIFragment;
import com.tz.healthdiary.fragment.CentreFragment;
import com.tz.healthdiary.fragment.NewsFragment;
import com.tz.healthdiary.fragment.OtherFragment;
import com.tz.healthdiary.utils.MyApplication;

/**
 * Created by 西野七濑 on 2016/10/19.
 */

public class MainActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;

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

    PopupWindow mPopupWindow;
    View mPopView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onCheckedChangeds();
    }

    private void onCheckedChangeds() {
        showFragment(0);
        centre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    showFragment(0);
                }
            }
        });
        bmi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    showFragment(1);
                }
            }
        });
        news.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    showFragment(2);
                }
            }
        });
        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    showFragment(3);
                }
            }
        });
    }

    private void initView() {
        mLinearLayout = (LinearLayout) findViewById(R.id.main);
        centre = (RadioButton) findViewById(R.id.centre);
        bmi = (RadioButton) findViewById(R.id.bmi);
        news = (RadioButton) findViewById(R.id.news);
        other = (RadioButton) findViewById(R.id.other);

        mCentreFragment = new CentreFragment();
        mBMIFragment = new BMIFragment();
        mNewsFragment = new NewsFragment();
        mOtherFragment = new OtherFragment();


        mPopupWindow = new PopupWindow(getLayoutInflater().inflate(R.layout.activity_test_m, null), WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setOutsideTouchable(false);
        centre.setChecked(true);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.fab_main:
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_LONG).show();

                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER, 0, 0);
                /*Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);*/
                break;
        }
    }


    private void showFragment(int i) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //先把所有的fragment隐藏
        HideAllFragment(transaction);
        switch (i) {
            case 0:
                if (mCentreFragment == null) {
                    transaction.add(R.id.fl_main, new CentreFragment());
                } else {
                    transaction.show(mCentreFragment);
                }
                transaction.replace(R.id.fl_main, mCentreFragment);
                break;
            case 1:
                if (mBMIFragment == null) {
                    transaction.add(R.id.fl_main, new BMIFragment());
                } else {
                    transaction.show(mBMIFragment);
                }
                transaction.replace(R.id.fl_main, mBMIFragment);
                break;
            case 2:
                if (mNewsFragment == null) {
                    transaction.add(R.id.fl_main, new NewsFragment());
                } else {
                    transaction.show(mNewsFragment);
                }
                transaction.replace(R.id.fl_main, mNewsFragment);
                break;
            case 3:
                if (mOtherFragment == null) {
                    transaction.add(R.id.fl_main, new OtherFragment());
                } else {
                    transaction.show(mOtherFragment);
                }
                transaction.replace(R.id.fl_main, mOtherFragment);
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

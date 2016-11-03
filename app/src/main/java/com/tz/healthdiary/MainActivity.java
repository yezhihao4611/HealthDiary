package com.tz.healthdiary;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tz.healthdiary.custom.WheelView;
import com.tz.healthdiary.fragment.BMIFragment;
import com.tz.healthdiary.fragment.CentreFragment;
import com.tz.healthdiary.fragment.NewsFragment;
import com.tz.healthdiary.fragment.OtherFragment;

import java.util.Arrays;

import static com.tz.healthdiary.R.id.bt_add_no;
import static com.tz.healthdiary.R.id.bt_add_yes;
import static com.tz.healthdiary.R.id.fab_add;

/**
 * Created by 西野七濑 on 2016/10/19.
 */

public class MainActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;
    Button mButton1;
    Button mButton2;

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
    WheelView wvkg;
    WheelView wvg;

    private static final String[] KgList = new String[]{"25", "26", "27", "28", "29", "30", "31",
            "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45",
            "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
            "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73",
            "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87",
            "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101",
            "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113",
            "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125",
            "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137",
            "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149",
            "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161",
            "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173",
            "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185",
            "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197",
            "198", "199", "200", "201", "202", "203", "204", "205", "206", "207", "208", "209",
            "210", "211", "212", "213", "214", "215", "216", "217", "218", "219", "220", "221",
            "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233",
            "234", "235", "236", "237", "238", "239", "240", "241", "242", "243", "244", "245",
            "246", "247", "248", "249", "250", "251", "252", "253", "254", "255", "256", "257",
            "258", "259", "260", "261", "262", "263", "264", "265", "266", "267", "268", "269",
            "270", "271", "272", "273", "274", "275", "276", "277", "278", "279", "280", "281",
            "282", "283", "284", "285", "286", "287", "288", "289", "290", "291", "292", "293",
            "294", "295", "296", "297", "298", "299", "300"};
    private static final String[] GList = new String[]{"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9"};

    private static final int NUM = 1;
    int KgSelectedIndex;
    int GSelectedIndex;

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

        mPopView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popupwindow_add_day_data, null);
        initWheelView();

        centre.setChecked(true);
    }

    private void initWheelView() {
        wvkg = (WheelView) mPopView.findViewById(R.id.wv_kg);
        wvg = (WheelView) mPopView.findViewById(R.id.wv_g);
        //设置首个item的起始值
        wvkg.setOffset(NUM);
        wvg.setOffset(NUM);
        //设置控件中各个item的内容
        wvkg.setItems(Arrays.asList(KgList));
        wvg.setItems(Arrays.asList(GList));

        wvkg.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                KgSelectedIndex = selectedIndex;
                Log.i("TZ", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        wvg.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                GSelectedIndex = selectedIndex;
                Log.i("TZ", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void doClick(View view) {
        switch (view.getId()) {
            case fab_add:
                showPopupWindow(mPopView);
                mPopupWindow.showAtLocation(mPopView, Gravity.CENTER, 0, 0);
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_LONG).show();
                break;
            case bt_add_no:
                mPopupWindow.dismiss();
                break;
            case bt_add_yes:
                mPopupWindow.dismiss();
                break;
            default:
                break;
        }
    }

    private void showPopupWindow(View view) {
        //设置控件选中的位置
        wvkg.setSeletion(KgSelectedIndex - NUM);
        wvg.setSeletion(GSelectedIndex - NUM);

        mPopupWindow = new PopupWindow(view);
        mPopupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置pop点击
        mPopupWindow.setTouchable(true);
        //设置外部点击
        mPopupWindow.setOutsideTouchable(true);
        //设置背景颜色，0x后面加8位
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置屏幕背景透明度
        backgroundAlpha(0.5f);
        //添加pop窗口关闭事件
        mPopupWindow.setOnDismissListener(new PopupWindowDismissListener());
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
    /**
     * popWin关闭事件，主要是为了将背景透明度改回来
     * @author cg
     *
     */
    class PopupWindowDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            //窗口关闭时恢复背景透明度
            backgroundAlpha(1f);
        }
    }
}

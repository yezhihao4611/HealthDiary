package com.tz.healthdiary.Jisuanqi;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tz.healthdiary.R;

/**
 * Created by anzhuo on 2016/10/20.
 */

public class BaseActivity extends Activity implements View.OnClickListener {
    ImageView iv_back_base;
    EditText et_year_base;
    EditText et_height_base;
    EditText et_weight_base;
    TextView tv_result_base;
    TextView tv_base_base;
    TextView tv_activity_base;
    ImageView iv_activity_base;
    RadioGroup rg_base;
    RadioButton rb_man_base;
    RadioButton rb_wumen_base;
    int height;
    int weight;
    int year;
    float str;
    int result;
    String re;
    String res;
    int i = 1;
    PopupWindow popupWindow;
    TextView tv_one;
    TextView tv_two;
    TextView tv_three;
    TextView tv_four;
    TextView tv_five;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_base_layout);
        iv_back_base = (ImageView) findViewById(R.id.iv_back_base);
        iv_activity_base = (ImageView) findViewById(R.id.iv_activity_base);
        et_year_base = (EditText) findViewById(R.id.et_year_base);
        et_height_base = (EditText) findViewById(R.id.et_height_base);
        et_weight_base = (EditText) findViewById(R.id.et_weight_base);
        tv_result_base = (TextView) findViewById(R.id.tv_result_base);
        tv_base_base = (TextView) findViewById(R.id.tv_base_base);
        tv_activity_base = (TextView) findViewById(R.id.tv_activity_base);
        rg_base = (RadioGroup) findViewById(R.id.rg_base);
        rb_man_base = (RadioButton) findViewById(R.id.rb_man_base);
        rb_wumen_base = (RadioButton) findViewById(R.id.rb_wumen_base);
        v = LayoutInflater.from(BaseActivity.this).inflate(R.layout.popwindow_layout, null);
        tv_one = (TextView) v.findViewById(R.id.tv_one);
        tv_two = (TextView) v.findViewById(R.id.tv_two);
        tv_three = (TextView) v.findViewById(R.id.tv_three);
        tv_four = (TextView) v.findViewById(R.id.tv_four);
        tv_five = (TextView) v.findViewById(R.id.tv_five);
        iv_back_base.setOnClickListener(this);
        iv_activity_base.setOnClickListener(this);
        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        tv_three.setOnClickListener(this);
        tv_four.setOnClickListener(this);
        tv_five.setOnClickListener(this);
        et_weight_base.addTextChangedListener(textWatcher);
        et_height_base.addTextChangedListener(textWatcher);
        et_year_base.addTextChangedListener(textWatcher);
        tv_activity_base.addTextChangedListener(textWatcher);
        //判断进到这个页面是哪个RadioGroup被点击
        if (i == 1) {
            rg_base.check(R.id.rb_man_base);
            BaseMan();
        } else {
            rg_base.check(R.id.rb_wumen_base);
            BaseWumen();
        }
        rg_base.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_man_base:
                        BaseMan();
                        break;
                    case R.id.rb_wumen_base:
                        BaseWumen();
                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_base:
                finish();
                break;
            case R.id.iv_activity_base:
                setpop();
                popupWindow.showAsDropDown(iv_activity_base, 0, 0);
                break;
            case R.id.tv_one:
                tv_activity_base.setText("久坐不动");
                popupWindow.dismiss();
                break;
            case R.id.tv_two:
                tv_activity_base.setText("偶尔运动");
                popupWindow.dismiss();
                break;
            case R.id.tv_three:
                tv_activity_base.setText("经常运动");
                popupWindow.dismiss();
                break;
            case R.id.tv_four:
                tv_activity_base.setText("持续运动");
                popupWindow.dismiss();
                break;
            case R.id.tv_five:
                tv_activity_base.setText("大量运动");
                popupWindow.dismiss();

                break;

        }
    }

    public void BaseMan() {
        if (et_year_base.getText().toString().equals("") || et_height_base.getText().toString().equals("") || et_weight_base.getText().toString().equals("")) {

        } else {
            year = Integer.parseInt(et_year_base.getText().toString());
            height = Integer.parseInt(et_height_base.getText().toString());
            weight = Integer.parseInt(et_weight_base.getText().toString());
            str = (float) (66 + (13.7 * weight) + (5 * height) - (6.8 * year));
            result = (int) str;
            re = String.valueOf(result);
            if (year >= 5 & year <= 120 && height >= 20 && height <= 250 & weight >= 10 && weight <= 400) {
                tv_result_base.setText(re+"kcal");
                if (tv_activity_base.getText().toString().equals("久坐不动")){
                    str = (float) ((66 + (13.7 * weight) + (5 * height) - (6.8 * year))*1.2);
                    result = (int) str;
                    re = String.valueOf(result);
                    tv_base_base.setText("≤"+re+"kcal");
                }
                else if(tv_activity_base.getText().toString().equals("偶尔运动")){
                    str = (float) ((66 + (13.7 * weight) + (5 * height) - (6.8 * year))*1.2);
                    result = (int) str;
                    re = String.valueOf(result);
                    str = (float) ((66 + (13.7 * weight) + (5 * height) - (6.8 * year))*1.3);
                    result = (int) str;
                    res = String.valueOf(result);
                    tv_base_base.setText(re+"-"+res+"kcal");
                }
                else if(tv_activity_base.getText().toString().equals("经常运动")){
                    str = (float) ((66 + (13.7 * weight) + (5 * height) - (6.8 * year))*1.3);
                    result = (int) str;
                    re = String.valueOf(result);
                    str = (float) ((66 + (13.7 * weight) + (5 * height) - (6.8 * year))*1.5);
                    result = (int) str;
                    res = String.valueOf(result);
                    tv_base_base.setText(re+"-"+res+"kcal");
                }
                else if(tv_activity_base.getText().toString().equals("持续运动")){
                    str = (float) ((66 + (13.7 * weight) + (5 * height) - (6.8 * year))*1.5);
                    result = (int) str;
                    re = String.valueOf(result);
                    str = (float) ((66 + (13.7 * weight) + (5 * height) - (6.8 * year))*1.7);
                    result = (int) str;
                    res = String.valueOf(result);
                    tv_base_base.setText(re+"-"+res+"kcal");
                }
                else if(tv_activity_base.getText().toString().equals("大量运动")){
                    str = (float) ((66 + (13.7 * weight) + (5 * height) - (6.8 * year))*1.7);
                    result = (int) str;
                    re = String.valueOf(result);
                    str = (float) ((66 + (13.7 * weight) + (5 * height) - (6.8 * year))*1.9);
                    result = (int) str;
                    res = String.valueOf(result);
                    tv_base_base.setText(re+"-"+res+"kcal");
                }

            } else {
                tv_result_base.setText("0");
                tv_base_base.setText("0");
            }
        }
    }

    public void BaseWumen() {
        if (et_year_base.getText().toString().equals("") || et_height_base.getText().toString().equals("") || et_weight_base.getText().toString().equals("")) {

        } else {
            year = Integer.parseInt(et_year_base.getText().toString());
            height = Integer.parseInt(et_height_base.getText().toString());
            weight = Integer.parseInt(et_weight_base.getText().toString());
            str = (float) (655 + (9.7 * weight) + (1.8 * height) - (4.7 * year));
            result = (int) str;
            re = String.valueOf(result);
            if (year >= 5 & year <= 120 && height >= 20 && height <= 250 & weight >= 10 && weight <= 400) {
                tv_result_base.setText(re+"kcal");
                if (tv_activity_base.getText().toString().equals("久坐不动")){
                    str = (float) ((655 + (9.7 * weight) + (1.8 * height) - (4.7 * year))*1.2);
                    result = (int) str;
                    re = String.valueOf(result);
                    tv_base_base.setText("≤"+re+"kcal");
                }
                else if(tv_activity_base.getText().toString().equals("偶尔运动")){
                    str = (float) ((655 + (9.7 * weight) + (1.8 * height) - (4.7 * year))*1.2);
                    result = (int) str;
                    re = String.valueOf(result);
                    str = (float) ((655 + (9.7 * weight) + (1.8 * height) - (4.7 * year))*1.3);
                    result = (int) str;
                    res = String.valueOf(result);
                    tv_base_base.setText(re+"-"+res+"kcal");
                }
                else if(tv_activity_base.getText().toString().equals("经常运动")){
                    str = (float) ((655 + (9.7 * weight) + (1.8 * height) - (4.7 * year))*1.3);
                    result = (int) str;
                    re = String.valueOf(result);
                    str = (float) ((655 + (9.7 * weight) + (1.8 * height) - (4.7 * year))*1.5);
                    result = (int) str;
                    res = String.valueOf(result);
                    tv_base_base.setText(re+"-"+res+"kcal");
                }
                else if(tv_activity_base.getText().toString().equals("持续运动")){
                    str = (float) ((655 + (9.7 * weight) + (1.8 * height) - (4.7 * year))*1.5);
                    result = (int) str;
                    re = String.valueOf(result);
                    str = (float) ((655 + (9.7 * weight) + (1.8 * height) - (4.7 * year))*1.7);
                    result = (int) str;
                    res = String.valueOf(result);
                    tv_base_base.setText(re+"-"+res+"kcal");
                }
                else if(tv_activity_base.getText().toString().equals("大量运动")){
                    str = (float) ((655 + (9.7 * weight) + (1.8 * height) - (4.7 * year))*1.7);
                    result = (int) str;
                    re = String.valueOf(result);
                    str = (float) ((655 + (9.7 * weight) + (1.8 * height) - (4.7 * year))*1.9);
                    result = (int) str;
                    res = String.valueOf(result);
                    tv_base_base.setText(re+"-"+res+"kcal");
                }
            } else {
                tv_result_base.setText("0");
                tv_base_base.setText("0");

            }
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (rb_man_base.isChecked()) {
                BaseMan();
            } else {
                BaseWumen();
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void setpop() {
        //设置popwindow
        popupWindow = new PopupWindow(v);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置pop点击
        popupWindow.setTouchable(true);
        //设置外部点击
        popupWindow.setOutsideTouchable(true);
        //设置背景颜色，0x后面加8位
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
    }

}

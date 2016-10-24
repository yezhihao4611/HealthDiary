package com.tz.healthdiary.Jisuanqi;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tz.healthdiary.R;

/**
 * Created by anzhuo on 2016/10/20.
 */

public class BodyActivity extends Activity implements View.OnClickListener {
    ImageView iv_back_body;
    EditText et_year_body;
    EditText et_height_body;
    EditText et_waist_body;
    LinearLayout ll_result_body;
    TextView tv_result_body;
    LinearLayout ll_hip_body;
    TextView tv_low_body;
    TextView tv_normal_body;
    TextView tv_high_body;
    TextView tv_mosthigh_body;
    RadioGroup rg_body;
    RadioButton rb_man_body;
    RadioButton rb_wumen_bod;
    EditText et_weight_bady;
    int height;
    int weight;
    int waist;
    double result;
    int a = 2;
    String str;
    double i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_body_layout);
        iv_back_body = (ImageView) findViewById(R.id.iv_back_body);
        et_year_body = (EditText) findViewById(R.id.et_year_body);
        et_height_body = (EditText) findViewById(R.id.et_height_body);
        et_waist_body = (EditText) findViewById(R.id.et_waist_body);
        ll_result_body = (LinearLayout) findViewById(R.id.ll_result_body);
        tv_result_body = (TextView) findViewById(R.id.tv_result_body);
        tv_low_body = (TextView) findViewById(R.id.tv_low_body);
        tv_normal_body = (TextView) findViewById(R.id.tv_normal_body);
        tv_high_body = (TextView) findViewById(R.id.tv_high_body);
        tv_mosthigh_body = (TextView) findViewById(R.id.tv_mosthigh);
        rg_body = (RadioGroup) findViewById(R.id.rg_body);
        rb_man_body = (RadioButton) findViewById(R.id.rb_man_body);
        rb_wumen_bod = (RadioButton) findViewById(R.id.rb_wumen_body);
        et_weight_bady = (EditText) findViewById(R.id.et_weight_body);
        iv_back_body.setOnClickListener(this);
        et_waist_body.addTextChangedListener(textWatcher);
        et_height_body.addTextChangedListener(textWatcher);
        et_weight_bady.addTextChangedListener(textWatcher);
        if (a == 1) {
            rg_body.check(R.id.rb_man_body);
            bManResult();
        } else {
            rg_body.check(R.id.rb_wumen_body);
            bWumenResult();
        }
        rg_body.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_man_body:
                        bManResult();
                        break;
                    case R.id.rb_wumen_body:
                        bWumenResult();

                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_body:
                finish();
                break;
        }
    }

    public void bManResult() {
        if (et_weight_bady.getText().toString().equals("") || et_height_body.getText().toString().equals("") || et_waist_body.getText().toString().equals("")) {

        } else {
            height = Integer.parseInt(et_height_body.getText().toString());
            weight = Integer.parseInt(et_weight_bady.getText().toString());
            waist = Integer.parseInt(et_waist_body.getText().toString());
            result = (0.74 * waist - weight * 0.082 - 34.89);
            str = String.format("%.2f", result);
            i = Double.parseDouble(str);
            tv_low_body.setText("≤13%");
            tv_normal_body.setText("13-25%");
            tv_high_body.setText("25-35%");
            tv_mosthigh_body.setText("≥35");
            if (height >= 50 && height <= 230 && weight >= 20 && weight <= 300 && waist >= 30 && waist <= 200&&i>0) {
                tv_result_body.setText(str + "%");
                if (i <= 13.00) {
                    ll_result_body.setBackground(new ColorDrawable(0xff00FFFF));
                } else if (i > 13.00 && i<= 25.00) {
                    ll_result_body.setBackground(new ColorDrawable(0xff00FF00));
                } else if (i > 25.00 && i <= 35.00) {
                    ll_result_body.setBackground(new ColorDrawable(0xffFFFF00));
                } else {
                    ll_result_body.setBackground(new ColorDrawable(0xffCD661D));
                }
            }
            else {
                ll_result_body.setBackground(new ColorDrawable(0xffdedada));
                tv_result_body.setText("0");
            }
        }
    }

    public void bWumenResult() {
        if (et_weight_bady.getText().toString().equals("") || et_height_body.getText().toString().equals("") || et_waist_body.getText().toString().equals("")) {

        } else {
            height = Integer.parseInt(et_height_body.getText().toString());
            weight = Integer.parseInt(et_weight_bady.getText().toString());
            waist = Integer.parseInt(et_waist_body.getText().toString());
            result = (0.74 * waist - weight * 0.082 - 34.89);
            str = String.format("%.2f", result);
            i = Double.parseDouble(str);
            tv_low_body.setText("≤20%");
            tv_normal_body.setText("20-30%");
            tv_high_body.setText("30-41%");
            tv_mosthigh_body.setText("≥41");
            if (height >= 50 && height <= 230 && weight >= 20 && weight <= 300 && waist >= 30 && waist <= 200) {
                tv_result_body.setText(str + "%");
                if (i <= 20) {
                    ll_result_body.setBackground(new ColorDrawable(0xff00FFFF));
                } else if (i > 20 && i <= 30) {
                    ll_result_body.setBackground(new ColorDrawable(0xff00FF00));
                } else if (i > 30 && i <= 41) {
                    ll_result_body.setBackground(new ColorDrawable(0xffFFFF00));
                } else if (i > 41) {
                    ll_result_body.setBackground(new ColorDrawable(0xffCD661D));
                }

            } else {
                ll_result_body.setBackground(new ColorDrawable(0xffdedada));
                tv_result_body.setText("0");
            }
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (rb_man_body.isChecked()) {
                Log.i("LT","12345");
                bManResult();
            }
            else {
                Log.i("LT","123");
                bWumenResult();
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
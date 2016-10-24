package com.tz.healthdiary.Jisuanqi;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.icu.math.BigDecimal;
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

import static com.tz.healthdiary.R.id.rb_wumen_waist;

/**
 * Created by anzhuo on 2016/10/20.
 */

public class WaistlineActivity extends Activity implements View.OnClickListener {
    RadioGroup rg_waist;
    ImageView iv_back_waist;
    EditText et_year_waist;
    RadioButton rb_man_waise;
    RadioButton rb_wumen_waise;
    EditText et_height_waist;
    EditText et_waist_waist;
    LinearLayout ll_result_waist;
    TextView tv_result_waist;
    TextView tv_result_w;
    TextView  tv_mostthin_waist;
    TextView  tv_thin_waist;
    TextView  tv_heather_waist;
    TextView  tv_fat_waist;
    TextView  tv_morefat_waist;
    TextView  tv_mostfat_waist;
    int height;
    int waist;
    double result;
    String str;
    double i;
    int a=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_waistline_layout);
        rg_waist= (RadioGroup) findViewById(R.id.rg_waist);
        iv_back_waist = (ImageView) findViewById(R.id.iv_back_waist);
        et_year_waist = (EditText) findViewById(R.id.et_year_waist);
        rb_man_waise = (RadioButton) findViewById(R.id.rb_man_waist);
        rb_wumen_waise = (RadioButton) findViewById(rb_wumen_waist);
        et_height_waist = (EditText) findViewById(R.id.et_height_waist);
        et_waist_waist = (EditText) findViewById(R.id.et_waist_waist);
        ll_result_waist = (LinearLayout) findViewById(R.id.ll_result_waist);
        tv_result_waist = (TextView) findViewById(R.id.tv_result_waist);
        tv_result_w = (TextView) findViewById(R.id.tv_resul_w);
        tv_thin_waist= (TextView) findViewById(R.id.tv_thin_waist);
        tv_mostthin_waist= (TextView) findViewById(R.id.tv_mostthin_waist);
        tv_heather_waist= (TextView) findViewById(R.id.tv_heather_waist);
        tv_fat_waist= (TextView) findViewById(R.id.tv_fat_waist);
        tv_morefat_waist= (TextView) findViewById(R.id.tv_morefat_waist);
        tv_mostfat_waist= (TextView) findViewById(R.id.tv_mostfat_waist);
        iv_back_waist.setOnClickListener(this);
        rb_man_waise.setOnClickListener(this);
        rb_wumen_waise.setOnClickListener(this);
        rg_waist.setOnClickListener(this);
        et_height_waist.addTextChangedListener(textWatcher);
        et_waist_waist.addTextChangedListener(textWatcher);
        if (a==2){
            rg_waist.check(R.id.rb_wumen_waist);
            WumenResult();
        }
        else {
            ManResult();
            rg_waist.check(R.id.rb_man_waist);
        }
        rg_waist.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_man_waist:
                        Log.i("LT","123");
                        ManResult();
                        break;
                    case R.id.rb_wumen_waist:
                        WumenResult();
                        Log.i("LT","456");
                        break;

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_waist:
                finish();
                break;
        }

    }

    //监听EditText数值的改变，其计算结果也及时改变
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (rb_man_waise.isChecked()){
                ManResult();
            }
           else {
                WumenResult();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    //进行结果判断
    private void ManResult() {
        if (et_waist_waist.getText().toString().equals("") || et_height_waist.getText().toString().equals("")) {
        } else {
            height = Integer.parseInt(et_height_waist.getText().toString());
            waist = Integer.parseInt(et_waist_waist.getText().toString());
            result = (double) waist / height;
            str = String.format("%.2f", result);
            i = Double.parseDouble(str);
            tv_mostthin_waist.setText("＜0.35");
            tv_thin_waist.setText("0.35-0.42");
            tv_heather_waist.setText("0.43-0.52");
            tv_fat_waist.setText("0.53-0.57");
            tv_morefat_waist.setText("0.58-0.62");
            tv_mostfat_waist.setText("≥0.63");
            if (height >= 20 && height <= 240 && waist >= 20 && waist <= 150) {
                tv_result_waist.setText(str);
                if (i < 0.35) {
                    ll_result_waist.setBackground(new ColorDrawable(0xff0000FF));
                } else if (i >= 0.35 && i <= 0.42) {
                    ll_result_waist.setBackground(new ColorDrawable(0xff00FFFF));
                } else if (i >= 0.43 && i <= 0.52) {
                    ll_result_waist.setBackground(new ColorDrawable(0xff00FF00));
                } else if (i >= 0.53 && i <= 0.57) {
                    ll_result_waist.setBackground(new ColorDrawable(0xffFFFF00));
                } else if (i >= 0.58 && i <= 0.62) {
                    ll_result_waist.setBackground(new ColorDrawable(0xffCD661D));
                } else {
                    ll_result_waist.setBackground(new ColorDrawable(0xffFF0000));
                }
            } else {
                tv_result_waist.setText("0");
                ll_result_waist.setBackground(new ColorDrawable(0xffdedada));
            }
        }
    }
    public void WumenResult(){
        if (et_waist_waist.getText().toString().equals("") || et_height_waist.getText().toString().equals("")) {
        } else {
            height = Integer.parseInt(et_height_waist.getText().toString());
            waist = Integer.parseInt(et_waist_waist.getText().toString());
            result = (double) waist / height;
            str = String.format("%.2f", result);
            i = Double.parseDouble(str);
            tv_mostthin_waist.setText("＜0.35");
            tv_thin_waist.setText("0.35-0.41");
            tv_heather_waist.setText("0.42-0.48");
            tv_fat_waist.setText("0.49-0.53");
            tv_morefat_waist.setText("0.54-0.57");
            tv_mostfat_waist.setText("≥0.58");
            if (height >= 20 && height <= 240 && waist >= 20 && waist <= 150) {
                tv_result_waist.setText(str);
                if (i < 0.35) {
                    ll_result_waist.setBackground(new ColorDrawable(0xff0000FF));
                } else if (i >= 0.35 && i <= 0.41) {
                    ll_result_waist.setBackground(new ColorDrawable(0xff00FFFF));
                } else if (i >= 0.42 && i <= 0.48) {
                    ll_result_waist.setBackground(new ColorDrawable(0xff00FF00));
                } else if (i >= 0.49 && i <= 0.53) {
                    ll_result_waist.setBackground(new ColorDrawable(0xffFFFF00));
                } else if (i >= 0.54 && i < 0.57) {
                    ll_result_waist.setBackground(new ColorDrawable(0xffCD661D));
                } else {
                    ll_result_waist.setBackground(new ColorDrawable(0xffFF0000));
                }
            } else {
                tv_result_waist.setText("0");
                ll_result_waist.setBackground(new ColorDrawable(0xffdedada));
            }
        }
    }
}
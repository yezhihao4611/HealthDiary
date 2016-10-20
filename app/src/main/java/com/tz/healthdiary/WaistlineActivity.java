package com.tz.healthdiary;

import android.app.Activity;
import android.icu.math.BigDecimal;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import static com.tz.healthdiary.R.id.rb_wumen_waist;

/**
 * Created by anzhuo on 2016/10/20.
 */

public class WaistlineActivity extends Activity implements View.OnClickListener {
    EditText et_year_waist;
    RadioButton rb_man_waise;
    RadioButton rb_wumen_waise;
    EditText et_height_waist;
    EditText et_waist_waist;
    LinearLayout ll_result_waist;
    TextView tv_result_waist;
    int height;
    int waist;
    double resulr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_waistline_layout);
        et_year_waist = (EditText) findViewById(R.id.et_year_waist);
        rb_man_waise = (RadioButton) findViewById(R.id.rb_man_waist);
        rb_wumen_waise = (RadioButton) findViewById(rb_wumen_waist);
        et_height_waist = (EditText) findViewById(R.id.et_height_waist);
        et_waist_waist = (EditText) findViewById(R.id.et_waist_waist);
        ll_result_waist = (LinearLayout) findViewById(R.id.ll_result_waist);
        tv_result_waist = (TextView) findViewById(R.id.tv_result_waist);
        height = Integer.parseInt(et_height_waist.getText().toString());
        waist=Integer.parseInt(et_waist_waist.getText().toString());
        resulr=(double) waist/height;
        String str=String.format("%.2f",resulr);
        if (height >= 20 && height <= 240&&waist>=20&&waist<=150){
            tv_result_waist.setText(str);
        }

    }

    @Override
    public void onClick(View v) {

    }
}

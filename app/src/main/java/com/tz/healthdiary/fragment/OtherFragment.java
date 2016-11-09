package com.tz.healthdiary.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tz.healthdiary.Jisuanqi.BaseActivity;
import com.tz.healthdiary.Jisuanqi.BodyActivity;
import com.tz.healthdiary.OpinionActivity;
import com.tz.healthdiary.R;
import com.tz.healthdiary.Jisuanqi.WaistlineActivity;
import com.tz.healthdiary.StartAnimaActivity;
import com.tz.healthdiary.sqlite.MyDataService;

/**
 * Created by 西野七濑 on 2016/10/18.
 * Other页面的Fragment
 */
public class OtherFragment extends Fragment implements View.OnClickListener {
    TextView tv_waist_other;
    TextView tv_body_other;
    TextView tv_base_other;
    Intent intent;
    TextView tv_opinion_other;
    View view;
    EditText et_height_other;
    EditText et_year_other;
    TextView tv_weight_other;
    RadioButton rb_man;
    RadioButton rb_wumen;
    RadioGroup rg_other;
    MyDataService myDataService= StartAnimaActivity.myDataService;
    int a = myDataService.getSex();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.oter_layout, null);
        tv_waist_other = (TextView) view.findViewById(R.id.tv_waist_other);
        tv_body_other = (TextView) view.findViewById(R.id.tv_body_other);
        tv_base_other = (TextView) view.findViewById(R.id.tv_base_other);
        tv_opinion_other = (TextView) view.findViewById(R.id.tv_opinion_other);
        tv_weight_other= (TextView) view.findViewById(R.id.tv_weight_other);
        et_height_other= (EditText) view.findViewById(R.id.et_height_other );
        et_year_other= (EditText) view.findViewById(R.id.et_year);
        rb_man= (RadioButton) view.findViewById(R.id.man);
        rb_wumen= (RadioButton) view.findViewById(R.id.wuman);
        rg_other= (RadioGroup) view.findViewById(R.id.rg_main);
        tv_waist_other.setOnClickListener(this);
        tv_body_other.setOnClickListener(this);
        tv_base_other.setOnClickListener(this);
        tv_opinion_other.setOnClickListener(this);
        tv_weight_other.setOnClickListener(this);
        et_year_other.addTextChangedListener(textWatcher);
        et_height_other.addTextChangedListener(textWatcher);
        //读取体重
        int Mweight=myDataService.getKg();
        int mweight=myDataService.getG();
        String Nweight= String.valueOf(Mweight+mweight*0.1);
        tv_weight_other.setText(Nweight);
        //读取身高
        int Mheight=myDataService.getMeter();
        int mheight=myDataService.getCm();
        int Nheighe=Mheight*100+mheight;
        et_height_other.setText(Nheighe+"");
        //读取年龄
        et_year_other.setText(myDataService.getAge()+"");
       if (a==1){
           rg_other.check(R.id.man);
       }
        else {
           rg_other.check(R.id.wuman);
       }
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_waist_other:
                intent = new Intent(getActivity(), WaistlineActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_body_other:
                intent = new Intent(getActivity(), BodyActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_base_other:
                intent = new Intent(getActivity(), BaseActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_opinion_other:
                intent = new Intent(getActivity(), OpinionActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_weight_other:
                Toast.makeText(getActivity(),"请在底部加号按钮处修改体重",Toast.LENGTH_SHORT).show();
                break;
        }

    }
    public TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int heighe= Integer.parseInt(et_height_other.getText().toString());
            int Mheight=heighe/100;
            int mheighe=heighe%100;
            myDataService.setMeter(Mheight);
            myDataService.setCm(mheighe);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}

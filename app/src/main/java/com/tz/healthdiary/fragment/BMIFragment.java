package com.tz.healthdiary.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tz.healthdiary.R;
import com.tz.healthdiary.StartAnimaActivity;
import com.tz.healthdiary.custom.CircleImageView;
import com.tz.healthdiary.custom.ColorArcProgressBar;
import com.tz.healthdiary.sqlite.MyDataService;

import java.text.DecimalFormat;

import static com.tz.healthdiary.R.id.stature_number;

/**
 * Created by 西野七濑 on 2016/10/18.
 * BMI页面的Fragment
 */
public class BMIFragment extends Fragment {

    private CircleImageView mCircleImageView;
    private TextView mTextView;
    private TextView minW;
    private TextView maxW;
    private TextView sTextView;
    private View view;

    private ColorArcProgressBar mColorArcProgressBar;

    private MyDataService mMyDataService = StartAnimaActivity.myDataService;

    private double BMI;
    private int M;
    private int Cm;
    private int sex;
    double weight;

    private double minWeight;
    private double maxWeight;
    double stature;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_bmi, null);
        initView();
        initData();
        return view;
    }

    private void initView() {
        mTextView = (TextView) view.findViewById(R.id.weight_number);
        minW = (TextView) view.findViewById(R.id.min_weight_number);
        maxW = (TextView) view.findViewById(R.id.max_weight_number);
        sTextView = (TextView) view.findViewById(stature_number);
        mCircleImageView = (CircleImageView) view.findViewById(R.id.civ_sex);
    }

    public void initData() {
        BMI = mMyDataService.getBMI();
        M = mMyDataService.getMeter();
        Cm = mMyDataService.getCm();
        Log.i("TZ", "M:" + M);
        Log.i("TZ", "Cm:" + Cm);
        sex = mMyDataService.getSex();
        weight = mMyDataService.getNewKg() + (double) mMyDataService.getNewG() / 10;
        stature = M + ((double) Cm / 100);
        Log.i("TZ", "stature:" + stature);

        mColorArcProgressBar = (ColorArcProgressBar) view.findViewById(R.id.bar);
        mColorArcProgressBar.setBMI(BMI);
        float x = Float.parseFloat(evolveBMI(BMI) + "");
        mColorArcProgressBar.setCurrentValues(x);

        mTextView.setText(weight + "");
        sTextView.setText(stature + "");

        if (sex == 0) {
            mCircleImageView.setImageDrawable(getResources().getDrawable(R.drawable.girl));
        } else if (sex == 1) {
            mCircleImageView.setImageDrawable(getResources().getDrawable(R.drawable.boy));
        }

        double a = 25.0 * (stature * stature);
        double b = 18.5 * (stature * stature);
        DecimalFormat df = new DecimalFormat("0.0");
        maxWeight = Double.parseDouble(df.format(a));
        minWeight = Double.parseDouble(df.format(b));

        Log.i("TZ", "maxWeight:" + maxWeight);
        Log.i("TZ", "minWeight:" + minWeight);
        maxW.setText(maxWeight + "");
        minW.setText(minWeight + "");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private double evolveBMI(double bmi) {
        double x;
        if (bmi < 16.0) {
            x = (bmi / 16.0) * 12.5;
            if (x < 0) {
                x = 0;
            }
        } else if (16.0 <= bmi & bmi < 17.0) {
            x = 12.5 + ((bmi - 16.0) / 1.0) * 12.5;
        } else if (17.0 <= bmi & bmi < 18.5) {
            x = 25.0 + ((bmi - 17.0) / 1.5) * 12.5;
        } else if (18.5 <= bmi & bmi < 25.0) {
            x = 37.5 + ((bmi - 18.5) / 6.5) * 12.5;
        } else if (25.0 <= bmi & bmi < 30.0) {
            x = 50.0 + ((bmi - 25.0) / 5.0) * 12.5;
        } else if (30.0 <= bmi & bmi < 35.0) {
            x = 62.5 + ((bmi - 30.0) / 5.0) * 12.5;
        } else if (35.0 <= bmi & bmi < 40.0) {
            x = 75.0 + ((bmi - 35.0) / 5.0) * 12.5;
        } else {
            x = 87.5 + ((bmi - 40.0) / 10.0) * 12.5;
            if (x > 100) {
                x = 100;
            }
        }
        return x;
    }
}

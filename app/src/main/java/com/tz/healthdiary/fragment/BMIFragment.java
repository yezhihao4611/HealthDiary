package com.tz.healthdiary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tz.healthdiary.R;
import com.tz.healthdiary.custom.ColorArcProgressBar;

/**
 * Created by 西野七濑 on 2016/10/18.
 * BMI页面的Fragment
 */
public class BMIFragment extends Fragment {

    ColorArcProgressBar mColorArcProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bmi,null);
        mColorArcProgressBar = (ColorArcProgressBar) view.findViewById(R.id.bar1);
        mColorArcProgressBar.setCurrentValues(30);

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

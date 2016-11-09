package com.tz.healthdiary.fragment;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.tz.healthdiary.Jgraph.graph.JcoolGraph;
import com.tz.healthdiary.Jgraph.inter.BaseGraph;
import com.tz.healthdiary.Jgraph.models.Jchart;
import com.tz.healthdiary.R;
import com.tz.healthdiary.StartAnimaActivity;
import com.tz.healthdiary.bean.WeightInfo;
import com.tz.healthdiary.bean.WeightListAdapter;
import com.tz.healthdiary.sqlite.MyDataService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 西野七濑 on 2016/10/18.
 * Centre页面的Fragment
 */

public class CentreFragment extends Fragment  {
    private JcoolGraph mLineChar;
    ListView lv_weight_center;
    ScrollView sv_curve;
    WeightInfo weightInfo;
    List<WeightInfo> list;
    WeightListAdapter weightListAdapter;
    private float[] numbers;
    MyDataService mMyDataService = StartAnimaActivity.myDataService;
    private List<List<Integer>> dataOnes;
    private List<Integer> dataOne;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_center, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLineChar = (JcoolGraph) view.findViewById(R.id.chart_line);
        lv_weight_center = (ListView) view.findViewById(R.id.lv_weight_center);
        sv_curve = (ScrollView) view.findViewById(R.id.sv_curve);
        sv_curve.smoothScrollTo(0, 0);
    }


    @Override
    public void onResume() {
        super.onResume();
        showCurve();
    }

    public void showCurve() {

        List<Jchart> lines = new ArrayList<>();
        int min = 300;
        dataOnes = mMyDataService.getListOnes();
        list = new ArrayList<>();

        for (int i = 0; i < dataOnes.size(); i++) {
            weightInfo = new WeightInfo();
            dataOne = dataOnes.get(i);
            float y = (float) dataOne.get(4) + ((float) dataOne.get(5) / 10f);
            lines.add(new Jchart(0f, y, dataOne.get(2) + "-" + dataOne.get(3), Color.BLACK));

            if (min > y) {
                min = (int) y;
            }
            weightInfo.setYear(dataOne.get(1) + "");
            weightInfo.setMonth(dataOne.get(2) + "");
            weightInfo.setDay(dataOne.get(3) + "");
            weightInfo.setWeight(y + "");

            list.add(0, weightInfo);
        }

        weightListAdapter = new WeightListAdapter(getActivity(), list);
        lv_weight_center.setAdapter(weightListAdapter);
        weightListAdapter.notifyDataSetChanged();

        mLineChar.feedData(lines);
        mLineChar.setLinePointRadio((int) mLineChar.getLineWidth());
        mLineChar.setLineStyle(JcoolGraph.LINE_CURVE);
        mLineChar.setLineShowStyle(JcoolGraph.LINESHOW_ASWAVE);
        mLineChar.setSelectedMode(BaseGraph.SELECETD_MSG_SHOW_TOP);
        mLineChar.setScrollAble(false);
        mLineChar.setNormalColor(Color.parseColor("#676567"));

        mLineChar.setYaxisValues(min, 0, 0);
        mLineChar.setPaintShaderColors(Color.RED, Color.parseColor("#E79D23"), Color.parseColor("#FFF03D"), Color.parseColor("#A9E16F"), Color.parseColor("#75B9EF"));

        mLineChar.aniShow_growing();
    }

}

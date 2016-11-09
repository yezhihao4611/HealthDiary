package com.tz.healthdiary.fragment;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tz.healthdiary.Jgraph.graph.JcoolGraph;
import com.tz.healthdiary.Jgraph.inter.BaseGraph;
import com.tz.healthdiary.Jgraph.models.Jchart;
import com.tz.healthdiary.R;
import com.tz.healthdiary.bean.WeightInfo;
import com.tz.healthdiary.bean.WeightListAdapter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 西野七濑 on 2016/10/18.
 * Centre页面的Fragment
 */

public class CentreFragment extends Fragment {

    private JcoolGraph mLineChar;
    ListView lv_weight_center;
    List<WeightInfo> list;
    WeightListAdapter weightListAdapter;
    private float[] numbers;


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

        List<Jchart> lines = new ArrayList<>();

        lines.add(new Jchart(0, 51.9f, "07-10", Color.BLACK));
        lines.add(new Jchart(0, 53.1f, "07-11", Color.BLACK));
        lines.add(new Jchart(0, 54.2f, "07-12", Color.BLACK));
        lines.add(new Jchart(0, 55.2f, "07-13", Color.BLACK));
        lines.add(new Jchart(0, 53.8f, "07-14", Color.BLACK));
        lines.add(new Jchart(0, 54.4f, "07-15", Color.BLACK));
        lines.add(new Jchart(0, 54.3f, "07-16", Color.BLACK));


        numbers = new float[]{51.9f, 53.1f, 54.2f, 55.2f, 53.8f, 54.4f, 54.3f};

        int min = (int) numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (min > numbers[i]) {
                min = (int) numbers[i];
            }
        }


        mLineChar.feedData(lines);
        mLineChar.setLinePointRadio((int) mLineChar.getLineWidth());
        mLineChar.setLineStyle(JcoolGraph.LINE_CURVE);
        mLineChar.setLineShowStyle(JcoolGraph.LINESHOW_ASWAVE);
        mLineChar.setSelectedMode(BaseGraph.SELECETD_MSG_SHOW_TOP);
        mLineChar.setScrollAble(false);
        mLineChar.setNormalColor(Color.parseColor("#676567"));


        mLineChar.setYaxisValues(min, 0, 0);
        mLineChar.setPaintShaderColors(Color.RED, Color.parseColor("#E79D23"), Color.parseColor("#FFF03D"), Color.parseColor("#A9E16F"), Color.parseColor("#75B9EF"));


        lv_weight_center.setAdapter(weightListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLineChar.aniShow_growing();
    }

}

package com.tz.healthdiary.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.tz.healthdiary.Jgraph.graph.JcoolGraph;
import com.tz.healthdiary.Jgraph.inter.BaseGraph;
import com.tz.healthdiary.Jgraph.models.Jchart;
import com.tz.healthdiary.R;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 西野七濑 on 2016/10/18.
 * Centre页面的Fragment
 */

public class CentreFragment extends Fragment {
    private JcoolGraph mLineChar;


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

        List<Jchart> lines = new ArrayList<>();

        lines.add(new Jchart(0, 25, "07-10", Color.parseColor("#5F77F6")));
        lines.add(new Jchart(0, 15, "07-11", Color.parseColor("#5F77F6")));
        lines.add(new Jchart(0, 32, "07-12", Color.parseColor("#5F77F6")));
        lines.add(new Jchart(0, 4, "07-13", Color.parseColor("#5F77F6")));
        lines.add(new Jchart(0, 25, "07-14", Color.parseColor("#5F77F6")));
        lines.add(new Jchart(0, 32, "07-15", Color.parseColor("#5F77F6")));
        lines.add(new Jchart(0, 49, "07-16", Color.parseColor("#5F77F6")));


        mLineChar.setLineStyle(JcoolGraph.LINE_CURVE);
        mLineChar.setLinePointRadio((int) mLineChar.getLineWidth());
        mLineChar.setLineShowStyle(JcoolGraph.LINESHOW_ASWAVE);
        mLineChar.setSelectedMode(BaseGraph.SELECETD_MSG_SHOW_TOP);
        mLineChar.setScrollAble(false);
        mLineChar.feedData(lines);
//        mLineChar.setYaxisValues(0, 0, 0);
        mLineChar.setPaintShaderColors(Color.RED, Color.parseColor("#E79D23"), Color.parseColor("#FFF03D"), Color.parseColor("#A9E16F"), Color.parseColor("#75B9EF"));

    }

    @Override
    public void onResume() {
        super.onResume();

        mLineChar.aniShow_growing();

    }


}

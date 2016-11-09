package com.tz.healthdiary.fragment;


import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tz.healthdiary.Jgraph.graph.JcoolGraph;
import com.tz.healthdiary.Jgraph.inter.BaseGraph;
import com.tz.healthdiary.Jgraph.models.Jchart;
import com.tz.healthdiary.R;
import com.tz.healthdiary.StartAnimaActivity;
import com.tz.healthdiary.bean.WeightInfo;
import com.tz.healthdiary.bean.WeightListAdapter;
import com.tz.healthdiary.sqlite.MyDataService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 西野七濑 on 2016/10/18.
 * Centre页面的Fragment
 */

public class CentreFragment extends Fragment {
    View v;
    Button bt_change;
    TextView tv_one;
    TextView tv_two;
    TextView tv_three;
    int dotsNumber;
    private JcoolGraph mLineChar;
    PopupWindow popupWindow;
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
        v = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_center_layout, null);
        dotsNumber = 7;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt_change = (Button) view.findViewById(R.id.bt_change);
        tv_one = (TextView) v.findViewById(R.id.tv_one);
        tv_two = (TextView) v.findViewById(R.id.tv_two);
        tv_three = (TextView) v.findViewById(R.id.tv_three);
        mLineChar = (JcoolGraph) view.findViewById(R.id.chart_line);
        lv_weight_center = (ListView) view.findViewById(R.id.lv_weight_center);
        sv_curve = (ScrollView) view.findViewById(R.id.sv_curve);
        sv_curve.smoothScrollTo(0, 0);

        bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setpop();
                popupWindow.showAsDropDown(bt_change, 0, 0);

            }
        });
        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_change.setText(" 7天 ▽");
                dotsNumber = 7;
                mLineChar.feedData(0);
                popupWindow.dismiss();
                showCurve();
            }
        });
        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_change.setText("14天 ▽");
                dotsNumber = 14;
                popupWindow.dismiss();
                showCurve();
            }
        });
        tv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_change.setText("28天 ▽");
                dotsNumber = 28;
                popupWindow.dismiss();
                showCurve();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        showCurve();
    }

    public void showCurve() {
        if (null != mLineChar) {
            List<Jchart> lines = new ArrayList<>();
            int min = 300;
            dataOnes = mMyDataService.getListOnes();
            list = new ArrayList<>();

            if (dataOnes.size() < dotsNumber) {
                dotsNumber = dataOnes.size();
            }
            for (int i = 0; i < dotsNumber; i++) {
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

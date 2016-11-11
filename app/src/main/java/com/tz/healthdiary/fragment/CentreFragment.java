package com.tz.healthdiary.fragment;


import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
    TextView tv_one_c;
    TextView tv_two_c;
    TextView tv_three_c;
    JcoolGraph mLineChar;
    PopupWindow popupWindow;
    ListView lv_weight_center;
    ScrollView sv_curve;
    WeightInfo weightInfo;
    List<WeightInfo> list;
    WeightListAdapter weightListAdapter;
    MyDataService mMyDataService = StartAnimaActivity.myDataService;
    private List<List<Integer>> dataLists;
    private List<Integer> dataList;
    private static int cDay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_center, null);
        v = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_center_layout, null);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt_change = (Button) view.findViewById(R.id.bt_change);
        tv_one_c = (TextView) v.findViewById(R.id.tv_one_c);
        tv_two_c = (TextView) v.findViewById(R.id.tv_two_c);
        tv_three_c = (TextView) v.findViewById(R.id.tv_three_c);
        mLineChar = (JcoolGraph) view.findViewById(R.id.chart_line);
        lv_weight_center = (ListView) view.findViewById(R.id.lv_weight_center);
        sv_curve = (ScrollView) view.findViewById(R.id.sv_curve);

        dataLists = new ArrayList<>();

        if (cDay == 2) {
            bt_change.setText(" 14次 ▽");
            dataLists = mMyDataService.getListTwos();
        } else if (cDay == 3) {
            dataLists = mMyDataService.getListFours();
            bt_change.setText(" 28次 ▽");

        } else {
            cDay = 1;
            dataLists = mMyDataService.getListOnes();
            bt_change.setText(" 7次 ▽");
        }

        bt_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setpop();
                popupWindow.showAsDropDown(bt_change, 0, 0);

            }
        });
        tv_one_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cDay = 1;
                bt_change.setText(" 7天 ▽");
                dataLists = mMyDataService.getListOnes();
                popupWindow.dismiss();
                onResume();
            }
        });
        tv_two_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cDay = 2;
                bt_change.setText("14天 ▽");
                dataLists = mMyDataService.getListTwos();
                Log.i("Hao", dataLists + "");
                popupWindow.dismiss();
                onResume();
            }
        });
        tv_three_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cDay = 3;
                bt_change.setText("28天 ▽");
                dataLists = mMyDataService.getListFours();
                popupWindow.dismiss();
                onResume();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        sv_curve.smoothScrollTo(0, 0);


        showCurve();
    }

    public void showCurve() {
        if (null != mLineChar) {
            List<Jchart> lines = new ArrayList<>();
            int min = 300;
            list = new ArrayList<>();

            for (int i = 0; i < dataLists.size(); i++) {
                weightInfo = new WeightInfo();
                dataList = dataLists.get(i);
                float y = (float) dataList.get(4) + ((float) dataList.get(5) / 10f);
                if (dataLists.size() > 14) {
                    if (i % 3 == 0) {
                        lines.add(new Jchart(0f, y, dataList.get(2) + "-" + dataList.get(3), Color.BLACK));
                    } else {
                        lines.add(new Jchart(0f, y, " ", Color.BLACK));
                    }
                } else if (dataLists.size() > 7) {
                    if (i % 2 == 0) {
                        lines.add(new Jchart(0f, y, dataList.get(2) + "-" + dataList.get(3), Color.BLACK));
                    } else {
                        lines.add(new Jchart(0f, y, " ", Color.BLACK));
                    }
                } else {
                    lines.add(new Jchart(0f, y, dataList.get(2) + "-" + dataList.get(3), Color.BLACK));
                }
                if (min > y) {
                    min = (int) y;
                }

                weightInfo.setYear(dataList.get(1) + "");
                weightInfo.setMonth(dataList.get(2) + "");
                weightInfo.setDay(dataList.get(3) + "");
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

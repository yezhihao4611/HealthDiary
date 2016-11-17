package com.tz.healthdiary.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tz.healthdiary.AboutActivity;
import com.tz.healthdiary.InitializeActivity;
import com.tz.healthdiary.Jisuanqi.BaseActivity;
import com.tz.healthdiary.Jisuanqi.BodyActivity;
import com.tz.healthdiary.Jisuanqi.WaistlineActivity;
import com.tz.healthdiary.OpinionActivity;
import com.tz.healthdiary.R;
import com.tz.healthdiary.StartAnimaActivity;
import com.tz.healthdiary.sqlite.MyDataService;
import com.tz.healthdiary.utils.MyApplication;

import static com.tz.healthdiary.MainActivity.location;

/**
 * Created by 西野七濑 on 2016/10/18.
 * Other页面的Fragment
 */
public class OtherFragment extends Fragment implements View.OnClickListener {
    TextView tv_waist_other;
    TextView tv_body_other;
    TextView tv_base_other;
    TextView deleteSQL;
    Intent intent;
    TextView tv_opinion_other;
    View view;
    EditText et_height_other;
    TextView tv_year_other;
    TextView tv_weight_other;
    ImageView head_other;
    TextView tv_about_other;
    MyDataService myDataService = StartAnimaActivity.myDataService;
    int a = myDataService.getSex();
    SharedPreferences mSharedPreferences = StartAnimaActivity.mSharedPreferences;
    Button btDeleteY;
    Button btDeleteN;
    private PopupWindow mPopupWindow;
    private View mPopView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.other_layout, null);
        location = 3;
        tv_waist_other = (TextView) view.findViewById(R.id.tv_waist_other);
        tv_body_other = (TextView) view.findViewById(R.id.tv_body_other);
        tv_base_other = (TextView) view.findViewById(R.id.tv_base_other);
        tv_opinion_other = (TextView) view.findViewById(R.id.tv_opinion_other);
        tv_weight_other = (TextView) view.findViewById(R.id.tv_weight_other);
        et_height_other = (EditText) view.findViewById(R.id.et_height_other);
        tv_year_other = (TextView) view.findViewById(R.id.tv_year);
        deleteSQL = (TextView) view.findViewById(R.id.tv_delete_other);
        head_other = (ImageView) view.findViewById(R.id.head_other);
        tv_about_other = (TextView) view.findViewById(R.id.tv_about_other);
        mPopView = LayoutInflater.from(getActivity()).inflate(R.layout.other_delete, null);
        btDeleteN = (Button) mPopView.findViewById(R.id.bt_deleteall_no);
        btDeleteY = (Button) mPopView.findViewById(R.id.bt_deleteall_yes);
        btDeleteN.setOnClickListener(this);
        btDeleteY.setOnClickListener(this);
        deleteSQL.setOnClickListener(this);
        tv_waist_other.setOnClickListener(this);
        tv_body_other.setOnClickListener(this);
        tv_base_other.setOnClickListener(this);
        tv_opinion_other.setOnClickListener(this);
        tv_weight_other.setOnClickListener(this);
        tv_about_other.setOnClickListener(this);
        tv_year_other.addTextChangedListener(textWatcher);
        et_height_other.addTextChangedListener(textWatcher);
        //读取体重
        int Mweight = myDataService.getNewKg();
        int mweight = myDataService.getNewG();
        Log.i("TZ", "Mweight:" + Mweight + "mweight" + mweight);
        String Nweight = String.valueOf(Mweight + (double) mweight * 0.1);
        tv_weight_other.setText(Nweight);
        //读取身高
        int Mheight = myDataService.getMeter();
        int mheight = myDataService.getCm();
        int Nheighe = Mheight * 100 + mheight;
        et_height_other.setText(Nheighe + "");
        //读取年龄
        tv_year_other.setText(myDataService.getAge() + "");
        if (a == 1) {
            head_other.setImageResource(R.drawable.male_selected);
        } else {
            head_other.setImageResource(R.drawable.female_selected);
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
            case R.id.tv_delete_other:
                showPopupWindow(mPopView);
                mPopupWindow.showAtLocation(mPopView, Gravity.CENTER, 0, 0);
                break;
            case R.id.bt_deleteall_no:
                mPopupWindow.dismiss();
                break;
            case R.id.tv_about_other:
                intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_deleteall_yes:
                myDataService.deleteAllData();
                mSharedPreferences = getActivity().getSharedPreferences("firstSharedPreferences", 0);
                mSharedPreferences.edit().putBoolean("initialize", true).apply();
                Toast.makeText(MyApplication.getContext(), "已删除", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), InitializeActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;

            default:
                break;
        }
    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (et_height_other.getText().toString().equals("")) {

            } else {
                int heighe = Integer.parseInt(et_height_other.getText().toString());
                int Mheight = heighe / 100;
                int mheighe = heighe % 100;
                Log.i("TZ", "Mheight:" + Mheight + "\n" + "mheighe" + mheighe);
                myDataService.setMeter(Mheight);
                myDataService.setCm(mheighe);
                myDataService.alterStatureData();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void showPopupWindow(View view) {
        mPopupWindow = new PopupWindow(view);
        mPopupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置pop点击
        mPopupWindow.setTouchable(true);
        //设置外部点击
        mPopupWindow.setOutsideTouchable(true);
        //设置背景颜色，0x后面加8位
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置屏幕背景透明度
        backgroundAlpha(0.5f);
        //添加pop窗口关闭事件
        mPopupWindow.setOnDismissListener(new PopupWindowDismissListener());
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    /**
     * popWin关闭事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class PopupWindowDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            //窗口关闭时恢复背景透明度
            backgroundAlpha(1f);
        }
    }
}

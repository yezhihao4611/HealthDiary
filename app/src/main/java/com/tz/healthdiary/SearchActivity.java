package com.tz.healthdiary;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tz.healthdiary.bean.Info;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by anzhuo on 2016/10/27.
 */

public class SearchActivity extends Activity {
    TextView tv_introduce;
    Response response;
    OkHttpClient okHttpClient;
    String str;
    EditText et_name;
    TextView tv_search;
    String Name;
    String url;
    ProgressDialog progressDialog;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Gson gson = new Gson();
                    Info info = gson.fromJson(str, Info.class);
                    if (str == null || str.equals("")) {
                        Toast.makeText(SearchActivity.this, "无网络连接....", Toast.LENGTH_SHORT).show();
                    } else {
                        if (info.isStatus()) {
                            tv_introduce.setText(Html.fromHtml(info.getMessage()));
                            progressDialog.dismiss();

                        } else {
                            tv_introduce.setText("数据不存在......");
                        }
                    }
                    break;
            }
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        tv_introduce = (TextView) findViewById(R.id.tv_introduce);
        et_name = (EditText) findViewById(R.id.et_name);
        tv_search = (TextView) findViewById(R.id.tv_search);
        okHttpClient = new OkHttpClient();
        et_name.addTextChangedListener(textwatch);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = et_name.getText().toString();
                Log.i("LT", Name);
                requestNetWork(Name);
                progressDialog = ProgressDialog.show(SearchActivity.this, "", "正在检索....", true, false);
                Window window=progressDialog.getWindow();
                WindowManager.LayoutParams lp=window.getAttributes();
                lp.alpha=0.5f;
                window.setAttributes(lp);
                progressDialog.setCancelable(true);       // 设置是否可以通过点击Back键取消
                progressDialog.setCanceledOnTouchOutside(true);// 设置在点击Dialog外是否取消Dialog进度条
                Log.i("LT", "123");

            }
        });

    }

    public String request(String url) {
        Request request = new Request.Builder().url(url).build();
        try {
            response = okHttpClient.newCall(request).execute();
            str = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public void requestNetWork(final String name) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                url = "http://www.tngou.net/api/food/name?name=" + name;
                request(url);
                handler.sendEmptyMessage(0);


            }
        }.start();
    }

    private TextWatcher textwatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tv_introduce.setText("");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}

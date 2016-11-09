package com.tz.healthdiary.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tz.healthdiary.ContantActivity;
import com.tz.healthdiary.R;
import com.tz.healthdiary.SearchActivity;
import com.tz.healthdiary.bean.NewsAdaputer;
import com.tz.healthdiary.bean.NewsInfo;

import java.util.ArrayList;
import java.util.List;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 西野七濑 on 2016/10/18.
 * News页面的Fragment
 */
public class NewsFragment extends Fragment {
    ListView lv_list;
    NewsInfo newsInfo;
    NewsAdaputer newsAdaputer;
    List<NewsInfo> list;
    BmobQuery<News> query;
    private static final int Number = 0;
    EditText et_name;
    TextView tv_search_news;
    ProgressDialog progressDialog;
    LinearLayout ll_search;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Number:
                    query.findObjects(new FindListener<News>() {
                        @Override
                        public void done(List<News> Mlist, BmobException e) {
                            if (e == null) {
                                for (News mlist : Mlist) {
                                    newsInfo = new NewsInfo();
                                    newsInfo.setImage(mlist.getImage());
                                    Log.i("LT", mlist.getTitle());
                                    newsInfo.setFrom(mlist.getFrom());
                                    newsInfo.setTitle(mlist.getTitle());
                                    list.add(newsInfo);
                                }
                                newsAdaputer = new NewsAdaputer(getActivity(), list);
                                lv_list.setAdapter(newsAdaputer);
                                newsAdaputer.notifyDataSetChanged();
                                et_name.setVisibility(View.VISIBLE);
                                ll_search.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();

                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "数据读取异常，请重新请求....", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    break;
            }
        }

    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, null);
        lv_list = (ListView) view.findViewById(R.id.lv_listview);
        et_name = (EditText) view.findViewById(R.id.et_name);
        tv_search_news= (TextView) view.findViewById(R.id.tv_search_news);
        ll_search= (LinearLayout) view.findViewById(R.id.ll_search);
        list = new ArrayList<>();
        Bmob.initialize(getActivity(), "84f7828da3cc41850ebdd4ffa47c8fcb");
        query = new BmobQuery<>();
        progressDialog = ProgressDialog.show(getActivity(), "", "正在加载中....", true, false);
        Window window=progressDialog.getWindow();
        WindowManager.LayoutParams lp=window.getAttributes();
        lp.alpha=0.5f;
        window.setAttributes(lp);
        progressDialog.setCancelable(true);       // 设置是否可以通过点击Back键取消
        progressDialog.setCanceledOnTouchOutside(true);// 设置在点击Dialog外是否取消Dialog进度条
        handler.sendEmptyMessage(Number);

        et_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        //取消edittext焦点，不弹出输入框
        et_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                et_name.setInputType(InputType.TYPE_NULL);
                return false;
            }
        });
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), list.get(position).getFrom().toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ContantActivity.class);
                intent.putExtra("title", list.get(position).getTitle().toString());
                intent.putExtra("from", list.get(position).getFrom().toString());
                startActivity(intent);
            }
        });
        tv_search_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"请输入搜索内容！",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public class News extends BmobObject {
        String context;
        String from;
        String image;
        String title;


        public String getTitle() {
            return title;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }



}


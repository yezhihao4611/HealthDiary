package com.tz.healthdiary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by anzhuo on 2016/10/31.
 */

public class OpinionActivity extends Activity implements View.OnClickListener {
    TextView tv_back_opinion;
    TextView tv_send_opinion;
    EditText et_content_opinion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opinion_layout);
        tv_back_opinion = (TextView) findViewById(R.id.tv_back_opinion);
        tv_send_opinion = (TextView) findViewById(R.id.tv_send_opinion);
        et_content_opinion = (EditText) findViewById(R.id.et_content_opinion);
        tv_back_opinion.setOnClickListener(this);
        tv_send_opinion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back_opinion:
                finish();
                break;
            case R.id.tv_send_opinion:
                if (et_content_opinion.getText().toString().equals("")) {
                    Toast.makeText(this, "填写内容不能为空...", Toast.LENGTH_SHORT).show();
                } else {
                    Bmob.initialize(this, "84f7828da3cc41850ebdd4ffa47c8fcb");
                    Opinion opinion = new Opinion();
                    opinion.setOpinion(et_content_opinion.getText().toString());
                    opinion.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Log.i("LT", "添加数据成功，返回objectId为：" + s);
                                TastyToast.makeText(OpinionActivity.this,"发送成功...",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                                finish();
                            } else {
                                TastyToast.makeText(OpinionActivity.this,"发送成功...",TastyToast.LENGTH_SHORT,TastyToast.ERROR);
                                Log.i("LT", "创建数据失败：" + e.getMessage());
                            }
                        }
                    });

                }
        }
    }

    public class Opinion extends BmobObject {
        private String opinion;

        public String getOpinion() {
            return opinion;
        }

        public void setOpinion(String opinion) {
            this.opinion = opinion;
        }
    }
}

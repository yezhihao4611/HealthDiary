package com.tz.healthdiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.tz.healthdiary.sqlite.MyDataService;


/**
 * Created by anzhuo on 2016/10/14.
 */

public class StartAnimaActivity extends AppCompatActivity {

    ImageView mImageView;

    static SharedPreferences mSharedPreferences;
    public static MyDataService myDataService;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Intent intent0 = new Intent(StartAnimaActivity.this, FirstActivity.class);

                    startActivity(intent0);
                    finish();
                    break;
                case 1:
                    Intent intent1 = new Intent(StartAnimaActivity.this, InitializeActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
                case 2:
                    Intent intent2 = new Intent(StartAnimaActivity.this, MainActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startanima);
        initView();//控件初始化
        initService();//启动Service
        newThread();//判断跳转
    }

    private void initService() {
        //启动服务
        Intent intent = new Intent(StartAnimaActivity.this, MyDataService.class);
        startService(intent);
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.iv_starAnima);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        myDataService = new MyDataService();
    }

    private void newThread() {
        mSharedPreferences = getSharedPreferences("firstSharedPreferences", 0);
        final boolean first = mSharedPreferences.getBoolean("first", true);//不存在则默认为true
        final boolean initialize = mSharedPreferences.getBoolean("initialize", true);//不存在则默认为true
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Message message = new Message();
                    if (first) {
                        message.what = 0;
                    } else {
                        if (initialize) {
                            message.what = 1;
                        } else {
                            message.what = 2;
                        }
                    }
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

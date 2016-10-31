package com.tz.healthdiary.sqlite;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by anzhuo on 2016/10/9.
 * 后台服务类
 * 主要作用为在后台调用数据库工具类的读写数据操作方法
 * 并缓存数据
 * 随时供调用
 */

public class MyDataService extends Service {

    HealthData mHealthData = new HealthData();
    private static List<String> mList;
    private static List<String> nList;

    public List<String> getmList() {
        return mList;
    }

    public void setmList(List<String> mList) {
        this.mList = mList;
        //Log.i("TZ", "this"+this.mList);
    }

    public List<String> getnList() {
        return nList;
    }

    public void setnList(List<String> nList) {
        this.nList = nList;
        //Log.i("TZ", "this"+this.nList);
    }

    @Override//1
    public void onCreate() {
        super.onCreate();
        Log.i("TZ", "Bind——onCreate");

    }

    @Override//2
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override//5
    public void onDestroy() {
        super.onDestroy();
        Log.i("TZ", "Bind——onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void initData(){
        mHealthData.ifDataNull();
        if (mList == null && nList == null) {
            mList = mHealthData.getmList();
            nList = mHealthData.getnList();
        }
    }

    public void updataData(){
        mHealthData.alterData(mList,nList);
    }
}

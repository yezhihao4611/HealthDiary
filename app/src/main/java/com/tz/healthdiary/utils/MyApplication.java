package com.tz.healthdiary.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by 西野七濑 on 2016/10/18.
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}

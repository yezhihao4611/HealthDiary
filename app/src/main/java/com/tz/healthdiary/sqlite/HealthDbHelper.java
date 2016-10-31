package com.tz.healthdiary.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anzhuo on 2016/9/27.
 * 数据库类
 */

public class HealthDbHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "healthData.db";//文件名
    public static String TABLE_NAME = "healthDataTb";//表名
    public static String CREATE_TABLE = "create table if not exists " + TABLE_NAME + "(_id integer primary key autoincrement, lately varchar(64), time varchar(64), weight varchar(64), stature varchar(64), age varchar(64), bmi varchar(64), point varchar(64))";
    public static int TABLE_VERSION = 1;

    public HealthDbHelper(Context context) {
        super(context, DB_NAME, null, TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

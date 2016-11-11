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
    public static String CREATE_TABLE = "create table if not exists " + TABLE_NAME + "(_id integer primary key autoincrement, name varchar(64), year varchar(64), month varchar(64), day varchar(64), age varchar(64), sex varchar(64), meter varchar(64), cm varchar(64), kg varchar(64), g varchar(64), waistline varchar(64), point varchar(64), y varchar(64), m varchar(64), d varchar(64), newKg varchar(64), newG varchar(64), bmi varchar(64))";
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

    /**
     * 删除数据库
     *
     * @param context
     * @return
     */
    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DB_NAME);
    }
}


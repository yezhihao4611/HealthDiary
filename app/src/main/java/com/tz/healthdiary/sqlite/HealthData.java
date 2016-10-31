package com.tz.healthdiary.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tz.healthdiary.utils.MyApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by anzhuo on 2016/9/27.
 * 数据库工具类
 * 包含对数据库的初始化和读写操作
 * 随时供Service调用
 */

public class HealthData {

    private final String[] TITLE_TRUE = {"头条", "财经", "游戏", "电影", "娱乐", "军事",
            "手机", "体育", "科技", "汽车"};
    private final String[] TITLE_FALSE = {"笑话", "游戏", "时尚", "情感", "精选", "电台",
            "数码", "NBA", "移动", "彩票", "教育", "论坛", "旅游", "博客", "社会", "家居", "暴雪",
            "亲子", "CBA", "足球", "房产"};

    private ContentValues contentValues;
    private HealthDbHelper mHealthDbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Date date;
    private SimpleDateFormat simpleDateFormat;
    private String time;

    private final String TRUE = "true";
    private final String FALSE = "false";

    /**
     * ③根据TitleData.getmList()和TitleData.getnList();调用
     *
     * @return
     */
    private List<String> mList = new ArrayList<>();
    private List<String> nList = new ArrayList<>();

    public List<String> getmList() {
        return mList;
    }

    public List<String> getnList() {
        return nList;
    }

    /**
     * ②读取信息
     * 根据point读取后分组
     * 分别获得mList和nList
     */
    public void readData() {
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        sqLiteDatabase = mHealthDbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(HealthDbHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String mPoint = cursor.getString(cursor.getColumnIndex("point"));
                if (mPoint.equals("true")) {
                    String mTitle = cursor.getString(cursor.getColumnIndex("title"));
                    mList.add(mTitle);
                } else {
                    String nTitle = cursor.getString(cursor.getColumnIndex("title"));
                    nList.add(nTitle);
                }
            }
        }
        Log.i("TZ", "readData()：读取完毕");
        Log.i("TZ", "readData()：mList:" + mList);
        Log.i("TZ", "readData()：nList:" + nList);
    }

    /**
     * ④修改数据库信息
     * 根据传入的两个表，遍历修改（title）
     * 分别修改位置（number），M&N（point），修改时间（newTime）
     */
    public void alterData(List<String> mList_s, List<String> nList_s) {
        contentValues = new ContentValues();
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        sqLiteDatabase = mHealthDbHelper.getWritableDatabase();

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        date = new Date();
        time = simpleDateFormat.format(date);
        for (int i = 0; i < mList_s.size(); i++) {
            date = new Date();
            time = simpleDateFormat.format(date);

            contentValues.put("number", i);
            contentValues.put("point", TRUE);
            contentValues.put("newTime", time);

            String where = "title=?";
            String[] title = {mList_s.get(i)};
            sqLiteDatabase.update(HealthDbHelper.TABLE_NAME, contentValues, where, title);
        }
        for (int i = 0; i < nList_s.size(); i++) {
            date = new Date();
            time = simpleDateFormat.format(date);

            contentValues.put("number", i);
            contentValues.put("point", FALSE);
            contentValues.put("newTime", time);

            String where = "title=?";
            String[] title = {nList_s.get(i)};
            sqLiteDatabase.update(HealthDbHelper.TABLE_NAME, contentValues, where, title);
        }
    }

    /**
     * ①数据库初始化
     */
    public void initData() {
        contentValues = new ContentValues();
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        sqLiteDatabase = mHealthDbHelper.getWritableDatabase();

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

        /*for (int i = 0; i < TITLE_TRUE.length; i++) {
            date = new Date();
            time = simpleDateFormat.format(date);

            contentValues.put("title", TITLE_TRUE[i]);
            contentValues.put("content", "");
            contentValues.put("number", i);
            contentValues.put("point", TRUE);
            contentValues.put("time", time);
            contentValues.put("newTime", time);
            sqLiteDatabase.insert(HealthDbHelper.TABLE_NAME, null, contentValues);
        }
        for (int i = 0; i < TITLE_FALSE.length; i++) {
            date = new Date();
            time = simpleDateFormat.format(date);

            contentValues.put("title", TITLE_FALSE[i]);
            contentValues.put("content", "");
            contentValues.put("number", i);
            contentValues.put("point", FALSE);
            contentValues.put("time", time);
            contentValues.put("newTime", time);
            sqLiteDatabase.insert(HealthDbHelper.TABLE_NAME, null, contentValues);
            Log.i("TZ", "initData()：加载完毕");
        }*/
    }

    public void ifDataNull() {
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        sqLiteDatabase = mHealthDbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(HealthDbHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String mTitle = cursor.getString(cursor.getColumnIndex("title"));
                mList.add(mTitle);
            }
        }

        if (mList.size() != 0) {
            Log.i("TZ", "ifDataNull()：有数据，不用创建");
            mList = new ArrayList<>();
        } else {
            Log.i("TZ", "ifDataNull()：没有数据，数据库正在加载数据");
            initData();
        }
        Log.i("TZ", "ifDataNull()：正在读取数据");
        readData();
    }
}

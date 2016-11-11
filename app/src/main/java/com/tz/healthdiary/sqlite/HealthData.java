package com.tz.healthdiary.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tz.healthdiary.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anzhuo on 2016/9/27.
 * 数据库工具类
 * 包含对数据库的初始化和读写操作
 * 随时供Service调用
 */

public class HealthData {

    //生日
    private int Year;
    private int Month;
    private int Day;
    //性别,男1，女0
    private int Sex;
    //年龄
    private int Age;
    //体重
    private int Kg;
    private int G;
    //身高
    private int Meter;
    private int Cm;

    //表标志位
    private int point;
    private int maxPoint;
    //日期
    private int y;
    private int m;
    private int d;
    //每日体重
    private int newKg;
    private int newG;
    //BMI指数
    private double BMI;
    //腰围
    private int waistline;

    public int getMeter() {
        return Meter;
    }

    public void setMeter(int meter) {
        Meter = meter;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getNewG() {
        return newG;
    }

    public void setNewG(int newG) {
        this.newG = newG;
    }

    public int getNewKg() {
        return newKg;
    }

    public void setNewKg(int newKg) {
        this.newKg = newKg;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public int getWaistline() {
        return waistline;
    }

    public void setWaistline(int waistline) {
        this.waistline = waistline;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public int getCm() {
        return Cm;
    }

    public void setCm(int cm) {
        Cm = cm;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getKg() {
        return Kg;
    }

    public void setKg(int kg) {
        Kg = kg;
    }

    private List<List<Integer>> listOnes;
    private List<List<Integer>> listTwos;
    private List<List<Integer>> listFours;
    private List<Integer> listOne;
    private List<Integer> listTwo;
    private List<Integer> listFour;

    public List<Integer> getListOne() {
        return listOne;
    }

    public List<List<Integer>> getListOnes() {
        return listOnes;
    }

    public List<Integer> getListTwo() {
        return listTwo;
    }

    public List<List<Integer>> getListTwos() {
        return listTwos;
    }

    public List<List<Integer>> getListFours() {
        return listFours;
    }

    public List<Integer> getListFour() {
        return listFour;
    }

    private ContentValues mContentValues;
    private HealthDbHelper mHealthDbHelper;
    private SQLiteDatabase mSQLiteDatabase;

    /**
     * 储存原始数据
     */
    public void reserveData() {
        Log.i("TZ", "reserveData()：开始储存原始数据");
        mContentValues = new ContentValues();
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        mSQLiteDatabase = mHealthDbHelper.getWritableDatabase();
        Log.i("TZ", "reserveData():" + "Sex:" + Sex + "Year:" + Year + "Month:" + Month + "Day:" + Day
                + "Age:" + Age + "Kg:" + Kg + "G:" + G + "Meter:" + Meter + "Cm:" + Cm
                + "waistline:" + waistline);
        mContentValues.put("name", "基本数据");
        mContentValues.put("year", Year);
        mContentValues.put("month", Month);
        mContentValues.put("day", Day);
        mContentValues.put("age", Age);

        mContentValues.put("sex", Sex);

        mContentValues.put("meter", Meter);
        mContentValues.put("cm", Cm);

        mContentValues.put("kg", Kg);
        mContentValues.put("g", G);

        mContentValues.put("waistline", waistline);

        mSQLiteDatabase.insert(HealthDbHelper.TABLE_NAME, null, mContentValues);

        Log.i("TZ", "reserveData()：原始数据储存完毕");
    }

    /**
     * 储存第一次数据
     */
    public void reserveFirstData() {
        Log.i("TZ", "reserveNewData()：开始储存第一次数据");
        mContentValues = new ContentValues();
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        mSQLiteDatabase = mHealthDbHelper.getWritableDatabase();

        mContentValues.put("name", "每日数据");

        mContentValues.put("point", point);

        mContentValues.put("y", y);
        mContentValues.put("m", m);
        mContentValues.put("d", d);

        mContentValues.put("newKg", newKg);
        mContentValues.put("newG", newG);

        mContentValues.put("bmi", BMI);

        mSQLiteDatabase.insert(HealthDbHelper.TABLE_NAME, null, mContentValues);

        Log.i("TZ", "reserveNewData()：第一次数据储存完毕");
    }

    /**
     * 储存每日数据
     */
    public void reserveNewData() {
        Log.i("TZ", "reserveNewData()：开始储存每日数据");
        mContentValues = new ContentValues();
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        mSQLiteDatabase = mHealthDbHelper.getWritableDatabase();

        mContentValues.put("name", "每日数据");

        mContentValues.put("point", maxPoint + 1);

        mContentValues.put("y", y);
        mContentValues.put("m", m);
        mContentValues.put("d", d);

        mContentValues.put("newKg", newKg);
        mContentValues.put("newG", newG);

        mContentValues.put("bmi", BMI);

        mSQLiteDatabase.insert(HealthDbHelper.TABLE_NAME, null, mContentValues);

        Log.i("TZ", "reserveNewData()：每日数据储存完毕");
        UpDatas();
    }

    /**
     * 储存每日数据后更新point和maxPoint
     */
    public void UpDatas() {
        Log.i("TZ", "UpDatas：开始更新point和maxPoint");
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        mSQLiteDatabase = mHealthDbHelper.getReadableDatabase();
        Cursor cursor = mSQLiteDatabase.query(HealthDbHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToLast()) {
                point = Integer.parseInt(cursor.getString(cursor.getColumnIndex("point")));
                maxPoint = point;
                Log.i("TZ", "point:" + point);
            }
        }
        Log.i("TZ", "point和maxPoint更新完毕：" + "point:" + point + "maxPoint:" + maxPoint);
        readWeekData();
    }

    /**
     * 储存每日数据后更新数据
     */
    public void UpData() {
        Log.i("TZ", "UpData()：开始更新数据");
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        mSQLiteDatabase = mHealthDbHelper.getReadableDatabase();
        Cursor cursor = mSQLiteDatabase.query(HealthDbHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToLast()) {
                point = Integer.parseInt(cursor.getString(cursor.getColumnIndex("point")));
                maxPoint = point;
                Log.i("TZ", "maxPoint:" + maxPoint);
            }
        }
        readWeekData();
        readTwoWeekData();
        readFourWeekData();
        Log.i("TZ", "UpData()：数据更新完毕");
    }

    /**
     * 读取原始数据
     */
    public void readData() {
        Log.i("TZ", "readData()：开始读取原始数据");
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        mSQLiteDatabase = mHealthDbHelper.getReadableDatabase();
        Cursor cursor = mSQLiteDatabase.query(HealthDbHelper.TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Year = Integer.parseInt(cursor.getString(cursor.getColumnIndex("year")));
            Month = Integer.parseInt(cursor.getString(cursor.getColumnIndex("month")));
            Day = Integer.parseInt(cursor.getString(cursor.getColumnIndex("day")));

            Age = Integer.parseInt(cursor.getString(cursor.getColumnIndex("age")));

            Sex = Integer.parseInt(cursor.getString(cursor.getColumnIndex("sex")));

            Kg = Integer.parseInt(cursor.getString(cursor.getColumnIndex("kg")));
            G = Integer.parseInt(cursor.getString(cursor.getColumnIndex("g")));

            Meter = Integer.parseInt(cursor.getString(cursor.getColumnIndex("meter")));
            Cm = Integer.parseInt(cursor.getString(cursor.getColumnIndex("cm")));

            waistline = Integer.parseInt(cursor.getString(cursor.getColumnIndex("waistline")));
            cursor.close();
            Log.i("TZ", "Sex:" + Sex + "Year:" + Year + "Month:" + Month + "Day:" + Day
                    + "Age:" + Age + "Kg:" + Kg + "G:" + G + "Meter:" + Meter + "Cm:" + Cm
                    + "waistline:" + waistline);
            Log.i("TZ", "readData()：原始数据读取完毕");
        }
    }

    /**
     * 读取最近1日数据
     */
    public void readNewData() {
        Log.i("TZ", "readNewData()：开始读取最近1日数据");
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        mSQLiteDatabase = mHealthDbHelper.getReadableDatabase();
        Cursor cursor = mSQLiteDatabase.query(HealthDbHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToLast()) {
                point = Integer.parseInt(cursor.getString(cursor.getColumnIndex("point")));
                maxPoint = point;

                y = Integer.parseInt(cursor.getString(cursor.getColumnIndex("y")));
                m = Integer.parseInt(cursor.getString(cursor.getColumnIndex("m")));
                d = Integer.parseInt(cursor.getString(cursor.getColumnIndex("d")));

                newKg = Integer.parseInt(cursor.getString(cursor.getColumnIndex("newKg")));
                newG = Integer.parseInt(cursor.getString(cursor.getColumnIndex("newG")));

                BMI = Float.parseFloat(cursor.getString(cursor.getColumnIndex("bmi")));
            }
            Log.i("TZ", "point:" + point + "maxPoint:" + maxPoint + "y:" + y + "m:" + m + "d:" + d + "newKg:" + newKg + "newG:" + newG
                    + "BMI:" + BMI);
            Log.i("TZ", "readNewData()：最近1日数据读取完毕");
        }
    }

    /**
     * 读取7次数据
     */
    public void readWeekData() {
        Log.i("TZ", "readWeekData：开始读取7次数据");
        listOne = null;
        listOnes = null;
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        SQLiteDatabase mSQLiteDatabase = mHealthDbHelper.getReadableDatabase();
        Cursor cursor = mSQLiteDatabase.query(HealthDbHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            listOnes = new ArrayList<>();
            cursor.move(1);
            while (cursor.moveToNext()) {
                int p = Integer.parseInt(cursor.getString(cursor.getColumnIndex("point")));
                if (p > maxPoint - 7) {
                    listOne = new ArrayList<>();

                    int y = Integer.parseInt(cursor.getString(cursor.getColumnIndex("y")));
                    int m = Integer.parseInt(cursor.getString(cursor.getColumnIndex("m")));
                    int d = Integer.parseInt(cursor.getString(cursor.getColumnIndex("d")));

                    int newKg = Integer.parseInt(cursor.getString(cursor.getColumnIndex("newKg")));
                    int newG = Integer.parseInt(cursor.getString(cursor.getColumnIndex("newG")));

                    listOne.add(p);
                    listOne.add(y);
                    listOne.add(m);
                    listOne.add(d);
                    listOne.add(newKg);
                    listOne.add(newG);
                    //Log.i("TZ", "该数据可用:" + "point=" + p);
                } else {
                    //Log.i("TZ", "该数据不可用:" + "point=" + p);
                }
                if (listOne != null) {
                    listOnes.add(listOne);
                }
            }
            Log.i("TZ", "readWeekData：listOne:" + listOne);
            Log.i("TZ", "readWeekData：listOnes:" + listOnes);
            Log.i("TZ", "readWeekData：7次数据读取完毕");
        }
    }

    /**
     * 读取14次数据
     */
    public void readTwoWeekData() {
        Log.i("TZ", "readTwoWeekData：开始读取14日数据");
        listTwo = null;
        listTwos = null;
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        SQLiteDatabase mSQLiteDatabase = mHealthDbHelper.getReadableDatabase();
        Cursor cursor = mSQLiteDatabase.query(HealthDbHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            listTwos = new ArrayList<>();
            cursor.move(1);
            while (cursor.moveToNext()) {
                int p = Integer.parseInt(cursor.getString(cursor.getColumnIndex("point")));
                if (p > maxPoint - 14) {
                    listTwo = new ArrayList<>();

                    int y = Integer.parseInt(cursor.getString(cursor.getColumnIndex("y")));
                    int m = Integer.parseInt(cursor.getString(cursor.getColumnIndex("m")));
                    int d = Integer.parseInt(cursor.getString(cursor.getColumnIndex("d")));

                    int newKg = Integer.parseInt(cursor.getString(cursor.getColumnIndex("newKg")));
                    int newG = Integer.parseInt(cursor.getString(cursor.getColumnIndex("newG")));

                    listTwo.add(p);
                    listTwo.add(y);
                    listTwo.add(m);
                    listTwo.add(d);
                    listTwo.add(newKg);
                    listTwo.add(newG);
                    //Log.i("TZ", "该数据可用:" + "point=" + p);
                } else {
                    //Log.i("TZ", "该数据不可用:" + "point=" + p);
                }
                if (listTwo != null) {
                    listTwos.add(listTwo);
                }
            }
            Log.i("TZ", "readTwoWeekData：listTwo:" + listTwo);
            Log.i("TZ", "readTwoWeekData：listTwos:" + listTwos);
            Log.i("TZ", "readTwoWeekData：14日数据读取完毕");
        }
    }

    /**
     * 读取28次数据
     */
    public void readFourWeekData() {
        Log.i("TZ", "readFourWeekData：开始读取28日数据");
        listFour = null;
        listFours = null;
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        SQLiteDatabase mSQLiteDatabase = mHealthDbHelper.getReadableDatabase();
        Cursor cursor = mSQLiteDatabase.query(HealthDbHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            listFours = new ArrayList<>();
            cursor.move(1);
            while (cursor.moveToNext()) {
                int p = Integer.parseInt(cursor.getString(cursor.getColumnIndex("point")));
                if (p > maxPoint - 28) {
                    listFour = new ArrayList<>();

                    int y = Integer.parseInt(cursor.getString(cursor.getColumnIndex("y")));
                    int m = Integer.parseInt(cursor.getString(cursor.getColumnIndex("m")));
                    int d = Integer.parseInt(cursor.getString(cursor.getColumnIndex("d")));

                    int newKg = Integer.parseInt(cursor.getString(cursor.getColumnIndex("newKg")));
                    int newG = Integer.parseInt(cursor.getString(cursor.getColumnIndex("newG")));

                    listFour.add(p);
                    listFour.add(y);
                    listFour.add(m);
                    listFour.add(d);
                    listFour.add(newKg);
                    listFour.add(newG);
                    //Log.i("TZ", "该数据可用:" + "point=" + p);
                } else {
                    //Log.i("TZ", "该数据不可用:" + "point=" + p);
                }
                if (listFour != null) {
                    listFours.add(listFour);
                }
            }
            Log.i("TZ", "readFourWeekData：listFour:" + listFour);
            Log.i("TZ", "readFourWeekData：listFours:" + listFours);
            Log.i("TZ", "readFourWeekData：28日数据读取完毕");
        }
    }

    /**
     * 修改最近一次体重
     */
    public void alterWeightData() {
        Log.i("TZ", "alterWeightData：开始修改最近一次体重");
        mContentValues = new ContentValues();
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        mSQLiteDatabase = mHealthDbHelper.getWritableDatabase();

        mContentValues.put("newKg", newKg);
        mContentValues.put("newG", newG);
        mContentValues.put("bmi", BMI);

        String where = "point=?";
        String[] point = {maxPoint + ""};
        mSQLiteDatabase.update(HealthDbHelper.TABLE_NAME, mContentValues, where, point);
        Log.i("TZ", "alterWeightData：最近一次体重修改完毕");
    }

    /**
     * 修改腰围
     */
    public void alterWaistlineData() {
        Log.i("TZ", "alterWaistlineData：开始修改腰围");
        mContentValues = new ContentValues();
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        mSQLiteDatabase = mHealthDbHelper.getWritableDatabase();

        mContentValues.put("waistline", waistline);

        String where = "name=?";
        String[] point = {"基本数据"};
        mSQLiteDatabase.update(HealthDbHelper.TABLE_NAME, mContentValues, where, point);
        Log.i("TZ", "alterWaistlineData：修改腰围完毕");
    }

    /**
     * 修改身高
     */
    public void alterStatureData() {
        Log.i("TZ", "alterStatureData：开始修改身高");
        mContentValues = new ContentValues();
        mHealthDbHelper = new HealthDbHelper(MyApplication.getContext());
        mSQLiteDatabase = mHealthDbHelper.getWritableDatabase();

        mContentValues.put("meter", Meter);
        mContentValues.put("cm", Cm);

        String where = "name=?";
        String[] point = {"基本数据"};
        mSQLiteDatabase.update(HealthDbHelper.TABLE_NAME, mContentValues, where, point);
        Log.i("TZ", "alterStatureData：修改身高完毕");
    }

    /**
     * 从数据库读取全部数据
     */
    public void startDataFromSql() {
        Log.i("TZ", "startDataFromSql：正在读取数据...");
        readData();//读取基本数据，10个
        readNewData();//读取最近一次数据，6个+1个
        readWeekData();//读取最近7次数据
        readTwoWeekData();//读取最近14次数据
        readFourWeekData();//读取最近28次数据
        Log.i("TZ", "startDataFromSql：数据读取完毕！");
    }

    /**
     * 删除所有数据
     */
    public void deleteAllData() {
        mHealthDbHelper.deleteDatabase(MyApplication.getContext());
    }
}

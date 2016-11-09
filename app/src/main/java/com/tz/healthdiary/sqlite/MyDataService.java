package com.tz.healthdiary.sqlite;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by anzhuo on 2016/10/9.
 * 后台服务类
 * 主要作用为在后台调用数据库工具类的读写数据操作方法
 * 并缓存数据
 * 随时供调用
 */

public class MyDataService extends Service {

    private Calendar calendar = Calendar.getInstance();

    //生日
    private int Year;
    private int Month;
    private int Day;
    //性别
    private int Sex;
    //年龄
    private int Age;
    //体重
    private int Kg;
    private int G;
    //身高
    private int Meter;
    private int Cm;

    //日
    private int point;
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
    //整体体重
    private int weight;
    //整体身高
    private int height;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getMeter() {
        return Meter;
    }

    public void setMeter(int meter) {
        Meter = meter;
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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
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

    HealthData mHealthData = new HealthData();

    private List<List<Integer>> listOnes;
    private List<List<Integer>> listTwos;
    private List<List<Integer>> listFours;
    private List<Integer> listOne;
    private List<Integer> listTwo;
    private List<Integer> listFour;

    public List<Integer> getListFour() {
        return listFour;
    }

    public void setListFour(List<Integer> listFour) {
        this.listFour = listFour;
    }

    public List<List<Integer>> getListFours() {
        return listFours;
    }

    public void setListFours(List<List<Integer>> listFours) {
        this.listFours = listFours;
    }

    public List<Integer> getListOne() {
        return listOne;
    }

    public void setListOne(List<Integer> listOne) {
        this.listOne = listOne;
    }

    public List<List<Integer>> getListOnes() {
        return listOnes;
    }

    public void setListOnes(List<List<Integer>> listOnes) {
        this.listOnes = listOnes;
    }

    public List<Integer> getListTwo() {
        return listTwo;
    }

    public void setListTwo(List<Integer> listTwo) {
        this.listTwo = listTwo;
    }

    public List<List<Integer>> getListTwos() {
        return listTwos;
    }

    public void setListTwos(List<List<Integer>> listTwos) {
        this.listTwos = listTwos;
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

    //获取数据
    public void initData() {
        mHealthData.startDataFromSql();
        //10
        Year = mHealthData.getYear();
        Month = mHealthData.getMonth();
        Day = mHealthData.getDay();
        Age = mHealthData.getAge();
        Sex = mHealthData.getSex();
        Kg = mHealthData.getKg();
        G = mHealthData.getG();
        Meter = mHealthData.getMeter();
        Cm = mHealthData.getCm();
        waistline = mHealthData.getWaistline();
        //6+1
        point = mHealthData.getPoint();
        y = mHealthData.getY();
        m = mHealthData.getM();
        d = mHealthData.getD();
        newKg = mHealthData.getNewKg();
        newG = mHealthData.getNewG();
        BMI = mHealthData.getBMI();

        weight= (int) (Kg+G*0.1);
        height=Meter*100+Cm;

        listOne = mHealthData.getListOne();
        listOnes = mHealthData.getListOnes();

        /*listTwo = mHealthData.getListOne();
        listTwos = mHealthData.getListTwos();

        listFour = mHealthData.getListOne();
        listFours = mHealthData.getListFours();*/

        Log.i("TZ", "获取数据10:" + "Sex:" + Sex + "Year:" + Year + "Month:" + Month + "Day:" + Day
                + "Age:" + Age + "Kg:" + Kg + "G:" + G + "Meter:" + Meter + "Cm:" + Cm
                + "Waistline:" + waistline);
        Log.i("TZ", "listOnes:" + listOnes);
        Log.i("TZ", "获取数据7:" + "point:" + point + "y:" + y + "m:" + m + "d:" + d
                + "newKg:" + newKg + "newG:" + newG + "BMI:" + BMI);
    }

    //初始化基本数据,添加基本信息，只在初次运行app的时候调用
    public void firstData() {
        Log.i("TZ", "Sex:" + Sex + "Year:" + Year + "Month:" + Month + "Day:" + Day
                + "Age:" + Age + "Kg:" + Kg + "G:" + G + "Meter:" + Meter + "Cm:" + Cm
                + "waistline:" + waistline);
        mHealthData.setSex(Sex);
        mHealthData.setYear(Year);
        mHealthData.setMonth(Month);
        mHealthData.setDay(Day);
        mHealthData.setAge(Age);
        mHealthData.setKg(Kg);
        mHealthData.setG(G);
        mHealthData.setMeter(Meter);
        mHealthData.setCm(Cm);
        mHealthData.setWaistline(waistline);

        mHealthData.reserveData();

        point = 1;
        int years = calendar.get(Calendar.YEAR);
        int months = calendar.get(Calendar.MONTH) + 1;
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        y = years;
        m = months;
        d = days;
        newKg = Kg;
        newG = G;
        DecimalFormat df = new DecimalFormat("0.00 ");
        double a = ((double) newKg + ((double) newG / 10)) / (((double) Meter + ((double) Cm / 100)) * ((double) Meter + ((double) Cm / 100)));
        BMI = Double.parseDouble(df.format(a));
        Log.i("TZ", "everydayAddData():" + "y:" + y + "m:" + m + "d:" + d + "newKg:" + newKg + "newG:" + newG
                + "BMI:" + BMI);
        mHealthData.setPoint(point);
        mHealthData.setY(y);
        mHealthData.setM(m);
        mHealthData.setD(d);
        mHealthData.setNewKg(newKg);
        mHealthData.setNewG(newG);
        mHealthData.setBMI(BMI);
        mHealthData.reserveFirstData();
    }

    //添加每日数据
    public void everydayAddData() {
        Log.i("TZ", "everydayAddData():" + "y:" + y + "m:" + m + "d:" + d + "newKg:" + newKg + "newG:" + newG
                + "BMI:" + BMI);
        mHealthData.setPoint(point);
        mHealthData.setY(y);
        mHealthData.setM(m);
        mHealthData.setD(d);
        mHealthData.setNewKg(newKg);
        mHealthData.setNewG(newG);
        DecimalFormat df = new DecimalFormat("0.00 ");
        double a = ((double) newKg + ((double) newG / 10)) / (((double) Meter + ((double) Cm / 100)) * ((double) Meter + ((double) Cm / 100)));
        BMI = Double.parseDouble(df.format(a));
        mHealthData.setBMI(BMI);
        mHealthData.reserveNewData();
        mHealthData.UpData();
    }

    //修改最近一次数据
    public void updataData() {
        mHealthData.setNewKg(newKg);
        mHealthData.setNewG(newG);
        DecimalFormat df = new DecimalFormat("0.00 ");
        double a = ((double) newKg + ((double) newG / 10)) / (((double) Meter + ((double) Cm / 100)) * ((double) Meter + ((double) Cm / 100)));
        BMI = Double.parseDouble(df.format(a));
        mHealthData.setBMI(BMI);
        mHealthData.alterWeightData();
    }

    //修改腰围
    public void alterWaistlineData() {
        mHealthData.setWaistline(waistline);
        mHealthData.alterWaistlineData();
        Log.i("TZ",waistline+"");
    }
}

package com.tz.healthdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tz.healthdiary.custom.CircleIndicator;
import com.tz.healthdiary.custom.WheelView;
import com.tz.healthdiary.sqlite.MyDataService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static com.tz.healthdiary.R.id.bt_birthday_next;
import static com.tz.healthdiary.R.id.bt_birthday_previous;
import static com.tz.healthdiary.R.id.bt_start;
import static com.tz.healthdiary.R.id.bt_stature_next;
import static com.tz.healthdiary.R.id.bt_stature_previous;
import static com.tz.healthdiary.R.id.bt_weight_next;
import static com.tz.healthdiary.R.id.bt_weight_previous;
import static com.tz.healthdiary.R.id.civ_boy;
import static com.tz.healthdiary.R.id.civ_girl;
import static com.tz.healthdiary.StartAnimaActivity.mSharedPreferences;

/**
 * Created by anzhuo on 2016/10/31.
 */

public class InitializeActivity extends AppCompatActivity {

    private List<View> viewList;
    private List<View> viewLists;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private CircleIndicator circleIndicator;

    MyDataService mMyDataService = StartAnimaActivity.myDataService;

    private View mViewOne;
    private View mViewTwo;
    private View mViewThree;
    private View mViewFour;
    private View mViewFive;

    private WheelView wvYear;
    private WheelView wvMonth;
    private WheelView wvDay;
    private WheelView wvkg;
    private WheelView wvg;
    private WheelView wvm;
    private WheelView wvcm;

    private static final String[] YearList = new String[]{"1930", "1931", "1932", "1933", "1934",
            "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945",
            "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956",
            "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967",
            "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978",
            "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
            "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000",
            "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011",
            "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022",
            "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033",
            "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044",
            "2045", "2046", "2047", "2048", "2049", "2050"};

    private static final String[] MonthList = new String[]{"1", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12"};

    private static final String[] BigDayList = new String[]{"1", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
            "24", "25", "26", "27", "28", "29", "30", "31"};

    private static final String[] KgList = new String[]{"25", "26", "27", "28", "29", "30", "31",
            "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45",
            "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
            "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73",
            "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87",
            "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101",
            "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113",
            "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125",
            "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137",
            "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149",
            "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161",
            "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173",
            "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185",
            "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197",
            "198", "199", "200", "201", "202", "203", "204", "205", "206", "207", "208", "209",
            "210", "211", "212", "213", "214", "215", "216", "217", "218", "219", "220", "221",
            "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233",
            "234", "235", "236", "237", "238", "239", "240", "241", "242", "243", "244", "245",
            "246", "247", "248", "249", "250", "251", "252", "253", "254", "255", "256", "257",
            "258", "259", "260", "261", "262", "263", "264", "265", "266", "267", "268", "269",
            "270", "271", "272", "273", "274", "275", "276", "277", "278", "279", "280", "281",
            "282", "283", "284", "285", "286", "287", "288", "289", "290", "291", "292", "293",
            "294", "295", "296", "297", "298", "299", "300"};

    private static final String[] GList = new String[]{"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9"};

    private static final String[] MList = new String[]{"0", "1", "2"};

    private static final String[] CmList = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
            "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37",
            "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
            "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65",
            "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
            "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93",
            "94", "95", "96", "97", "98", "99"};

    private static final int NUM = 1;

    private int sex;

    private int Year = 1992;
    private int Month = 1;
    private int Day = 1;

    private int Age;

    private int Kg = 60;
    private int G = 0;

    private int Meter = 1;
    private int Cm = 70;

    private int YearX = 63;
    private int MonthX = 1;
    private int DayX = 1;

    private int KgX = 36;
    private int GX = 1;

    private int MeterX = 2;
    private int CmX = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialize);

        initView();

        initData();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        circleIndicator.setViewPager(viewPager);
    }

    private void initView() {
        mViewOne = LayoutInflater.from(InitializeActivity.this).inflate(R.layout.activity_one, null);
        mViewTwo = LayoutInflater.from(InitializeActivity.this).inflate(R.layout.activity_two, null);
        mViewThree = LayoutInflater.from(InitializeActivity.this).inflate(R.layout.activity_three, null);
        //控件初始化
        wvYear = (WheelView) mViewThree.findViewById(R.id.wv_year);
        //NUM “item” NUM，item的position是从NUM开始计算的
        wvYear.setOffset(NUM);
        //添加item的内容
        wvYear.setItems(Arrays.asList(YearList));
        //设置控件选中的位置

        wvMonth = (WheelView) mViewThree.findViewById(R.id.wv_month);
        wvMonth.setOffset(NUM);
        wvMonth.setItems(Arrays.asList(MonthList));

        wvDay = (WheelView) mViewThree.findViewById(R.id.wv_day);
        wvDay.setOffset(NUM);
        wvDay.setItems(Arrays.asList(BigDayList));

        mViewFour = LayoutInflater.from(InitializeActivity.this).inflate(R.layout.activity_four, null);
        wvkg = (WheelView) mViewFour.findViewById(R.id.wv_kg);
        wvkg.setOffset(NUM);
        wvkg.setItems(Arrays.asList(KgList));

        wvg = (WheelView) mViewFour.findViewById(R.id.wv_g);
        wvg.setOffset(NUM);
        wvg.setItems(Arrays.asList(GList));


        mViewFive = LayoutInflater.from(InitializeActivity.this).inflate(R.layout.activity_five, null);
        wvm = (WheelView) mViewFive.findViewById(R.id.wv_m);
        wvm.setOffset(NUM);
        wvm.setItems(Arrays.asList(MList));

        wvcm = (WheelView) mViewFive.findViewById(R.id.wv_cm);
        wvcm.setOffset(NUM);
        wvcm.setItems(Arrays.asList(CmList));

        viewLists = new ArrayList<View>();

        viewLists.add(mViewOne);
        viewLists.add(mViewTwo);
        viewLists.add(mViewThree);
        viewLists.add(mViewFour);
        viewLists.add(mViewFive);

        viewList = new ArrayList<View>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            View view = viewLists.get(i);
            if (i > 0) {
                view.setBackgroundColor(0xff000000 | random.nextInt(0x00ffffff));
            }
            viewList.add(view);
        }
    }

    private void initData() {
        pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {

                return arg0 == arg1;
            }

            @Override
            public int getCount() {

                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));

            }

            @Override
            public int getItemPosition(Object object) {

                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return "title";
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));

                return viewList.get(position);
            }
        };

        wvYear.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                YearX = selectedIndex;
                Year = Integer.parseInt(item);
                //MyThread();
                Log.i("TZ", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        wvMonth.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                MonthX = selectedIndex;
                Month = Integer.parseInt(item);
                //MyThread();
                Log.i("TZ", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        wvDay.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                DayX = selectedIndex;
                Day = Integer.parseInt(item);
                Log.i("TZ", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });

        wvkg.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                KgX = selectedIndex;
                Kg = Integer.parseInt(item);
                Log.i("TZ", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        wvg.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                GX = selectedIndex;
                G = Integer.parseInt(item);
                Log.i("TZ", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });

        wvm.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                MeterX = selectedIndex;
                Meter = Integer.parseInt(item);
                Log.i("TZ", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        wvcm.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                CmX = selectedIndex;
                Cm = Integer.parseInt(item);
                Log.i("TZ", "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
    }

    public void doClick(View view) {
        switch (view.getId()) {
            case bt_start:
                //Toast.makeText(InitializeActivity.this, "开始吧", Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                break;
            case civ_boy:
                sex = 1;
                mMyDataService.setSex(sex);//1
                //Toast.makeText(InitializeActivity.this, "男孩", Toast.LENGTH_SHORT).show();
                wvYear.setSeletion(YearX - NUM);
                wvMonth.setSeletion(MonthX - NUM);
                wvDay.setSeletion(DayX - NUM);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                break;
            case civ_girl:
                sex = 0;
                mMyDataService.setSex(sex);//1
                //Toast.makeText(InitializeActivity.this, "女孩", Toast.LENGTH_SHORT).show();
                wvYear.setSeletion(YearX - NUM);
                wvMonth.setSeletion(MonthX - NUM);
                wvDay.setSeletion(DayX - NUM);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                break;
            case bt_birthday_previous:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);

                break;
            case bt_birthday_next:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                Log.i("TZ", "year:" + year + "month:" + month + "day:" + day);
                if ((month == Month & day >= Day) | month > Month) {
                    Age = year - Year;
                } else {
                    Age = year - Year - 1;
                }
                mMyDataService.setYear(Year);//2
                mMyDataService.setMonth(Month);//3
                mMyDataService.setDay(Day);//4
                mMyDataService.setAge(Age);//5
                //Toast.makeText(InitializeActivity.this, "生日", Toast.LENGTH_SHORT).show();
                wvkg.setSeletion(KgX - NUM);
                wvg.setSeletion(GX - NUM);
                if ((Year < year) | (Year == year & Month < month) | (Year == year & Month == month & Day < day)) {
                    //生日日期至少小于今天日期
                    if (((Year % 4 == 0) | (Year % 400 == 0)) & Month == 2) {
                        //闰年2月
                        if (Day <= 29) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                        } else {
                            Toast.makeText(InitializeActivity.this, "日期错误，请重新选择", Toast.LENGTH_SHORT).show();
                        }
                    } else if (Month == 2) {
                        //普通2月
                        if (Day <= 28) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                        } else {
                            Toast.makeText(InitializeActivity.this, "日期错误，请重新选择", Toast.LENGTH_SHORT).show();
                        }
                    } else if (Month == 4 || Month == 6 || Month == 9 || Month == 11) {
                        //小月
                        if (Day <= 30) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                        } else {
                            Toast.makeText(InitializeActivity.this, "日期错误，请重新选择", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //大月
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    }
                } else {
                    Toast.makeText(InitializeActivity.this, "您的出生日期超出范围，请检查后重新选择", Toast.LENGTH_SHORT).show();
                }
                break;
            case bt_weight_previous:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
                wvYear.setSeletion(YearX - NUM);
                wvMonth.setSeletion(MonthX - NUM);
                wvDay.setSeletion(DayX - NUM);
                break;
            case bt_weight_next:
                mMyDataService.setKg(Kg);//6
                mMyDataService.setG(G);//7
                //Toast.makeText(InitializeActivity.this, "体重", Toast.LENGTH_SHORT).show();
                wvm.setSeletion(MeterX - NUM);
                wvcm.setSeletion(CmX - NUM);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                break;
            case bt_stature_previous:
                wvkg.setSeletion(KgX - NUM);
                wvg.setSeletion(GX - NUM);
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
                break;
            case bt_stature_next:
                mMyDataService.setMeter(Meter);//8
                mMyDataService.setCm(Cm);//9
                mMyDataService.setWaistline(0);//10
                mMyDataService.firstData();//储存基本数据
                //Toast.makeText(InitializeActivity.this, "身高", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                mSharedPreferences = getSharedPreferences("firstSharedPreferences", 0);
                mSharedPreferences.edit().putBoolean("initialize", false).apply();
                finish();
                break;
            default:
                break;
        }
    }
}
package com.tz.healthdiary;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by anzhuo on 2016/10/20.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_base_layout);
    }
}

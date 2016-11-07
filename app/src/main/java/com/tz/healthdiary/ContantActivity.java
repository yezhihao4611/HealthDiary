package com.tz.healthdiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tz.healthdiary.fragment.NewsFragment;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/10/26.
 */

public class ContantActivity extends Activity {
    TextView contant;
    TextView from;
    TextView title;
    String mtitle;
    String mfrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contant_layout);
        contant= (TextView) findViewById(R.id.contant);
        from= (TextView) findViewById(R.id.from);
        title= (TextView) findViewById(R.id.title);

        Bmob.initialize(ContantActivity.this, "84f7828da3cc41850ebdd4ffa47c8fcb");
        Intent intent=getIntent();
        mtitle= (String) intent.getExtras().get("title");
        mfrom= (String) intent.getExtras().get("from");
        title.setText(mtitle);
        from.setText(mfrom);
        BmobQuery<NewsFragment.News>query=new BmobQuery<>();
        query.findObjects(new FindListener<NewsFragment.News>() {
            @Override
            public void done(List<NewsFragment.News> Mlist, BmobException e) {
                if (e == null) {
                    for (NewsFragment.News mlist : Mlist) {
                        if (mlist.getTitle().equals(mtitle)){
                            contant.setText(mlist.getContext());
                            title.setText(mlist.getTitle());
                            from.setText(mlist.getFrom());
                        }
                    }

                }
            }
        });


    }
}

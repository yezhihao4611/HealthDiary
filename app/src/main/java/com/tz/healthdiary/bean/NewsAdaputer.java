package com.tz.healthdiary.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tz.healthdiary.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/10/26.
 */

public class NewsAdaputer extends BaseAdapter {
    Context mcontext;
    List<NewsInfo> mlist;
    NewsInfo newsInfo;
    public  NewsAdaputer(Context context,List list){
        this.mcontext=context;
        this.mlist=list;
    }
    @Override
    public int getCount() {
        return mlist==null?0:mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.news_item,null);
            viewHolder.tv_from= (TextView) convertView.findViewById(R.id.tv_from);
            viewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.iv_image= (ImageView) convertView.findViewById(R.id.iv_image);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        newsInfo=mlist.get(position);
        viewHolder.tv_title.setText(newsInfo.getTitle());
        viewHolder.tv_from.setText(newsInfo.getFrom());
        Glide.with(mcontext).load(newsInfo.getImage()).asBitmap().into(viewHolder.iv_image);
        return convertView;
    }
    public class ViewHolder{
        private TextView tv_title;
        private TextView tv_from;
        private ImageView iv_image;
    }
}

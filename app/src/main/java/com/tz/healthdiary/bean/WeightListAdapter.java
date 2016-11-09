package com.tz.healthdiary.bean;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tz.healthdiary.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class WeightListAdapter extends BaseAdapter {
    Context mContext;
    WeightInfo weightInfo;
    List<WeightInfo> mList;

    public WeightListAdapter(Context context, List<WeightInfo> list) {
        this.mContext = context;
        this.mList = list;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_weight, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_date_year = (TextView) convertView.findViewById(R.id.tv_date_year);
            viewHolder.tv_date_month = (TextView) convertView.findViewById(R.id.tv_date_month);
            viewHolder.tv_date_day = (TextView) convertView.findViewById(R.id.tv_date_day);
            viewHolder.tv_weight = (TextView) convertView.findViewById(R.id.tv_weight);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.tv_date_year.setText(weightInfo.getYear());
        viewHolder.tv_date_month.setText(weightInfo.getMonth());
        viewHolder.tv_date_day.setText(weightInfo.getDay());
        viewHolder.tv_weight.setText(weightInfo.getWeight());
        return convertView;
    }


    public class ViewHolder {
        TextView tv_date_year;
        TextView tv_date_month;
        TextView tv_date_day;
        TextView tv_weight;
    }

}

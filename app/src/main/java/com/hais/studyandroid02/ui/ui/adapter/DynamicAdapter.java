package com.hais.studyandroid02.ui.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hais.studyandroid02.ui.R;

import java.util.List;

/**
 * Created by Huang hai-sen on 2016/6/16 16:03.
 */
public class DynamicAdapter extends BaseAdapter {
    private List<String> list;
    private Context mContext;

    public DynamicAdapter(List<String> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item,null);
            holder = new ViewHolder();
            holder.tv = (TextView) view.findViewById(R.id.tv_item);
            holder.rl = (LinearLayout) view.findViewById(R.id.rl);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        //动态加载开始
        if(i==3){
            TextView dy = new TextView(mContext);
            dy.setText("动态布局");
            dy.setPadding(10, 10, 10, 10);
            dy.setGravity(Gravity.CENTER);
            holder.rl.addView(dy);
        }

        holder.tv.setText(list.get(i));
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    class ViewHolder{
        TextView tv;
        LinearLayout rl;
    }
}

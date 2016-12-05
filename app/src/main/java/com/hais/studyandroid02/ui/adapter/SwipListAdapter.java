package com.hais.studyandroid02.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hais.studyandroid02.ui.R;
import com.hais.swipmenulistview.BaseSwipListAdapter;

import java.util.List;

/**
 * Created by Huang hai-sen on 2016/4/26 11:02.
 */
public class SwipListAdapter extends BaseSwipListAdapter {
    private Context mContext;
    private List<String> mDates;

    public SwipListAdapter(Context mContext, List<String> mDates) {
        this.mContext = mContext;
        this.mDates = mDates;
    }

    @Override
    public int getCount() {
        return mDates.size();
    }

    @Override
    public Object getItem(int i) {
        return mDates.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_swipe_menu,null);
            holder.textView = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(mDates.get(i).toString());
        return view;
    }

    @Override
    public boolean getSwipEnableByPosition(int position) {
        return super.getSwipEnableByPosition(position);
    }

    private final class ViewHolder{
        TextView textView;
    }
}

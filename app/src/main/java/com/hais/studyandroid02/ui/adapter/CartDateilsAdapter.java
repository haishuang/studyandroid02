package com.hais.studyandroid02.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hais.studyandroid02.bean.CartDetailsBean;
import com.hais.studyandroid02.ui.R;

import java.util.List;

/**
 * Created by Huang hai-sen on 2016/4/27 16:05.
 */
public class CartDateilsAdapter extends BaseAdapter {

    private Context mContext;
    private List<CartDetailsBean> cartDateilses;

    public CartDateilsAdapter(Context context, List<CartDetailsBean> cartDateilses) {
        this.mContext = context;
        this.cartDateilses = cartDateilses;
    }

    @Override
    public int getCount() {
        return cartDateilses.size();
    }

    @Override
    public Object getItem(int i) {
        return cartDateilses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_cart_list_dateils,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(cartDateilses.get(i).getGoodsName());
        return view;
    }

    class ViewHolder{
        TextView name;
        protected ViewHolder(View view){
            name = (TextView) view.findViewById(R.id.tv_item_name);
        }
    }
}

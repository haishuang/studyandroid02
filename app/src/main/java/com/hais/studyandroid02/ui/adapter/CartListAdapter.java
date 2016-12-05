package com.hais.studyandroid02.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hais.studyandroid02.bean.CartInfoBean;
import com.hais.studyandroid02.ui.R;
import com.hais.swipmenulistview.SwipeMenu;
import com.hais.swipmenulistview.SwipeMenuCreator;
import com.hais.swipmenulistview.SwipeMenuItem;
import com.hais.swipmenulistview.SwipeMenuListView;

import java.util.List;

/**
 * Created by Huang hai-sen on 2016/4/27 14:35.
 */
public class CartListAdapter extends BaseAdapter {
    private Context mContext;
    private List<CartInfoBean> cartInfos;

    public CartListAdapter(Context mContext, List<CartInfoBean> cartInfos) {
        this.mContext = mContext;
        this.cartInfos = cartInfos;
    }

    @Override
    public int getCount() {
        return cartInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return cartInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    ViewHolder holder = null;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_cart_list, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.title.setText(cartInfos.get(i).getSellerName());

        //holder.lv.setAdapter();
        CartDateilsAdapter adapter = new CartDateilsAdapter(mContext, cartInfos.get(i).getGoodsList());
        holder.lv.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem item = new SwipeMenuItem(mContext);
                item.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                item.setIcon(R.drawable.ic_delete);
                item.setWidth(dp2px(88));
                menu.addMenuItem(item);
            }
        };

        holder.lv.setMenuCreator(creator);

        return view;
    }

    class ViewHolder {
        TextView title;
        SwipeMenuListView lv;

        protected ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.tv_item_title);
            lv = (SwipeMenuListView) view.findViewById(R.id.smlv_item);
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }
}

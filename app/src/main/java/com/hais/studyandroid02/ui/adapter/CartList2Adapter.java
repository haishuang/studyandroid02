package com.hais.studyandroid02.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.hais.studyandroid02.bean.CartDetailsBean;
import com.hais.studyandroid02.ui.R;

import java.util.List;

/**
 * Created by Huang hai-sen on 2016/4/27 14:35.
 */
public class CartList2Adapter extends BaseSwipeAdapter {
    private Context mContext;
    private List<CartDetailsBean> cartDetails;
    private String serverUrl = "";

    private String lastSellerId = "-1";
    private Handler mHandler;

    public CartList2Adapter(Context mContext, List<CartDetailsBean> cartDetails, Handler handler) {
        this.mContext = mContext;
        this.cartDetails = cartDetails;
        mHandler = handler;
        if (cartDetails.size() > 0)
            serverUrl = cartDetails.get(0).getServerUrl();
    }


    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.rl_item_swipe;
    }

    @Override
    public View generateView(final int i, ViewGroup viewGroup) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cart_list_swipelayout, null);
        final SwipeLayout swipeLayout = (SwipeLayout) view.findViewById(getSwipeLayoutResourceId(i));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                //YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.iv_delete));
                //Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击了" + i, Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage("确定要删除吗？")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                Message msg = mHandler.obtainMessage();
                                msg.what = 13;
                                msg.arg1 = i;
                                Log.e("Tag3",i+"");
                                mHandler.sendMessage(msg);
                                swipeLayout.close();//关闭滑出
                            }
                        }).setNegativeButton("N", null).show();


            }
        });
        return view;
    }

    @Override
    public void fillValues(final int i, View view) {
        //商家部分
        RelativeLayout rlItemTop = (RelativeLayout) view.findViewById(R.id.rl_item_top);
        TextView sellerName = (TextView) view.findViewById(R.id.tv_seller_name);
        ImageView sellerCheck = (ImageView) view.findViewById(R.id.iv_all_checked);
        ImageView sellerLogo = (ImageView) view.findViewById(R.id.iv_seller_logo);

        //购物项部分
        ImageView goodsCheck = (ImageView) view.findViewById(R.id.iv_checked);
        ImageView goodsImage = (ImageView) view.findViewById(R.id.iv_image);
        TextView goodsName = (TextView) view.findViewById(R.id.tv_goods_name);
        TextView goodsDetails = (TextView) view.findViewById(R.id.tv_goods_details);
        TextView goodsPrice = (TextView) view.findViewById(R.id.tv_goods_sale_price);
        TextView Num = (TextView) view.findViewById(R.id.tv_num);

        final CartDetailsBean bean = cartDetails.get(i);
        //-------判断是否显示头部
        if (bean.getSellerId().equals(lastSellerId)) {//和上一次相同，不显示
            rlItemTop.setVisibility(View.GONE);
        } else {
            rlItemTop.setVisibility(View.VISIBLE);
            sellerName.setText(bean.getSellerName());
        }
        //记录上一个商家id
        lastSellerId = bean.getSellerId();

        //-----判断购物车商品是否被选中
        if (bean.isChecked()) {
            goodsCheck.setImageResource(R.drawable.cart_selected);
        } else {
            goodsCheck.setImageResource(R.drawable.cart_unselected);
        }


        //-----判断商家是否被选中
        if (bean.isSellerIsChecked()) {
            sellerCheck.setImageResource(R.drawable.cart_selected);
        } else {
            sellerCheck.setImageResource(R.drawable.cart_unselected);
        }


        goodsName.setText(bean.getGoodsName());
        goodsPrice.setText("￥" + bean.getSalePrice());
        goodsDetails.setText(bean.getStyMixName());
        Num.setText(bean.getNum() + "");

        goodsCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = mHandler.obtainMessage();
                msg.what = 11;
                msg.arg1 = i;
                mHandler.sendMessage(msg);
            }
        });
        sellerCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = mHandler.obtainMessage();
                msg.what = 12;
                msg.arg1 = i;
                mHandler.sendMessage(msg);
            }
        });

    }

    @Override
    public int getCount() {
        return cartDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return cartDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}

package com.hais.studyandroid02.ui.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.daimajia.swipe.util.Attributes;
import com.google.gson.Gson;
import com.hais.activity.okhttputils.OkHttpUtils;
import com.hais.activity.okhttputils.callback.StringCallback;
import com.hais.studyandroid02.bean.CartDetailsBean;
import com.hais.studyandroid02.bean.CartInfoBean;
import com.hais.studyandroid02.bean.DataBean;
import com.hais.studyandroid02.ui.R;
import com.hais.studyandroid02.ui.adapter.CartList2Adapter;
import com.hais.studyandroid02.widget.HaisListview;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class CartList2Activity extends Activity implements View.OnClickListener {
    private static final String TAG = "CartList2Activity";
    /*
        * 每页请求数量
        * */
    private final int PAGE_SIZE = 20;
    /**
     * 请求页数
     */
    private int pageStart = 0;

    /*
    是否点击了最外层的全选按钮
    * */
    private boolean isCheckedAllCart = false;

    /**
     * 当前所选商品价格总数
     */
    private double totalCost = 0;

    private ImageView ivAllChecked;
    private TextView tvAllChecked;
    private TextView tvTotalCost;
    private TextView tvSetTlement;


    private HaisListview lv;
    private CartList2Adapter adapter;

    private List<CartInfoBean> cartInfoBeansTmp = new ArrayList<CartInfoBean>();

    private List<CartDetailsBean> cartDetailsBeans = new ArrayList<>();
    private List<CartDetailsBean> cartDetailsBeansTemp = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    lv.onRefreshComplete();
                    lv.setResultSize(2);
                    adapter.notifyDataSetChanged();
                    break;
                case 11:
                    setGoodsSelectedChange(msg.arg1);
                    adapter.notifyDataSetChanged();
                    break;
                case 12:
                    setSellerSelectedChange(msg.arg1);
                    adapter.notifyDataSetChanged();
                    break;
                case 13:
                    cartDetailsBeans.remove(msg.arg1);
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    private void setSellerSelectedChange(int j) {
        String sellerId = cartDetailsBeans.get(j).getSellerId();
        //商家原来的选择状态
        boolean sellerIsChecked = cartDetailsBeans.get(j).isSellerIsChecked();
        for (int z = 0; z < cartDetailsBeans.size(); z++) {
            if (cartDetailsBeans.get(z).getSellerId().equals(sellerId)) {
                cartDetailsBeans.get(z).setIsChecked(!sellerIsChecked);
                cartDetailsBeans.get(z).setSellerIsChecked(!sellerIsChecked);
            }
        }

        if (sellerIsChecked) {
            isCheckedAllCart = false;
            ivAllChecked.setImageResource(R.drawable.cart_unselected);
        } else {
            boolean flag = false;
            for (int i = 0; i < cartDetailsBeans.size(); i++) {
                if (cartDetailsBeans.get(i).isChecked()) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ivAllChecked.setImageResource(R.drawable.cart_selected);
                isCheckedAllCart = true;
            }
        }

        setTotalCostText();
    }

    //点击商品
    private void setGoodsSelectedChange(int i) {
        cartDetailsBeans.get(i).setIsChecked(!cartDetailsBeans.get(i).isChecked());
        String sellerId = cartDetailsBeans.get(i).getSellerId();
        //判断原来的选中状态
        if (cartDetailsBeans.get(i).isSellerIsChecked()) {
            //原来全选，则选了一个后必将不是全选，故同一个商家的全选取消
            setSellerIsChecked(sellerId, false);

            //如果原来是整个购物车全选状态，则选了一个之后取消
            if (isCheckedAllCart) {
                isCheckedAllCart = !isCheckedAllCart;
                ivAllChecked.setImageResource(R.drawable.cart_unselected);
            }

        } else {//原来非全选状态，遍历同一商家所有商品，只要还有一项未选择则跳过
            boolean flag = false;
            boolean allFlag = true;
            for (int k = 0; k < cartDetailsBeans.size(); k++) {
                if (cartDetailsBeans.get(k).getSellerId().equals(sellerId)) {
                    if (cartDetailsBeans.get(k).isChecked()) {
                        flag = true;
                    } else {
                        flag = false;
                        allFlag = false;
                        break;
                    }
                }

                if (allFlag) {
                    if (cartDetailsBeans.get(k).isChecked()) {
                        allFlag = true;
                    } else {
                        allFlag = false;
                    }
                }

            }

            //置商家全选
            if (flag)
                setSellerIsChecked(sellerId, true);
            //整个购物车全选
            if (allFlag) {
                isCheckedAllCart = true;
                ivAllChecked.setImageResource(R.drawable.cart_selected);
            }
        }

        setTotalCostText();
    }
    /*
    **计算并显示总金额
     */
    private void setTotalCostText() {
        totalCost = 0;
        for (CartDetailsBean bean : cartDetailsBeans) {
            if (bean.isChecked())
                totalCost += bean.getNum() * bean.getSalePrice();
        }
        tvTotalCost.setText("总计￥：" + totalCost);
    }

    /*
    * 设置商家的状态是否全为选中
    * */
    private void setSellerIsChecked(String sellerId, boolean sellerIsChecked) {
        for (int i = 0; i < cartDetailsBeans.size(); i++) {
            if (cartDetailsBeans.get(i).getSellerId().equals(sellerId)) {
                cartDetailsBeans.get(i).setSellerIsChecked(sellerIsChecked);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list2);

        ivAllChecked = (ImageView) findViewById(R.id.iv_all_checked);
        tvAllChecked = (TextView) findViewById(R.id.tv_all_checked);
        tvTotalCost = (TextView) findViewById(R.id.tv_total_cost);
        tvSetTlement = (TextView) findViewById(R.id.tv_jiesaun);


        lv = (HaisListview) findViewById(R.id.hlv_cart_list);
        lv.setPageSize(PAGE_SIZE);

        adapter = new CartList2Adapter(this, cartDetailsBeans, mHandler);
        adapter.setMode(Attributes.Mode.Multiple);

        lv.setAdapter(adapter);
        lv.setHeaderDividersEnabled(false);
        loadData(HaisListview.REFRESH);

        ivAllChecked.setOnClickListener(this);
        tvAllChecked.setOnClickListener(this);
        tvSetTlement.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_all_checked:
            case R.id.tv_all_checked:
                setCartAllSelected();
                break;
        }
    }

    /*
    购物车全选按钮处理
    * */
    private void setCartAllSelected() {
        totalCost = 0;
        if (isCheckedAllCart) {
            //当前处于全选状态，点击后状态全部改变
            for (int i = 0; i < cartDetailsBeans.size(); i++) {
                cartDetailsBeans.get(i).setIsChecked(!isCheckedAllCart);
                cartDetailsBeans.get(i).setSellerIsChecked(!isCheckedAllCart);
            }
            ivAllChecked.setImageResource(R.drawable.cart_unselected);
            isCheckedAllCart = !isCheckedAllCart;
        } else {
            //当前处于非全选状态
            for (int i = 0; i < cartDetailsBeans.size(); i++) {
                cartDetailsBeans.get(i).setIsChecked(!isCheckedAllCart);
                cartDetailsBeans.get(i).setSellerIsChecked(!isCheckedAllCart);
                totalCost += cartDetailsBeans.get(i).getNum() * cartDetailsBeans.get(i).getSalePrice();
            }
            ivAllChecked.setImageResource(R.drawable.cart_selected);
            isCheckedAllCart = !isCheckedAllCart;
        }
        tvTotalCost.setText("￥" + totalCost);
        adapter.notifyDataSetChanged();

    }

    void loadData(int type) {
        //如果是刷新，则
        if (type == HaisListview.REFRESH)
            pageStart = 1;
        String url = "http://192.168.34.230/SpringMVCAndMybatis/shoppingCart/getCartInfo.action";
        //为下一次查询做准备
        pageStart++;

        OkHttpUtils.post()
                .url(url)
                .addParams("uId", "aa1314")
                .addParams("token", "2016-01-28 10:57:39:791")
                .addParams("pageStart", pageStart + "")
                .addParams("pageSize", PAGE_SIZE + "")
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Log.e(TAG, "请求出错" + e.getMessage() + "\n" + e.getCause());
                        //使用模拟数据
                        getDatas(str);

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "请求返回" + response);
                        getDatas(response);
                    }
                });
    }

    private void getDatas(String response) {
        DataBean b = new Gson().fromJson(response, DataBean.class);
        cartInfoBeansTmp = b.getData();

        //全部转换格式后期需考虑在服务器转换格式
        for (int i = 0; i < cartInfoBeansTmp.size(); i++) {
            CartInfoBean info = cartInfoBeansTmp.get(i);
            for (int j = 0; j < info.getGoodsList().size(); j++) {
                CartDetailsBean detail = info.getGoodsList().get(j);
                /**
                 * private String sellerId;
                 private String sellerName;
                 private String sellerLogo;
                 private int fristCategory;
                 * */
                detail.setServerUrl(b.getInfo());
                detail.setSellerId(info.getSellerId());
                detail.setSellerName(info.getSellerName());
                detail.setSellerLogo(info.getSellerLogo());
                detail.setFristCategory(info.getFristCategory());
                cartDetailsBeansTemp.add(detail);
            }
        }

        //cartInfoBeans.addAll(cartInfoBeansTmp);
        cartDetailsBeans.addAll(cartDetailsBeansTemp);
        mHandler.sendEmptyMessage(0);
    }

    String str = "   {\"type\":300001,\"info\":\"http://192.168.3.230/SpringMVCAndMybatis/download/downloadChatFile.action?hadoopFileName=\",\"errorCode\":0,\"result\":200,\"data\":[{\"sellerId\":\"1\",\"sellerName\":\"小魔鬼酷炫服装\",\"sellerLogo\":\"q\",\"fristCategory\":2,\"goodsList\":[{\"cartId\":\"f304cd73-2e4b-461d-ac50-3930bd86175a\",\"goodsId\":\"3\",\"num\":13,\"goodsName\":\"时尚西装外套\",\"styMixId\":\"1@#8\",\"styMixName\":\"M码  黑色\",\"styMixNum\":94,\"imgUrl\":\"9c7b67e5-530b-41db-a557-f9c39d4308b6\",\"isValidity\":1,\"salePrice\":258},{\"cartId\":\"df02d60a-04d0-4e72-8296-804c30b91411\",\"goodsId\":\"3\",\"num\":4,\"goodsName\":\"时尚西装外套\",\"styMixId\":\"1@#8\",\"styMixName\":\"M码  黑色\",\"styMixNum\":94,\"imgUrl\":\"9c7b67e5-530b-41db-a557-f9c39d4308b6\",\"isValidity\":1,\"salePrice\":258}],\"goodsNum\":2},{\"sellerId\":\"d56b4e6b-6c4e-4e0d-b1f6-e30b23542464\",\"sellerName\":\"雅乐轩酒店\",\"sellerLogo\":\"q\",\"fristCategory\":1,\"goodsList\":[{\"cartId\":\"e53895af-05c8-47db-b1b5-d1c3f87f40ba\",\"goodsId\":\"82a08c5c-185c-4373-ab1f-e5d534bac404\",\"num\":41,\"goodsName\":\"麻辣烫\",\"styMixId\":\"\",\"styMixName\":\"\",\"styMixNum\":0,\"imgUrl\":\"664e55c7-87ca-4f93-9f1b-25517d41b9c1\",\"isValidity\":1,\"salePrice\":202.5},{\"cartId\":\"1\",\"goodsId\":\"37ad9fbf-2d57-47a5-9fc0-69a15027794e\",\"num\":16,\"goodsName\":\"清蒸鱼\",\"styMixId\":\"\",\"styMixName\":\"\",\"styMixNum\":0,\"imgUrl\":\"878c078d-4462-40ef-9898-09ce255d9523\",\"isValidity\":1,\"salePrice\":230.4}],\"goodsNum\":2}]}\n";


}

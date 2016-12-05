package com.hais.studyandroid02.ui.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hais.activity.okhttputils.OkHttpUtils;
import com.hais.activity.okhttputils.callback.StringCallback;
import com.hais.studyandroid02.bean.CartInfoBean;
import com.hais.studyandroid02.bean.DataBean;
import com.hais.studyandroid02.ui.R;
import com.hais.studyandroid02.ui.adapter.CartListAdapter;
import com.hais.studyandroid02.widget.HaisListview;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class CartListActivity extends Activity {
    private static final String TAG = "CartListActivity";
    private final int PAGE_SIZE = 20;
    private HaisListview lv;
    private TextView tvCartTop;
    private int pageStart = 1;
    private CartListAdapter adapter;

    private List<CartInfoBean> cartInfoBeans = new ArrayList<>();
    private List<CartInfoBean> cartInfoBeansTmp = new ArrayList<>();


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    lv.onRefreshComplete();
                    lv.setResultSize(2);
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        lv = (HaisListview) findViewById(R.id.hlv_cart_list);
        lv.setPageSize(PAGE_SIZE);
        tvCartTop = (TextView) findViewById(R.id.tv_cart_top);
        loadData(HaisListview.REFRESH);
        adapter = new CartListAdapter(this,cartInfoBeans);
        lv.setAdapter(adapter);
    }


    void loadData(int type) {
        //如果是刷新，则
        if (type == HaisListview.REFRESH)
            pageStart = 1;
        String url = "http://192.168.3.230/SpringMVCAndMybatis/shoppingCart/getCartInfo.action";
        //为下一次查询做准备
        pageStart++;

        OkHttpUtils.post()
                .url(url)
                .addParams("uId", "aa1314")
                .addParams("token","2016-01-28 10:57:39:791")
                .addParams("pageStart","1")
                .addParams("pageSize","20")
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Log.e(TAG,"请求出错"+e.getMessage()+"\n"+e.getCause());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG,"请求返回"+response);
                        cartInfoBeansTmp = new Gson().fromJson(response,DataBean.class).getData();

                        cartInfoBeans.addAll(cartInfoBeansTmp);
                        mHandler.sendEmptyMessage(0);
                    }
                });
    }
}

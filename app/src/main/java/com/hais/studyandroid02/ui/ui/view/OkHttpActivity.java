package com.hais.studyandroid02.ui.ui.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hais.activity.okhttputils.OkHttpUtils;
import com.hais.activity.okhttputils.callback.StringCallback;
import com.hais.studyandroid02.ui.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class OkHttpActivity extends Activity {
    private ListView lv;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        lv = (ListView) findViewById(R.id.lv_ok_http);

        getData();

        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas));
    }

    private void getData() {
        mDatas = new ArrayList<String>();

        /**
         * http://192.168.3.230/SpringMVCAndMybatis/shoppingCart/getCartInfo.action?
         * uId=aa1314
         * &token=2016-01-28%2010:57:39:791
         * &pageStart=1
         * &pageSize=14
         */
//        OkHttpUtils.getInstance().get()
//                .addParams("uId","aa1314")
//                .addParams("token","2016-01-28%2010:57:39:791")
//                .addParams("pageStart","1")
//                .addParams("pageSize","20")
//                .url("http://192.168.3.230/SpringMVCAndMybatis/shoppingCart/getCartInfo.action")
//                .tag("123")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        Log.e("u","报错");
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("u",response.toString());
//                    }
//                });

        OkHttpUtils.post().url("http://192.168.3.252:8080/SpringMVCAndMybatis/shoppingCart/getCartInfo.action")
                .tag(this)
                .addParams("uId","aa1314")
                .addParams("token","2016-01-28 10:57:39:791")
                .addParams("pageStart","1")
                .addParams("pageSize","20")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Log.e("u","报错");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("u","post返回："+response.toString());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag("123");
    }
}

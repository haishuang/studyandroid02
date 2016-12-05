package com.hais.studyandroid02.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hais.studyandroid02.ui.ui.view.AlphaChageActivity;
import com.hais.studyandroid02.ui.ui.view.BannerActivity;
import com.hais.studyandroid02.ui.ui.view.CartList2Activity;
import com.hais.studyandroid02.ui.ui.view.CartListActivity;
import com.hais.studyandroid02.ui.ui.view.DynamicLoadingLayoutActivity;
import com.hais.studyandroid02.ui.ui.view.FragmentActivity;
import com.hais.studyandroid02.ui.ui.view.LoadingActivity;
import com.hais.studyandroid02.ui.ui.view.OkHttpActivity;
import com.hais.studyandroid02.ui.ui.view.RatingActivity;
import com.hais.studyandroid02.ui.ui.view.RatingGethubActivity;
import com.hais.studyandroid02.ui.ui.view.SeekBarActivity;
import com.hais.studyandroid02.ui.ui.view.SwipeMenuListViewActivity;
import com.hais.studyandroid02.ui.ui.view.TestLoginActivity;
import com.hais.studyandroid02.ui.ui.view.TextViewActivity;
import com.hais.studyandroid02.ui.ui.view.TitleActivity;
import com.hais.studyandroid02.ui.ui.view.TopTitleActivity;
import com.hais.studyandroid02.utils.BaseActivity;
import com.hais.studyandroid02.widget.HaisDialog;
import com.hais.studyandroid02.widget.HaisDialogs;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private ListView lv;
    private TextView tv;
    private TextView tv2;
    private List<String> mDatas;
    private Context mContext;
    private HaisDialog haisDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv_main);
        tv = (TextView) findViewById(R.id.tv_main);
        tv2 = (TextView) findViewById(R.id.tv_main_2);
        initDate();
        initEvent();
        mContext = this;

    }

    private void initDate() {
        mDatas = new ArrayList<>();
        mDatas.add("自定义顶部布局,以引入xml方式");
        mDatas.add("自定义星星");
        mDatas.add("自定义星星-gethub.com");
        mDatas.add("用swipmenulistview实现QQ滑动删除效果");
        mDatas.add("经过鸿大神封装的okHttp框架，经过本人微调");
        mDatas.add("购物车-swipeMenuList");
        mDatas.add("购物车-SwipeLayout(效果好)");
        mDatas.add("动态加载布局");
        mDatas.add("自定义标题栏BaseActivity");
        mDatas.add("TextView的一些设置");
        mDatas.add("关于SeekBar");
        mDatas.add("关于进度条和加载更多的动画");
        mDatas.add("标题栏的透明度根据listview的滑动距离改变");
        mDatas.add("测试登陆方式");
        mDatas.add("广告轮播图");
        mDatas.add("点击启动Fragment");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        lv.setAdapter(adapter);
    }

    private void initEvent() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lvSelected(i);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (haisDialog == null)
                    haisDialog = new HaisDialog(MainActivity.this);
                //haisDialog.showAsDropDown(tv);
                haisDialog.showAtLocation(tv, Gravity.CENTER, 0, 0);
                haisDialog.setonDialogListener(new HaisDialog.onDialogListener() {
                    @Override
                    public void onConfirmClick() {
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelClick() {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //简单的自定义弹窗
//                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//                alertDialog.show();
//                Window window = alertDialog.getWindow();
//                window.setContentView(R.layout.pop_dialog);

                //HaisDialogs dialog = new HaisDialogs(MainActivity.this, R.style.Dialog);
                final HaisDialogs.Builder dialog = new HaisDialogs.Builder(MainActivity.this);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                // dialog.create().show();
//                dialog.show();
//                AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
//                AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
//                a.show();
//                a.create().show();

            }
        });
    }

    private void lvSelected(int i) {
        switch (i) {
            case 0:
                startActivity(new Intent(mContext, TopTitleActivity.class));
                break;
            case 1:
                startActivity(new Intent(mContext, RatingActivity.class));
                break;
            case 2:
                startActivity(new Intent(mContext, RatingGethubActivity.class));
                break;
            case 3:
                startActivity(new Intent(mContext, SwipeMenuListViewActivity.class));
                break;
            case 4:
                startActivity(new Intent(mContext, OkHttpActivity.class));
            case 5:
                startActivity(new Intent(mContext, CartListActivity.class));
                break;
            case 6:
                startActivity(new Intent(mContext, CartList2Activity.class));
                break;
            case 7:
                startActivity(new Intent(mContext, DynamicLoadingLayoutActivity.class));
                break;
            case 8:
                startActivity(new Intent(mContext, TitleActivity.class));
            case 9:
                startActivity(new Intent(mContext, TextViewActivity.class));
                break;
            case 10:
                startActivity(new Intent(mContext, SeekBarActivity.class));
                break;
            case 11:
                startActivity(new Intent(mContext, LoadingActivity.class));
                break;
            case 12:
                startActivity(new Intent(mContext, AlphaChageActivity.class));
                break;
            case 13:
                startActivity(new Intent(mContext, TestLoginActivity.class));
                break;
            case 14:
                startActivity(new Intent(mContext, BannerActivity.class));
                break;
            case 15:
                startActivity(new Intent(mContext, FragmentActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 拦截弹出菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);//直接写返回true也行，但是返回false的话表示不创建
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "你点击了" + item.getTitle(), Toast.LENGTH_LONG).show();
        return true;
    }
}

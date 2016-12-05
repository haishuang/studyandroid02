package com.hais.studyandroid02.ui.ui.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.hais.studyandroid02.ui.R;
import com.hais.studyandroid02.ui.adapter.SwipListAdapter;
import com.hais.swipmenulistview.SwipeMenu;
import com.hais.swipmenulistview.SwipeMenuCreator;
import com.hais.swipmenulistview.SwipeMenuItem;
import com.hais.swipmenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class SwipeMenuListViewActivity extends Activity {
    private static final String TAG = "SwipeMenuListViewActivity";
    private SwipeMenuListView smlv;
    private List<String> mData;
    private SwipListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_menu_list_view);
        smlv = (SwipeMenuListView) findViewById(R.id.smlv_swipe_menu_listview);

        getData();

        adapter = new SwipListAdapter(this,mData);
        smlv.setAdapter(adapter);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        smlv.setMenuCreator(creator);


        smlv.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {
                Log.w(TAG, "------------->onSwipeStart");
            }

            @Override
            public void onSwipeEnd(int position) {
                Log.w(TAG, "------------->onSwipeEnd");

            }
        });

        smlv.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
                Log.w(TAG, "------------->onMenuOpen");


            }

            @Override
            public void onMenuClose(int position) {
                Log.w(TAG, "------------->onMenuClose");

            }
        });

      smlv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
          @Override
          public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
              Log.w(TAG, "------------->onMenuItemClick");
              Toast.makeText(SwipeMenuListViewActivity.this, "你点击了" + (index + 1), Toast.LENGTH_SHORT).show();
              //返回true不消失
              return false;
          }
      });

        smlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SwipeMenuListViewActivity.this, "你点击了" + (i + 1) + "个item", Toast.LENGTH_SHORT).show();
            }
        });

        smlv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SwipeMenuListViewActivity.this, "你使用了长点击事件", Toast.LENGTH_SHORT).show();
                return true;//返回true拦截点击事件
            }
        });



    }

    private void getData() {
        mData = new ArrayList<String>();

        for(int i = 0;i<10;i++){
            mData.add("开始"+i);
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


}

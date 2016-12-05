package com.hais.studyandroid02.ui.ui.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.hais.activity.okhttputils.utils.L;
import com.hais.studyandroid02.ui.R;
import com.hais.studyandroid02.ui.ui.adapter.AlphaAdapter;
import com.hais.studyandroid02.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AlphaChageActivity extends BaseActivity {
    private ListView lv;
    private List<String> list = new ArrayList<>();
    private AlphaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_chage);
        lv = (ListView) findViewById(R.id.lv);

        for(int i=0;i<300;i++){
            list.add("哈哈哈"+i);
        }

        lv.setAdapter(adapter = new AlphaAdapter(list,this));

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                Log.e("aa","现在的距离1："+getScrollY());
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                Log.e("hnh","现在的距离："+getScrollY());
                llTitle.setAlpha(1-getScrollY()*1.0f/3000);
            }
        });
    }
    public int getScrollY() {
        View c = lv.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = lv.getFirstVisiblePosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight() ;
    };

}

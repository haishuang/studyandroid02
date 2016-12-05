package com.hais.studyandroid02.ui.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.hais.studyandroid02.ui.R;
import com.hais.studyandroid02.ui.ui.adapter.DynamicAdapter;

import java.util.ArrayList;
import java.util.List;

public class DynamicLoadingLayoutActivity extends Activity {
    private ListView lv;
    private List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_loading_layout);

        lv = (ListView) findViewById(R.id.lv);

        list.clear();
        for(int i=0;i<10;i++){
            list.add("测试"+i);
        }
        lv.setAdapter(new DynamicAdapter(list,this));

    }


}

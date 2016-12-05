package com.hais.studyandroid02.ui.ui.view;

import android.os.Bundle;
import android.widget.TextView;

import com.hais.studyandroid02.ui.R;
import com.hais.studyandroid02.utils.BaseActivity;

public class TitleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        ((TextView)findViewById(R.id.tv_1)).setText("是我啊");

    }
}

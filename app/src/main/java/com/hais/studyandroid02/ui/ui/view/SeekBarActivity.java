package com.hais.studyandroid02.ui.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hais.studyandroid02.ui.R;

public class SeekBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

//        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(outMetrics);
//
//        int w = outMetrics.widthPixels;
//        int h = outMetrics.heightPixels;
//
//        if(w>h){//横屏
//            ((ImageView)findViewById(R.id.iv_test)).getLayoutParams().height = h;
//        }
    }


}

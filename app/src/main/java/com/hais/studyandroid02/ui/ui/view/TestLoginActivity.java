package com.hais.studyandroid02.ui.ui.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hais.studyandroid02.ui.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestLoginActivity extends AppCompatActivity {


    @Bind(R.id.tv)
    TextView tv;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_login);
        ButterKnife.bind(this);
        mContext = this;
        tv.setText("未登录1");
    }

    @OnClick({R.id.btn_login_in, R.id.btn_login_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_in:
                startActivityForResult(new Intent(mContext, LoginActivity.class), 2);
                break;
            case R.id.btn_login_out:
                SharedPreferences sp = getSharedPreferences(LoginActivity.FILE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor et = sp.edit();
                et.putString(LoginActivity.COOKIE, "-1");
                et.commit();
                et.clear();
                tv.setText("未登录");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == LoginActivity.LOGIN_RESULT_CODE) {
            tv.setText("登陆成功");
        }
    }
}

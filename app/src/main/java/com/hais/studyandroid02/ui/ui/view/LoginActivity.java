package com.hais.studyandroid02.ui.ui.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.hais.studyandroid02.ui.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 * <p/>
 * 保存账号的文件名：login
 * 账号的key：uname
 * 密码的key:uPw
 * cookie的key:cookie
 */
public class LoginActivity extends AppCompatActivity {


    public static final String FILE_NAME = "login";
    public static final String COOKIE = "cookie";
    public static final String DEFAULT_COOKIE = "-1";
    public static final int LOGIN_RESULT_CODE = 600;
    @Bind(R.id.et_login_useName)
    EditText etLoginUseName;
    @Bind(R.id.et_login_usePw)
    EditText etLoginUsePw;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //检查登陆的cookie是否存在
        checkedLoginCookie();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    private void checkedLoginCookie() {
        SharedPreferences sp = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String cookie = sp.getString(COOKIE, DEFAULT_COOKIE);
        if (!cookie.equals(DEFAULT_COOKIE)) {
            Toast.makeText(mContext, "已经登录，不用再次登录，可以直接进入页面", Toast.LENGTH_SHORT).show();
            setResult(LOGIN_RESULT_CODE);
            finish();
        }
    }

    @OnClick(R.id.btn_login_in)
    public void onClick() {
        String uName = etLoginUseName.getText().toString().trim();
        if (TextUtils.isEmpty(uName)) {
            Toast.makeText(mContext, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }
        String uPw = etLoginUsePw.getText().toString().trim();
        if (TextUtils.isEmpty(uPw)) {
            Toast.makeText(mContext, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }

        if (uName.equals("aa") && uPw.equals("bb")) {
            SharedPreferences sp = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
            SharedPreferences.Editor et = sp.edit();
            et.putString(COOKIE, "1234");
            et.commit();
            et.clear();
            Toast.makeText(mContext, "用户登陆成功！", Toast.LENGTH_SHORT).show();
            setResult(LOGIN_RESULT_CODE);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}


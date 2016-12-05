package com.hais.studyandroid02.ui.ui.view;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hais.activity.okhttputils.utils.L;
import com.hais.studyandroid02.ui.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.tv_change_password)
    TextView tvChangePassword;
    @Bind(R.id.tv_change_password1)
    TextView tvChangePassword1;
    private FragmentManager fm;
    private ChangePasswordFragment1 fragment1;
    private ChangePasswordFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
        fm = getFragmentManager();
        fragment1 = new ChangePasswordFragment1();
        fragment = new ChangePasswordFragment();
        tvChangePassword.setOnClickListener(this);
        tvChangePassword1.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        //fm.beginTransaction().replace(R.id.flt_content, fragment, "pw").commit();

        switch (view.getId()){
            case R.id.tv_change_password:
                fm.beginTransaction().replace(R.id.flt_content, fragment, "pw").commit();
                break;
            case R.id.tv_change_password1:
                fm.beginTransaction().replace(R.id.flt_content,fragment1).addToBackStack(null).commit();
                break;
            default:
                break;
        }
    }
}

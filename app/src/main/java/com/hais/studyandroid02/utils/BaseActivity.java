package com.hais.studyandroid02.utils;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hais.studyandroid02.ui.R;

public class BaseActivity extends Activity {
    /**
     * 最外层线性布局
     */
    public RelativeLayout container;
    /**
     * 标题栏相对布局
     */
    public LinearLayout llTitle;
    /**
     * 返回（左边）布局
     */
    private LinearLayout llBack;
    /**
     * 右边布局
     */
    private RelativeLayout rlRight;
    /**
     * 中间布局
     */
    private RelativeLayout rlContent;
    /**
     * 返回（左边）小图标
     */
    private ImageView ivBack;
    /**
     * 返回（左边）文字
     */
    private TextView tvBack;
    /**
     * 中间标题内容，经测试，最多支持11个汉字
     */
    private TextView tvContent;

    /**
     * 右边的右边小图标
     */
    private ImageView ivRightRight;
    /**
     * 右边的左边小图标
     */
    private ImageView ivRightLeft;
    /**
     * 右边文字
     */
    private TextView tvRight;



    /**
     * 是否使用沉浸式标题
     */
    public boolean isImmersion = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base2);

        findView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            //	 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void findView() {
        container = (RelativeLayout) findViewById(R.id.rl_base_top);
        rlContent = (RelativeLayout) findViewById(R.id.rl_base_title_content);
        llTitle = (LinearLayout) findViewById(R.id.ll_base_title);
        llBack = (LinearLayout) llTitle.findViewById(R.id.ll_base_title_back);
        rlRight = (RelativeLayout) findViewById(R.id.rl_base_title_right);
        ivBack = (ImageView) findViewById(R.id.iv_base_title_back);
        tvBack = (TextView) findViewById(R.id.tv_base_title_back);
        tvContent = (TextView) findViewById(R.id.tv_content);
        ivRightLeft = (ImageView) findViewById(R.id.iv_base_title_right_left);
        ivRightRight = (ImageView) findViewById(R.id.iv_base_title_right_right);
        tvRight = (TextView) findViewById(R.id.tv_base_title_right);

        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleBackOnClick();
            }
        });
    }

    public void titleBackOnClick() {
        finish();
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = (View) getLayoutInflater().inflate(layoutResID, null);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.BELOW, R.id.ll_base_title);
        if (null != container)
            container.addView(view, lp);
    }

    /***
     * 设置返回按钮文字
     * @param str
     */
    public void setBackText(String str){
        if(null!=tvBack)
            tvBack.setText(str);
    }

    /**
     * 设置中间标题
     */
    public void setTitleText(String str){
        if(null!=tvContent)
            tvContent.setText(str);
    }

    /**
     * 设置右边文字
     */
    public void setRightText(String str){
        if(null!=tvRight)
            tvRight.setText(str);
    }

    /**
     * 设置左边返回箭头图片
     * @param rId
     */
    public void setBackImage(int rId){
        if(null!=ivBack)
            ivBack.setImageResource(rId);
    }

    /**
     * 设置右边的右边图片按钮
     * @param rId
     */
    public void setRightRightImage(int rId){
        if(null!=ivRightRight)
            ivRightRight.setImageResource(rId);
    }
    /**
     * 设置右边的左边图片按钮
    * @param rId
    */
    public void setRightLeftImage(int rId){
        if(null!=ivRightLeft)
            ivRightLeft.setImageResource(rId);
    }

    /**
     * 设置背景颜色
     * @param color
     */
    public void setTitleBackgroundColor(int color){
        container.setBackgroundColor(color);
        llTitle.setBackgroundColor(color);
    }



    public boolean isImmersion() {
        return isImmersion;
    }

    /**
     * 是否使用沉浸式状态栏，默认true
     * @param isImmersion
     */
    public void setIsImmersion(boolean isImmersion) {
        this.isImmersion = isImmersion;
    }

    public LinearLayout getLlBack() {
        return llBack;
    }

    public RelativeLayout getRlRight() {
        return rlRight;
    }

    public ImageView getIvRightRight() {
        return ivRightRight;
    }

    public ImageView getIvRightLeft() {
        return ivRightLeft;
    }

    public RelativeLayout getRlContent() {
        return rlContent;
    }
}

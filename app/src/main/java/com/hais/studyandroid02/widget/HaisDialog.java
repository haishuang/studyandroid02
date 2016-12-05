package com.hais.studyandroid02.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hais.studyandroid02.ui.R;

/**
 * Created by Huang hai-sen on 2016/6/15 9:50.
 */
public class HaisDialog extends PopupWindow {

    private int mWidth = 0;
    private int mHeight = 0;

    private View mView;
    private Activity mContext;

    private TextView tvTilte;
    private TextView tvConfirm;
    private TextView tvCancel;

    private String title = "";
    private String confirm = "";
    private String cancel = "";

    public interface onDialogListener {
        void onConfirmClick();

        void onCancelClick();
    }

    private onDialogListener mListener;

    public void setonDialogListener(onDialogListener mListener) {
        this.mListener = mListener;
    }

    public HaisDialog(Activity context) {
        this.mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.pop_dialog, null);
        setWidthAndHeight();
        setContentView(mView);
        initPopupWindow();
        initView();
        initEvent();
        setWindowParamsAttributes(context, 0.3f);
    }

    private void initEvent() {
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onConfirmClick();
                setWindowParamsAttributes(mContext, 1.0f);
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onCancelClick();
                setWindowParamsAttributes(mContext, 1.0f);
                dismiss();
            }
        });
    }

    private void initView() {
        tvTilte = (TextView) mView.findViewById(R.id.tv_content);
        tvConfirm = (TextView) mView.findViewById(R.id.tv_dialog_cancel);
        tvCancel = (TextView) mView.findViewById(R.id.tv_dialog_confirm);

        if (!"".equals(title))
            tvTilte.setText(title);

        if (!"".equals(confirm))
            tvConfirm.setText(confirm);

        if (!"".equals(cancel))
            tvCancel.setText(cancel);
    }


    /**
     * 获取屏幕宽和高，并且设置给弹出窗
     */
    private void setWidthAndHeight() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        mWidth = metrics.widthPixels * 4 / 5;
        mHeight = metrics.heightPixels / 5;
        setWidth(mWidth);
        setHeight(mHeight);

    }

    private void initPopupWindow() {
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(false);
        setBackgroundDrawable(new BitmapDrawable());
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }


    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }


    public void setWindowParamsAttributes(Activity activity, float alpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = alpha;
        activity.getWindow().setAttributes(lp);
    }
}

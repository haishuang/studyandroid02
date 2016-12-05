package com.hais.studyandroid02.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hais.studyandroid02.ui.R;

/**
 * Created by Huang hai-sen on 2016/6/15 15:45.
 */
public class HaisDialogs extends Dialog {
    public HaisDialogs(Context context) {
        super(context);
    }

    public HaisDialogs(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected HaisDialogs(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pop_dialog);
//    }

    public static class Builder {
        private Context context;
        private String message;

        private TextView tvPositive;
        private TextView tvNegative;

        private View contentView;
        private String positiveText;
        private String negativeText;
        private OnClickListener positiveClickListener;
        private OnClickListener negativeClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveText = (String) context.getText(positiveButtonText);
            this.positiveClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveText = positiveButtonText;
            this.positiveClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeText = (String) context
                    .getText(negativeButtonText);
            this.negativeClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeText = negativeButtonText;
            this.negativeClickListener = listener;
            return this;
        }


        public HaisDialogs create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final HaisDialogs dialog = new HaisDialogs(context, R.style.Dialog);
            View mView = inflater.inflate(R.layout.pop_dialog, null);
            dialog.addContentView(mView,
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            tvPositive = (TextView) mView.findViewById(R.id.tv_dialog_confirm);
            tvNegative = (TextView) mView.findViewById(R.id.tv_dialog_cancel);
            View v_2 =  mView.findViewById(R.id.v_2);
            //positive
            if (null != positiveText) {
                tvPositive.setText(positiveText);
                if (positiveClickListener != null) {
                    tvPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            positiveClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                tvPositive.setVisibility(View.GONE);
                v_2.setVisibility(View.GONE);
            }

            //negv
            if (null != negativeText) {
                tvNegative.setText(negativeText);
                if (negativeClickListener != null) {
                    tvNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            negativeClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                tvNegative.setVisibility(View.GONE);
                v_2.setVisibility(View.GONE);
            }

            //message
            if (message != null) {
                ((TextView) mView.findViewById(R.id.tv_content)).setText(message);
            } else if (contentView != null) {

                ((LinearLayout) mView.findViewById(R.id.ll_content)).removeAllViews();
                ((LinearLayout) mView.findViewById(R.id.ll_content))
                        .addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            dialog.setContentView(mView);
            return dialog;
        }

        public HaisDialogs show() {
            HaisDialogs dialog = create();
            dialog.show();
            return dialog;
        }
    }
}

package com.hais.studyandroid02.ui.ui.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.widget.TextView;

import com.hais.studyandroid02.ui.R;

public class TextViewActivity extends Activity {
    private TextView lineateTop;
    private TextView lineateBottom;
    private TextView lineateConter;
    private TextView colorNeative;
    private TextView colorHtml;
    private TextView size;

    private TextView flagTop;
    private TextView flagBottom;
    private TextView scale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        lineateTop = (TextView) findViewById(R.id.tv_lineate_top);
        lineateBottom = (TextView) findViewById(R.id.tv_lineate_bottom);
        lineateConter = (TextView) findViewById(R.id.tv_lineate_center);
        colorNeative = (TextView) findViewById(R.id.tv_color_neative);
        colorHtml = (TextView) findViewById(R.id.tv_color_html);
        size = (TextView) findViewById(R.id.tv_size);
        flagTop = (TextView) findViewById(R.id.tv_flag_top);
        flagBottom = (TextView) findViewById(R.id.tv_flag_bottom);
        scale = (TextView) findViewById(R.id.tv_scale_x);

        textStyle();
    }

    private void textStyle() {
        //字体加粗
        lineateTop.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        lineateTop.getPaint().setAntiAlias(true);//抗锯齿
        // 下划线
        lineateConter.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //lineateConter.getPaint().setAntiAlias(true);//抗锯齿
        // 下划线
        lineateBottom.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        lineateBottom.getPaint().setAntiAlias(true);//抗锯齿//下划线

        //字体颜色多样,设置其他样式也可以使用
        colorHtml.setText(Html.fromHtml("<font color='red'>字体</font><font color='#00ff00'>多种颜色</font><font color='#0000ff'>二</font>"));

        SpannableStringBuilder sb = new SpannableStringBuilder("字体多种颜色一&背景色");
        sb.setSpan(new ForegroundColorSpan(Color.RED),0,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new ForegroundColorSpan(Color.YELLOW),2,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new ForegroundColorSpan(Color.BLUE), 5, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //背景色
        sb.setSpan(new BackgroundColorSpan(Color.GREEN), 7, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        colorNeative.setText(sb);

        //字体大小不一
        SpannableStringBuilder sb1 = new SpannableStringBuilder("字体大小样式不一");
        sb1.setSpan(new AbsoluteSizeSpan(80), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb1.setSpan(new AbsoluteSizeSpan(40), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//绝对大小
        //sb1.setSpan(new RelativeSizeSpan(40), 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//相对大小
        sb1.setSpan(new AbsoluteSizeSpan(60), 5, sb1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        size.setText(sb1);

        sb.clear();
        sb.append("设置字符上标");
        sb.setSpan(new SuperscriptSpan(), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new RelativeSizeSpan(0.5f),2,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        flagTop.setText(sb);
        sb.clear();

        sb.append("设置字符下标");
        sb.setSpan(new SubscriptSpan(), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        flagBottom.setText(sb);
        sb.clear();

        sb.append("设置字体缩放。。。");
        sb.setSpan(new ScaleXSpan(2f), 2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new ScaleXSpan(0.5f), 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        scale.setText(sb);

    }


}

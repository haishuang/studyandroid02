package com.hais.studyandroid02.ui.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hais.studyandroid02.ui.R;

import java.util.List;

/**
 * Created by Huang hai-sen on 2016/6/16 16:03.
 */
public class AlphaAdapter extends BaseAdapter {
    private List<String> list;
    private Context mContext;

    MyTextWatcher myTextWatcher;

    private String[] content;
    int index =-1;

    public AlphaAdapter(List<String> list, Context context) {
        this.list = list;
        this.mContext = context;

        content = new String[list.size()];
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_et,null);
            holder = new ViewHolder();
            holder.tv = (TextView) view.findViewById(R.id.tv_item);
            holder.et = (EditText) view.findViewById(R.id.et);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv.setText(list.get(position));
        //----处理EditText开始-----------------
        holder.et.setOnTouchListener(new View.OnTouchListener() {
            //@SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    index = position;
                }
                return false;
            }
        });


        holder.et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                EditText et=(EditText) view;
                if(myTextWatcher==null)
                    myTextWatcher = new MyTextWatcher();
                if(b){
                    et.addTextChangedListener(myTextWatcher);
                }else {
                    et.removeTextChangedListener(myTextWatcher);
                }
            }
        });

        //过后清除焦点
        holder.et.clearFocus();

        if(index!=-1&&index==position){
            holder.et.requestFocus();
        }
        holder.et.setText(content[position]);
        holder.et.setSelection(holder.et.getText().length());


        return view;
    }

    class  MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            content[index] = editable.toString();
        }
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    class ViewHolder{
        TextView tv;
        EditText et;
    }
}

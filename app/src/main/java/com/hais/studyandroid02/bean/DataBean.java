package com.hais.studyandroid02.bean;

import java.util.List;

/**
 * Created by Huang hai-sen on 2016/4/27 13:27.
 */
public class DataBean {

    private int type;
    private String info;
    private int errorCode;
    private int result;
    private List<CartInfoBean> data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<CartInfoBean> getData() {
        return data;
    }

    public void setData(List<CartInfoBean> data) {
        this.data = data;
    }
}

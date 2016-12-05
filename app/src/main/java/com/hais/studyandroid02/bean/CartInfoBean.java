package com.hais.studyandroid02.bean;

import java.util.List;

/**
 * Created by Huang hai-sen on 2016/4/27 13:39.
 */
public class CartInfoBean {

    private String sellerId;
    private String sellerName;
    private String sellerLogo;
    private int fristCategory;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private List<CartDetailsBean> goodsList;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    public int getFristCategory() {
        return fristCategory;
    }

    public void setFristCategory(int fristCategory) {
        this.fristCategory = fristCategory;
    }

    public List<CartDetailsBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<CartDetailsBean> goodsList) {
        this.goodsList = goodsList;
    }
}

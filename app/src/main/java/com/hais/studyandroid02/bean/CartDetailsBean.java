package com.hais.studyandroid02.bean;

/**
 * Created by Huang hai-sen on 2016/4/27 13:40.
 */
public class CartDetailsBean {
    private String cartId;
    private String goodsId;
    private int num;
    private String goodsName;
    private String styMixId;
    private String styMixName;
    private int styMixNum;
    private String imgUrl;
    private int isValidity;
    private double salePrice;
    private boolean isChecked;
    private boolean sellerIsChecked;

    public boolean isSellerIsChecked() {
        return sellerIsChecked;
    }

    public void setSellerIsChecked(boolean sellerIsChecked) {
        this.sellerIsChecked = sellerIsChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    //商家相关
    private String sellerId;
    private String sellerName;
    private String sellerLogo;
    private int fristCategory;
    private String serverUrl;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStyMixId() {
        return styMixId;
    }

    public void setStyMixId(String styMixId) {
        this.styMixId = styMixId;
    }

    public String getStyMixName() {
        return styMixName;
    }

    public void setStyMixName(String styMixName) {
        this.styMixName = styMixName;
    }

    public int getStyMixNum() {
        return styMixNum;
    }

    public void setStyMixNum(int styMixNum) {
        this.styMixNum = styMixNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getIsValidity() {
        return isValidity;
    }

    public void setIsValidity(int isValidity) {
        this.isValidity = isValidity;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }


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
}

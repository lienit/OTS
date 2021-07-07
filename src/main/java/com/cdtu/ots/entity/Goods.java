package com.cdtu.ots.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @ClassName Goods
 * @Description TODO
 * @Author mars_sea
 * @Date 2021/7/5
 **/

public class Goods {
    private int gId;
    private int gCatId;
    private int gStoreId;
    private String gName;
    private Double gPrice;
    private String gImage;
    private String gParameter;
    private int gNumber;

    public Goods() {

    }

    public Goods(int gId, int gCatId, int gStoreId, String gName, Double gPrice, String gImage, String gParameter) {
        this.gId = gId;
        this.gCatId = gCatId;
        this.gStoreId = gStoreId;
        this.gName = gName;
        this.gPrice = gPrice;
        this.gImage = gImage;
        this.gParameter = gParameter;
    }

    public Goods(int gCatId, int gStoreId, String gName, Double gPrice, String gImage,
                 String gParameter, int gNumber) {
        this.gCatId = gCatId;
        this.gStoreId = gStoreId;
        this.gName = gName;
        this.gPrice = gPrice;
        this.gImage = gImage;
        this.gParameter = gParameter;
        this.gNumber = gNumber;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public int getgCatId() {
        return gCatId;
    }

    public void setgCatId(int gCatId) {
        this.gCatId = gCatId;
    }

    public int getgStoreId() {
        return gStoreId;
    }

    public void setgStoreId(int gStoreId) {
        this.gStoreId = gStoreId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Double getgPrice() {
        return gPrice;
    }

    public void setgPrice(Double gPrice) {
        this.gPrice = gPrice;
    }

    public String getgImage() {
        return gImage;
    }

    public void setgImage(String gImage) {
        this.gImage = gImage;
    }

    public String getgParameter() {
        return gParameter;
    }

    public void setgParameter(String gParameter) {
        this.gParameter = gParameter;
    }

    public int getgNumber() {
        return gNumber;
    }

    public void setgNumber(int gNumber) {
        this.gNumber = gNumber;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gId=" + gId +
                ", gCatId=" + gCatId +
                ", gStoreId=" + gStoreId +
                ", gName='" + gName + '\'' +
                ", gPrice=" + gPrice +
                ", gImage='" + gImage + '\'' +
                ", gParameter='" + gParameter + '\'' +
                ", gNumber=" + gNumber +
                '}';
    }
}

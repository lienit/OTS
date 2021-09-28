package com.cdtu.ots.entity;

import java.util.Date;

public class Indent {
    private String iUserId;
    private String  iSotreId;
    private String iCatId;
    private String iGoodsId;
    private Double iPrice;
    private String iAddress;
    private String iMessage;
    private String iState;
    private String iDate;
    private String gName;
    private String gPrice;
    private String sName;



    public String getiUserId() {
        return iUserId;
    }

    public void setiUserId(String iUserId) {
        this.iUserId = iUserId;
    }

    public String getiSotreId() {
        return iSotreId;
    }

    public void setiSotreId(String iSotreId) {
        this.iSotreId = iSotreId;
    }

    public String getiCatId() {
        return iCatId;
    }

    public void setiCatId(String iCatId) {
        this.iCatId = iCatId;
    }

    public String getiGoodsId() {
        return iGoodsId;
    }

    public void setiGoodsId(String iGoodsId) {
        this.iGoodsId = iGoodsId;
    }

    public Double getiPrice() {
        return iPrice;
    }

    public void setiPrice(Double iPrice) {
        this.iPrice = iPrice;
    }

    public String getiAddress() {
        return iAddress;
    }

    public void setiAddress(String iAddress) {
        this.iAddress = iAddress;
    }

    public String getiMessage() {
        return iMessage;
    }

    public void setiMessage(String iMessage) {
        this.iMessage = iMessage;
    }

    public String getiState() {
        return iState;
    }

    public void setiState(String iState) {
        this.iState = iState;
    }

    public String getiDate() {
        return iDate;
    }

    public void setiDate(String iDate) {
        this.iDate = iDate;
    }



    @Override
    public String toString() {
        return "Indent{" +
                "iUserId=" + iUserId +
                ", iSotreId=" + iSotreId +
                ", iCatId=" + iCatId +
                ", iGoodsId=" + iGoodsId +
                ", iPrice=" + iPrice +
                ", iAddress='" + iAddress + '\'' +
                ", iMessage='" + iMessage + '\'' +
                ", iState='" + iState + '\'' +
                ", iDate=" + iDate +
                '}';
    }
}

package com.cdtu.ots.entity;


public class Category {
    private int cId;
    private String cName;
    private String cState;
    private int Belong;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcState() {
        return cState;
    }

    public void setcState(String cState) {
        this.cState = cState;
    }

    public int getBelong() {
        return Belong;
    }

    public void setBelong(int belong) {
        Belong = belong;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cState='" + cState + '\'' +
                ", Belong=" + Belong +
                '}';
    }
}

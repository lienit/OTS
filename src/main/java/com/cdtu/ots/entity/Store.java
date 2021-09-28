package com.cdtu.ots.entity;

public class Store {
    private  int sId;
    private  String  sName;
    private int sUserId;

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getsUserId() {
        return sUserId;
    }

    public void setsUserId(int sUserId) {
        this.sUserId = sUserId;
    }

    @Override
    public String toString() {
        return "Store{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", sUserId=" + sUserId +
                '}';
    }
}

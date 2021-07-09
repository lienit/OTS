package com.cdtu.ots.entity;

public class AdminCategory {
    private int cId;
    private String cName;
    private String cState;
    private int Belong;


    public AdminCategory(){

    }

    public AdminCategory(int cId, String cName, String cState, int Belong){
        this.cId = cId;
        this.cName = cName;
        this.cState = cState;
        this.Belong = Belong;
    }

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

    public void setBelong(int Belong) {
        this.Belong = Belong;
    }


    @Override
    public String toString() {
        return "Category{" +
                "cId=" + cId +
                ", cName=" + cName +'\''+
                ", cState=" + cState +'\''+
                ", Belong=" + Belong +'\''+
                '}';
    }
}

package com.cdtu.ots.entity;

public class Address {
    private int aId;
    private int aUserid;
    private String aAddress;
    private String aPostcode;
    private String aConsignee;
    private String aPhone;
    private Boolean isDefault;


    public void setaId(int aId) {
        this.aId = aId;
    }

    public int getaId() {
        return aId;
    }

    public int getaUserid() {
        return aUserid;
    }

    public void setaUserid(int aUserid) {
        this.aUserid = aUserid;
    }

    public String getaAddress() {
        return aAddress;
    }

    public void setaAddress(String aAddress) {
        this.aAddress = aAddress;
    }

    public String getaPostcode() {
        return aPostcode;
    }

    public void setaPostcode(String aPostcode) {
        this.aPostcode = aPostcode;
    }

    public String getaConsignee() {
        return aConsignee;
    }

    public void setaConsignee(String aConsignee) {
        this.aConsignee = aConsignee;
    }

    public String getaPhone() {
        return aPhone;
    }

    public void setaPhone(String aPhone) {
        this.aPhone = aPhone;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "aId=" + aId +
                ", aUserid=" + aUserid +
                ", aAddress='" + aAddress + '\'' +
                ", aPostcode='" + aPostcode + '\'' +
                ", aConsignee='" + aConsignee + '\'' +
                ", aPhone='" + aPhone + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}

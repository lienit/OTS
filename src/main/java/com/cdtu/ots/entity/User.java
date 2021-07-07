package com.cdtu.ots.entity;

public class User {
    private int uId;
    private String userName;
    private String password;
    private String uPhone;
    private String uEmail;
    private String uLevel;
    private String uImage;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuLevel() {
        return uLevel;
    }

    public void setuLevel(String uLevel) {
        this.uLevel = uLevel;
    }

    public String getuImage() {
        return uImage;
    }

    public void setuImage(String uImage) {
        this.uImage = uImage;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", uPhone='" + uPhone + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uLevel='" + uLevel + '\'' +
                ", uImage='" + uImage + '\'' +
                '}';
    }
}

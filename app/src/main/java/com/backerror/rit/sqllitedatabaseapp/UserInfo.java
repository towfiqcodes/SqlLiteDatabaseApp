package com.backerror.rit.sqllitedatabaseapp;

public class UserInfo {
    private int UserId;
    private String userName;
    private String phoneNumber;

    public UserInfo(int userId, String userName, String phoneNumber) {
        UserId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public UserInfo(String userName, String phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }
    public  UserInfo(){}

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

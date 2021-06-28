package com.example.spreadsheetexampl;

public class MyModel {
    private String userdate,userId,username,userNumber;

    public MyModel(String userdate, String userId, String username, String userNumber) {
        this.userdate = userdate;
        this.userId = userId;
        this.username = username;
        this.userNumber = userNumber;
    }

    public MyModel() {
    }

    public String getUserdate() {
        return userdate;
    }

    public void setUserdate(String userdate) {
        this.userdate = userdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
}

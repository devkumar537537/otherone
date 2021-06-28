package com.example.firebaseconcepts;

public class UserModel {
    private String email;
    private String name;
    private String number;
    private String password;
    private String userid;

    public UserModel(String email, String name, String number, String password, String userid) {
        this.email = email;
        this.name = name;
        this.number = number;
        this.password = password;
        this.userid = userid;
    }

    public UserModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

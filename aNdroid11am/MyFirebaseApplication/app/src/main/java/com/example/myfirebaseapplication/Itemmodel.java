package com.example.myfirebaseapplication;

public class Itemmodel {
    private String email;
    private String name;
    private String password;
    private String userid;

    public Itemmodel(String email, String name, String password, String userid) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.userid = userid;
    }

    public Itemmodel() {
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

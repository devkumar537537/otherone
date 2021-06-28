package com.example.firebaseconcetpiexample.models;

import java.util.ArrayList;

public class Usermodel {
    private String email;
    private String number;
    private String password;
    private String name;
    private String userid;


    public Usermodel(String email, String number, String password, String name, String userid) {
        this.email = email;
        this.number = number;
        this.password = password;
        this.name = name;
        this.userid = userid;
    }

    public Usermodel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

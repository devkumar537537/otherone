package com.example.androidspreadsheets;

public class EamilModels {
    private String id;
    private String email;
    private String number;
    private String date;

    public EamilModels(String id, String email, String number, String date) {
        this.id = id;
        this.email = email;
        this.number = number;
        this.date = date;
    }

    public EamilModels() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

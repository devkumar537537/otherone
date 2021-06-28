package com.example.typesofadapters.models;

public class Itemnumber {
    private int imageurl;

    private String number;

    public Itemnumber(int imageurl, String number) {
        this.imageurl = imageurl;
        this.number = number;
    }

    public Itemnumber() {
    }

    public int getImageurl() {
        return imageurl;
    }

    public void setImageurl(int imageurl) {
        this.imageurl = imageurl;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

package com.example.recycleradapter;

public class RecyclerModel {
    private String name;
    private int imagurl;

    public RecyclerModel(String name, int imagurl) {
        this.name = name;
        this.imagurl = imagurl;
    }

    public RecyclerModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImagurl() {
        return imagurl;
    }

    public void setImagurl(int imagurl) {
        this.imagurl = imagurl;
    }
}

package com.example.adapterexamples;

public class ModelItem {
   private String name;
   private int imageurl;

    public ModelItem(String name, int imageurl) {
        this.name = name;
        this.imageurl = imageurl;
    }

    public ModelItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageurl() {
        return imageurl;
    }

    public void setImageurl(int imageurl) {
        this.imageurl = imageurl;
    }
}

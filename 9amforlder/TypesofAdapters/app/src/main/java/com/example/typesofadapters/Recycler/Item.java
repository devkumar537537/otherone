package com.example.typesofadapters.Recycler;

public class Item {
   private int imageurl;
   private String name;

    public Item(int imageurl, String name) {
        this.imageurl = imageurl;
        this.name = name;
    }

    public Item() {
    }

    public int getImageurl() {
        return imageurl;
    }

    public void setImageurl(int imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

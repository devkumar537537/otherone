package com.example.differentadapters.models;

public class ModelItem {
    private int imageurl;
    private String usernumber;

    public ModelItem(int imageurl, String usernumber) {
        this.imageurl = imageurl;
        this.usernumber = usernumber;
    }

    public ModelItem() {
    }

    public int getImageurl() {
        return imageurl;
    }

    public void setImageurl(int imageurl) {
        this.imageurl = imageurl;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }
}

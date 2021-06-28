package com.example.myfirebaseapplication;

public class ImageModelClass {
    private String imagurl;
    private String userid;

    public ImageModelClass(String imagurl, String userid) {
        this.imagurl = imagurl;
        this.userid = userid;
    }

    public ImageModelClass() {
    }

    public String getImagurl() {
        return imagurl;
    }

    public void setImagurl(String imagurl) {
        this.imagurl = imagurl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

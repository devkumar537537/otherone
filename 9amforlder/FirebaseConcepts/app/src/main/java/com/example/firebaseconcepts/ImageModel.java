package com.example.firebaseconcepts;

public class ImageModel {
    private String imagurl;
    private String userid;

    public ImageModel(String imagurl, String userid) {
        this.imagurl = imagurl;
        this.userid = userid;
    }

    public ImageModel() {
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

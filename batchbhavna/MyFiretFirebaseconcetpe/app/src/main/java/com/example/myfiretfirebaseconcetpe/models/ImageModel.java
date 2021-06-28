package com.example.myfiretfirebaseconcetpe.models;

public class ImageModel {
    private String imageurl;
    private String userid;

    public ImageModel(String imageurl, String userid) {
        this.imageurl = imageurl;
        this.userid = userid;
    }

    public ImageModel() {
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

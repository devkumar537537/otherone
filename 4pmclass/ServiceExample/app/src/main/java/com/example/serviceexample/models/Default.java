package com.example.serviceexample.models;

public class Default{
    public String url;
    public int width;
    public int height;

    public Default(String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public Default() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

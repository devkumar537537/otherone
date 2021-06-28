package com.example.serviceexample.models;

import com.google.gson.annotations.SerializedName;

public class Thumbnails{

    @SerializedName("default")
    public Default defaults;

    public Medium medium;
    public High high;

    public Thumbnails(Default defaults, Medium medium, High high) {
        this.defaults = defaults;
        this.medium = medium;
        this.high = high;
    }

    public Thumbnails() {
    }

    public Default getDefaults() {
        return defaults;
    }

    public void setDefaults(Default defaults) {
        this.defaults = defaults;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }
}

package com.example.contentproviders;

public class Item {
   private String name;
    private String number;

    public Item(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

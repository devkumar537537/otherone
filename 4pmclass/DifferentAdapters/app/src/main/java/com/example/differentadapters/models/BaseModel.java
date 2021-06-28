package com.example.differentadapters.models;

public class BaseModel {
    private String studentnumber;
    private int studentimage;

    public BaseModel(String studentnumber, int studentimage) {
        this.studentnumber = studentnumber;
        this.studentimage = studentimage;
    }

    public BaseModel() {
    }

    public String getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(String studentnumber) {
        this.studentnumber = studentnumber;
    }

    public int getStudentimage() {
        return studentimage;
    }

    public void setStudentimage(int studentimage) {
        this.studentimage = studentimage;
    }
}

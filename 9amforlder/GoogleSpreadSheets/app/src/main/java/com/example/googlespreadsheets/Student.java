package com.example.googlespreadsheets;

public class Student {
    String studentdate;
    String studentId;
    String studentemail;
    String studentNumber;

    public Student(String studentdate, String studentId, String studentemail, String studentNumber) {
        this.studentdate = studentdate;
        this.studentId = studentId;
        this.studentemail = studentemail;
        this.studentNumber = studentNumber;
    }

    public Student() {
    }

    public String getStudentdate() {
        return studentdate;
    }

    public void setStudentdate(String studentdate) {
        this.studentdate = studentdate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}

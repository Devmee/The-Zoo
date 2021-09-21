package com.example.zooapp;

public class GtBook {

    private String Dv_email;
    private String Tnation;
    private String Dv_Adult;
    private String Dv_Child;
    private String Dv_date;
    private String userID;

    public GtBook(String dv_email, String tnation, String dv_Adult, String dv_Child, String dv_date, String userID) {
        Dv_email = dv_email;
        Tnation = tnation;
        Dv_Adult = dv_Adult;
        Dv_Child = dv_Child;
        Dv_date = dv_date;
        this.userID = userID;
    }

    public String getDv_email() {
        return Dv_email;
    }

    public String getTnation() {
        return Tnation;
    }

    public String getDv_Adult() {
        return Dv_Adult;
    }

    public String getDv_Child() {
        return Dv_Child;
    }

    public String getDv_date() {
        return Dv_date;
    }

    public String getUserID() {
        return userID;
    }
}

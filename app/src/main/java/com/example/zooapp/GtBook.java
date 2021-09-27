package com.example.zooapp;

public class GtBook {

    private String Dv_email;
    private String Tnation;
    private String Dv_Adult;
    private String Dv_Child;
    private String Dv_date;
    private String userID;
    private  String tktKeyValue;

    public GtBook(String tktKeyValue,String dv_email, String tnation, String dv_Adult, String dv_Child, String dv_date, String userID) {
        Dv_email = dv_email;
        Tnation = tnation;
        Dv_Adult = dv_Adult;
        Dv_Child = dv_Child;
        Dv_date = dv_date;
        this.userID = userID;
        this.tktKeyValue = tktKeyValue;

    }

    public void setDv_email(String dv_email) {
        Dv_email = dv_email;
    }

    public void setTnation(String tnation) {
        Tnation = tnation;
    }

    public void setDv_Adult(String dv_Adult) {
        Dv_Adult = dv_Adult;
    }

    public void setDv_Child(String dv_Child) {
        Dv_Child = dv_Child;
    }

    public void setDv_date(String dv_date) {
        Dv_date = dv_date;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTktKeyValue() {
        return tktKeyValue;
    }

    public void setTktKeyValue(String tktKeyValue) {
        this.tktKeyValue = tktKeyValue;
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

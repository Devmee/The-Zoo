package com.example.zooapp;

public class GuidModel {

    private String tktKeyValue;
    String dv_Adult,dv_Child,dv_date,dv_email,tnation,userID;

    public GuidModel(String tktKeyValue, String dv_Adult, String dv_Child, String dv_date, String dv_email, String tnation, String userID) {
        this.tktKeyValue = tktKeyValue;
        this.dv_Adult = dv_Adult;
        this.dv_Child = dv_Child;
        this.dv_date = dv_date;
        this.dv_email = dv_email;
        this.tnation = tnation;
        this.userID = userID;
    }

    public String getTktKeyValue() {
        return tktKeyValue;
    }

    public void setTktKeyValue(String tktKeyValue) {
        this.tktKeyValue = tktKeyValue;
    }

    public String getDv_Adult() {
        return dv_Adult;
    }

    public void setDv_Adult(String dv_Adult) {
        this.dv_Adult = dv_Adult;
    }

    public String getDv_Child() {
        return dv_Child;
    }

    public void setDv_Child(String dv_Child) {
        this.dv_Child = dv_Child;
    }

    public String getDv_date() {
        return dv_date;
    }

    public void setDv_date(String dv_date) {
        this.dv_date = dv_date;
    }

    public String getDv_email() {
        return dv_email;
    }

    public void setDv_email(String dv_email) {
        this.dv_email = dv_email;
    }

    public String getTnation() {
        return tnation;
    }

    public void setTnation(String tnation) {
        this.tnation = tnation;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

package com.example.zooapp;

public class ShowModel {

    String aq_Seat,aq_date,aqanimal,aqtime,b_Seat,b_date,btime,userID;
    private String tktKeyValue;

    public ShowModel(String aq_Seat, String aq_date, String aqanimal, String aqtime, String b_Seat, String b_date, String btime, String userID, String tktKeyValue) {
        this.aq_Seat = aq_Seat;
        this.aq_date = aq_date;
        this.aqanimal = aqanimal;
        this.aqtime = aqtime;
        this.b_Seat = b_Seat;
        this.b_date = b_date;
        this.btime = btime;
        this.userID = userID;
        this.tktKeyValue = tktKeyValue;



    }

    public String getAq_Seat() {
        return aq_Seat;
    }

    public void setAq_Seat(String aq_Seat) {
        this.aq_Seat = aq_Seat;
    }

    public String getAq_date() {
        return aq_date;
    }

    public void setAq_date(String aq_date) {
        this.aq_date = aq_date;
    }

    public String getAqanimal() {
        return aqanimal;
    }

    public void setAqanimal(String aqanimal) {
        this.aqanimal = aqanimal;
    }

    public String getAqtime() {
        return aqtime;
    }

    public void setAqtime(String aqtime) {
        this.aqtime = aqtime;
    }

    public String getB_Seat() {
        return b_Seat;
    }

    public void setB_Seat(String b_Seat) {
        this.b_Seat = b_Seat;
    }

    public String getB_date() {
        return b_date;
    }

    public void setB_date(String b_date) {
        this.b_date = b_date;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getUserID() {
        return userID;
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
}

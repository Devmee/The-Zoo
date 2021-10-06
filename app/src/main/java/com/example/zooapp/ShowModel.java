package com.example.zooapp;

public class ShowModel {

    String aq_Seat,aq_date,aqanimal,aqtime,userID;
    private String tktKeyValue;

    public ShowModel(String tktKeyValue,String aqanimal,String aq_Seat,String aqtime,String aq_date,   String userID) {
        this.aq_Seat = aq_Seat;
        this.aq_date = aq_date;
        this.aqanimal = aqanimal;
        this.aqtime = aqtime;

        this.userID = userID;
        this.tktKeyValue = tktKeyValue;



    }
    public ShowModel(){}

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

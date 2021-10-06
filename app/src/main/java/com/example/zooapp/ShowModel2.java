package com.example.zooapp;

public class ShowModel2 {

    String b_Seat,b_date,btime,userID;
    private String tktKeyValue;

    public ShowModel2(String b_Seat, String b_date, String btime, String userID, String tktKeyValue) {
        this.b_Seat = b_Seat;
        this.b_date = b_date;
        this.btime = btime;
        this.userID = userID;
        this.tktKeyValue = tktKeyValue;
    }

    public ShowModel2(){}

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

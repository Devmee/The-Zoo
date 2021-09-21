package com.example.zooapp;

public class B_Show {


    private String B_Seat;
    private String Btime;
    private String B_date;
    private String userID;

    public B_Show(String b_Seat, String btime, String b_date, String userID) {
        B_Seat = b_Seat;
        Btime = btime;
        B_date = b_date;
        this.userID = userID;
    }

    public String getB_Seat() {
        return B_Seat;
    }

    public String getBtime() {
        return Btime;
    }

    public String getB_date() {
        return B_date;
    }

    public String getUserID() {
        return userID;
    }
}

package com.example.zooapp;

public class Ticket {

    private String S_National;
    private String S_Adult;
    private String S_Child;
    private String etDate;
    private String userID;

    public Ticket(String s_National, String s_Adult, String s_Child, String etDate, String userID) {
        S_National = s_National;
        S_Adult = s_Adult;
        S_Child = s_Child;
        this.etDate = etDate;
        this.userID = userID;
    }

    public String getS_National() {
        return S_National;
    }

    public String getS_Adult() {
        return S_Adult;
    }

    public String getS_Child() {
        return S_Child;
    }

    public String getEtDate() {
        return etDate;
    }

    public String getUserID() {
        return userID;
    }
}

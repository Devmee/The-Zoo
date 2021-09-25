package com.example.zooapp;

public class Ticket {

    private String S_National;
    private String S_Adult;
    private String S_Child;
    private String etDate;
    private int number_child;
    private int number_adult;
    private double child_t_Amount;
    private double adult_t_Amount;
    private double total_amount;
    private  String userID;
    private  String tktKeyValue;

    public Ticket(String s_National, String tktKeyValue,String s_Adult, String s_Child, String etDate, int number_child, int number_adult, double child_t_Amount, double adult_t_Amount, double total_amount, String userID) {
        S_National = s_National;
        S_Adult = s_Adult;
        S_Child = s_Child;
        this.etDate = etDate;
        this.number_child = number_child;
        this.number_adult = number_adult;
        this.child_t_Amount = child_t_Amount;
        this.adult_t_Amount = adult_t_Amount;
        this.total_amount = total_amount;
        this.tktKeyValue = tktKeyValue;
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

    public int getNumber_child() {
        return number_child;
    }

    public int getNumber_adult() {
        return number_adult;
    }

    public double getChild_t_Amount() {
        return child_t_Amount;
    }

    public double getAdult_t_Amount() {
        return adult_t_Amount;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public String getTktKeyValue() {
        return tktKeyValue;
    }

    public String getUserID() {
        return userID;
    }


}

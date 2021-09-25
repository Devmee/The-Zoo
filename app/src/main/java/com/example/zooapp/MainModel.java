package com.example.zooapp;

public class MainModel {

    private String tktKeyValue;
    String etDate, s_Adult, s_Child, s_National, userID;
    private double total_amount;
    private double child_t_Amount;
    private double adult_t_Amount;
    private int number_child;
    private int number_adult;

    MainModel()

    {


    }

    public MainModel(String userID, String tktKeyValue, String s_National,String s_Adult,String etDate, String s_Child,int number_child,int number_adult,double child_t_Amount,double adult_t_Amount,double total_amount) {
        this.etDate = etDate;
        this.s_Adult = s_Adult;
        this.s_Child = s_Child;
        this.s_National = s_National;
        this.userID = userID;
        this.total_amount = total_amount;
        this.child_t_Amount = child_t_Amount;
        this.adult_t_Amount = adult_t_Amount;
        this.tktKeyValue = tktKeyValue;
        this.number_child = number_child;
        this.number_adult = number_adult;
    }

    public String getEtDate() {
        return etDate;
    }

    public void setEtDate(String etDate) {
        this.etDate = etDate;
    }

    public String getS_Adult() {
        return s_Adult;
    }

    public void setS_Adult(String s_Adult) {
        this.s_Adult = s_Adult;
    }

    public String getS_Child() {
        return s_Child;
    }

    public void setS_Child(String s_Child) {
        this.s_Child = s_Child;
    }

    public String getS_National() {
        return s_National;
    }

    public void setS_National(String s_National) {
        this.s_National = s_National;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getChild_t_Amount() {
        return child_t_Amount;
    }

    public double getAdult_t_Amount() {
        return adult_t_Amount;
    }

    public String getTktKeyValue() {
        return tktKeyValue;
    }


    public int getNumber_child() {
        return number_child;
    }

    public int getNumber_adult() {
        return number_adult;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }




}

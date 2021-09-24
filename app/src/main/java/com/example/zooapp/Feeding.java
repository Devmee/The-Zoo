package com.example.zooapp;

import android.widget.EditText;
import android.widget.Spinner;

public class Feeding {
    private String animal;
    private String time;
    private String a_date;
    private String ch_ad;
    private String age;
    private String userID;
    private String tktKeyValue;

    public Feeding(String animal, String time, String a_date, String ch_ad, String age, String userID,String tktKeyValue) {
        this.animal = animal;
        this.time = time;
        this.a_date = a_date;
        this.ch_ad = ch_ad;
        this.age = age;
        this.userID = userID;
        this.tktKeyValue=tktKeyValue;
    }

    public String getAnimal() {
        return animal;
    }

    public String getTime() {
        return time;
    }

    public String getA_date() {
        return a_date;
    }

    public String getCh_ad() {
        return ch_ad;
    }

    public String getAge() {
        return age;
    }

    public String getUserID() {
        return userID;
    }

    public String getTktKeyValue() {
        return tktKeyValue;
    }


}

package com.example.zooapp;

public class feedingMod {


        private String animal;
        private String time;
        private String a_date;
        private String ch_ad;
        private String age;
        private String userID;
        private String tktKeyValue;

        public feedingMod(String animal, String time, String a_date, String ch_ad, String age, String userID, String tktKeyValue) {
            this.animal = animal;
            this.time = time;
            this.a_date = a_date;
            this.ch_ad = ch_ad;
            this.age = age;
            this.userID = userID;
            this.tktKeyValue = tktKeyValue;
        }

        public String getAnimal() {
            return animal;
        }

        public void setAnimal(String animal) {
            this.animal = animal;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getA_date() {
            return a_date;
        }

        public void setA_date(String a_date) {
            this.a_date = a_date;
        }

        public String getCh_ad() {
            return ch_ad;
        }

        public void setCh_ad(String ch_ad) {
            this.ch_ad = ch_ad;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
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



package com.example.zooapp;

public class AqShow {

        private String Aqanimal;
        private String Aq_Seat;
        private String Aqtime;
        private String Aq_date;
        private String userID;
        private  String tktKeyValue;

        public AqShow(String tktKeyValue,String aqanimal, String aq_Seat, String aqtime, String aq_date, String userID) {
                Aqanimal = aqanimal;
                Aq_Seat = aq_Seat;
                Aqtime = aqtime;
                Aq_date = aq_date;
                this.userID = userID;
                this.tktKeyValue = tktKeyValue;
        }


        public String getTktKeyValue() {
                return tktKeyValue;}

        public String getAqanimal() {
                return Aqanimal;
        }

        public String getAq_Seat() {
                return Aq_Seat;
        }

        public String getAqtime() {
                return Aqtime;
        }

        public String getAq_date() {
                return Aq_date;
        }

        public String getUserID() {
                return userID;
        }
}

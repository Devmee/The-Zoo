package com.example.zooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class guided_tour_home extends AppCompatActivity {

    Button Rep1,Rep2,Rep3;
    String getmail,userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_tour_home);
        getSupportActionBar().hide();

        Rep1= findViewById(R.id.dv_btn1);
        Rep2 = findViewById(R.id.dv_btn2);
        Rep3 = findViewById(R.id.dv_btn3);



        getmail = getIntent().getStringExtra("keyEmail");
        userID = getIntent().getStringExtra("keyuserID");


        //go to the Ticket Booking
        Rep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(guided_tour_home.this,TourBooking.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });
        //go to the previous Ticket booking
        Rep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(guided_tour_home.this, TourBooking.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

        //go to the previous Ticket booking
        Rep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(guided_tour_home.this, TourBooking.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });



    }

}
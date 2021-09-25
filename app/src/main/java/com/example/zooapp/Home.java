package com.example.zooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    Button T_Book,AF_Book,AS_Book,G_Book,user_view;
    TextView PT_Book,PAF_Book,PAS_Book,PG_Book;
    String getmail,userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        T_Book= findViewById(R.id.H_BookT);
        AF_Book = findViewById(R.id.H_AnimalF);
        AS_Book = findViewById(R.id.H_AnimalS);
        G_Book = findViewById(R.id.H_GuidedT);
        PT_Book= findViewById(R.id.BookPT);
        PAF_Book = findViewById(R.id.BookPAF);
        PAS_Book = findViewById(R.id.BookPAS);
        PG_Book = findViewById(R.id.BookPG);
        user_view = findViewById(R.id.profile_view_btn);


        getmail = getIntent().getStringExtra("keyEmail");
        userID = getIntent().getStringExtra("keyuserID");



        //go to the user profile
       user_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, LoginDetails.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });
        //go to the Ticket Booking
        T_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Ticketbooking.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });
        //go to the previous Ticket booking
        PT_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ViewTicketBooking.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

        //go to the Animal Feeding booking
        AF_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AnimalFeeding.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

        //go to the previous Feeding booking
        PAF_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AnimalFeedingView.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

        //go to the Animal Show
        AS_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AnimalShowHome.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

        //go to the previous Animal Show booking
        PAS_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Bird_Show_Details.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });


        //go to the guided tour booking
        G_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, guided_tour_home.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

        //go to the previous guided tour booking
        PG_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, guided_tour_home.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

    }

}
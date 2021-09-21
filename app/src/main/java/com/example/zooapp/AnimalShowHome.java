package com.example.zooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnimalShowHome extends AppCompatActivity {

    Button AnimalB,BirdB;
    String getmail,userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_show_home);
        getSupportActionBar().hide();

        AnimalB= findViewById(R.id.B_animalShow_btn);
        BirdB = findViewById(R.id.B_BirdShow_btn);



        getmail = getIntent().getStringExtra("keyEmail");
        userID = getIntent().getStringExtra("keyuserID");


        //go to the Ticket Booking
        AnimalB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimalShowHome.this,AquariumShow.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });
        //go to the previous Ticket booking
        BirdB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimalShowHome.this, BirdShow.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });




    }




}
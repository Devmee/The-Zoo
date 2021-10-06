package com.example.zooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnimalShowHome extends AppCompatActivity {

    Button AnimalB,BirdB;
    TextView AqShowD1,BShowD1;
    String getmail,userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_show_home);
        getSupportActionBar().hide();

        AnimalB= findViewById(R.id.B_animalShow_btn);
        BirdB = findViewById(R.id.B_BirdShow_btn);
        AqShowD1=findViewById(R.id.AqShowD);
        BShowD1=findViewById(R.id.BShowD);



        getmail = getIntent().getStringExtra("keyEmail");
        userID = getIntent().getStringExtra("keyuserID");


        //go to the animal Show
        AnimalB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimalShowHome.this,AquariumShow.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });
        //go to the Aqshow  Show Details
        AqShowD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimalShowHome.this, ShowView.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

        //go to the bird Show
        BirdB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimalShowHome.this, BirdShow.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

        //go to the Birdshow  Show Details
        BShowD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimalShowHome.this, BirdShowVIew.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });




    }




}
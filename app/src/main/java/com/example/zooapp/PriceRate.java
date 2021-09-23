package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PriceRate extends AppCompatActivity {


    DatabaseReference reff;
    TextView P_LocalAdult,P_LocalChild,P_FAdult,P_FChild;
    FirebaseAuth fauth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_rate);
        getSupportActionBar().hide();

        P_LocalAdult = findViewById(R.id.S_Padult2);
        P_LocalChild = findViewById(R.id.S_Pchild2);
        P_FAdult = findViewById(R.id.S_Fadult2);
        P_FChild = findViewById(R.id.S_Fchild2);


        reff = FirebaseDatabase.getInstance().getReference("TicketPrice").child("PriceRate");



        //get value from the database
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1 = String.valueOf(snapshot.child("Foreign_adult").getValue(Long.class));
                String Option2= String.valueOf(snapshot.child("Foreign_child").getValue(Long.class));
                String Option3 = String.valueOf(snapshot.child("Local_adult").getValue(Long.class));
                String Option4 = String.valueOf(snapshot.child("Local_child").getValue(Long.class));



                P_FAdult.setText(Option1);
                P_FChild.setText(Option2);
                P_LocalAdult.setText(Option3);
                P_LocalChild.setText(Option4);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
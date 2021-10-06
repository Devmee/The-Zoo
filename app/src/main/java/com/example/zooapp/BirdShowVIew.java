package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BirdShowVIew extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference reff;
    ShowAdapter2 showAdapter2;
    ArrayList<ShowModel2> list;

    String userID,email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_show_view);

        recyclerView =(RecyclerView)findViewById(R.id.BirdShowView);
        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");
        reff= FirebaseDatabase.getInstance().getReference("AnimalShow").child("BirdShow").child(userID);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list =new ArrayList<>();
        showAdapter2 =new ShowAdapter2(this,list);
        recyclerView.setAdapter(showAdapter2);

        reff.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    ShowModel2 showModel2 = dataSnapshot.getValue(ShowModel2.class);
                    list.add(showModel2);




                }
                showAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}

package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnimalFeedingView extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference reff;
    MyAdapter myAdapter;
    ArrayList<feedingMod>mList;
    String userID,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_feeding_view);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");
        reff= FirebaseDatabase.getInstance().getReference("Feed").child("Feeding");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();
        myAdapter =new MyAdapter(this,mList);
        recyclerView.setAdapter(myAdapter);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    feedingMod F =dataSnapshot.getValue(feedingMod.class);
                    mList.add(F);

                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}
package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GuidedView extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference reff;
    GuidAdapter guidAdapter;
    ArrayList<GuidModel> list;
    String userID,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_view);

        recyclerView=(RecyclerView)findViewById(R.id.guiderecyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");
        reff= FirebaseDatabase.getInstance().getReference("GuidedTour").child("GTour").child(uid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        guidAdapter =new GuidAdapter(this,list);
        recyclerView.setAdapter(guidAdapter);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    GuidModel guidModel =dataSnapshot.getValue(GuidModel.class);
                    list.add(guidModel);

                }
                guidAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
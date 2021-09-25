package com.example.zooapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<feedingMod> mList;
    Context context;


    public MyAdapter(Context context, ArrayList<feedingMod> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item2,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        feedingMod F = mList.get(position);


        holder.animal.setText(F.getAnimal());
        holder.time.setText(F.getTime());
        holder.a_date.setText(F.getA_date());
        holder.ch_ad.setText(F.getCh_ad());
        holder.age.setText(F.getAge());

        //String norderId = String.valueOf(member.getTktKeyValue());

        holder.userID.setText(F.getUserID());
        holder.tktKeyValue.setText(F.getTktKeyValue());

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView animal,time,a_date,ch_ad,age, userID,tktKeyValue;






        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            animal = itemView.findViewById(R.id.tvfirstName);
            time = itemView.findViewById(R.id.tvlastName);
            a_date = itemView.findViewById(R.id.Date);
            ch_ad = itemView.findViewById(R.id.child);
            age = itemView.findViewById(R.id.tota);

            userID = itemView.findViewById(R.id.Oid);
            tktKeyValue = itemView.findViewById(R.id.usid);




        }
       
    }


}

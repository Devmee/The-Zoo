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



        feedingMod  fee = mList.get(position);


        holder.animal.setText(fee.getAnimal());
        holder.time.setText(fee.getTime());
        holder.a_date.setText(fee.getA_date());
        holder.ch_ad.setText(fee.getCh_ad());
        holder.age.setText(fee.getAge());

        //String norderId = String.valueOf(member.getTktKeyValue());

        holder.userID.setText(fee.getUserID());
        holder.orderID.setText(fee.getTktKeyValue());

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView animal,time,a_date,ch_ad,age, userID,orderID;






        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            animal = itemView.findViewById(R.id.dype);
            time = itemView.findViewById(R.id.Dtime);
            a_date = itemView.findViewById(R.id.Ddate);
            ch_ad = itemView.findViewById(R.id.Dcat);
            age = itemView.findViewById(R.id.Dage);

            orderID  = itemView.findViewById(R.id.orderIDD);
            userID = itemView.findViewById(R.id.userIDD);






        }
       
    }


}

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

    ArrayList<Feeding> mList;
    Context context;


    public MyAdapter(Context context, ArrayList<Feeding> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Feeding D = mList.get(position);


        holder.animal.setText(D.getAnimal());
        holder.time.setText(D.getTime());
        holder.a_date.setText(D.getA_date());
        holder.ch_ad.setText(D.getCh_ad());
        holder.age.setText(D.getAge());

        //String norderId = String.valueOf(member.getTktKeyValue());

        holder.userID.setText(D.getUserID());
        holder.tktKeyValue.setText(D.getTktKeyValue());

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView animal,time,a_date,ch_ad,age, userID,tktKeyValue;
        Button deleteBtn;





        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            animal = itemView.findViewById(R.id.park_txt);
            time = itemView.findViewById(R.id.type_txt);
            a_date = itemView.findViewById(R.id.fulltkt_txt);
            ch_ad = itemView.findViewById(R.id.halftkt_txt);
            age = itemView.findViewById(R.id.fulltktAmount_txt);
            deleteBtn = itemView.findViewById(R.id.deleteSaf);
            userID = itemView.findViewById(R.id.userID);
            tktKeyValue = itemView.findViewById(R.id.orderID_txt);


            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String useID = userID.getText().toString();
                            String  Vaniaml= animal.getText().toString();
                            String VTime = time.getText().toString();
                            String Vdate = a_date.getText().toString();
                            String Vch = ch_ad.getText().toString();
                            String Vage = age.getText().toString();
                            String Vkey = tktKeyValue.getText().toString();


                            Feeding  Dfeed = new Feeding( useID,Vaniaml,VTime,Vdate,Vch,Vage,Vkey);
                            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Feed").child("Feeding").child(useID).child(String.valueOf(Dfeed.getTktKeyValue()));

                            Task<Void> mTsk = dbref.removeValue();
                            //Toast.makeText(context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                            mTsk.addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    context.startActivity(new Intent(context, AnimalFeedingView.class).putExtra("keyuserID", useID));
                                    Toast.makeText(context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                                    //showToast("Deleted Success!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context,"UnSuccessfull",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).setNegativeButton("Cancle", null);

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();




                }
            });

        }
       
    }

}

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
    static Context context;


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
        Button deleteBtn;





        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            animal = itemView.findViewById(R.id.dype);
            time = itemView.findViewById(R.id.Dtime);
            a_date = itemView.findViewById(R.id.Ddate);
            ch_ad = itemView.findViewById(R.id.Dcat);
            age = itemView.findViewById(R.id.Dage);

            orderID  = itemView.findViewById(R.id.orderIDD);
            userID = itemView.findViewById(R.id.userIDD);

            deleteBtn = itemView.findViewById(R.id.deleteDj);

            deleteBtn.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            String djanimal = animal.getText().toString();
                            String djtime =time.getText().toString();
                            String djdate = a_date.getText().toString();
                            String djac = ch_ad.getText().toString();
                            String djage = age.getText().toString();
                            String tktKeyValue = orderID.getText().toString();
                            String useID = userID.getText().toString();




                            feedingMod feedingmod   = new feedingMod (useID,tktKeyValue,djanimal,djtime,djdate,djac,djage);
                            DatabaseReference reff = FirebaseDatabase.getInstance().getReference("Feeding").child("Feed").child(useID).child(String.valueOf(feedingmod.getTktKeyValue()));

                            Task<Void> mTsk = reff.removeValue();
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

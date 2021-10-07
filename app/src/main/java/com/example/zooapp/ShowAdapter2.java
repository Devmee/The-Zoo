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

public class ShowAdapter2 extends RecyclerView.Adapter<ShowAdapter2.MyViewHolder> {


    ArrayList<ShowModel2> list;
    static Context context;

    public ShowAdapter2(Context context, ArrayList<ShowModel2> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ShowAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item5,parent,false);
        return new ShowAdapter2.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ShowModel2 showModel2 =list.get(position);

        holder.b_Seat.setText(showModel2.getB_Seat());
        holder.b_date.setText(showModel2.getB_date());
        holder.btime.setText(showModel2.getBtime());
        holder.userID.setText(showModel2.getUserID());
        holder.orderID.setText(showModel2.getTktKeyValue());


    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView b_Seat, b_date, btime, userID, orderID;
        Button deleteBtn;
        private Context context;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            b_Seat = itemView.findViewById(R.id.Numseat22);
            b_date = itemView.findViewById(R.id.showdate22);
            btime = itemView.findViewById(R.id.showtime22);
            orderID = itemView.findViewById(R.id.orderIDb2);
            userID = itemView.findViewById(R.id.userIDb2);

            deleteBtn = itemView.findViewById(R.id.deleteShow2);


            //Delete Bird Show
            deleteBtn.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowAdapter2.context);

                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            String BBseat = b_Seat.getText().toString();
                            String BBdate = b_date.getText().toString();
                            String BBtime = btime.getText().toString();

                            String useID = userID.getText().toString();
                            String tktKeyValue = orderID.getText().toString();




                            ShowModel2  showModel2 = new ShowModel2( BBseat,BBdate,BBtime,useID,tktKeyValue);
                            DatabaseReference reff = FirebaseDatabase.getInstance().getReference("AnimalShow").child("BirdShow").child(useID).child(String.valueOf(showModel2.getTktKeyValue()));

                            Task<Void> mTsk = reff.removeValue();
                            //Toast.makeText(context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                            mTsk.addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    ShowAdapter2.context.startActivity(new Intent(ShowAdapter2.context, BirdShowVIew.class).putExtra("keyuserID", useID));
                                    Toast.makeText(ShowAdapter2.context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                                    //showToast("Deleted Success!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ShowAdapter2.context,"UnSuccessfull",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).setNegativeButton("Cancle", null);

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();




                }
            });

        }
    }}

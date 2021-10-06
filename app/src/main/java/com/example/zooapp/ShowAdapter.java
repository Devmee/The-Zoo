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

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.MyViewHolder> {



    ArrayList<ShowModel> list;
     static Context context;

    public ShowAdapter(Context context, ArrayList<ShowModel> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item3,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ShowModel showModel =list.get(position);
        holder.aq_Seat.setText(showModel.getAq_Seat());
        holder.aq_date.setText(showModel.getAq_date());
        holder.aqanimal.setText(showModel.getAqanimal());
        holder.aqtime.setText(showModel.getAqtime());
        //holder.b_Seat.setText(showModel2.getB_Seat());
       // holder.b_date.setText(showModel2.getB_date());
       // holder.btime.setText(showModel2.getBtime());
        holder.orderID.setText(showModel.getTktKeyValue());
        holder.userID.setText(showModel.getUserID());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView aq_Seat,aq_date,aqanimal,aqtime,b_Seat,b_date,btime,orderID,userID;
        Button deleteBtn;
        private Context context;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            aq_Seat =itemView.findViewById(R.id.Numseat1);
            aq_date=itemView.findViewById(R.id.showdate1);
            aqanimal=itemView.findViewById(R.id.atype);
            aqtime=itemView.findViewById(R.id.fshowtime1);
           // b_Seat =itemView.findViewById(R.id.Numseat2);
            //b_date=itemView.findViewById(R.id.showdate2);
           // btime=itemView.findViewById(R.id.showtime2);
            orderID = itemView.findViewById(R.id.orderIDb);
            userID=itemView.findViewById(R.id.userIDb);
            deleteBtn = itemView.findViewById(R.id.deleteShow);



            deleteBtn.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowAdapter.context);

                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String useID = userID.getText().toString();
                            String Bseat = aq_Seat.getText().toString();
                            String Bdate = aq_date.getText().toString();
                            String Banimal = aqanimal.getText().toString();
                            String Btime = aqtime.getText().toString();
                            // String BBseat = b_Seat.getText().toString();
                            //String BBdate = b_date.getText().toString();
                            //String BBtime = btime.getText().toString();
                            String tktKeyValue = orderID.getText().toString();




                            ShowModel  showModel = new ShowModel( tktKeyValue,Banimal,Bseat,Btime,Bdate,useID);
                            DatabaseReference reff = FirebaseDatabase.getInstance().getReference("AnimalShow").child("AquariumShow").child(useID).child(String.valueOf(showModel.getTktKeyValue()));

                            Task<Void> mTsk = reff.removeValue();
                            //Toast.makeText(context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                            mTsk.addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    ShowAdapter.context.startActivity(new Intent(ShowAdapter.context, ShowView.class).putExtra("keyuserID", useID));
                                    Toast.makeText(ShowAdapter.context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                                    //showToast("Deleted Success!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ShowAdapter.context,"UnSuccessfull",Toast.LENGTH_SHORT).show();
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

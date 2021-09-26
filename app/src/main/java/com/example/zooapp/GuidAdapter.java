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

public class GuidAdapter extends RecyclerView.Adapter<GuidAdapter.MyViewHolder> {


    ArrayList<GuidModel> list;
    static Context context;

    public GuidAdapter(Context context, ArrayList<GuidModel> list) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(context).inflate(R.layout.item4,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        GuidModel guidModel = list.get(position);


        holder.dv_Adult.setText(guidModel.getDv_Adult());
        holder.dv_Child.setText(guidModel.getDv_Child());
        holder.dv_date.setText(guidModel.getDv_date());
        holder.dv_email.setText(guidModel.getDv_email());
        holder.tnation.setText(guidModel.getTnation());

        //String norderId = String.valueOf(member.getTktKeyValue());
        holder.orderID.setText(guidModel.getTktKeyValue());
        holder.userID.setText(guidModel.getUserID());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView  userID,orderID,dv_Adult,dv_Child,dv_date,dv_email,tnation;
        Button deleteBtn;



        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            dv_Adult = itemView.findViewById(R.id.dwadult);
            dv_Child = itemView.findViewById(R.id.DwChild);
            dv_date = itemView.findViewById(R.id.dwdate);
            dv_email = itemView.findViewById(R.id.dwemail);
            tnation = itemView.findViewById(R.id.dwnation);
            userID = itemView.findViewById(R.id.userID);
            orderID = itemView.findViewById(R.id.orderID_txt);
            deleteBtn = itemView.findViewById(R.id.deleteDw);



            deleteBtn.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String useID = userID.getText().toString();
                            String typeD = tnation.getText().toString();
                            String DVadu =dv_Adult.getText().toString();
                            String DVch = dv_Child.getText().toString();
                            String DVdate = dv_date.getText().toString();
                            String DVemail = dv_email.getText().toString();
                            String tktKeyValue = orderID.getText().toString();




                            GuidModel  guidModel = new GuidModel( useID,tktKeyValue,typeD,DVadu,DVch,DVdate,DVemail);
                            DatabaseReference reff = FirebaseDatabase.getInstance().getReference("GuidedTour").child("GTour").child(useID).child(String.valueOf(guidModel.getTktKeyValue()));

                            Task<Void> mTsk = reff.removeValue();
                            //Toast.makeText(context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                            mTsk.addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    context.startActivity(new Intent(context, ViewTicketBooking.class).putExtra("keyuserID", useID));
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



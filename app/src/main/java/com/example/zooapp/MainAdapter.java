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

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {


    ArrayList <MainModel>list;
    static Context context;

    public MainAdapter(Context context, ArrayList<MainModel> list) {

        this.list = list;
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

        MainModel mainModel = list.get(position);

        MainModel main =list.get(position);
        holder.s_National.setText(main.getS_National());
        holder.s_Adult.setText(main.getS_Adult());
        holder.s_Child.setText(main.getS_Child());
        holder.etDate.setText(main.getEtDate());
        String  total_amount = String.valueOf(main.getTotal_amount());
        holder.total_amount.setText(total_amount);
        String child_t_Amount   = String.valueOf(main.getChild_t_Amount());
        holder.child_t_Amount.setText(child_t_Amount);
        String  adult_t_Amount = String.valueOf(main.getAdult_t_Amount());
        holder.adult_t_Amount.setText(adult_t_Amount);
        String number_adult = String.valueOf(main.getNumber_adult());
        holder.number_adult.setText(number_adult);
        String number_child = String.valueOf(main.getNumber_child());
        holder.number_child.setText(number_child);
        //String norderId = String.valueOf(member.getTktKeyValue());
        holder.orderID.setText(main.getTktKeyValue());
        holder.userID.setText(main.getUserID());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView  etDate, s_Adult, s_Child, s_National, userID,total_amount,child_t_Amount,adult_t_Amount,orderID,number_adult,number_child;
        Button deleteBtn;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            etDate=itemView.findViewById(R.id.date_txt);
            s_Adult=itemView.findViewById(R.id.adultnum);
            s_Child=itemView.findViewById(R.id.childnum);
            s_National=itemView.findViewById(R.id.type_txt);
            total_amount=itemView.findViewById(R.id.totalAmount_txt);
            child_t_Amount=itemView.findViewById(R.id.halftktAmount_txt);
            adult_t_Amount=itemView.findViewById(R.id.fulltktAmount_txt);
            userID=itemView.findViewById(R.id.userID);
            orderID = itemView.findViewById(R.id.orderID_txt);
            number_adult=itemView.findViewById(R.id.Numad);
            number_child = itemView.findViewById(R.id.Numch);
            deleteBtn = itemView.findViewById(R.id.deleteSaf);



            deleteBtn.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String useID = userID.getText().toString();
                            String typeN = s_National.getText().toString();
                            String Dsadult = s_Adult.getText().toString();
                            String Dchild = s_Child.getText().toString();
                            double Dadfamount = Double.parseDouble(adult_t_Amount.getText().toString().trim());
                            double Dchamount = Double.parseDouble(child_t_Amount.getText().toString().trim());
                            double DfullAmount = Double.parseDouble(total_amount.getText().toString().trim());
                            int Dnumad = Integer.parseInt(number_adult.getText().toString().trim());
                            int Dnumch = Integer.parseInt(number_child.getText().toString().trim());
                            String Ddate = etDate.getText().toString();
                            String tktKeyValue = orderID.getText().toString();




                            MainModel  main = new MainModel( useID,tktKeyValue,typeN,Dsadult,Ddate,Dchild,Dnumad,Dnumch,Dchamount,Dadfamount,DfullAmount);
                            DatabaseReference reff = FirebaseDatabase.getInstance().getReference("TicketBooking").child("TicketBookDetails").child(useID).child(String.valueOf(main.getTktKeyValue()));

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



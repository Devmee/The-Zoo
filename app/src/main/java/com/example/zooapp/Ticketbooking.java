package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ticketbooking extends AppCompatActivity {

        EditText S_Adult,S_Child,etDate;
        Button STbook;
        Spinner S_National;
        String userID,userid,email;
        TextView Prate_B;
        DatePickerDialog.OnDateSetListener setListener;
        double child_t_Amount =0 ,adult_t_Amount = 0,fullAmount = 0;
        long loc_adult_price,loc_child_price,for_adult_price,for_child_price;
        FirebaseAuth fauth = FirebaseAuth.getInstance();
         DatabaseReference reff,rates;
        int number_child ;
        int number_adult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketbooking);
        getSupportActionBar().hide();

    etDate=findViewById(R.id.S_Date2);
    Prate_B=findViewById(R.id.S_PrateB);

    Calendar calendar = Calendar.getInstance();
    final int year =calendar.get(Calendar.YEAR);
    final int month =calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

       //create database connection to ticket price table
        rates = FirebaseDatabase.getInstance().getReference("TicketPrice").child("PriceRate");


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        Ticketbooking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;

                        etDate.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });


        //direcct to the rates page
        Prate_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ticketbooking.this, PriceRate.class));
            }
        });



        //assign id's
        S_National = findViewById(R.id.S_national);
        S_Adult = findViewById(R.id.S_adult2);
        S_Child= findViewById(R.id.S_child2);
        etDate= findViewById(R.id.S_Date2);
        STbook= findViewById(R.id.S_Book);



        //get user id and email
        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");




        reff= FirebaseDatabase.getInstance().getReference("TicketBooking").child("TicketBookDetails");


        rates.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1 = String.valueOf(snapshot.child("Foreign_adult").getValue(Long.class));
                String Option2= String.valueOf(snapshot.child("Foreign_child").getValue(Long.class));
                String Option3 = String.valueOf(snapshot.child("Local_adult").getValue(Long.class));
                String Option4 = String.valueOf(snapshot.child("Local_child").getValue(Long.class));


                for_adult_price = Long.parseLong(Option1);
                for_child_price = Long.parseLong(Option2);
                loc_adult_price = Long.parseLong(Option3);
                loc_child_price= Long.parseLong(Option4);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        STbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { insertS_Book();

            }

        });
    }

    private void insertS_Book() {
        String Snational = S_National.getSelectedItem().toString();
        String Sadult = S_Adult.getText().toString();
        String Schild = S_Child.getText().toString();
        String Sdate = etDate.getText().toString();

        //get current object id
        String id = reff.push().getKey();


        if(TextUtils.isEmpty(Sadult)){
            S_Adult.setError("  Required!");
            return;
        }
        if(TextUtils.isEmpty(Schild)){
            S_Child.setError(" Required!");
            return;
        }
        if(TextUtils.isEmpty(Sdate)){
            etDate.setError("Date Is Required!");
            return;
        }

        //String value convert to integer
        if(S_Adult.isEnabled())
        {
            number_adult= Integer.parseInt(Sadult);

        }else{
            number_adult = 0;
        }
        if(S_Child.isEnabled())
        {
            number_child =Integer.parseInt(Schild);

        }else{
            number_child = 0;
        }

        //calculation
        if (Snational.equals("Foreign"))
        {
             child_t_Amount= number_child * for_child_price;
            adult_t_Amount = number_adult * for_adult_price;
        }
        else if(Snational.equals("Local"))
        {
            child_t_Amount= number_child * loc_child_price;
            adult_t_Amount = number_adult * loc_adult_price;
        }
        else{
            child_t_Amount= 0;
            adult_t_Amount = 0;
        }
        fullAmount = child_t_Amount+ adult_t_Amount;


        Ticket S =new Ticket (Snational,id,Sadult,Schild,Sdate,number_child,number_adult,child_t_Amount,adult_t_Amount,fullAmount,userID);

        reff.push().setValue(S);
        Toast.makeText(Ticketbooking.this, "Ticket Booking successful", Toast.LENGTH_LONG).show();

        //form Validation





    }
}
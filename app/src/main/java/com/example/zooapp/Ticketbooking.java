package com.example.zooapp;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ticketbooking extends AppCompatActivity {

        EditText S_Adult,S_Child,etDate;
        Button STbook;
        Spinner S_National;
        String userID,userid,email;
        TextView Prate_B;
        DatePickerDialog.OnDateSetListener setListener;
        FirebaseAuth fauth = FirebaseAuth.getInstance();
         DatabaseReference reff;



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



        Ticket S =new Ticket (Snational,Sadult,Schild,Sdate,userID);

        reff.push().setValue(S);
        Toast.makeText(Ticketbooking.this, "Ticket Booking successful", Toast.LENGTH_LONG).show();

        //form Validation





    }
}
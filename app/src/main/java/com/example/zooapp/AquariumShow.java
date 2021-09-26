package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AquariumShow extends AppCompatActivity {

    //get wigdets
    EditText Aq_Seat,Aq_date;
    Button aqnbtn;
    Spinner Aqanimal,Aqtime;
    String userID,userid,email;
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    DatabaseReference reff;
    DatePickerDialog.OnDateSetListener setListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aquarium_show);
        getSupportActionBar().hide();
        Aq_date=findViewById(R.id.B_Date2);

        Calendar calendar = Calendar.getInstance();
        final int year =calendar.get(Calendar.YEAR);
        final int month =calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        //assign id's
        Aqanimal = findViewById(R.id.B_Aanimal);
        Aq_Seat= findViewById(R.id.B_Aq_Number);
        Aqtime= findViewById(R.id.B_AShowTimeS);
        Aq_date= findViewById(R.id.B_Date2);
        aqnbtn= findViewById(R.id.B_AButton);



        //get user id and email
        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");




        reff= FirebaseDatabase.getInstance().getReference("AnimalShow").child("AquariumShow").child(userID);

        Aq_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        AquariumShow.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;

                        Aq_date.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        aqnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAqShow();
            }
        });
    }
    private void insertAqShow() {
        String AAqanimal = Aqanimal.getSelectedItem().toString();
        String AAq_Seat = Aq_Seat.getText().toString();
        String AAqtime = Aqtime.getSelectedItem().toString();
        String AAq_date = Aq_date.getText().toString();

        //get current object id
        String id = reff.push().getKey();


        if(TextUtils.isEmpty(AAq_Seat)){
            Aq_Seat.setError("  Required!");
            return;
        }

        if(TextUtils.isEmpty(AAq_date)){
            Aq_date.setError("Date Is Required!");
            return;
        }


        AqShow B =new AqShow (id,AAqanimal,AAqtime,AAq_date,AAq_Seat,userID);




        reff.child(id).setValue(B).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Book Saved",Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(AquariumShow.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });







    }
}
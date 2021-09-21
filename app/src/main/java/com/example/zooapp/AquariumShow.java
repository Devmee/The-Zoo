package com.example.zooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

    int year;
    int month;
    int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aquarium_show);
        getSupportActionBar().hide();
        Aq_date=findViewById(R.id.B_Date2);
        Calendar calendar = Calendar.getInstance();
        Aq_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                year= calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AquariumShow.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Aq_date.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));

                    }
                }, year,month,day);
                datePickerDialog.show();

            }
        });


        //assign id's
        Aqanimal = findViewById(R.id.B_Aanimal);
        Aq_Seat= findViewById(R.id.B_Aq_Number);
        Aqtime= findViewById(R.id.B_AShowTimeS);
        Aq_date= findViewById(R.id.B_Date2);
        aqnbtn= findViewById(R.id.B_AButton);



        //get user id and email
        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");




        reff= FirebaseDatabase.getInstance().getReference("AnimalShow").child("AquariumShow");

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


        AqShow B =new AqShow (AAqanimal,AAqtime,AAq_date,AAq_Seat,userID);

        reff.push().setValue(B);
        Toast.makeText(AquariumShow.this, "Booking successful", Toast.LENGTH_LONG).show();






    }
}
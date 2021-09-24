package com.example.zooapp;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AnimalFeeding extends AppCompatActivity {


    //get wigdets
    EditText age,a_date;
    Button anbtn;
    Spinner animal,time,ch_ad;
    String userID,userid,email;
    Calendar myCalendar = Calendar.getInstance();
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    DatabaseReference reff;
    DatePickerDialog.OnDateSetListener setListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_feeding);
        getSupportActionBar().hide();

        a_date=findViewById(R.id.D_date2);

        Calendar calendar = Calendar.getInstance();
        final int year =calendar.get(Calendar.YEAR);
        final int month =calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);




        a_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        AnimalFeeding.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;

                        a_date.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        //assign id's
        animal = findViewById(R.id.f2);
        time= findViewById(R.id.f3);
        a_date= findViewById(R.id.D_date2);
        ch_ad = findViewById(R.id.f4);
        age= findViewById(R.id.D_email2);
        anbtn= findViewById(R.id.D_submit1);



        //get user id and email
        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");




        reff= FirebaseDatabase.getInstance().getReference("Feed").child("Feeding");

        anbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertFeeding();
            }
        });

    }

    private void insertFeeding() {
        String fanimal = animal.getSelectedItem().toString();
        String ftime = time.getSelectedItem().toString();
        String fa_date = a_date.getText().toString();
        String fch_ad = ch_ad.getSelectedItem().toString();
        String fage = age.getText().toString();
        //get current object id
        String id = reff.push().getKey();


        if(TextUtils.isEmpty(fa_date)){
            a_date.setError("  Required!");
            return;
        }

        if(TextUtils.isEmpty(fage)){
            age.setError("Date Is Required!");
            return;
        }



        Feeding D =new Feeding(fanimal,ftime,fa_date,fch_ad,fage,userID,id);

        reff.push().setValue(D);
        Toast.makeText(AnimalFeeding.this, "Booking successful", Toast.LENGTH_LONG).show();





    }

}
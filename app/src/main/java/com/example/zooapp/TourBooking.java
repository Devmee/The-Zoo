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

public class TourBooking extends AppCompatActivity {

    //get wigdets
    EditText Dv_email,Dv_date,Dv_Adult,Dv_Child;
    Button tbnbtn;
    Spinner Tnation;
    String userID,userid,email;
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    DatabaseReference reff;
    DatePickerDialog.OnDateSetListener setListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_booking);
        getSupportActionBar().hide();
        Dv_date=findViewById(R.id.dv_date2);



        Calendar calendar = Calendar.getInstance();
        final int year =calendar.get(Calendar.YEAR);
        final int month =calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        //assign id's
        Dv_email = findViewById(R.id.dv_email2);
        Tnation= findViewById(R.id.Dv_national);
        Dv_Adult= findViewById(R.id.dv_numofA2);
        Dv_Child= findViewById(R.id.dv_numofAc2);
        Dv_date= findViewById(R.id.dv_date2);
        tbnbtn= findViewById(R.id.Dv_submit1);



        //get user id and email
        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");




        reff= FirebaseDatabase.getInstance().getReference("GuidedTour").child("GTour").child(userID);


        Dv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        TourBooking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;

                        Dv_date.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        tbnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTbook();
            }
        });
    }
    private void insertTbook() {
        String Gtemail = Dv_email.getText().toString();
        String Gtnation = Tnation.getSelectedItem().toString();
        String Gtadult = Dv_Adult.getText().toString();
        String Gtchild = Dv_Child.getText().toString();
        String Gtdate = Dv_date.getText().toString();

        //get current object id
        String id = reff.push().getKey();


        if(TextUtils.isEmpty(Gtemail)){
            Dv_email.setError("  Required!");
            return;
        }
        if(TextUtils.isEmpty(Gtadult)){
            Dv_Adult.setError(" Required!");
            return;
        }
        if(TextUtils.isEmpty(Gtchild)){
            Dv_Child.setError("Date Is Required!");
            return;
        }

        if(TextUtils.isEmpty(Gtdate)){
            Dv_date.setError("Date Is Required!");
            return;
        }



        GtBook dv =new GtBook (id,Gtemail,Gtnation,Gtadult,Gtchild,Gtdate,userID);


        reff.child(id).setValue(dv).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Book  Saved",Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(TourBooking.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}
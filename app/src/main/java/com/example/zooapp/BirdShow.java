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

public class BirdShow extends AppCompatActivity {

    //get wigdets
    EditText B_Seat,B_date;
    Button Bsnbtn;
    Spinner Btime;
    String userID,userid,email;
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    DatabaseReference reff;
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_show);
        getSupportActionBar().hide();
        B_date=findViewById(R.id.B_B_Date2);

        Calendar calendar = Calendar.getInstance();
        final int year =calendar.get(Calendar.YEAR);
        final int month =calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        //assign id's

        B_Seat= findViewById(R.id.B_BirdShow2);
        Btime= findViewById(R.id.B_BShowTimeS);
        B_date= findViewById(R.id.B_B_Date2);
        Bsnbtn= findViewById(R.id.B_BButton);



        //get user id and email
        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");




        reff= FirebaseDatabase.getInstance().getReference("AnimalShow").child("BirdShow").child(userID);

        B_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        BirdShow.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date = day+"/"+month+"/"+year;

                        B_date.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });




        Bsnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAqShow();
            }
        });
    }
    private void insertAqShow() {

        String Bs_Seat = B_Seat.getText().toString();
        String Bstime = Btime.getSelectedItem().toString();
        String Bs_date = B_date.getText().toString();


        //get current object id
        String id = reff.push().getKey();

        if(TextUtils.isEmpty(Bs_Seat)){
            B_Seat.setError("  Required!");
            return;
        }

        if(TextUtils.isEmpty(Bs_date)){
            B_date.setError("Date Is Required!");
            return;
        }


        B_Show BB =new B_Show (Bs_Seat,Bstime,Bs_date,userID,id);





        reff.child(id).setValue(BB).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Book Saved",Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(BirdShow.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}
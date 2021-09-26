package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button loginbtn;
    EditText lemail, lpassword;
    TextView signup,forgetpass;
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    String userID;
    ProgressBar prologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        loginbtn = findViewById(R.id.S_LoginB);
        lemail = findViewById(R.id.S_Uname2);
        lpassword = findViewById(R.id.S_Lp2);
        signup = findViewById(R.id.textView2);
        forgetpass = findViewById(R.id.S_Fog);
        prologin = findViewById(R.id.progresslogin);

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = lemail.getText().toString().trim();
                String password = lpassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    lemail.setError("Email is Required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    lpassword.setError("Password is Required!");
                    return;
                }
                if(password.length() < 5){
                    lpassword.setError("Password Must be Greater Than 4 characters!");
                    return;
                }

                prologin.setVisibility(View.VISIBLE);


                //Authenticate the user
                fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Logged in Successfully!",Toast.LENGTH_LONG).show();
                            //get current user ID
                            userID = fauth.getCurrentUser().getUid();
                            startActivity(new Intent(getApplicationContext(),Home.class).putExtra("keyEmail",email).putExtra("keyuserID",userID));

                            prologin.setVisibility(View.GONE);

                        }else{
                            Toast.makeText(MainActivity.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();

                            prologin.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }
}
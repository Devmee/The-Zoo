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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    Button register_u;
    EditText rname,remail,rcontact,rpassword,rconfirmpwd;
    FirebaseAuth fauth;
    DatabaseReference reff;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        //get the value and assign to the variables
        register_u = (Button)findViewById(R.id.S_RgButton);
        rname = (EditText)findViewById(R.id.S_Rusername2);
        remail = (EditText)findViewById(R.id.S_Remail2);
        rcontact =(EditText)findViewById(R.id.S_Rphone2);
        rpassword = (EditText)findViewById(R.id.S_Rpassword2);
        rconfirmpwd = (EditText)findViewById(R.id.S_RconfirmP2);
        fauth = FirebaseAuth.getInstance();
        reff = FirebaseDatabase.getInstance().getReference("User");

       /* if(fauth.getCurrentUser() != null){
            //Toast.makeText(Register.this,"User Already exists!",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),mainpage.class));
            finish();
        }*/

        //storing data
        register_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = rname.getText().toString().trim();
                String email = remail.getText().toString().trim();
                String contact =rcontact.getText().toString().trim();
                String password = rpassword.getText().toString().trim();
                String confirmpwd = rconfirmpwd.getText().toString().trim();

                //form Validation
                if(TextUtils.isEmpty(name)){
                    rname.setError("Username is Required!");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    remail.setError("Email is Required!");
                    return;
                }
                if(TextUtils.isEmpty(contact)){
                    rcontact.setError("Contact Number is Required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    rpassword.setError("Password is Required!");
                    return;
                }
                if(TextUtils.isEmpty(confirmpwd)){
                    rconfirmpwd.setError("Confirm Password is Required!");
                    return;
                }
                if(password.length() < 5){
                    rpassword.setError("Password Must be Greater Than 4 characters!");
                    return;
                }
                if(!(password.equals(confirmpwd))){
                    rpassword.setError("Password and Confirmation Password Should be the Same.!");
                    return;
                }

                // register the user to the firbase
                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            //email verification
                            FirebaseUser fUser = fauth.getCurrentUser();
                            fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //Show succussful message
                                    Toast.makeText(Registration.this,"Verification email has been sent! ",Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //Show unsuccussful message
                                    Toast.makeText(Registration.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });

                            //get current user ID
                            userID = fauth.getCurrentUser().getUid();
                            UserRegister regUser = new UserRegister(name,email,password,contact,userID);
                            reff.child(String.valueOf(userID)).setValue(regUser);
                            Toast.makeText(Registration.this,"User Created!",Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getApplicationContext(),MainActivity.class).putExtra("keyEmail",email).putExtra("keyuserID",userID));

                        }else{

                            Toast.makeText(Registration.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });


            }
}


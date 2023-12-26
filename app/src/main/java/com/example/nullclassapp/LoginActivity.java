package com.example.nullclassapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText phoneNumber;
    private FirebaseAuth auth;
    private Button signin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //email = findViewById(R.id.userEmail);
        phoneNumber = findViewById(R.id.userPhone);
        //password = findViewById(R.id.userPassword);
        signin = findViewById(R.id.signinButton);

        auth= FirebaseAuth.getInstance();

        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        } else {
            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this,OTPActivity.class);
                    if(phoneNumber.equals("") || phoneNumber.getText().toString().isEmpty() ){
                        Toast.makeText(LoginActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    }
                    if((phoneNumber.getText().toString().length()<10) || (phoneNumber.getText().toString().length()>10)){
                        Toast.makeText(LoginActivity.this, "Please enter a valid mobile", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        intent.putExtra("phonenumber", phoneNumber.getText().toString());
                        startActivity(intent);
                    }
                }
            });

        }


    }



}
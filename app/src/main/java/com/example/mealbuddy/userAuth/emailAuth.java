package com.example.mealbuddy.userAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mealbuddy.R;
import com.example.mealbuddy.mainPage.mainPage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class emailAuth extends AppCompatActivity {
    Button verify, verified;
    FirebaseAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_auth);

        verify = findViewById(R.id.verifybutton);
        verified = findViewById(R.id.verifiedbutton);
        final FirebaseUser user = fireAuth.getCurrentUser();

        if(!user.isEmailVerified()){
            verify.setVisibility(View.VISIBLE);
            verified.setVisibility(View.INVISIBLE);
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(emailAuth.this,"Verification Email Sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "OnFailure: Verification not sent. " + e.getMessage());
                        }
                    });
                }
            });
        }
        if(user.isEmailVerified()){
            verify.setVisibility(View.INVISIBLE);
            verified.setVisibility(View.VISIBLE);
            verified.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), mainPage.class));
                }
            });
        }
    }
}

package com.example.mealbuddy.userAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealbuddy.mainPage.findAMealBuddy.time;
import com.example.mealbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    EditText SIEmail, SIPass;
    Button SIButt;
    TextView SITxt, Forget;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        SIEmail = findViewById(R.id.SignInEmail);
        SIPass = findViewById(R.id.SignInPassword);
        SIButt = findViewById(R.id.SignInButt);
        SITxt = findViewById(R.id.createTxt);
        Forget = findViewById(R.id.ForgetPassword);

        fAuth = FirebaseAuth.getInstance();

        SIButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = SIEmail.getText().toString().trim();
                String pass = SIPass.getText().toString().trim();

                if (email.isEmpty()){
                    SIEmail.setError("Please enter email");
                }

                if (pass.isEmpty()){
                    SIPass.setError("Please enter password");
                }

                fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), time.class));
                        }
                        else{
                            Toast.makeText(LoginPage.this,"Error, please try again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        SITxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });
    }
}

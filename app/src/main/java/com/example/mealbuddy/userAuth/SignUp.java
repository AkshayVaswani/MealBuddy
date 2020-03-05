package com.example.mealbuddy.userAuth;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealbuddy.R;
import com.example.mealbuddy.mainPage.mainPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {
    EditText SUName, SUEmail, SUPass, SUPass2;
    Button SUButt;
    TextView SUText;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    CheckBox SUCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SUName = findViewById(R.id.SignUpName);
        SUEmail = findViewById(R.id.SignUpEmail);
        SUPass = findViewById(R.id.SignUpPassword1);
        SUPass2 = findViewById(R.id.SignUpPassword2);
        SUButt = findViewById(R.id.SignUpButt);
        SUText = findViewById(R.id.SignUpTxt);
        SUCheck = findViewById(R.id.checkBox);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        SUButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = SUEmail.getText().toString().trim();
                final String password = SUPass.getText().toString().trim();
                String password2 = SUPass2.getText().toString().trim();
                final String name = SUName.getText().toString().trim();

                if(email.isEmpty()){
                    SUEmail.setError("Please enter email");
                    return;
                }

                if(!email.contains("@drexel.edu")){
                    SUPass.setError("Please use your Drexel email for this Account");
                    return;
                }

                if(password.isEmpty()){
                    SUPass.setError("Please enter password");
                    return;
                }

                if(password.length()<10){
                    SUPass.setError("Password must be more than 10 characters long");
                    return;
                }

                if(!password2.equals(password)){
                    SUPass2.setError("The second password must match the first password");
                    return;
                }

                if(!SUCheck.isChecked()){
                    SUCheck.setError("Please accept our privacy policy");
                    return;
                }

                if(fAuth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), mainPage.class));
                    finish();
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser userAuth = fAuth.getCurrentUser();
                            userAuth.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(SignUp.this,"Verification Email Sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "OnFailure: Verification not sent. " + e.getMessage());
                                }
                            });
                            Toast.makeText(SignUp.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map <String, Object> user = new HashMap<>();
                            user.put("first name", name);
                            user.put("email", email);
                            user.put("password", password);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "On Success: User profile is created for " + userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), emailAuth.class));
                        }
                        else {
                            Toast.makeText(SignUp.this, "Error, please try again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        SUText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginPage.class));
            }
        });

    }
}

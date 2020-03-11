package com.example.mealbuddy.mainPage.findAMealBuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealbuddy.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class location extends AppCompatActivity {

    Button hans, urban, north;
    String timeChosen, userID;
    TextView timeC;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        hans = findViewById(R.id.locButHans);
        urban = findViewById(R.id.locButUrban);
        north = findViewById(R.id.locButNorthside);
        timeC = findViewById(R.id.timeChosenDynamic);
        Intent intent = getIntent();
        timeChosen = intent.getStringExtra("Chosen Time");
        timeC.setText(timeChosen);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        hans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map <String, Object> user = new HashMap<>();
                user.put("time", timeChosen);
                user.put("location", "Hans");

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Added to database" + userID);
                    }
                });
                startActivity(new Intent(getApplicationContext(), outfit.class));
            }
        });

        urban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map <String, Object> user = new HashMap<>();
                user.put("time", timeChosen);
                user.put("location", "Urban");
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Added to database " + userID);
                    }
                });

                startActivity(new Intent(getApplicationContext(), outfit.class));
            }
        });

        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("time", timeChosen);
                user.put("location", "North Side");

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Added to database " + userID);
                    }
                });
                startActivity(new Intent(getApplicationContext(), outfit.class));
            }
        });
    }
}

package com.example.mealbuddy.mainPage.findAMealBuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealbuddy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
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
                String locationStr = "Hans";
                Intent intent = new Intent(location.this, outfit.class);
                intent.putExtra("Chosen Location", locationStr);
                intent.putExtra("Chosen Time", timeChosen);
                startActivity(intent);
            }
        });

        urban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationStr = "Urban";
                Intent intent = new Intent(location.this, outfit.class);
                intent.putExtra("Chosen Location", locationStr);
                intent.putExtra("Chosen Time", timeChosen);
                startActivity(intent);
            }
        });

        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationStr = "North Side";
                Intent intent = new Intent(location.this, outfit.class);
                intent.putExtra("Chosen Location", locationStr);
                intent.putExtra("Chosen Time", timeChosen);
                startActivity(intent);
            }
        });
    }
}

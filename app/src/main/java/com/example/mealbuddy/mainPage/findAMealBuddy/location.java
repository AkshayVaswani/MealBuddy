package com.example.mealbuddy.mainPage.findAMealBuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealbuddy.R;

public class location extends AppCompatActivity {

    Button hans, urban, north;
    String timeChosen;
    TextView timeC;

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


    }
}

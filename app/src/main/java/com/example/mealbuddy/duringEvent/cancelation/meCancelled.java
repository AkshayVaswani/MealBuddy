package com.example.mealbuddy.duringEvent.cancelation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mealbuddy.R;
import com.example.mealbuddy.mainPage.mainPage;

public class meCancelled extends AppCompatActivity {
    Button backHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_cancelled);

        backHome = findViewById(R.id.backToHome);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), mainPage.class));
            }
        });
    }
}

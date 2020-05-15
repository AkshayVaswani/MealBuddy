package com.example.mealbuddy.afterEvent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mealbuddy.R;
import com.example.mealbuddy.afterEvent.reviewBuddy.alrightMeal;
import com.example.mealbuddy.afterEvent.reviewBuddy.badMeal;
import com.example.mealbuddy.afterEvent.reviewBuddy.veryGoodMeal;

public class overallReviewPage extends AppCompatActivity {

    Button yes, no, wait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_review_page);
        yes = findViewById(R.id.overallReviewYes);
        no = findViewById(R.id.overallReviewNo);
        wait = findViewById(R.id.overallReviewWait);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), veryGoodMeal.class));
            }
        });
        wait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), alrightMeal.class));
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), badMeal.class));
            }
        });
    }
}

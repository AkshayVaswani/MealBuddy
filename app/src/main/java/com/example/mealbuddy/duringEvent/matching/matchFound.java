package com.example.mealbuddy.duringEvent.matching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealbuddy.R;
import com.example.mealbuddy.afterEvent.overallReviewPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Time;

public class matchFound extends AppCompatActivity {
    TextView TimeChosen, PlaceChosen, OutfitChosen;
    Button ButtMealDone, ButtCancel;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String MatchedUser, time_chosen, place_chosen, outfit_chosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_found);

        TimeChosen = findViewById(R.id.timeDynamic);
        PlaceChosen = findViewById(R.id.locDynamic);
        ButtMealDone = findViewById(R.id.mealDone);
        ButtCancel = findViewById(R.id.cancelBad);
        OutfitChosen = findViewById(R.id.matchFoundOutfit);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        MatchedUser = intent.getStringExtra("MatchedUser");
        System.out.println(MatchedUser);
        DocumentReference documentReference = fStore.collection("users").document(MatchedUser);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot != null) {
                        time_chosen = documentSnapshot.getString("time");
                        place_chosen = documentSnapshot.getString("location");
                        outfit_chosen = documentSnapshot.getString("outfit");

                        System.out.println(time_chosen);
                        System.out.println(place_chosen);
                        System.out.println(outfit_chosen);

                        TimeChosen.setText(time_chosen);
                        PlaceChosen.setText(place_chosen);
                        OutfitChosen.setText(outfit_chosen);
                    }
                }
            }
        });

        ButtMealDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), overallReviewPage.class));
            }
        });
    }
}

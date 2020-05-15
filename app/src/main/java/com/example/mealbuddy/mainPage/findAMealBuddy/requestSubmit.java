package com.example.mealbuddy.mainPage.findAMealBuddy;

import com.example.mealbuddy.duringEvent.matching.findingBuddy;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealbuddy.R;
import com.example.mealbuddy.duringEvent.matching.matchFound;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class requestSubmit extends AppCompatActivity {
    TextView time_chosen, place_chosen, outfit_chosen;
    Button ButtSub, ButtCancel;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String TimeChosen, PlaceChosen, OutfitChosen, userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_submit);

        time_chosen = findViewById(R.id.timeDynamic2);
        place_chosen = findViewById(R.id.locDynamic);
        outfit_chosen = findViewById(R.id.outfitDynamic);

        ButtSub = findViewById(R.id.outfitSubmitBut2);
        ButtCancel = findViewById(R.id.backToHome);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        TimeChosen = document.getString("time");
                        PlaceChosen = document.getString("location");
                        OutfitChosen = document.getString("outfit");
                        //Log.d("Time, Place, Outfit:", TimeChosen);

                        time_chosen.setText(TimeChosen);
                        place_chosen.setText(PlaceChosen);
                        outfit_chosen.setText(OutfitChosen);

                    } else {
                        Log.d("ERROR", "Document does not exist");
                    }
                }
                else {
                    Log.d("ERROR", "Fail to access database, please try again later!");
                }
            }
        });

        ButtSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Temp code, change to findingBuddy later!!!!!
                startActivity(new Intent(getApplicationContext(), findingBuddy.class));
                DocumentReference usermatching = fStore.collection("users").document(userID);

                usermatching
                        .update("matching", "1");
            }
        });
    }
}

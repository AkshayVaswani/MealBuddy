package com.example.mealbuddy.mainPage.findAMealBuddy;
import com.example.mealbuddy.mainPage.mainPage;
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

public class outfit extends AppCompatActivity {
    TextView outfit;
    Button SubButt;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Intent intent;
    String time, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit2);

        intent = getIntent();
        location = intent.getStringExtra("Chosen Location");
        Log.d("TAG", location);
        time = intent.getStringExtra("Chosen Time");

        outfit = findViewById(R.id.outfitInput);
        SubButt = findViewById(R.id.outfitSubmitBut);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        SubButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String out_fit = outfit.getText().toString().trim();
                userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("outfit", out_fit);
                user.put("time", time);
                user.put("location", location);
                user.put("userID", userID);

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Added to database" + userID);
                    }
                });
                startActivity(new Intent(getApplicationContext(), requestSubmit.class));
            }
        });
    }
}

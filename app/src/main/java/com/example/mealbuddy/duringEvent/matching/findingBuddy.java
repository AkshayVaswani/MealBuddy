package com.example.mealbuddy.duringEvent.matching;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class findingBuddy extends AppCompatActivity {
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    ProgressBar pBar;
    public String userID, TimeChosen, PlaceChosen, userID2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finding_buddy);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        pBar = findViewById(R.id.progressBar);

        userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        final CollectionReference collectionReference = fStore.collection("users");
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot != null) {
                        TimeChosen = documentSnapshot.getString("time");
                        PlaceChosen = documentSnapshot.getString("location");
                        Query query = collectionReference.whereEqualTo("time", TimeChosen)
                                .whereEqualTo("location", PlaceChosen);
                        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    List<String> usersList = new ArrayList<>();
                                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                        String id = queryDocumentSnapshot.getId();
                                        usersList.add(id);
                                    }
                                    for (String user : usersList) {
                                         if (userID.equals(user)){
                                            usersList.remove(user);
                                        }
                                    }
                                    Random rand = new Random();
                                    int index = rand.nextInt(usersList.size());
                                    userID2 = usersList.get(index);
                                    System.out.println(userID2);

                                    Intent intent = new Intent(findingBuddy.this, matchFound.class);
                                    intent.putExtra("MatchedUser", userID2);
                                    startActivity(intent);
                                }
                                else {
                                    Log.d("ERROR", "Cannot access to the database!");
                                }
                            }
                        });
                    }
                    else {
                        Log.d("ERROR", "Cannot access to the database!");
                    }
                }
            }
        });
        //while (loop == Boolean.TRUE) {
            //sleep(10000);

        //}
    }
}

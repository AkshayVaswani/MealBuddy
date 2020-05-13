package com.example.mealbuddy.afterEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealbuddy.R;
import com.example.mealbuddy.afterEvent.reporting.mainReportPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class overallReviewPage extends AppCompatActivity {
    Button yes1Butt, yes2Butt, noButt, reportButt;
    String matchedId;
    FirebaseFirestore fStore;
    Intent intent;
    TextView BudName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_review_page);

        intent = getIntent();
        matchedId = intent.getStringExtra("MatchedUser");

        BudName = findViewById(R.id.overallReviewBuddyDynamic);
        yes1Butt = findViewById(R.id.overallReviewYes);
        yes2Butt = findViewById(R.id.overallReviewWait);
        noButt = findViewById(R.id.overallReviewNo);
        reportButt = findViewById(R.id.overallReviewReport);

        fStore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = fStore.collection("users").document(matchedId);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot != null) {
                        String budName = documentSnapshot.getString("name");
                        BudName.setText(budName);
                    }
                }
            }
        });

        reportButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), mainReportPage.class));
            }
        });
    }
}

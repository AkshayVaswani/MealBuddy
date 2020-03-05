package com.example.mealbuddy.duringEvent.matching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealbuddy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class matchFound extends AppCompatActivity {
    TextView a,b,c;
    Button btn;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_found);

        a=findViewById(R.id.locationview);
        b=findViewById(R.id.timeview);
        c=findViewById(R.id.emailview);
        btn=findViewById(R.id.displayinfo);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference().child("users").child("Z5Mk84qVCneiGndKZTO8");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String location = dataSnapshot.child("Location").getValue().toString();
                        String time = dataSnapshot.child("Time").getValue().toString();
                        String email = dataSnapshot.child("email").getValue().toString();
                        a.setText(location);
                        b.setText(time);
                        c.setText(email);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


        });
    }


}


package com.example.mealbuddy.mainPage.findAMealBuddy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.mealbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class time extends AppCompatActivity {

    List<timeList> liveList;
    ListView listView;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        fStore = FirebaseFirestore.getInstance();
        liveList = new ArrayList<>();
        CollectionReference coll = fStore.collection("Times");

        coll.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        liveList.add(new timeList(documentSnapshot.getString("Time")));
                        //Log.d("TAG2", documentSnapshot.getString("Time"));
                    }
                }
                customAdapter adapter = new customAdapter(time.this, R.layout.activity_time_template, liveList);

                listView = findViewById(R.id.listView);
                Log.d("TAG2", liveList.get(3).getTime());
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(time.this, location.class);
                        intent.putExtra("Chosen Time", liveList.get(position).getTime());
                        startActivity(intent);
                    }
                });

            }
        });




       






    }
}

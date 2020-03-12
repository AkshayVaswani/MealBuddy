package com.example.mealbuddy.mainPage.findAMealBuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mealbuddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


//        String[] quarterHours = {"00","15","30","45"};
//        List<String> timesMil = new ArrayList<>();
//        List<String> timesNorm = new ArrayList<>();
//        for(int i = 7; i < 22; i++) {
//            for(int j = 0; j < 4; j++) {
//                String time = i + ":" + quarterHours[j];
//                if(i < 10) {
//                    time = "0" + time;
//                }
//                timesMil.add(time);
//            }
//
//        }
//        Log.d("TAG2", timesMil.toString());
//        DateFormat inFormat = new SimpleDateFormat("HH:mm");
//        DateFormat outFormat = new SimpleDateFormat("hh:mm aa");
//        Date date = null;
//        String output = null;
//        for(int i = 0; i < timesMil.size(); i++) {
//            try {
//                date = inFormat.parse(timesMil.get(i));
//                output = outFormat.format(date);
//                timesNorm.add(output);
//                Log.d("TAG2", timesNorm.get(i));
//
//            } catch (ParseException pe) {
//                pe.printStackTrace();
//            }
//        }
//
//        for(int i = 0; i < timesMil.size(); i++){
//            Map<String, Object> tim = new HashMap<>();
//            tim.put("normalTime", timesNorm.get(i));
//            tim.put("milTime", timesMil.get(i));
//            tim.put("Order", i);
//            coll.document(timesNorm.get(i)).set(tim);
//        }
//        coll.orderBy("Order", Query.Direction.DESCENDING);
//        final Map<Integer, String> collList = new HashMap<>();
        coll.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
//                        collList.put(documentSnapshot.getData)
                        int temp = documentSnapshot.getLong("Order").intValue();
                        liveList.add(new timeList(documentSnapshot.getString("normalTime"), temp));


                        //Log.d("TAG2", documentSnapshot.getString("Time"));
                    }
                }

                Collections.sort(liveList);
                customAdapter adapter = new customAdapter(time.this, R.layout.activity_time_template, liveList);

                listView = findViewById(R.id.listView);

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

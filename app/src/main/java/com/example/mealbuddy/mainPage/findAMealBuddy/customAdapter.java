package com.example.mealbuddy.mainPage.findAMealBuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mealbuddy.R;

import java.util.List;

public class customAdapter extends ArrayAdapter<timeList> {

    List<timeList> timeLists;

    Context context;
    int resource;


    public customAdapter(@NonNull Context context, int resource, List<timeList> timeLists) {
        super(context, resource, timeLists);
        this.context = context;
        this.resource = resource;
        this.timeLists = timeLists;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);
        TextView time = view.findViewById(R.id.timeSlot);

        timeList event = timeLists.get(position);

        time.setText(event.getTime());
        return view;
    }
}

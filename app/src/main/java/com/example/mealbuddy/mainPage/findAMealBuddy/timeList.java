package com.example.mealbuddy.mainPage.findAMealBuddy;

import java.util.Collection;

public class timeList implements Comparable {

    public String getTime() {
        return time;
    }

    String time;
    int order;

    public int getOrder(){ return order; }

    public timeList(String time, int order){
        this.time = time;
        this.order = order;
    }


    @Override
    public int compareTo(Object compareOrd) {
        int compareOrder =((timeList)compareOrd).getOrder();
        return this.order - compareOrder;
    }
}

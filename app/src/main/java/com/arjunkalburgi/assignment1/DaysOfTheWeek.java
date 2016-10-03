package com.arjunkalburgi.assignment1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Arjun on 2016-10-02.
 */

public class DaysOfTheWeek extends ArrayList<DayOfTheWeek> {
    // this is a list of all the Days of the week that the item will repeat on!
    private static final String TAG = "MainActivity";

    public DaysOfTheWeek() {
        this.add(new DayOfTheWeek("Sunday"));
        this.add(new DayOfTheWeek("Monday"));
        this.add(new DayOfTheWeek("Tuesday"));
        this.add(new DayOfTheWeek("Wednesday"));
        this.add(new DayOfTheWeek("Thursday"));
        this.add(new DayOfTheWeek("Friday"));
        this.add(new DayOfTheWeek("Saturday"));
    }

    public DayOfTheWeek getDay(DayOfTheWeek d) {
        return this.get(d.getNumber());
    }

    public boolean isDaySelected(String s) {
        return this.getDay(new DayOfTheWeek(s)).isSeletected();
    }

    public void selectDay(String s) {
        this.getDay(new DayOfTheWeek(s)).select();
    }

    @Override
    public String toString() {
        String returnString = "";
        for (DayOfTheWeek d : this) {
            if (d.isSeletected()) {
                returnString = returnString + d.getName() + ", ";
            }
        }
        return returnString;
    }
}

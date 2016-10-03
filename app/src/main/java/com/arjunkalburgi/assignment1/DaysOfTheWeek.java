package com.arjunkalburgi.assignment1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Arjun on 2016-10-02.
 */

public class DaysOfTheWeek {
    private List<DayOfTheWeek> days = new ArrayList<>();
    // this is a list of all the Days of the week that the item will repeat on!
    private static final String TAG = "MainActivity";

    public DaysOfTheWeek() {
        this.days.add(new DayOfTheWeek("Sunday"));
        this.days.add(new DayOfTheWeek("Monday"));
        this.days.add(new DayOfTheWeek("Tuesday"));
        this.days.add(new DayOfTheWeek("Wednesday"));
        this.days.add(new DayOfTheWeek("Thursday"));
        this.days.add(new DayOfTheWeek("Friday"));
        this.days.add(new DayOfTheWeek("Saturday"));
    }

    public DayOfTheWeek getDay(DayOfTheWeek d) {
        return this.days.get(d.getNumber());
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
        for (DayOfTheWeek d : days) {
            if (d.isSeletected()) {
                returnString = returnString + d.getName() + ", ";
            }
        }
        return returnString;
    }
}

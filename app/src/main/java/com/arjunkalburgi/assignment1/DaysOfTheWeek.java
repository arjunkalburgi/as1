package com.arjunkalburgi.assignment1;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Arjun on 2016-10-02.
 */

public class DaysOfTheWeek extends ArrayList<DayOfTheWeek> {
    // this is a list of all the Days of the week that the item will repeat on!

    public DaysOfTheWeek() {
        this.add(0, new DayOfTheWeek("Sunday"));
        this.add(1, new DayOfTheWeek("Monday"));
        this.add(2, new DayOfTheWeek("Tuesday"));
        this.add(3, new DayOfTheWeek("Wednesday"));
        this.add(4, new DayOfTheWeek("Thursday"));
        this.add(5, new DayOfTheWeek("Friday"));
        this.add(6, new DayOfTheWeek("Saturday"));
    }

    public DayOfTheWeek getDay(DayOfTheWeek d) {
        return this.get(d.getNumber());
    }

    public boolean isDaySeletect(String s) {
        return this.getDay(new DayOfTheWeek(s)).isSeletected();
    }

    public void selectDay(String s) {
        this.getDay(new DayOfTheWeek(s)).select();
    }

    @Override
    public String toString() {
        return "";
    }
}

package com.arjunkalburgi.assignment1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Arjun on 2016-09-28.
 */

public class Task {
    private String taskName;
    private int numTimesCompleted;
    private Date dateEntered;
    private DaysOfTheWeek repeatDays = new DaysOfTheWeek();

    Task(String s) {
        taskName = s;
        numTimesCompleted = 0;
        dateEntered = new Date();
    }

    public int incrementNumTimesCompleted() {
        numTimesCompleted++;
        return numTimesCompleted;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getNumTimesCompleted() {
        return numTimesCompleted;
    }

    public String getStartDate() {
        SimpleDateFormat MDY = new SimpleDateFormat("yyyy-mm-dd");
        return "Started on " + MDY.format(dateEntered);
    }

    public String getRepeatDays() {
        return "Repeats on " + repeatDays.toString();
    }

    public boolean doesTaskRepeatOn(String day) {
        return repeatDays.isDaySelected(day);
    }

    public void setTaskToRepeatOn(String day) {repeatDays.selectDay(day);}

    @Override
    public String toString(){
        return "" + taskName + " (" + numTimesCompleted + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            Task t = (Task)obj;
            if (this.getTaskName().equals(t.getTaskName()))
                return true;
        }
        return false;
    }
}

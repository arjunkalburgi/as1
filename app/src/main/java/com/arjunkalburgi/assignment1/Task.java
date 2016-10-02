package com.arjunkalburgi.assignment1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Arjun on 2016-09-28.
 */

public class Task {
    private String taskName;
    private int numberOfTimesCompleted;
    private Date dateEntered;
    private DaysOfTheWeek repeatDays = new DaysOfTheWeek();

    Task(String s) {
        taskName = s;
        numberOfTimesCompleted = 0;
        dateEntered = new Date();
    }

    public int incrementNumberOfTimesCompleted() {
        numberOfTimesCompleted++;
        return numberOfTimesCompleted;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getNumberOfTimesCompleted() {
        return numberOfTimesCompleted;
    }

    public String getStartDate() {
        SimpleDateFormat MDY = new SimpleDateFormat("MMM dd, yyyy");
        return "Started on " + MDY.format(dateEntered);
    }

    public String getRepeatDays() {
        return "Repeats on " + repeatDays.toString();
    }

    public void setTaskToRepeatOn(String day) {repeatDays.selectDay(day);}

    @Override
    public String toString(){
        return "" + taskName + " (" + numberOfTimesCompleted + ")";
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

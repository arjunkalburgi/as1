package com.arjunkalburgi.assignment1;

/**
 * Created by Arjun on 2016-09-28.
 */

public class Task {
    private String taskName;
    private int numberOfTimesCompleted;

    Task(String s) {
        taskName = s;
        numberOfTimesCompleted = 0;
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

    @Override
    public String toString(){
        return "" + taskName + " (" + numberOfTimesCompleted + ")";
    }
}

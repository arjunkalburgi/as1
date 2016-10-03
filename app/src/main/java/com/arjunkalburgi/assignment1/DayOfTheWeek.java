package com.arjunkalburgi.assignment1;

/**
 * Created by Arjun on 2016-10-02.
 */

public class DayOfTheWeek {
    private String name;
    private Integer number;
    private String letter;
    private boolean seletected = false;

    public DayOfTheWeek(String n) {
        this.name = n;
        switch (n) {
            case "Sunday":
                this.number = 0;
                this.letter = "S";
                break;
            case "Monday":
                this.number = 1;
                this.letter = "M";
                break;
            case "Tuesday":
                this.number = 2;
                this.letter = "T";
                break;
            case "Wednesday":
                this.number = 3;
                this.letter = "W";
                break;
            case "Thursday":
                this.number = 4;
                this.letter = "R";
                break;
            case "Friday":
                this.number = 5;
                this.letter = "F";
                break;
            case "Saturday":
                this.number = 6;
                this.letter = "S";
                break;
        }
    }

    public void select() {
        seletected = true;
    }

    public void unselect() {
        seletected = false;
    }

    public boolean isSeletected() {
        return seletected;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            DayOfTheWeek d = (DayOfTheWeek) obj;
            if (this.getName().equals(d.getName()))
                return true;
        }
        return false;
    }
}

package com.nathankumar.iforgot;

import java.util.Date;

public class Reminder {
    //Stores the id for the reminder type, 0 = urgent, 1 = casual, 2 = routine
    private int type;
    private String name;
    private String description;
    private String taskDate;
    private String taskTime;
    private boolean mon;
    private boolean tues;
    private boolean wed;
    private boolean thurs;
    private boolean fri;
    private boolean sat;
    private boolean sun;
    public Reminder(int type, String name, String description, String taskDate, String time) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.taskDate = taskDate;
        this.taskTime = time;
    }

    public Reminder(int type, String name, String description, String taskDate, String time, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.taskDate = taskDate;
        this.taskTime = time;
        this.mon = monday;
        this.tues = tuesday;
        this.wed = wednesday;
        this.thurs = thursday;
        this.fri = friday;
        this.sat = saturday;
        this.sun = sunday;
    }

    public int getType() {
        return this.type;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getDate() {
        if (!(taskDate.equals(""))) {
            return this.taskDate;
        }
        return "No Date";
    }
    public String getTime() {
        return this.taskTime;
    }
    public String getDays() {
        String tempDay = "";
        if (this.sun) {
            tempDay = tempDay + "S";
        } else {
            tempDay = tempDay + "-";
        }
        if (this.mon) {
            tempDay = tempDay + "M";
        } else {
            tempDay = tempDay + "-";
        }
        if (this.tues) {
            tempDay = tempDay + "T";
        } else {
            tempDay = tempDay + "-";
        }
        if (this.wed) {
            tempDay = tempDay + "W";
        } else {
            tempDay = tempDay + "-";
        }
        if (this.thurs) {
            tempDay = tempDay + "T";
        } else {
            tempDay = tempDay + "-";
        }
        if (this.fri) {
            tempDay = tempDay + "F";
        } else {
            tempDay = tempDay + "-";
        }
        if (this.sat) {
            tempDay = tempDay + "S ";
        } else {
            tempDay = tempDay + "-";
        }
        return tempDay;
    }
}

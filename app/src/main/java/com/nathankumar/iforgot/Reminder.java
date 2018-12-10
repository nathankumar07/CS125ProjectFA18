package com.nathankumar.iforgot;

import java.util.Date;

public class Reminder {
    //Stores the id for the reminder type, 0 = urgent, 1 = casual, 2 = routine
    private int type;
    private String name;
    private String description;
    private String taskDate;

    public Reminder(int type, String name, String description, String taskDate) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.taskDate = taskDate;
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
        return this.taskDate;
    }
}

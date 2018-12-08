package com.nathankumar.iforgot;

import java.util.Date;

public class Reminder {
    //Stores the id for the reminder type, 0 = urgent, 1 = casual, 2 = routine
    private int type;
    private String name;
    private String description;
    private Date taskDate;
}

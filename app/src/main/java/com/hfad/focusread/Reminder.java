package com.hfad.focusread;

public class Reminder {
    private  String date, time;

    public Reminder(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public String getReminderDate() {
        return date;
    }

    public String getReminderTime() {
        return time;
    }
}

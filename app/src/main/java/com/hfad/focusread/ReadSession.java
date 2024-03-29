package com.hfad.focusread;

import java.util.Date;
/**
 * class ReadSession containing the attributes for the list
 * **/
public class ReadSession {
    private int pagesRead;
    private boolean targetHit;
    private String date;
    private String time;

    public ReadSession(int pagesRead, boolean targetHit, String date, String time) {
        this.pagesRead = pagesRead;
        this.targetHit = targetHit;
        this.date = date;
        this.time = time;
    }

    public ReadSession() {

    }

    public int getPagesRead() {

        return pagesRead;
    }

    public void setPagesRead(int pagesRead) {

        this.pagesRead = pagesRead;
    }

    public boolean isTargetHit() {

        return targetHit;
    }

    public void setTargetHit(boolean targetHit) {

        this.targetHit = targetHit;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getTime() {

        return time;
    }
    public void setTime(String time){
        this.time = time;
    }
}
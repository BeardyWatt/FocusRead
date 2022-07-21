package com.hfad.focusread;

import java.util.Date;

public class Statistic {
    private int startingPage;
    private int target;
    private boolean targetHit;
    private Date date;

    public Statistic(int startingPage, int target, boolean targetHit, Date date) {
        this.startingPage = startingPage;
        this.target = target;
        this.targetHit = targetHit;
        this.date = date;
    }

    public int getStartingPage() {
        return startingPage;
    }

    public int getTarget() {
        return target;
    }

    public boolean isTargetHit() {
        return targetHit;
    }

    public Date getDate() {
        return date;
    }
}

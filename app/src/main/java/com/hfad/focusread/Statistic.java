package com.hfad.focusread;

public class Statistic {
    private int startingPage;
    private int target;
    private boolean targetHit;

    public Statistic(int startingPage, int target, boolean targetHit) {
        this.startingPage = startingPage;
        this.target = target;
        this.targetHit = targetHit;
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

    public void setStartingPage(int startingPage) {
        this.startingPage = startingPage;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void setTargetHit(boolean targetHit) {
        this.targetHit = targetHit;
    }
}
package com.hfad.focusread;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReadSessionTest {
    ReadSession readSession = new ReadSession();
    @Test
    public void getPagesRead() {
        readSession.setPagesRead(100);
        assert readSession.getPagesRead() == 100;
    }

    @Test
    public void setPagesRead() {
        readSession.setPagesRead(100);
        assert readSession.getPagesRead()== 100;
    }

    @Test
    public void isTargetHit() {
    }

    @Test
    public void setTargetHit() {

    }

    @Test
    public void getDate() {
    }

    @Test
    public void setDate() {
    }

    @Test
    public void getTime() {
      //readSession.setTime();
      //assert readSession.getTime()== 01;
    }

    @Test
    public void setTime() {
        //readSession.setTime();
        //assert readSession.getTime()== ;
    }
}
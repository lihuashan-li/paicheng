package com.maxxis.domain;

import javax.xml.crypto.Data;
import java.util.Date;

public class a {
    private Date gotowork;
    private  Date getoffwork;

    public Date getGotowork() {
        return gotowork;
    }

    public void setGotowork(Date gotowork) {
        this.gotowork = gotowork;
    }

    public Date getGetoffwork() {
        return getoffwork;
    }

    public void setGetoffwork(Date getoffwork) {
        this.getoffwork = getoffwork;
    }

    @Override
    public String toString() {
        return "a{" +
                "gotowork=" + gotowork +
                ", getoffwork=" + getoffwork +
                '}';
    }
}

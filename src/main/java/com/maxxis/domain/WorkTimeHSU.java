package com.maxxis.domain;

import com.maxxis.utils.StringUtils;

public class WorkTimeHSU {
    private int id;
    private long gotowork;
    private  String gotoworkStr;
    private long getoffwork;
    private  String getoffworkStr;
    private  int past;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getGotowork() {
        return gotowork;
    }

    public void setGotowork(long gotowork) {
        this.gotowork = gotowork;
    }

    public String getGotoworkStr() {
        if (gotowork!=0){
            gotoworkStr= StringUtils.LongString((gotowork*1000),"yyyy-MM-dd HH:mm:ss");
        }
        return gotoworkStr;
    }

    public void setGotoworkStr(String gotoworkStr) {
        this.gotoworkStr = gotoworkStr;
    }

    public long getGetoffwork() {
        return getoffwork;
    }

    public void setGetoffwork(long getoffwork) {
        this.getoffwork = getoffwork;
    }

    public String getGetoffworkStr() {
        if (getoffwork!=0){
            getoffworkStr= StringUtils.LongString((getoffwork*1000),"yyyy-MM-dd HH:mm:ss");
        }
        return getoffworkStr;
    }

    public void setGetoffworkStr(String getoffworkStr) {
        this.getoffworkStr = getoffworkStr;
    }

    public int getPast() {
        return past;
    }

    public void setPast(int past) {
        this.past = past;
    }

    @Override
    public String toString() {
        return "WorkTimeHSU{" +
                "id=" + id +
                ", gotowork=" + gotowork +
                ", gotoworkStr='" + gotoworkStr + '\'' +
                ", getoffwork=" + getoffwork +
                ", getoffworkStr='" + getoffworkStr + '\'' +
                ", past=" + past +
                '}';
    }
}

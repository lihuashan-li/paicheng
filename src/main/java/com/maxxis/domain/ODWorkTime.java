package com.maxxis.domain;

import java.util.Date;

public class ODWorkTime {

    //    QAWorkTimeId int not null
    //primary key,
    //    State        varchar(20) default '',
    //    StartTime    datetime,
    //    EndTime      datetime
    private int ODWorkTimeId;
    private String State;
    private Date StartTime;
    private Date EndTime;

    public int getODWorkTimeId() {
        return ODWorkTimeId;
    }

    public void setODWorkTimeId(int ODWorkTimeId) {
        this.ODWorkTimeId = ODWorkTimeId;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }
}

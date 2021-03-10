package com.maxxis.domain;

public class TimeTest {
    private String standard;
    private int duration;
    private String TestItem;

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTestItem() {
        return TestItem;
    }

    public void setTestItem(String testItem) {
        TestItem = testItem;
    }

    @Override
    public String toString() {
        return "TimeTest{" +
                "standard='" + standard + '\'' +
                ", duration=" + duration +
                ", TestItem='" + TestItem + '\'' +
                '}';
    }
}

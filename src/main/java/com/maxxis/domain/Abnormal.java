package com.maxxis.domain;

public class Abnormal {
    private String id;
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Abnormal{" +
                "id='" + id + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

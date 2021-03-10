package com.maxxis.domain;

public class ODAdjustOrderObj {
    private String adjustId;
    private String purposeOrder;

    public String getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(String adjustId) {
        this.adjustId = adjustId;
    }

    public String getPurposeOrder() {
        return purposeOrder;
    }

    public void setPurposeOrder(String purposeOrder) {
        this.purposeOrder = purposeOrder;
    }

    public ODAdjustOrderObj(String adjustId, String purposeOrder) {
        this.adjustId = adjustId;
        this.purposeOrder = purposeOrder;
    }
}

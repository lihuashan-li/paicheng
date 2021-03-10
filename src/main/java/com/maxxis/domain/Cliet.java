package com.maxxis.domain;

import com.maxxis.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

public class    Cliet implements Serializable {

    private String id;
    private String RequestId;
    private String Project;
    private String Purpose;
    private String ProjectEngineer;
    private String PriorityDes;
    private Date RequestTime;
    private String RequestTimeStr;
    private Date TimeA;
    private String TimeAStr;
    private String TestTime;
    private String ContactExt;
    private String Remark;
    private int PriorityDesNumber;
    private String sampleDisposal;
    private  String Target;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getProjectEngineer() {
        return ProjectEngineer;
    }

    public void setProjectEngineer(String projectEngineer) {
        ProjectEngineer = projectEngineer;
    }

    public String getPriorityDes() {
        return PriorityDes;
    }

    public void setPriorityDes(String priorityDes) {
        PriorityDes = priorityDes;
    }

    public Date getRequestTime() {
        return RequestTime;
    }

    public void setRequestTime(Date requestTime) {
        RequestTime = requestTime;
    }

    public String getRequestTimeStr() {
        if (RequestTime!=null){
            RequestTimeStr   =DateUtils.dateString(RequestTime,"yyyy-MM-dd");
        }
        return RequestTimeStr;
    }

    public void setRequestTimeStr(String requestTimeStr) {
        RequestTimeStr = requestTimeStr;
    }

    public Date getTimeA() {
        return TimeA;
    }

    public void setTimeA(Date timeA) {
        TimeA = timeA;
    }

    public String getTimeAStr() {
        if (TimeA!=null){
            TimeAStr   =DateUtils.dateString(TimeA,"yyyy-MM-dd HH:mm");
        }

        return TimeAStr;
    }

    public void setTimeAStr(String timeAStr) {
        TimeAStr = timeAStr;
    }

    public String getTestTime() {
        return TestTime;
    }

    public void setTestTime(String testTime) {
        TestTime = testTime;
    }

    public String getContactExt() {
        return ContactExt;
    }

    public void setContactExt(String contactExt) {
        ContactExt = contactExt;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getPriorityDesNumber() {
        return PriorityDesNumber;
    }

    public void setPriorityDesNumber(int priorityDesNumber) {
        PriorityDesNumber = priorityDesNumber;
    }

    public String getSampleDisposal() {
        return sampleDisposal;
    }

    public void setSampleDisposal(String sampleDisposal) {
        this.sampleDisposal = sampleDisposal;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        Target = target;
    }

    @Override
    public String toString() {
        return "Cliet{" +
                "id='" + id + '\'' +
                ", RequestId='" + RequestId + '\'' +
                ", Project='" + Project + '\'' +
                ", Purpose='" + Purpose + '\'' +
                ", ProjectEngineer='" + ProjectEngineer + '\'' +
                ", PriorityDes='" + PriorityDes + '\'' +
                ", RequestTime=" + RequestTime +
                ", RequestTimeStr='" + RequestTimeStr + '\'' +
                ", TimeA=" + TimeA +
                ", TimeAStr='" + TimeAStr + '\'' +
                ", TestTime='" + TestTime + '\'' +
                ", ContactExt='" + ContactExt + '\'' +
                ", Remark='" + Remark + '\'' +
                ", PriorityDesNumber=" + PriorityDesNumber +
                ", sampleDisposal='" + sampleDisposal + '\'' +
                ", Target='" + Target + '\'' +
                '}';
    }
}

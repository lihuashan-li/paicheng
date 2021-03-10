package com.maxxis.domain;

import com.maxxis.utils.DateUtils;

import java.util.Date;

public class TestClietTyre {
    private  int number;
    private  String idRctest;
    private  String TestItem;
    private  String sampleID;
    private  String Standard;
    private  String UsageRim;
    private  String pt;
    private  String Fz;
    private  String Vr;
    private  String CA;
    private  String SA;
    private  String PA;
    private  int Mileage;
    private  String FRAngleDeg;
    private  String FFTOrder;
    private  String FlatSpotPt;
    private  String FlatSpotFz;
    private Date ExpectedDate;
    private  String ExpectedDateStr;
    private  String state;
    private  String complete;
    private  String cancel;
    private  int red;
    private  int spbh;
    private  int caozuo;
    private  int kaishi;
    private  Date endTime;
    private  String endTimeStr;
    private  Date cancellationTime;
    private  String cancellationTimeStr;

    private String idRctyre;  //id 主键
    private  String Size;

    private String idRccliet;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getIdRctest() {
        return idRctest;
    }

    public void setIdRctest(String idRctest) {
        this.idRctest = idRctest;
    }

    public String getTestItem() {
        return TestItem;
    }

    public void setTestItem(String testItem) {
        TestItem = testItem;
    }

    public String getSampleID() {
        return sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String standard) {
        Standard = standard;
    }

    public String getUsageRim() {
        return UsageRim;
    }

    public void setUsageRim(String usageRim) {
        UsageRim = usageRim;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getFz() {
        return Fz;
    }

    public void setFz(String fz) {
        Fz = fz;
    }

    public String getVr() {
        return Vr;
    }

    public void setVr(String vr) {
        Vr = vr;
    }

    public String getCA() {
        return CA;
    }

    public void setCA(String CA) {
        this.CA = CA;
    }

    public String getSA() {
        return SA;
    }

    public void setSA(String SA) {
        this.SA = SA;
    }

    public String getPA() {
        return PA;
    }

    public void setPA(String PA) {
        this.PA = PA;
    }

    public int getMileage() {
        return Mileage;
    }

    public void setMileage(int mileage) {
        Mileage = mileage;
    }

    public String getFRAngleDeg() {
        return FRAngleDeg;
    }

    public void setFRAngleDeg(String FRAngleDeg) {
        this.FRAngleDeg = FRAngleDeg;
    }

    public String getFFTOrder() {
        return FFTOrder;
    }

    public void setFFTOrder(String FFTOrder) {
        this.FFTOrder = FFTOrder;
    }

    public String getFlatSpotPt() {
        return FlatSpotPt;
    }

    public void setFlatSpotPt(String flatSpotPt) {
        FlatSpotPt = flatSpotPt;
    }

    public String getFlatSpotFz() {
        return FlatSpotFz;
    }

    public void setFlatSpotFz(String flatSpotFz) {
        FlatSpotFz = flatSpotFz;
    }

    public Date getExpectedDate() {
        return ExpectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        ExpectedDate = expectedDate;
    }

    public String getExpectedDateStr() {
        String s="等待设定排测日期...";
        String p="未排";
        int completeA= Integer.parseInt(complete);

        if (ExpectedDate!=null){
            ExpectedDateStr   =DateUtils.dateString(ExpectedDate,"yyyy-MM-dd HH:mm");
            if (ExpectedDateStr.equals("1900-01-01 00:00")){
                ExpectedDateStr= s ;
            }
            if (number==0 && completeA==0){
                ExpectedDateStr=p;
            }

        }


        return ExpectedDateStr;
    }

    public void setExpectedDateStr(String expectedDateStr) {
        ExpectedDateStr = expectedDateStr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getSpbh() {
        return spbh;
    }

    public void setSpbh(int spbh) {
        this.spbh = spbh;
    }

    public int getCaozuo() {
        return caozuo;
    }

    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }

    public int getKaishi() {
        return kaishi;
    }

    public void setKaishi(int kaishi) {
        this.kaishi = kaishi;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeStr() {

        if (endTime!=null){
            endTimeStr  =DateUtils.dateString(endTime,"yyyy-MM-dd HH:mm");
        }

        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public Date getCancellationTime() {
        return cancellationTime;
    }

    public void setCancellationTime(Date cancellationTime) {
        this.cancellationTime = cancellationTime;
    }

    public String getCancellationTimeStr() {

        if (cancellationTime!=null){

            cancellationTimeStr  =DateUtils.dateString(cancellationTime,"yyyy-MM-dd HH:mm");
        }
        return cancellationTimeStr;
    }

    public void setCancellationTimeStr(String cancellationTimeStr) {
        this.cancellationTimeStr = cancellationTimeStr;
    }

    public String getIdRctyre() {
        return idRctyre;
    }

    public void setIdRctyre(String idRctyre) {
        this.idRctyre = idRctyre;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getIdRccliet() {
        return idRccliet;
    }

    public void setIdRccliet(String idRccliet) {
        this.idRccliet = idRccliet;
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
        return "TestClietTyre{" +
                "number=" + number +
                ", idRctest='" + idRctest + '\'' +
                ", TestItem='" + TestItem + '\'' +
                ", sampleID='" + sampleID + '\'' +
                ", Standard='" + Standard + '\'' +
                ", UsageRim='" + UsageRim + '\'' +
                ", pt='" + pt + '\'' +
                ", Fz='" + Fz + '\'' +
                ", Vr='" + Vr + '\'' +
                ", CA='" + CA + '\'' +
                ", SA='" + SA + '\'' +
                ", PA='" + PA + '\'' +
                ", Mileage=" + Mileage +
                ", FRAngleDeg='" + FRAngleDeg + '\'' +
                ", FFTOrder='" + FFTOrder + '\'' +
                ", FlatSpotPt='" + FlatSpotPt + '\'' +
                ", FlatSpotFz='" + FlatSpotFz + '\'' +
                ", ExpectedDate=" + ExpectedDate +
                ", ExpectedDateStr='" + ExpectedDateStr + '\'' +
                ", state='" + state + '\'' +
                ", complete='" + complete + '\'' +
                ", cancel='" + cancel + '\'' +
                ", red=" + red +
                ", spbh=" + spbh +
                ", caozuo=" + caozuo +
                ", kaishi=" + kaishi +
                ", endTime=" + endTime +
                ", endTimeStr='" + endTimeStr + '\'' +
                ", cancellationTime=" + cancellationTime +
                ", cancellationTimeStr='" + cancellationTimeStr + '\'' +
                ", idRctyre='" + idRctyre + '\'' +
                ", Size='" + Size + '\'' +
                ", idRccliet='" + idRccliet + '\'' +
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

package com.maxxis.domain;

import com.maxxis.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

public class Test implements Serializable {
    private  int number;
    private  String id;
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
    private  Date ExpectedDate;
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
    private  String factory;
    private  Tyre tyre;


    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (ExpectedDate!=null){
            ExpectedDateStr   =DateUtils.dateString(ExpectedDate,"yyyy-MM-dd HH:mm");
            if (ExpectedDateStr.equals("1900-01-01 00:00")){
                ExpectedDateStr= s ;
            }
//            if (number==0){
//                ExpectedDateStr=p;
//            }
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

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
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

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    @Override
    public String toString() {
        return "Test{" +
                "number=" + number +
                ", id='" + id + '\'' +
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
                ", factory='" + factory + '\'' +
                ", tyre=" + tyre +
                '}';
    }
}

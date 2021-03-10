package com.maxxis.service;

import com.maxxis.domain.WorkTimeRR;

import java.util.List;

public interface IWorkTimeRRServer {

    //    RR添加工时的方法
  public   void setRR(long gotowork, long getoffwork)throws  Exception;

    //   RR删除工时的方法
  public   void deleteWorkTimeIdRR(int id)throws  Exception;


    //    RR修改工时方法
 public    void UpdateWorkTimeRR(int id, long getoffwork)throws  Exception;

    //        添加工时之前先去查询
    List<WorkTimeRR> findRR()throws  Exception;
}

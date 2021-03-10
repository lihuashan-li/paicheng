package com.maxxis.service;

import com.maxxis.domain.WorkTimeSp;

import java.util.List;

public interface IWorkTimeSpServer {

    //    SP添加工时的方法
  public   void setSP(long gotowork, long getoffwork)throws  Exception;

    //    SP删除工时的方法
  public   void deleteWorkTimeIdSP(int id)throws  Exception;


    //    SP修改工时方法
 public    void UpdateWorkTimeSP(int id, long getoffwork)throws  Exception;

    //        添加工时之前先去查询
    List<WorkTimeSp> findSP()throws Exception;
}

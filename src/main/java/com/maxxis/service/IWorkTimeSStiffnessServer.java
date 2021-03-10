package com.maxxis.service;

import com.maxxis.domain.WorkTimeSStiffness;

import java.util.Date;
import java.util.List;

public interface IWorkTimeSStiffnessServer {

    //    SS添加工时的方法
  public   void setSStiffness(long gotowork, long getoffwork)throws  Exception;

    //    SS删除工时的方法
  public   void deleteWorkTimeIdSStiffness(int id)throws  Exception;


    //    SS修改工时方法
 public    void UpdateWorkTimeSStiffness(int id, long getoffwork)throws  Exception;

    //        添加工时之前先去查询
    List<WorkTimeSStiffness> findSStiffness()throws  Exception;
}

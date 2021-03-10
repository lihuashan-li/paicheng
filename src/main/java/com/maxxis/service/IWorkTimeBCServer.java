package com.maxxis.service;


import com.maxxis.domain.WorkTimeBC;

import java.util.List;

public interface IWorkTimeBCServer {
//BC添加工时的方法
   public   void setBC(long gotowork, long getoffwork)throws  Exception;
    //    BC删除工时的方法
   public void deleteWorkTimeIdBC(int id)throws  Exception;

    //   BC修改工时方法
   public void UpdateWorkTimeBC(int id, long getoffwork)throws  Exception;


    //        添加工时之前先去查询
    List<WorkTimeBC> findBC()throws Exception;
}

package com.maxxis.service;


import com.maxxis.domain.WorkTimeHSU;

import java.util.Date;
import java.util.List;

public interface IWorkTimeHSUServer {
//HSU添加工时的方法
   public   void setHSU(long gotowork, long getoffwork)throws  Exception;
    //    HSU删除工时的方法
   public void deleteWorkTimeIdHSU(int id)throws  Exception;

    //    HSU修改工时方法
   public void UpdateWorkTimeHSU(int id, long getoffwork)throws  Exception;


    void savea(String gotowork, String getoffwork)throws  Exception;

    //        添加工时之前先去查询
    List<WorkTimeHSU> findHSU()throws Exception;


}

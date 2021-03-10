package com.maxxis.service;


import com.maxxis.domain.WorkTime3D;

import java.util.List;

public interface IWorkTime3DServer {
//3D添加工时的方法
   public   void set3D(long gotowork, long getoffwork)throws  Exception;
    //   3D删除工时的方法
   public void deleteWorkTimeId3D(int id)throws  Exception;

    //   3D修改工时方法
   public void UpdateWorkTime3D(int id, long getoffwork)throws  Exception;

    //        添加工时之前先去查询
    List<WorkTime3D> find3D()throws Exception;
}

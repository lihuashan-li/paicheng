package com.maxxis.dao;

import com.maxxis.domain.WorkTime3D;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkTime3DDao {

//    BC添加工时的方法
    @Insert("insert into Rcworktime3D(gotowork,getoffwork)values(#{gotowork},#{getoffwork})")
   public void set3D(@Param("gotowork") long gotowork, @Param("getoffwork") long getoffwork)throws  Exception;

    //    3D删除工时的方法
    @Delete("delete from Rcworktime3D where id=#{id}")
   public void deleteWorkTimeId3D(int id)throws Exception;

    //   3D修改工时方法
    @Update("update Rcworktime3D set getoffwork=#{getoffwork} where id=#{id}")
  public   void UpdateWorkTime3D(@Param("id") int id, @Param("getoffwork") long getoffwork)throws Exception;

    //        添加工时之前先去查询
    @Select("select * from  Rcworktime3D where past=0")
    List<WorkTime3D> find3D()throws  Exception;
}

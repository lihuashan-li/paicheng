package com.maxxis.dao;

import com.maxxis.domain.WorkTimeBC;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkTimeBCDao {

//    BC添加工时的方法
    @Insert("insert into RcworktimeBC(gotowork,getoffwork)values(#{gotowork},#{getoffwork})")
   public void setBC(@Param("gotowork") long gotowork, @Param("getoffwork") long getoffwork)throws  Exception;


//    BC删除工时的方法
    @Delete("delete from RcworktimeBC where id=#{id}")
   public void deleteWorkTimeIdBC(int id)throws Exception;

    //   BC修改工时方法
    @Update("update RcworktimeBC set getoffwork=#{getoffwork} where id=#{id}")
  public   void UpdateWorkTimeBC(@Param("id") int id, @Param("getoffwork") long getoffwork)throws Exception;


    //        添加工时之前先去查询
    @Select("select * from  RcworktimeBC where past=0")
    List<WorkTimeBC> findBC()throws Exception;
}

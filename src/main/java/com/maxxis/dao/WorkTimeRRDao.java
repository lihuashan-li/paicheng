package com.maxxis.dao;

import com.maxxis.domain.WorkTimeRR;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkTimeRRDao {
    //    RR添加工时的方法
    @Insert("insert into RcworktimeRR(gotowork,getoffwork)values(#{gotowork},#{getoffwork})")
  public   void setRR(@Param("gotowork") long gotowork, @Param("getoffwork") long getoffwork);


    //    RR删除工时的方法
    @Delete("delete from RcworktimeRR where id=#{id}")
   public void deleteWorkTimeIdRR(int id)throws  Exception;


    //    RR修改工时方法
    @Update("update RcworktimeRR set getoffwork=#{getoffwork} where id=#{id}")
  public   void UpdateWorkTimeRR(@Param("id") int id, @Param("getoffwork") long getoffwork);


    //        添加工时之前先去查询
    @Select("select * from  RcworktimeRR where past=0")
    List<WorkTimeRR> findRR()throws  Exception;
}

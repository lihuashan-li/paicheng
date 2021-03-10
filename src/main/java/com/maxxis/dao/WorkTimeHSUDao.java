package com.maxxis.dao;

import com.maxxis.domain.WorkTimeHSU;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface WorkTimeHSUDao {

//    HSU添加工时的方法
    @Insert("insert into RcworktimeHSU(gotowork,getoffwork)values(#{gotowork},#{getoffwork})")
   public void setHSU(@Param("gotowork") long gotowork, @Param("getoffwork") long getoffwork)throws  Exception;


//    HSU删除工时的方法
    @Delete("delete from RcworktimeHSU where id=#{id}")
   public void deleteWorkTimeIdHSU(int id)throws Exception;

    //    HSU修改工时方法
    @Update("update RcworktimeHSU set getoffwork=#{getoffwork} where id=#{id}")
  public   void UpdateWorkTimeHSU(@Param("id") int id,@Param("getoffwork") long getoffwork)throws Exception;

    @Insert("insert into a(gotowork,getoffwork)values(#{gotowork},#{getoffwork})")
    void savea(@Param("gotowork")String gotowork, @Param("getoffwork") String getoffwork)throws Exception;

    //        添加工时之前先去查询
    @Select("select * from RcworktimeHSU where past=0")
    List<WorkTimeHSU> findHSU()throws Exception;
}

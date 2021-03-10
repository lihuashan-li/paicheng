package com.maxxis.dao;

import com.maxxis.domain.WorkTimeSp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkTimeSpDao {
    //    SP添加工时的方法
    @Insert("insert into RcworktimeSP(gotowork,getoffwork)values(#{gotowork},#{getoffwork})")
  public   void setSP(@Param("gotowork") long gotowork, @Param("getoffwork") long getoffwork);


    //    SP删除工时的方法
    @Delete("delete from RcworktimeSP where id=#{id}")
   public void deleteWorkTimeIdSP(int id)throws  Exception;


    //    SP修改工时方法
    @Update("update RcworktimeSP set getoffwork=#{getoffwork} where id=#{id}")
  public   void UpdateWorkTimeSP(@Param("id") int id,@Param("getoffwork") long getoffwork);

    //        添加工时之前先去查询
    @Select("select * from RcworktimeSP where past=0")
    List<WorkTimeSp> findSP()throws Exception;
}

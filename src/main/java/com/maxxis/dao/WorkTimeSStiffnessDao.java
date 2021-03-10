package com.maxxis.dao;

import com.maxxis.domain.WorkTimeSStiffness;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface WorkTimeSStiffnessDao {
    //    SS添加工时的方法
    @Insert("insert into RcworktimeSStiffness(gotowork,getoffwork)values(#{gotowork},#{getoffwork})")
  public   void setSStiffness(@Param("gotowork") long gotowork, @Param("getoffwork") long getoffwork);


    //    SS删除工时的方法
    @Delete("delete from RcworktimeSStiffness where id=#{id}")
   public void deleteWorkTimeIdSStiffness(int id)throws  Exception;


    //    SS修改工时方法
    @Update("update RcworktimeSStiffness set getoffwork=#{getoffwork} where id=#{id}")
  public   void UpdateWorkTimeSStiffness(@Param("id") int id, @Param("getoffwork") long getoffwork);

    //        添加工时之前先去查询
    @Select("select * from RcworktimeSStiffness where past=0")
    List<WorkTimeSStiffness> findSStiffness()throws Exception;
}

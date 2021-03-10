package com.maxxis.dao;

import com.maxxis.domain.Abnormal;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AbnormalDao {
    //暂停原因保存
    @Insert("insert into Rcabnormal(id,remarks)values(#{id},#{remarks})")
    public void saveAbnormal(@Param("remarks") String remarks, @Param("id") String id) throws Exception;

    //      更改背景颜色
    @Update("update Rctest set red=1 where id=#{idd}")
   public void updateTestRed(String idd)throws  Exception;


    //   出错信息方法
    @Select("select * from  Rcabnormal where id=#{id} ")
  public   Abnormal remarks(String id)throws  Exception;

    //      暂停时隐藏完成按钮
    @Update("update Rctest set spbh=0 where id=#{id}")
     public void spbhA(String id)throws  Exception;
}



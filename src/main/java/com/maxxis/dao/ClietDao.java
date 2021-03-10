package com.maxxis.dao;

import com.maxxis.domain.Cliet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
public interface ClietDao{
//    @Select("select * from Rccliet where id=#{id} and RequestId like '%CSTC%'")
    @Select("select * from Rccliet where id=#{id}")
    public List<Cliet> findById(String id)throws Exception;

    @Select("select *  from Rccliet where  order by RequestTime,PriorityDesNumber")
    public List<Cliet> findAll()throws Exception;

    @Select("select *  from Rccliet where id=#{id}")
    public List<Cliet> findByClietId(String id);

//    项目工程师搜索
    @Select("select * from Rccliet where ProjectEngineer")
  public   List<Cliet> findByclietEngineer(String engineer);

}

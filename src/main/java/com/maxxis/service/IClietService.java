package com.maxxis.service;

import com.maxxis.domain.Cliet;

import java.util.List;

public interface IClietService {

//    public List<Cliet> findAll() throws  Exception ;
    public List<Cliet> findAll(int page,int size) throws  Exception ;

  public    List<Cliet> findByClietId(String id) throws  Exception;

  //项目工程师搜索
   public List<Cliet> findByclietEngineer(String engineer) throws Exception;


}

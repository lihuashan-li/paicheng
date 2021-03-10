package com.maxxis.service;

import com.maxxis.domain.Tyre;

import java.util.List;

public interface ITyreService {

 public  List<Tyre> findByClietId(String clietId) throws  Exception;

  public   List<Tyre> findByTyreIdA(String id) throws Exception;
}

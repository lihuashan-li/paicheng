package com.maxxis.service;

import com.maxxis.domain.Permission;

import java.util.List;

public interface IPermissionServer {
  public    List<Permission> findAll(int page,int size) throws  Exception;
}

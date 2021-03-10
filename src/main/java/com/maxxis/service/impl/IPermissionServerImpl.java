package com.maxxis.service.impl;

import com.github.pagehelper.PageHelper;
import com.maxxis.dao.PermissionDao;
import com.maxxis.domain.Permission;
import com.maxxis.service.IPermissionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPermissionServerImpl implements IPermissionServer {
    @Autowired
  private   PermissionDao permissionDao;
    @Override
    public List<Permission> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }
}

package com.maxxis.service.impl;

import com.maxxis.dao.RoleDao;
import com.maxxis.domain.Permission;
import com.maxxis.domain.Role;
import com.maxxis.service.IRoleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IRoleServerImpl implements IRoleServer {
@Autowired
private RoleDao roleDao;
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public Role findById(int id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(int id) throws Exception {
        return roleDao.findRoleByIdAndAllPermission(id);
    }

    @Override
    public void addPermissionToRole(int roleId, int[] permissionds) throws Exception {
        for (int permissiond :permissionds){

            roleDao.addPermissionToRole(roleId,permissiond);
        }
    }

    //删除角色
    @Override
    public void DeleteById(int id) throws Exception {
        roleDao.DeleteById(id);
    }

    //添加保存角色
    @Override
    public void roleAdd(String roleName, String roleDesc) throws Exception {
        roleDao.roleAdd(roleName,roleDesc);
    }

    @Override
    public List<Role> findByRoleRoleNzme(String roleName) throws Exception {
        return roleDao.findByRoleRoleNzme(roleName);
    }
}

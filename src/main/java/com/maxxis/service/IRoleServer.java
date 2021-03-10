package com.maxxis.service;

import com.maxxis.domain.Permission;
import com.maxxis.domain.Role;

import java.util.List;

public interface IRoleServer {
//    查询所有角色
   public   List<Role> findAll() throws  Exception;

   public   Role findById(int id) throws Exception;

  public   List<Permission>  findRoleByIdAndAllPermission(int id)throws  Exception;

    // /为用户添加角色
   public   void addPermissionToRole(int roleId, int[] permissionds)throws  Exception;

   //删除角色
    public void DeleteById(int id)throws  Exception;

   public   void roleAdd(String roleName, String roleDesc)throws  Exception;

   public  List<Role> findByRoleRoleNzme(String roleName)throws  Exception;
}

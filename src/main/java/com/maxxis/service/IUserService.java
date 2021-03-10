package com.maxxis.service;

import com.maxxis.domain.Permission;
import com.maxxis.domain.Role;
import com.maxxis.domain.Users;

import java.util.List;

public interface IUserService {

    public Users findByUsername(String username)throws  Exception;

     public   List<Users> findAll(int page,int size)throws  Exception;

      public   void save(Users users) throws Exception;


      public  List<Role> findRoleById(int id) throws  Exception;


     public   List<Permission> findPermissionByrid(int rid) throws  Exception;

     //用户详情方法
    public   Users   findById(int id) throws Exception;
    //用户删除
   public  void delete(int id) throws Exception;

   //查询
   public   List<Users>  findByUserUsername(String username) throws  Exception;

   //根据id查询出来这个用户没有的角色
    public   List<Role> findUserByIdAndAllRole(int userId);

    //用户添加角色
    public    void addRoleToUser(int userId, int[] roleids) throws Exception;
}

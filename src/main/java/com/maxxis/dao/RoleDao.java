package com.maxxis.dao;

import com.maxxis.domain.Permission;
import com.maxxis.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RoleDao {

    @Select("select * from Rcrole where id in (select roleId from Rcuser_role where usersId=#{userId})")
    @Results({
            @Result(id=true,column ="id" ,property = "id"),
            @Result(column ="roleName" ,property = "roleName"),
            @Result(column ="roleDesc" ,property = "roleDesc"),
//           @Result(column = "id",property = "permissions",javaType = Permission.class,one = @One(select = "com.maxxis.dao.PermissionDao.findPermissionByRoleId")),
         @Result(column = "id",property = "permissions",javaType = java.util.List.class,many = @Many(select = "com.maxxis.dao.PermissionDao.findPermissionByRoleId")),
    })
        public List<Role> findRoleByUserId(String userId) throws  Exception;
//      查询所有角色
    @Select("select * from  Rcrole")
      public   List<Role> findAll() throws  Exception;


    //根据id查询出这个角色
    @Select("select * from Rcrole where id=#{id}")
   public Role findById(int id)throws  Exception;


   //根据id查询出这个角色没有的权限
    @Select("select * from Rcpermission where  id not in (select permissionSid from Rcrole_permission where roleSid=#{id})")
   public List<Permission> findRoleByIdAndAllPermission(int id);

//    为用户添加角色
    @Insert("insert into Rcrole_permission(roleSid,permissionSid)values(#{roleId},#{permissiond})")
    public  void addPermissionToRole(@Param("roleId") int roleId, @Param("permissiond") int permissiond)throws  Exception;


//    删除角色
    @Delete("delete from Rcrole where id=#{id}")
   public void DeleteById(int id)throws  Exception;

//    保存添加角色
    @Insert("insert into Rcrole(roleName,roleDesc)values(#{roleName},#{roleDesc})")
   public void roleAdd(@Param("roleName") String roleName, @Param("roleDesc") String roleDesc)throws  Exception;


//    搜索
    @Select("select * from Rcrole where roleName=#{roleName}")
    public  List<Role> findByRoleRoleNzme(String roleName)throws  Exception;
}

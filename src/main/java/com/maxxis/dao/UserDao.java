package com.maxxis.dao;

import com.maxxis.domain.Permission;
import com.maxxis.domain.Role;
import com.maxxis.domain.Users;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface UserDao {
//    登录的方法
    @Select("select * from Rcusers where username=#{username}")
    public Users findByUsername(String username)throws  Exception;


    //查询所有的用户
    @Select("select * from Rcusers")
    public  List<Users> findAll();
    //添加用户
    @Insert("insert into Rcusers(email,username,PASSWORD,phonenum)values(#{email},#{username},#{PASSWORD},#{phonenum})")
       public void save(Users users);

      // 查询用户角色
       @Select("select * from Rcrole where id in (select roleId from Rcuser_role where usersId=#{id})")
        public  List<Role> findRoleById(int id)throws Exception;
//       查询用户的权限
@Select("select * from Rcpermission where id in (select permissionSid from Rcrole_permission where roleSid=#{rid})")
      public List<Permission> findPermissionByrid(int rid)throws  Exception;

    //用户详情
    @Select("select * from  Rcusers where id=#{id}")
   @Results({
           @Result(id = true,column = "id",property = "id"),
           @Result(column = "email",property = "email"),
           @Result(column = "username",property = "username"),
           @Result(column = "PASSWORD",property = "PASSWORD"),
           @Result(column = "phonenum",property = "phonenum"),
           @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "com.maxxis.dao.RoleDao.findRoleByUserId"))


   })
            public  Users findById(int id) ;


         //    用户删除
             @Delete("delete from Rcusers where id=#{id}")
             public  void delete(int id) throws  Exception;

             //查询
    @Select("select * from  Rcusers where username=#{username}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "email",property = "email"),
            @Result(column = "username",property = "username"),
            @Result(column = "PASSWORD",property = "PASSWORD"),
            @Result(column = "phonenum",property = "phonenum"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "com.maxxis.dao.RoleDao.findRoleByUserId"))


    })
          public  List<Users> findByUserUsername(String username)  throws Exception;


    //根据id查询出来这个用户没有的角色
    @Select("select  * from  Rcrole  where id not in (select roleId from Rcuser_role where usersId=#{userId})")
    List<Role> findUserByIdAndAllRole(int userId);

    @Insert("insert into Rcuser_role(usersId,roleId)values(#{userId},#{roleids1})")
     public    void addRoleToUser(@Param("userId") int userId,@Param("roleids1") int roleids1)throws  Exception;
}

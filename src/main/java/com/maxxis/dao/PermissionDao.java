package com.maxxis.dao;

import com.maxxis.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PermissionDao {
    @Select("select * from Rcpermission where id in (select permissionSid from Rcrole_permission where roleSid=#{roleId})")
    public List<Permission> findPermissionByRoleId(String roleId);

    @Select("select * from Rcpermission")
    public  List<Permission> findAll()throws  Exception;
}

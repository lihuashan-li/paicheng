package com.maxxis.controller;

import com.maxxis.domain.Permission;
import com.maxxis.domain.Role;
import com.maxxis.service.IRoleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/role")
public class roleController {

ModelAndView modelAndView=new ModelAndView();
@Autowired
private IRoleServer iRoleServer;
@RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception{
     List<Role> roles= iRoleServer.findAll();
   modelAndView.addObject("roleList",roles);
   modelAndView.setViewName("role-list");
        return modelAndView;
    }

//    添加权限
    @RequestMapping("/findRoleByIdAndAllPermission")
    public  ModelAndView findRoleByIdAndAllPermission(int id)throws  Exception{
//    查询出这个角色
      Role role=  iRoleServer.findById(id);

//查询出这个角色没有的权限
     List<Permission> permissions= iRoleServer.findRoleByIdAndAllPermission(id);

     modelAndView.addObject("role",role);
     modelAndView.addObject("permissionList",permissions);
     modelAndView.setViewName("role-permission-add");
    return modelAndView;
    }

//    为角色添加权限
    @RequestMapping("/addPermissionToRole")
    public  String addPermissionToRole(int roleId,@RequestParam(name = "ids",required = true) int[] permissionds)throws Exception{
        iRoleServer.addPermissionToRole(roleId,permissionds);
        return "redirect:/role/findAll";
    }


//    删除角色
    @RequestMapping("/DeleteById")
    public String  DeleteById(int id)throws  Exception{
        iRoleServer.DeleteById(id);
    return "redirect:/role/findAll";

    }

//    添加保存角色
    @RequestMapping("/roleAdd")
    public String roleAdd(@RequestParam(name = "roleName",required = true)String roleName ,@RequestParam(name = "roleDesc",required = true)String roleDesc )throws  Exception{
        iRoleServer.roleAdd(roleName,roleDesc);
        return "redirect:/role/findAll";
    }

//    搜索
    @RequestMapping("/findByRoleRoleNzme")
    public  ModelAndView findByRoleRoleNzme(String roleName)throws  Exception{
        List<Role> roles= iRoleServer.findByRoleRoleNzme(roleName);
        modelAndView.addObject("roleList",roles);
        modelAndView.setViewName("role-list");

    return modelAndView;
    }

}

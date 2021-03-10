package com.maxxis.controller;

import com.github.pagehelper.PageInfo;
import com.maxxis.domain.Permission;
import com.maxxis.service.IPermissionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/permission")
public class permissionController {

    ModelAndView modelAndView =new ModelAndView();
    @Autowired
    private IPermissionServer iPermissionServer;

    //查询所有权限
    @RequestMapping("/findAll")
    public  ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "10") int size)throws Exception{
      List<Permission> permissions= iPermissionServer.findAll(page,size);
        PageInfo pageInfo = new PageInfo(permissions);
      modelAndView.addObject("permission",pageInfo);
      modelAndView.setViewName("permission-list");
       return modelAndView;
    }
}

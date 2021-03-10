package com.maxxis.controller;

import com.github.pagehelper.PageInfo;
import com.maxxis.domain.Cliet;
import com.maxxis.domain.Msg;
import com.maxxis.service.IClietService;
import com.maxxis.service.ITestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/cliet")
public class clietController {
    @Autowired
  private IClietService iClietService;

    ModelAndView modelAndView = new ModelAndView();



    //  cliet-list方法
//    未分页
//  @RequestMapping("findAll")
//  public  ModelAndView findAll()throws  Exception{
//      List<Cliet> cliets = iClietService.findAll();
//      modelAndView.addObject("clietList",cliets);
//      modelAndView.setViewName("cliet-list");
//      System.out.println(cliets);
//      return modelAndView;
//  }
//  分页代码
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")int page, @RequestParam(name = "size",required = true,defaultValue = "4")int size) throws Exception {
        List<Cliet> cliets = iClietService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(cliets);
        modelAndView.addObject("clietList", pageInfo);
        modelAndView.setViewName("cliet-page-list");
        return modelAndView;
    }
//cliet-list-A方法
        @RequestMapping("/findByClietId")
        public ModelAndView findByClietId ( String id)throws Exception {
            List<Cliet> all = iClietService.findByClietId(id);
            modelAndView.addObject("clietList", all);
            modelAndView.setViewName("cliet-list-A");
            return modelAndView;
        }






//        项目工程师搜索
    public  ModelAndView findByclietEngineer(String Engineer) throws  Exception{
        List<Cliet> all=iClietService.findByclietEngineer(Engineer);

        return modelAndView;
    }



    }


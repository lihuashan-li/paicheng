package com.maxxis.controller;

import com.maxxis.domain.Tyre;
import com.maxxis.service.ITyreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/tyre")

public class tyreController {
ModelAndView modelAndView=new ModelAndView();
    @Autowired
    private ITyreService iTyreService;
//    tyre-list的方法  先查询cliet在查询tyre 在查询test
    @RequestMapping("/findByClietId")
    public ModelAndView findByClietId(String clietId) throws  Exception{
        List<Tyre> all=iTyreService.findByClietId(clietId);
        modelAndView.addObject("tyreList",all);
        modelAndView.setViewName("tyre-list");
        return modelAndView;
    }


// tyre-list-A的方法  先查询cliet在查询tyre 在查询test
    @RequestMapping("/findByTyreIdA")
     public ModelAndView findByTyreIdA(String id)throws Exception{
      List<Tyre> all= iTyreService.findByTyreIdA(id);
      modelAndView.addObject("tyreList",all);
      modelAndView.setViewName("tyre-list-A");
        return modelAndView;
     }
}

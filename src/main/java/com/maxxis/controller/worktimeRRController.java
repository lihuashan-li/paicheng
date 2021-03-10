package com.maxxis.controller;


import com.maxxis.domain.WorkTimeRR;
import com.maxxis.domain.WorkTimeSp;
import com.maxxis.service.ITestService;
import com.maxxis.service.IWorkTimeRRServer;
import com.maxxis.service.IWorkTimeSpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/workTimeRR")
public class worktimeRRController {
    @Autowired
    private IWorkTimeRRServer iWorkTimeRRServer;
    @Autowired
    private ITestService iTestService;
    ModelAndView modelAndView=new ModelAndView();

    //   RR添加工时的方法
    @RequestMapping("/setRR")
    public String  setRR(@RequestParam(name = "startDate",required = true) String gotowork, @RequestParam(name = "endDate",required = true) String getoffwork)throws Exception{
////        iUserService.save(users);

        long Gotowork = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(gotowork).getTime()/1000;
        long Getoffwork = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getoffwork).getTime()/1000;
        //        添加工时之前先去查询
        List<WorkTimeRR> list=  iWorkTimeRRServer.findRR();
        System.out.println(list);
        boolean a;
        boolean b;
        List<Boolean> list1= new ArrayList<>();
        boolean c=false;
        for (int i = 0; i <list.size() ; i++){
            a= list.get(i).getGotowork()<=Gotowork&&Gotowork<=list.get(i).getGetoffwork();
            b= list.get(i).getGotowork()<=Getoffwork&&Getoffwork<=list.get(i).getGetoffwork();
            list1.add(a);
            list1.add(b);
        }
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i)==true){
                c=list1.get(i);
                break;
            }
        }
        if (c==false){
            iWorkTimeRRServer.setRR(Gotowork,Getoffwork);
        }
        return "redirect:/workTimeRR/findWorkTimeSPShuaXinRR";

    }

    //   RR删除工时的方法
    @RequestMapping("/deleteWorkTimeIdRR")
    public  String deleteWorkTimeIdRR(int id)throws  Exception{
        iWorkTimeRRServer.deleteWorkTimeIdRR(id);

        return "redirect:/workTimeRR/findWorkTimeSPShuaXinRR";
    }

    //    RR修改工时方法
    @RequestMapping("/UpdateWorkTimeRR")
    public  String UpdateWorkTimeRR(@RequestParam(name = "idB",required = true) int id, @RequestParam(name = "end",required = true) String getoffwork)throws Exception{

        long Getoffwork = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getoffwork).getTime()/1000;

        iWorkTimeRRServer.UpdateWorkTimeRR(id,Getoffwork);
        return "redirect:/workTimeRR/findWorkTimeSPShuaXinRR";
    }


    @RequestMapping("/findWorkTimeSPShuaXinRR")
    public ModelAndView findWorkTimeSPShuaXinRR()throws  Exception{
//        先把已经过去的时间进行隐藏（past变成1）
        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long zeroA = zero / 1000;
//        把时间像前面退2天
        long result1a=zeroA-172800;

     //  RR 把已经过的时间的past变成1
        iTestService.updatePastRR(result1a);
//  RR 查询出没有过去的工时
      List<WorkTimeRR>  list= iTestService.findByRcworktimeRR();
      modelAndView.addObject("WorkTime",list);
      modelAndView.setViewName("setRR");
        return modelAndView;
    }

//    时间搜索
    @RequestMapping("/findWorkTimeSPSouSuoRR")
    public  ModelAndView findWorkTimeSPSouSuoRR(String gotowork) throws Exception {
        long gotoworkS = new SimpleDateFormat("yyyy-MM-dd").parse(gotowork).getTime()/1000;
        long gotoworkLingChen= gotoworkS;
        long gotoworkWanShang= gotoworkLingChen+86399;
//        RR时间搜索按钮
       List<WorkTimeRR> list= iTestService.findWorkTimeSouSuoRR(gotoworkLingChen,gotoworkWanShang);
       modelAndView.addObject("WorkTime",list);
       modelAndView.setViewName("setRR");
       return  modelAndView;
    }






}

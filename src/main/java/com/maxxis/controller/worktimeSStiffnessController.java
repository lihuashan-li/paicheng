package com.maxxis.controller;


import com.maxxis.domain.WorkTimeSStiffness;
import com.maxxis.domain.WorkTimeSp;
import com.maxxis.service.ITestService;
import com.maxxis.service.IWorkTimeSStiffnessServer;
import com.maxxis.service.IWorkTimeSpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/workTimeSStiffness")
public class worktimeSStiffnessController {
    @Autowired
    private IWorkTimeSStiffnessServer iWorkTimeSStiffnessServer;
    @Autowired
    private ITestService iTestService;
    ModelAndView modelAndView=new ModelAndView();


    //   SStiffness添加工时的方法
    @RequestMapping("/setSStiffness")
    @ResponseBody
    public String  setSStiffness(@RequestParam(name = "startDate",required = true) String gotowork, @RequestParam(name = "endDate",required = true) String getoffwork)throws Exception{
        long Gotowork = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(gotowork).getTime()/1000;
        long Getoffwork = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getoffwork).getTime()/1000;
        //        添加工时之前先去查询
        List<WorkTimeSStiffness> list=  iWorkTimeSStiffnessServer.findSStiffness();
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
        System.out.println(list1);
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i)==true){
                c=list1.get(i);
                break;
            }
        }
        if (c==false){
            iWorkTimeSStiffnessServer.setSStiffness(Gotowork,Getoffwork);
            return "保存成功";
        }else {
            return "时间冲突";
        }

    }

    //  SStiffness删除工时的方法
    @RequestMapping("/deleteWorkTimeIdSStiffness")
    public  String deleteWorkTimeIdSStiffness(int id)throws  Exception{
        iWorkTimeSStiffnessServer.deleteWorkTimeIdSStiffness(id);

        return "redirect:/workTimeSStiffness/findWorkTimeSStiffnessShuaXin";
    }

    //    SP修改工时方法
    @RequestMapping("/UpdateWorkTimeSStiffness")
    public  String UpdateWorkTimeSStiffness(@RequestParam(name = "idB",required = true) int id, @RequestParam(name = "end",required = true) String getoffwork)throws Exception{
//        long Gotowork = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(gotowork).getTime()/1000;
//        System.out.println(Gotowork);
        long Getoffwork = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getoffwork).getTime()/1000;

        iWorkTimeSStiffnessServer.UpdateWorkTimeSStiffness(id,Getoffwork);
        return "redirect:/workTimeSStiffness/findWorkTimeSStiffnessShuaXin";
    }


    @RequestMapping("/findWorkTimeSStiffnessShuaXin")
    public ModelAndView findWorkTimeSPShuaXin()throws  Exception{
//        先把已经过去的时间进行隐藏（past变成1）
        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long zeroA = zero / 1000;
//        把时间像前面退2天
        long result1a=zeroA-172800;

     //  SStiffness 把已经过的时间的past变成1
        iTestService.updatePastSStiffness(result1a);
//  SStiffness查询出没有过去的工时
      List<WorkTimeSStiffness>  list= iTestService.findByRcworktimeSStiffness();
      modelAndView.addObject("WorkTime",list);
      modelAndView.setViewName("setSStiffness");
        return modelAndView;
    }

//    时间搜索
    @RequestMapping("/findWorkTimeSStiffnessSouSuo")
    public  ModelAndView findWorkTimeSStiffnessSouSuo(String gotowork) throws Exception {
        long gotoworkS = new SimpleDateFormat("yyyy-MM-dd").parse(gotowork).getTime()/1000;
        long gotoworkLingChen= gotoworkS;
        long gotoworkWanShang= gotoworkLingChen+86399;
//        SStiffness时间搜索按钮
       List<WorkTimeSp> list= iTestService.findWorkTimeSStiffnessSouSuo(gotoworkLingChen,gotoworkWanShang);
       modelAndView.addObject("WorkTime",list);
       modelAndView.setViewName("setSStiffness");
       return  modelAndView;
    }






}

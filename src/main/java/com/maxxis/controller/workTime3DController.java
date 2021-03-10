package com.maxxis.controller;

import com.maxxis.domain.WorkTime3D;
import com.maxxis.domain.WorkTimeBC;
import com.maxxis.service.ITestService;
import com.maxxis.service.IWorkTime3DServer;
import com.maxxis.service.IWorkTimeBCServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/workTime3D")
public class workTime3DController {

    @Autowired
    private IWorkTime3DServer iWorkTime3DServer;
    @Autowired
    private ITestService iTestService;
    ModelAndView modelAndView=new ModelAndView();
//    3D添加工时的方法
    @RequestMapping("/set3D")
     @ResponseBody
    public String set3D(@RequestParam(name = "startDate",required = true) String gotowork, @RequestParam(name = "endDate",required = true) String getoffwork)throws Exception{
////        iUserService.save(users);

        long Gotowork = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(gotowork).getTime()/1000;
        long Getoffwork = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getoffwork).getTime()/1000;
        //        添加工时之前先去查询
        List<WorkTime3D> list=  iWorkTime3DServer.find3D();
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
            iWorkTime3DServer.set3D(Gotowork,Getoffwork);
            return "保存成功";
        }else {
            return "时间冲突";
        }
    }

//    3D删除工时的方法
    @RequestMapping("/deleteWorkTimeId3D")
    public  String deleteWorkTimeId3D(int id)throws  Exception{
        iWorkTime3DServer.deleteWorkTimeId3D(id);
       return "redirect:/workTime3D/findWorkTimeShuaXin3D";
    }

//   3D修改工时方法
    @RequestMapping("/UpdateWorkTime3D")
    public  String UpdateWorkTime3D(@RequestParam(name = "idB",required = true) int id,@RequestParam(name = "start",required = true) String gotowork, @RequestParam(name = "end",required = true) String getoffwork)throws Exception{
//        long Gotowork = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(gotowork).getTime()/1000;
//        System.out.println(Gotowork);
        long Getoffwork = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getoffwork).getTime()/1000;
        System.out.println(Getoffwork);

        iWorkTime3DServer.UpdateWorkTime3D(id,Getoffwork);
        return "redirect:/workTime3D/findWorkTimeShuaXin3D";
    }

    @RequestMapping("/findWorkTimeShuaXin3D")
    public ModelAndView findWorkTimeShuaXin3D()throws  Exception{
//        先把已经过去的时间进行隐藏（past变成1）
        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long zeroA = zero / 1000;
//        把时间像前面退2天
        long result1a=zeroA-172800;

        //  3D把已经过的时间的past变成1
        iTestService.updatePast3D(result1a);
//      3D查询出没有过去的工时
        List<WorkTime3D>  list= iTestService.findByRcworktime3D();
        modelAndView.addObject("WorkTime",list);
        modelAndView.setViewName("set3D");
        return modelAndView;
    }


    //    3D时间搜索
    @RequestMapping("/findWorkTimeSouSuo3D")
    public ModelAndView findWorkTimeSouSuo3D(String gotowork) throws Exception {
        long gotoworkS = new SimpleDateFormat("yyyy-MM-dd").parse(gotowork).getTime()/1000;
        long gotoworkLingChen= gotoworkS;
        long gotoworkWanShang= gotoworkLingChen+86399;
//       3D时间搜索按钮
        List<WorkTime3D> list= iTestService.findWorkTimeSouSuo3D(gotoworkLingChen,gotoworkWanShang);
        modelAndView.addObject("WorkTime",list);
        modelAndView.setViewName("set3D");
        return  modelAndView;
    }




}

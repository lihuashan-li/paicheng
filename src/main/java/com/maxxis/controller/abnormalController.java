package com.maxxis.controller;

import com.maxxis.domain.Abnormal;
import com.maxxis.service.IAbnormalServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/abnormal")
public class abnormalController {
  @Autowired
    private IAbnormalServer iAbnormalServer;
//暂停原因保存
  @RequestMapping("/saveAbnormal")
    public  String saveAbnormal(String remarks, @RequestParam(name = "id", required = true) String id)throws Exception{
      String idd=id;
//      System.out.println("xnjscbdsjbcshdcbsdhcbsdhcbsdhcsdjhc"+idd);
      iAbnormalServer.saveAbnormal(remarks,id);
//      更改背景颜色
      iAbnormalServer.updateTestRed(idd);

//      暂停时隐藏完成按钮
      iAbnormalServer.spbhA(id);

      return "redirect:/test/findTestByTestItemSP";
  }

    //   出错信息方法
    @ResponseBody
    @RequestMapping("/remarks")
    public Abnormal remarks(String id) throws Exception {

        Abnormal all = iAbnormalServer.remarks(id);
        System.out.println(all);
        return all;
    }
//    修改1



//暂停原因保存RR
  @RequestMapping("/saveAbnormalRR")
  public  String saveAbnormalRR(String remarks, @RequestParam(name = "id", required = true) String id)throws Exception{
    String idd=id;
//      System.out.println("xnjscbdsjbcshdcbsdhcbsdhcbsdhcsdjhc"+idd);
    iAbnormalServer.saveAbnormal(remarks,id);
//      更改背景颜色
    iAbnormalServer.updateTestRed(idd);

//      暂停时隐藏完成按钮
    iAbnormalServer.spbhA(id);

    return "redirect:/test/findTestByTestItemRR";
  }

  //暂停原因保存SStiffness
  @RequestMapping("/saveAbnormalSStiffness")
  public  String saveAbnormalSStiffness(String remarks, @RequestParam(name = "id", required = true) String id)throws Exception{
    String idd=id;
//      System.out.println("xnjscbdsjbcshdcbsdhcbsdhcbsdhcsdjhc"+idd);
    iAbnormalServer.saveAbnormal(remarks,id);
//      更改背景颜色
    iAbnormalServer.updateTestRed(idd);

//      暂停时隐藏完成按钮
    iAbnormalServer.spbhA(id);

    return "redirect:/test/findTestByTestItemSStiffness";
  }


  //暂停原因保存hsu
  @RequestMapping("/saveAbnormalHSU")
  public  String saveAbnormalHSU(String remarks, @RequestParam(name = "id", required = true) String id)throws Exception{
    String idd=id;
//      System.out.println("xnjscbdsjbcshdcbsdhcbsdhcbsdhcsdjhc"+idd);
    iAbnormalServer.saveAbnormal(remarks,id);
//      更改背景颜色
    iAbnormalServer.updateTestRed(idd);

//      暂停时隐藏完成按钮
    iAbnormalServer.spbhA(id);

    return "redirect:/test/findTestByTestItemHSU";
  }



  //暂停原因保存BC
  @RequestMapping("/saveAbnormalBC")
  public  String saveAbnormalBC(String remarks, @RequestParam(name = "id", required = true) String id)throws Exception{
    String idd=id;
    iAbnormalServer.saveAbnormal(remarks,id);
//      更改背景颜色
    iAbnormalServer.updateTestRed(idd);

//      暂停时隐藏完成按钮
    iAbnormalServer.spbhA(id);

    return "redirect:/test/findTestByTestItemBC";
  }



  //暂停原因保存3D
  @RequestMapping("/saveAbnormal3D")
  public  String saveAbnormal3D(String remarks, @RequestParam(name = "id", required = true) String id)throws Exception{
    String idd=id;
    iAbnormalServer.saveAbnormal(remarks,id);
//      更改背景颜色
    iAbnormalServer.updateTestRed(idd);

//      暂停时隐藏完成按钮
    iAbnormalServer.spbhA(id);

    return "redirect:/test/findTestByTestItem3D";
  }
}

package com.maxxis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.maxxis.domain.*;
import com.maxxis.service.ITestService;
import com.maxxis.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("/test")
public class testController {
    @Autowired
    private ITestService iTestService;
    ModelAndView modelAndView = new ModelAndView();



    //    BC实验室查询

    @RequestMapping("/findTestByTestItemBC")
    public ModelAndView findTestByTestItemBC(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "8") int size) throws Exception {
        List<Test> all = iTestService.findTestByTestItemBC(page, size);
        System.out.println("jjjjjjj");
        System.out.println(all);
        List<TestClietTyre> aLL = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateBC();
        for (int j = 0; j < list.size(); j++) {
            aLL.add(list.get(j));
            if (aLL.size() > 60) {
                break;
            }
        }
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        //        查询BC已排现在一共的已排记录
        int count = iTestService.findStateCountBC();
        //        查询BC待排现在一共的记录
        int countDai = iTestService.findCountDaiBC();
        PageInfo pageInfo = new PageInfo(all);
        modelAndView.addObject("countDaiBC", countDai);
        modelAndView.addObject("count", count);
        modelAndView.addObject("BC", aLL);
        modelAndView.addObject("testListBC", pageInfo);
        modelAndView.setViewName("test-list-BC");
        return modelAndView;
    }


    //    BC实验室待排计划
    @RequestMapping("/findByStateBC")
    public ModelAndView findByStateBC() throws Exception {
        //  先把紧急程度赋予数字  便于排序
        iTestService.updatePriorityDesNumber();
        List<TestClietTyre> all = iTestService.findByStateBC();
        //        查询BC待排现在一共的记录
        int countDai = iTestService.findCountDaiBC();
        modelAndView.addObject("countDaiBC", countDai);
        modelAndView.addObject("BC", all);
        modelAndView.setViewName("test-list-BC");
        return modelAndView;
    }
      //BC实验室待排数据导出
    @RequestMapping("/ShuJuDaoChuDaiPaiBC")
    public ResponseEntity<byte[]> ShuJuDaoChuDaiPaiBC()throws Exception{
        List<TestClietTyre> all = iTestService.findByStateBC();
        return PoiUtilsDai.exportUser2Excel(all);
    }




    //       BC实验室一键排程
    @RequestMapping("/BCInstall")
    public String BCInstall() throws Exception {
        List<TestClietTyre> all = new ArrayList<>();
        List<TestClietTyre> listS = iTestService.findByStateBC();
        for (int j = 0; j < listS.size(); j++) {
            all.add(listS.get(j));
        }
//        存放id
        List<String> list = new ArrayList<>();
//        存放法规
        List<String> listStandard = new ArrayList<>();
//        存放时间
        List<Long> durationS = new ArrayList<>();

        //        存放实验项目
        List<String> listTestItem = new ArrayList<>();
//        存放工时
        List<long[]> workTime = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            list.add(all.get(i).getIdRctest());
            listStandard.add(all.get(i).getStandard());
            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
        }
//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);

            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);
            durationS.add((long) listDate.get(0).getDuration());
        }

//            BC查询工时
        List<WorkTimeBC> works = iTestService.findworktimeBC();
        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
        }

        // 获取BC实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
        int numberBC = iTestService.findByTestnumberBC();
        System.out.println("目前实验初始顺位是：" + numberBC);
        //BC查询出目前有没有已经排好的计划
        int numberSSS = iTestService.findNumberBC();
        int numberSP = numberSSS + 1;
        if (numberSSS == 0) {
            long ttt = 0;
            String id = new String();
            long ExpectedDate = 0;
            long current = System.currentTimeMillis();//当前时间毫秒数
            long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
            long zeroA = zero / 1000;
//            System.out.println("我是今天零点的时间："+zeroA);
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                ttt = durationS.get(r);//ttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[0] < zeroA) {
                        continue;
                    } else {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
                            ExpectedDate = f[0] - ttt;
                            id = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.updateBCExpectedDate(id, result1a, numberBC);
                            numberBC++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }
        } else {
            //   查询出BC一共有多少条已经排好的
            int count = iTestService.findNumberBC();
            //        查出BC已排最后一条数据
            Test test = iTestService.findBycountBC(count);
//         最后一条的时间
            Date ExpectedDate = test.getExpectedDate();
//            //  查询hsu待排排
//            List<Test> All = iTestService.findByStateHSU();
            //最后一条的法规和项目
            String ATestItem = test.getTestItem();
            String AStandard = test.getStandard();
//          查询出最后一条所需的时间
            List<TimeTest> timeTest = iTestService.findDataStandard(AStandard, ATestItem);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //       最后一条时间变成时间戳
            String timeFormat = sdf.format(ExpectedDate);
            long timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeFormat).getTime() / 1000;

//    最后一条时间戳加上最后一条的工时就是待排第一条的开始时间。

            long endTime = timeTest.get(0).getDuration();
            long beginTime = timestamp + endTime;
            long tttt = 0;
            int p = 0;
            String id = new String();
            long AExpectedDate = 0;
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                tttt = durationS.get(r);//tttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[1] <= beginTime) {
                        continue;
                    } else {
                        if (f[0] < f[1]) {
                            if (beginTime > f[0]) {
                                if (p == 0) {
                                    f[0] = beginTime;
                                    p++;
                                }
                            }
                            f[0] = (int) (f[0] + tttt);
//                  System.out.println(f[0]);
                            AExpectedDate = f[0] - tttt;
                            id = list.get(r);
                            String result1a = result1.format((AExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.updateBCExpectedDate(id, result1a, numberBC);
                            numberBC++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }

        }
        return "redirect:/test/findTestByTestItemBC ";
    }

    //    BC排程更新
    @RequestMapping("/renovateBC")
    public String renovateBC() throws Exception {
        PaichengShuaXinBCUtils.paichengBC();
        return "redirect:/test/findTestByTestItemBC";
    }

    //    BC实验室完成查询（按时间查询）
    @RequestMapping("/findByendTimeBC")
    public ModelAndView findByendTimeBC(String start, @RequestParam(name = "end", required = true) String end) throws Exception {
//        List<Test> test = iTestService.findByendTimeCompleteBC(start, end);
//        modelAndView.addObject("testList", test);
//        modelAndView.setViewName("ShiYanchaxunBC");
//        return modelAndView;
        List<Test> test = iTestService.findByendTimeCompleteBC(start, end);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
        }
        int a=1;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListA",JSONArray.toJSONString(test));
        modelAndView.setViewName("ShiYanchaxunBC");
        return modelAndView;
    }


    //  BC实验室取消查询（按时间查询）
    @RequestMapping("/findByCancellationTimeBC")
    public ModelAndView findByCancellationTimeBC(String startQuXiao, @RequestParam(name = "endQuXiao", required = true) String endQuXiao) throws Exception {
//            根据时间去查  BC 已经取消的
//        List<Test> test = iTestService.findByCancellationTimeCancelBC(startQuXiao, endQuXiao);
//        modelAndView.addObject("testList", test);
//        modelAndView.setViewName("ShiYanchaxunBC");
//        return modelAndView;
        List<Test> test = iTestService.findByCancellationTimeCancelBC(startQuXiao, endQuXiao);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
            test.get(i).getTyre().getCliet().setRemark(test.get(i).getTyre().getCliet().getRemark().replaceAll("\"","%22"));
        }
        int a=2;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListB",JSONArray.toJSONString(test));
//
        modelAndView.setViewName("ShiYanchaxunBC");
        return modelAndView;

    }
    //BC实验室完成和取消数据导出
    @RequestMapping("/shujudaochuBCWanCheng")
    public  ResponseEntity<byte[]> shujudaochuBCWanCheng(String testList)throws Exception{
//      System.out.println(testList);
        testList = new String(testList.replaceAll("\n","\\\\n"));
        List<Test> all = JSON.parseObject(testList, new TypeReference<List<Test>>(){});
//        System.out.println("aaaaaaaaa");
//        System.out.println(all);
        return  PoiUtilsCha.exportUser2ExcelCha(all);
//        return null;
    }


    //BC实验室已排数据导出
    @RequestMapping("/ShuJuDaoChuBC")
    public ResponseEntity<byte[]> exportUserBC()throws Exception{
        List<Test> all = iTestService.ShuJuDaoChuBC();
        return   PoiUtils.exportUser2Excel(all);
    }


    //    BC实验室开始
    @Transactional
    @RequestMapping("/BeginBC")
    public String BeginBC(int number) throws Exception {
        //        BC开始的时候把错误信息删除(先查id)
        String ID = iTestService.findTestIdBC(number);
        //        开始的时候把错误信息删除(再去删除数据)
        iTestService.deleteAbnormal(ID);
        //      BC开始的时候把颜色改成0
        iTestService.updateredBC(ID);
//      BC 改变隐藏按钮number
        iTestService.spbhNumberBC(number);

//        当点击开始的时候  时间变成现在的时间  如果点击第二条的开始  那么第一条的时间不变  只改变第二条和 后面的时间  以此类推
//   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
        List<Test> all = iTestService.findByNoStateNumberBC(number);

        //        存放实验项目
        List<String> listTestItem = new ArrayList<>();
        //        存放number
        List<Integer> list = new ArrayList<>();
//        存放法规
        List<String> listStandard = new ArrayList<>();
//        存放时间
        List<Long> durationS = new ArrayList<>();

//        存放工时
        List<long[]> workTime = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            list.add(all.get(i).getNumber()); //存的是数据的number
            listStandard.add(all.get(i).getStandard());//存的是数据的法规
            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
        }
//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);
//            System.out.println(Standard);
            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);

            durationS.add((long) listDate.get(0).getDuration());
        }
//    获取设定的时间
        List<WorkTimeBC> works = iTestService.findworktimeBC();

        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
        }
        long ttt = 0;
        Integer numberA = 0;//设置number
        long ExpectedDate = 0;

        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long zeroA = zero / 1000;

        //        当前时间
        long currentA = current / 1000;

        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long[] f = new long[10];
        //        最后一个没有循环的number
        int numberMax = 0;
        //   里面存的是已经循环的number
        List<Integer> list1 = new ArrayList<>();
        int r;
        int p = 0;
        int o = 0;
        long q = currentA;

        for (r = 0; r < durationS.size(); r++) {
            ttt = durationS.get(r);//ttt代表每一个试验需要的时间
            for (int i = 0; i < workTime.size(); i++) {
                f = workTime.get(i);
                if (f[0] < zeroA) {
                    continue;
                } else {
                    //        开始时间小于工时时间的话  直接取工时时间
                    if (currentA < f[0]) {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            System.out.println("aaaaaaaaaaa");
                            iTestService.updateBCExpectedDateUP(numberA, result1a);

                            break;
                        } else {
                            continue;
                        }
                    }

                    //假如现在时间在工时的区域内  那就把现在时间当成工时开始时间
                    if (f[0] <= currentA && currentA < f[1]) {
                        if (f[0] < f[1]) {
                            if (p == 0) {
                                f[0] = currentA;
                                p++;
                            }
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            iTestService.updateBCExpectedDateUP(numberA, result1a);
                            o++;
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (currentA > f[1]) {
                        continue;
                    }
                }
            }
        }



        for (int j = 0; j < list1.size(); j++) {
            if (list1.get(j) > numberMax)   // 判断最大值
                numberMax = list1.get(j);
        }
//        去查询数据库有几个已经开始不需要进行循环的
        int spbhOne = iTestService.findByspbhOneBC();

        //        BC把数据库里面的操作更改数据
        iTestService.updateCaoZuoBC(spbhOne);

        int numberN = numberMax + 2 + spbhOne;
        System.out.println("numberN" + numberN);
//                    去数据库把没有循环到的已经排好的数据变成待排(BC)
        iTestService.updateBCDaiPai(numberN);
        return "redirect:/test/findTestByTestItemBC";
    }


    //   BC实验室完成
    @Transactional
    @RequestMapping("/updateCompleteBC")
    public String   updateCompleteBC( @RequestParam(name = "id", required = true) String id,@RequestParam(name = "number", required = true) int number) throws Exception {
        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCompleteBC(id,number,result1a);
        //       BC 去查询数据库有几个已经开始的
        int spbhOne = iTestService.findByspbhOneBC();
        //       BC 把数据库里面的操作更改数据
        iTestService.updateCaoZuoBC(spbhOne);
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        return "redirect:/test/findTestByTestItemBC";
    }



    //    BC实验室调整 number代表输入的数字   numberS代表本来的序号    id代表该数据id
    @RequestMapping("/TestNumberBC")
    public String TestNumberBC(int number, @RequestParam(name = "ids", required = true) int numberS,
                               @RequestParam(name = "id", required = true) String id) throws Exception {
        //  查询BC已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoBC();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        if (number <= numberS) {

            iTestService.TestNumberBC(number, numberS, id);
//            System.out.println(numberS);
            PaichengBCCaoZuoUtils.paichengBC(kaishishijian);
        } else {
            iTestService.TestNumberDownBC(number, numberS, id);
            PaichengBCCaoZuoUtils.paichengBC(kaishishijian);
        }
        return "redirect:/test/findTestByTestItemBC";
    }


    //   BC 已排 实验室取消
    @RequestMapping("/ValuesQuXiaoBC")
    public String ValuesQuXiaoBC(String id, @RequestParam(name = "number", required = true) int number) throws Exception {
        //  查询BC已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoBC();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCancelBC(id, number, result1a);
        PaichengBCCaoZuoUtils.paichengBC(kaishishijian);
        return "redirect:/test/findTestByTestItemBC";
    }

    //   BC实验室取消开始
    @RequestMapping("/quxiaokaishiBC")
    public String quxiaokaishiBC()throws Exception{
        iTestService.quxiaokaishiBC();
        return "redirect:/test/findTestByTestItemBC";
    }


    //    BC实验室已排批量操作
    @Transactional
    @RequestMapping("/PiLiangTestNumberBC")
    public String PiLiangTestNumberBC(int numberS,
                                      @RequestParam(name = "id", required = true) String id,
                                      @RequestParam(name = "number", required = true) String number) throws Exception {

        //  查询BC已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoBC();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
//    numberS代表输入    id就是本来id   number代表原来序号
        String[] split = id.split(",");
        String[] numberA = number.split(",");
        String Oldnumber = "";
        int parseInt = 0;
        //    代表总长
        int a = split.length;

        for (int d = 0; d < numberA.length; d++) {
            Oldnumber = numberA[d];
        }
        parseInt = Integer.parseInt(Oldnumber);
        if (numberS >= parseInt) {
            //    代表输入
            int nubmerOld;
            String IdID = "";

            for (int i = 0; i < split.length; i++) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(i<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld-i;
                }
                iTestService.PiLiangOperateDownBC(numberS, nubmerOld, IdID);
            }

            PaichengBCCaoZuoUtils.paichengBC(kaishishijian);

        } else {

            int nubmerOld;
            String IdID = "";
            int p=0;
            for (int i = a - 1; i >= 0; i--) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(p<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld+p;
                }
                iTestService.PiLiangOperateUpBC(numberS, nubmerOld, IdID);
                p=p+1;
            }
            PaichengBCCaoZuoUtils.paichengBC(kaishishijian);
        }
        return "redirect:/test/findTestByTestItemBC";
    }



    //BC取消已排  进入待排
    @Transactional
    @RequestMapping("/JinRuDaiPaiBC")
    public String JinRuDaiPaiBC(String id, @RequestParam(name = "number", required = true) String number) throws
            Exception {
        //  查询BC已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoBC();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        String[] IDA = id.split(",");
        String[] numberA = number.split(",");
        String Anumber = "";
        String Aid = "";
        int idAA = IDA.length;
        for (int i = idAA - 1; i >= 0; i--) {
            Anumber = numberA[i];
            Aid = IDA[i];
            iTestService.JinRuDaiPaiBC(Anumber, Aid);
        }
        PaichengBCCaoZuoUtils.paichengBC(kaishishijian);

        return "redirect:/test/findTestByTestItemBC";
    }


    //BC已排取消（批量）
    @Transactional
    @RequestMapping("/PiLiangQuXiaoYiPaiBC")
    public String PiLiangQuXiaoYiPaiBC(String QuXiaoidYiPai, @RequestParam(name = "QuXiaonumberYiPai", required = true) String QuXiaonumberYiPai) throws
            Exception {
        //  查询BC已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoBC();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }

        String[] splitID = QuXiaoidYiPai.split(",");
        String[] splitNumber = QuXiaonumberYiPai.split(",");
        String id;
        int number;
        for (int i = splitID.length - 1; i <= 0; i--) {
            id = splitID[i];
            number = Integer.valueOf(splitNumber[i]);
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);

            iTestService.updateCancelBC(id, number, result1a);
        }
        PaichengBCCaoZuoUtils.paichengBC(kaishishijian);
        return "redirect:/test/findTestByTestItemBC";
    }

    //   BC实验室顺位排程
    @Transactional
    @RequestMapping("/updateStateBC")
    public String updateStateBC(String id) throws Exception {

        //  查询BC已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoBC();
//        System.out.println(all.size());
        if (all.size() != 0) {
            Date kaishishijian = null;
            for (int i = 0; i < all.size(); i++) {
                if (i == 0) {
                    kaishishijian = all.get(i).getExpectedDate();
                }
            }
            iTestService.updateStateBC(id);
            PaichengBCCaoZuoUtils.paichengBC(kaishishijian);
        } else {
            iTestService.updateStateBC(id);
            PaichengBCUtils.paichengBC();
        }
        return "redirect:/test/findTestByTestItemBC";
    }

    //   BC实验室待排插位
    @Transactional
    @RequestMapping("/TestNumberChaBC")
    public String TestNumberChaBC(int numberB, @RequestParam(name = "idB", required = true) String id) throws
            Exception {
        //  查询BC已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoBC();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        iTestService.TestNumberDaiChaBC(numberB, id);
        PaichengBCCaoZuoUtils.paichengBC(kaishishijian);
        return "redirect:/test/findTestByTestItemBC";

    }

    //   BC待排取消
    @RequestMapping("/TestCancelBC")
    public String TestCancelBC(String id) throws Exception {
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.TestCancel(id, result1a);
        return "redirect:/test/findByStateBC";
    }


    //    BC实验室待排批量操作（插位排程）
    @Transactional
    @RequestMapping("/PiLiangChaWeiBC")
    public String PiLiangChaWeiBC(int numberS, @RequestParam(name = "id", required = true) String id) throws
            Exception {
        //  查询BC已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoBC();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        String[] split = id.split(",");
        int count = split.length;
        String idA;
        for (int i = count - 1; i >= 0; i--) {
            idA = split[i];
            iTestService.TestNumberDaiChaBC(numberS, idA);
        }
        PaichengBCCaoZuoUtils.paichengBC(kaishishijian);
        return "redirect:/test/findTestByTestItemBC";
    }

    //    BC实验室待排批量操作（顺位排程）
    @Transactional
    @RequestMapping("/PiLiangShunWeiBC")
    public String PiLiangShunWeiBC(String id) throws Exception {

        //  查询BC已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoBC();

        if (all.size() != 0) {
            Date kaishishijian = null;
            for (int i = 0; i < all.size(); i++) {
                if (i == 0) {
                    kaishishijian = all.get(i).getExpectedDate();
                }
            }

            String[] split = id.split(",");
            String idA;
            for (int i = 0; i < split.length; i++) {
                idA = split[i];
                System.out.println(idA);
                iTestService.updateStateBC(idA);
            }
            PaichengBCCaoZuoUtils.paichengBC(kaishishijian);
        } else {

            String[] split = id.split(",");
            String idA;
            for (int i = 0; i < split.length; i++) {
                idA = split[i];
                System.out.println(idA);
                iTestService.updateStateBC(idA);

            }
            PaichengBCUtils.paichengBC();
        }
        return "redirect:/test/findTestByTestItemBC";
    }

    // BC实验室待排批量操作（取消）
    @Transactional
    @RequestMapping("/PiLiangQuXiaoBC")
    public String PiLiangQuXiaoBC(String QuXiaoid) throws Exception {

        String[] split = QuXiaoid.split(",");
        String id;

        for (int i = 0; i < split.length; i++) {
            id = split[i];
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);
            iTestService.TestCancel(id, result1a);
        }
        return "redirect:/test/findByStateBC";
    }


    //    BC需求编号搜索(待排)
    @RequestMapping("/findByTestRequestIdDaiPaiBC")
    public ModelAndView findByTestRequestIdDaiPaiBC(String RequestId) throws Exception {
        System.out.println("我是数据" + RequestId);
        List<TestClietTyre> all = iTestService.findByTestRequestIdDaiPaiBC(RequestId);
        modelAndView.addObject("BC", all);
        modelAndView.setViewName("test-list-BC");
        return modelAndView;
    }


//            SStiffness 实验室已排查询
    @RequestMapping("/findTestByTestItemSStiffness")
    public ModelAndView findTestByTestItemSStiffness(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "8") int size) throws Exception {

        List<Test> all = iTestService.findTestByTestItemSStiffness(page, size);
        List<TestClietTyre> aLL = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateSStiffness();
        PageInfo pageInfo = new PageInfo(all);

        for (int j = 0; j < list.size(); j++) {
            aLL.add(list.get(j));
            if (aLL.size() > 60) {
                break;
            }
        }
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        //        查询SStiffness已排现在一共的已排记录
        int count = iTestService.findStateCountSStiffness();
        //        查询SStiffness待排现在一共的已排记录
        int countDai = iTestService.findCountDaiSStiffness();
        modelAndView.addObject("countDaiSStiffness", countDai);
        modelAndView.addObject("countSStiffness", count);
        modelAndView.addObject("testListSStiffness", pageInfo);
        modelAndView.addObject("SStiffness", aLL);
        modelAndView.setViewName("test-list-SStiffness");
        return modelAndView;

    }

    //     SStiffness实验室待排
    @RequestMapping("/findByStateSStiffness")
    public ModelAndView findByStateSStiffness() throws Exception {
        //  先把紧急程度赋予数字  便于排序
        iTestService.updatePriorityDesNumber();
        List<TestClietTyre> aLL = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateSStiffness();
        for (int j = 0; j < list.size(); j++) {
            aLL.add(list.get(j));
            if (aLL.size() > 60) {
                break;
            }
        }
//        查询出SStiffness待排一共多少条记录
        int countDai = iTestService.findCountDaiSStiffness();
        modelAndView.addObject("countDaiSStiffness", countDai);
        modelAndView.addObject("SStiffness", aLL);
        modelAndView.setViewName("test-list-SStiffness");
        return modelAndView;

    }

    //SStiffness实验室待排数据导出
    @RequestMapping("/ShuJuDaoChuDaiPaiSStiffness")
    public ResponseEntity<byte[]> ShuJuDaoChuDaiPaiSStiffness()throws Exception{
        List<TestClietTyre> all = iTestService.findByStateSStiffness();
        return PoiUtilsDai.exportUser2Excel(all);
    }

//    SStiffness排程更新
@RequestMapping("/renovateSStiffness")
public String renovateSStiffness() throws Exception {
    PaichengShuaXinSStiffnessUtils.paichengSStiffness();
    return "redirect:/test/findTestByTestItemSStiffness";
}

//SStiffness实验室  取消查询
    @RequestMapping("/findByCancellationTimeSStiffness")
    public ModelAndView findByCancellationTimeSStiffness(String startQuXiao, @RequestParam(name = "endQuXiao", required = true) String endQuXiao) throws Exception {
//            根据时间去查  SStiffness 已经取消的
//        List<Test> test = iTestService.findByCancellationTimeSStiffness(startQuXiao, endQuXiao);
//        modelAndView.addObject("testList", test);
//        modelAndView.setViewName("ShiYanchaxunSStiffness");
//        return modelAndView;
        List<Test> test = iTestService.findByCancellationTimeSStiffness(startQuXiao, endQuXiao);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
            test.get(i).getTyre().getCliet().setRemark(test.get(i).getTyre().getCliet().getRemark().replaceAll("\"","%22"));
        }
        int a=2;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListB",JSONArray.toJSONString(test));
//
        modelAndView.setViewName("ShiYanchaxunSStiffness");
        return modelAndView;
    }

    //    SStiffness实验室完成查询（按时间查询）
    @RequestMapping("/findByendTimeSStiffness")
    public ModelAndView findByendTimeSStiffness(String start, @RequestParam(name = "end", required = true) String end) throws Exception {
//        List<Test> test = iTestService.findByendTimeSStiffness(start, end);
//        modelAndView.addObject("testList", test);
//        modelAndView.setViewName("ShiYanchaxunSStiffness");
//        return modelAndView;
        List<Test> test = iTestService.findByendTimeSStiffness(start, end);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
        }
        int a=1;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListA",JSONArray.toJSONString(test));
        modelAndView.setViewName("ShiYanchaxunSStiffness");
        return modelAndView;
    }

    //SStiffness实验室完成和取消数据导出
    @RequestMapping("/shujudaochuSStiffnessWanCheng")
    public  ResponseEntity<byte[]> shujudaochuSStiffnessWanCheng(String testList)throws Exception{
//      System.out.println(testList);
        testList = new String(testList.replaceAll("\n","\\\\n"));
        List<Test> all = JSON.parseObject(testList, new TypeReference<List<Test>>(){});
//        System.out.println("aaaaaaaaa");
//        System.out.println(all);
        return  PoiUtilsCha.exportUser2ExcelCha(all);
//        return null;
    }


    //     SStiffness实验室开始
    @Transactional
    @RequestMapping("/BeginSStiffness")
    public String BeginSStiffness(int number) throws Exception {
        //        SStiffness开始的时候把错误信息删除(先查id)
        String ID = iTestService.findTestIdSStiffness(number);
        //        开始的时候把错误信息删除(再去删除数据)
        iTestService.deleteAbnormal(ID);
        //        开始的时候把颜色改成0
        iTestService.updatered(ID);
//       SStiffness 改变隐藏按钮number
        iTestService.spbhNumberSStiffness(number);

//        当点击开始的时候  时间变成现在的时间  如果点击第二条的开始  那么第一条的时间不变  只改变第二条和 后面的时间  以此类推
//   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
        List<Test> all = iTestService.findByNoStateNumberSStiffness(number);

        //        存放实验项目
        List<String> listTestItem = new ArrayList<>();
        //        存放number
        List<Integer> list = new ArrayList<>();
//        存放法规
        List<String> listStandard = new ArrayList<>();
//        存放时间
        List<Long> durationS = new ArrayList<>();

//        存放工时
        List<long[]> workTime = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            list.add(all.get(i).getNumber()); //存的是数据的number
            listStandard.add(all.get(i).getStandard());//存的是数据的法规
            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
        }
//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);
//            System.out.println(Standard);
            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);

            durationS.add((long) listDate.get(0).getDuration());
        }
//    获取设定的时间
        List<WorkTimeSStiffness> works = iTestService.findworktimeSStiffness();

        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
        }
        long ttt = 0;
//            String id=new String();
        Integer numberA = 0;//设置number
        long ExpectedDate = 0;

        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long zeroA = zero / 1000;

        //        当前时间
        long currentA = current / 1000;

        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long[] f = new long[10];
        //        最后一个没有循环的number
        int numberMax = 0;
        //   里面存的是已经循环的number
        List<Integer> list1 = new ArrayList<>();
        int r;
        int p = 0;
        int o = 0;
        long q = currentA;

        for (r = 0; r < durationS.size(); r++) {
            ttt = durationS.get(r);//ttt代表每一个试验需要的时间
            for (int i = 0; i < workTime.size(); i++) {
                f = workTime.get(i);
                if (f[0] < zeroA) {
                    continue;
                } else {
                    //        开始时间小于工时时间的话  直接取工时时间
                    if (currentA < f[0]) {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            System.out.println("aaaaaaaaaaa");
                            iTestService.updateHSUExpectedDateUPSStiffness(numberA, result1a);

                            break;
                        } else {
                            continue;
                        }
                    }

                    //假如现在时间在工时的区域内  那就把现在时间当成工时开始时间
                    if (f[0] <= currentA && currentA < f[1]) {
                        if (f[0] < f[1]) {
                            if (p == 0) {
                                f[0] = currentA;
                                p++;
                            }
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            iTestService.updateHSUExpectedDateUPSStiffness(numberA, result1a);
                            o++;
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (currentA > f[1]) {
                        continue;
                    }
                }
            }
        }



        for (int j = 0; j < list1.size(); j++) {
            if (list1.get(j) > numberMax)   // 判断最大值
                numberMax = list1.get(j);
        }
//        去查询数据库有几个已经开始不需要进行循环的
        int spbhOne = iTestService.findByspbhOneSStiffness();

        //        把数据库里面的操作更改数据
        iTestService.updateCaoZuo(spbhOne);

        int numberN = numberMax + 2 + spbhOne;
        System.out.println("numberN" + numberN);
//                    去数据库把没有循环到的已经排好的数据变成待排(SP)
        iTestService.updateSStiffnessnumberDaiPai(numberN);
        return "redirect:/test/findTestByTestItemSStiffness";
    }


    //    SStiffness实验室取消开始
    @RequestMapping("/quxiaokaishiStiffness")
    public String quxiaokaishiStiffness()throws Exception{
        iTestService.quxiaokaishiStiffness();
        return "redirect:/test/findTestByTestItemSStiffness";
    }



    //    SStiffness实验室完成
    @Transactional
    @RequestMapping("/updateCompleteSStiffness")
    public String   updateCompleteSStiffness(@RequestParam(name = "number", required = true) int number,
                                   @RequestParam(name = "id", required = true) String id) throws Exception {
        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCompleteSStiffness(number, id, result1a);
        //        去查询数据库有几个已经开始的
        int spbhOne = iTestService.findByspbhOneSStiffness();
        //       SStiffness 把数据库里面的操作更改数据
        iTestService.updateCaoZuoSStiffness(spbhOne);
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        return "redirect:/test/findTestByTestItemSStiffness";
    }



    //SStiffness实验室操作
      @RequestMapping("/TestNumberSStiffness")
    public String TestNumberCaoSStiffness(int number, @RequestParam(name = "ids", required = true) int numberS, @RequestParam(name = "id", required = true) String id) throws Exception {
        //  查询SStiffness已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSStiffness();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        if (number <= numberS) {
       iTestService.TestNumberSStiffness(number, numberS, id);
           PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);

        } else {
            iTestService.TestNumberDownSStiffness(number, numberS, id);
          PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);
        }
        return "redirect:/test/findTestByTestItemSStiffness";

    }


    //   SStiffness 已排 实验室取消
    @RequestMapping("/updateCancelSStiffness")
    public String updateCancelSStiffness(String id, @RequestParam(name = "number", required = true) int number) throws
            Exception {
        //  查询SStiffness已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSStiffness();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCancelSStiffness(id, number, result1a);
         PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);
        return "redirect:/test/findTestByTestItemSStiffness";
    }


    //   SStiffness实验室顺位排程
    @Transactional
    @RequestMapping("/updateStateSStiffness")
    public String updateStateSStiffness(String id) throws Exception {

        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSStiffness();
//        System.out.println(all.size());
        if (all.size() != 0) {
            Date kaishishijian = null;
            for (int i = 0; i < all.size(); i++) {
                if (i == 0) {
                    kaishishijian = all.get(i).getExpectedDate();
                }
            }
            iTestService.updateStateSStiffness(id);
            PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);
        } else {
            iTestService.updateStateSStiffness(id);
            PaichengSStiffnessUtils.paichengSStiffness();
        }
        return "redirect:/test/findTestByTestItemSStiffness";
    }

    //   SStiffness实验室插位
    @Transactional
    @RequestMapping("/TestNumberChaSStiffness")
    public String TestNumberChaSStiffness(int numberB, @RequestParam(name = "idB", required = true) String id) throws
            Exception {
        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSStiffness();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        iTestService.TestNumberChaSStiffness(numberB, id);
        PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);
        return "redirect:/test/findTestByTestItemSStiffness";

    }


    //    SStiffness实验室待排取消
    @RequestMapping("/TestCancelSStiffness")
    public String TestCancelSStiffness(String id) throws Exception {
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.TestCancel(id, result1a);
        return "redirect:/test/findByStateSStiffness";
    }




    //    SStiffness实验室待排批量操作（插位排程）
    @Transactional
    @RequestMapping("/PiLiangChaWeiSStiffness")
    public String PiLiangChaWeiSStiffness(int numberS, @RequestParam(name = "id", required = true) String id) throws
            Exception {
        //  查询SStiffness已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSStiffness();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        String[] split = id.split(",");
        int count = split.length;
        String idA;
        for (int i = count - 1; i >= 0; i--) {
            idA = split[i];
            iTestService.TestNumberChaSStiffness(numberS, idA);
        }
        PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);
        return "redirect:/test/findTestByTestItemSStiffness";
    }

    //    SStiffness实验室待排批量操作（顺位排程）
    @Transactional
    @RequestMapping("/PiLiangShunWeiSStiffness")
    public String PiLiangShunWeiSStiffness(String id) throws Exception {

        //  查询SStiffness已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSStiffness();

        if (all.size() != 0) {
            Date kaishishijian = null;
            for (int i = 0; i < all.size(); i++) {
                if (i == 0) {
                    kaishishijian = all.get(i).getExpectedDate();
                }
            }

        String[] split = id.split(",");
        String idA;
        for (int i = 0; i < split.length; i++) {
            idA = split[i];
            System.out.println(idA);
            iTestService.updateStateSStiffness(idA);
        }
            PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);
        } else {

            String[] split = id.split(",");
            String idA;
            for (int i = 0; i < split.length; i++) {
                idA = split[i];
                iTestService.updateStateSStiffness(idA);

            }
            PaichengSStiffnessUtils.paichengSStiffness();
        }
       return "redirect:/test/findTestByTestItemSStiffness";
    }

    // SStiffness实验室待排批量操作（取消）
    @Transactional
    @RequestMapping("/PiLiangQuXiaoSStiffness")
    public String PiLiangQuXiaoSStiffness(String QuXiaoid) throws Exception {

        String[] split = QuXiaoid.split(",");
        String id;

        for (int i = 0; i < split.length; i++) {
            id = split[i];
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);
            iTestService.TestCancel(id, result1a);
        }
        return "redirect:/test/findByStateSStiffness";
    }

    //    SStiffness需求编号搜索(待排)
    @RequestMapping("/findByTestRequestIdDaiPaiSStiffness")
    public ModelAndView findByTestRequestIdDaiPaiSStiffness(String RequestId) throws Exception {
//        System.out.println("我是数据" + RequestId);
        List<TestClietTyre> all = iTestService.findByTestRequestIdDaiPaiSStiffness(RequestId);
        modelAndView.addObject("SStiffness", all);
        modelAndView.setViewName("test-list-SStiffness");
        return modelAndView;
    }


    //SStiffness实验室一键排程

    @Transactional
    @RequestMapping("/SStiffnessInstall")
    public String SStiffnessInstall() throws Exception {
        List<TestClietTyre> all = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateSStiffness();
        for (int j = 0; j < list.size(); j++) {
            all.add(list.get(j));
        }
//        存放id
        List<String> list1 = new ArrayList<>();
//        存放法规
        List<String> listStandard = new ArrayList<>();
//        存放时间
        List<Long> durationS = new ArrayList<>();
        //        存放实验项目
        List<String> listTestItem = new ArrayList<>();
//        存放工时
        List<long[]> workTime = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            list1.add(all.get(i).getIdRctest());
            listStandard.add(all.get(i).getStandard());
            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
        }

//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);
            System.out.println(Standard);
            System.out.println(TestItem);
            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);
            System.out.println(listDate);
            System.out.println("到这里");
            durationS.add((long) listDate.get(0).getDuration());
        }
        //        SStiffness查询工时
        List<WorkTimeSStiffness> works = iTestService.findworktimeSStiffness();
        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
        }

//SStiffness查询出目前有没有已经排好的计划
        int numberSSS = iTestService.findNumberSStiffness();
        int numberSP = numberSSS + 1;
//        System.out.println(numberSSS);

        if (numberSSS == 0) {
            long ttt = 0;
            String id = new String();
            long ExpectedDate = 0;
            long current = System.currentTimeMillis();//当前时间毫秒数
            long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
            long zeroA = zero / 1000;
//            System.out.println("我是今天零点的时间："+zeroA);
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                ttt = durationS.get(r);//ttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[0] < zeroA) {

                        continue;

                    } else {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
                            id = list1.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.updateExpectedDateSStiffness(id, result1a, numberSP);
//                             System.out.println("id" + list.get(r));
//                            System.out.println("时间" + (f[0]));
//                            System.out.println("初始顺位" + numberSP);
                            numberSP++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }
        } else {
//      查询出一共有多少条已经排好的
            int count = iTestService.findNumberSStiffness();
//        查出SStiffness已排最后一条数据
            Test test = iTestService.findBycountSStiffness(count);

            //   System.out.println("我是最后一条的时间"+ExpectedDate);
            Date ExpectedDate = test.getExpectedDate();

            //最后一条的法规和项目
            String ATestItem = test.getTestItem();
            String AStandard = test.getStandard();
//          查询出最后一条所需的时间
            List<TimeTest> timeTest = iTestService.findDataStandard(AStandard, ATestItem);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //       最后一条时间变成时间戳
            String timeFormat = sdf.format(ExpectedDate);
            long timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeFormat).getTime() / 1000;
//            System.out.println(timeFormat);
//    最后一条时间戳加上最后一条的工时就是待排第一条的开始时间。
            long endTime = timeTest.get(0).getDuration();
            long beginTime = timestamp + endTime;
            long tttt = 0;
            int p = 0;
            String id = new String();
            long AExpectedDate = 0;
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                tttt = durationS.get(r);//tttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[1] <= beginTime) {
                        continue;
                    } else {
                        if (f[0] < f[1]) {
                            if (beginTime > f[0]) {
                                if (p == 0) {
                                    f[0] = beginTime;
                                    p++;
                                }
                            }
                            f[0] = (int) (f[0] + tttt);
//                  System.out.println(f[0]);
                            AExpectedDate = f[0] - tttt;
                            id = list1.get(r);
                            String result1a = result1.format((AExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.updateExpectedDateSStiffness(id, result1a, numberSP);
//                            System.out.println("id" + list.get(r));
//                            System.out.println("时间" + (f[0]));
//                            System.out.println("初始顺位" + numberSP);
                            numberSP++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }

        }

        return "redirect:/test/findTestByTestItemSStiffness";
    }



    //    SStiffness实验室已排批量操作
    @Transactional
    @RequestMapping("/PiLiangTestNumberSStiffness")
    public String PiLiangTestNumberSStiffness(int numberS,
                                      @RequestParam(name = "id", required = true) String id,
                                      @RequestParam(name = "number", required = true) String number) throws Exception {

        //  查询SStiffness已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSStiffness();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
//    numberS代表输入    id就是本来id   number代表原来序号
        String[] split = id.split(",");
        String[] numberA = number.split(",");
        String Oldnumber = "";
        int parseInt = 0;
        //    代表总长
        int a = split.length;

        for (int d = 0; d < numberA.length; d++) {
            Oldnumber = numberA[d];
        }
        parseInt = Integer.parseInt(Oldnumber);
        if (numberS >= parseInt) {
            //    代表输入
            int nubmerOld;
            String IdID = "";

            for (int i = 0; i < split.length; i++) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(i<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld-i;
                }

                iTestService.PiLiangOperateDownSStiffness(numberS, nubmerOld, IdID);
            }

          PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);

        } else {
            int nubmerOld;
            String IdID = "";
            int p=0;
            for (int i = a - 1; i >= 0; i--) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(p<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld+p;
                }
                iTestService.PiLiangOperateUpSStiffness(numberS, nubmerOld, IdID);
                 p=p+1;
            }
            PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);
        }
        return "redirect:/test/findTestByTestItemSStiffness";
    }


    //SStiffness取消已排  进入待排
    @Transactional
    @RequestMapping("/JinRuDaiPaiSStiffness")
    public String JinRuDaiPaiSStiffness(String id, @RequestParam(name = "number", required = true) String
            number) throws
            Exception {
        //  查询SStiffness已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSStiffness();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        String[] IDA = id.split(",");
        String[] numberA = number.split(",");
        String Anumber = "";
        String Aid = "";
        int idAA = IDA.length;
        for (int i = idAA - 1; i >= 0; i--) {
            Anumber = numberA[i];
            Aid = IDA[i];
            iTestService.JinRuDaiPaiSStiffness(Anumber, Aid);
        }
      PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);

        return "redirect:/test/findTestByTestItemSStiffness";
    }


    //SStiffness已排取消（批量）
    @Transactional
    @RequestMapping("/PiLiangQuXiaoYiPaiSStiffness")
    public String PiLiangQuXiaoYiPaiSStiffness(String QuXiaoidYiPai, @RequestParam(name = "QuXiaonumberYiPai", required = true) String QuXiaonumberYiPai) throws Exception {

//        System.out.println(QuXiaoidYiPai);
//        System.out.println(QuXiaonumberYiPai);
        //  查询SStiffness已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSStiffness();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }

        String[] splitID = QuXiaoidYiPai.split(",");
        String[] splitNumber = QuXiaonumberYiPai.split(",");
        String id;
        int number;
        for (int i = splitID.length - 1; i >= 0; i--) {
            id = splitID[i];
            number = Integer.valueOf(splitNumber[i]);

            System.out.println(id);
            System.out.println(number);
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);

            iTestService.updateCancelSStiffness(id, number, result1a);
        }
        PaichengSStiffnessCaoZuoUtils.paichengSStiffness(kaishishijian);
        return "redirect:/test/findTestByTestItemSStiffness";
    }


    //SStiffness实验室已排数据导出
    @RequestMapping("/ShuJuDaoChuSStiffness")
    public ResponseEntity<byte[]> exportUserSStiffness()throws Exception{
        List<Test> all = iTestService.ShuJuDaoChuSStiffness();
        return   PoiUtils.exportUser2Excel(all);
    }




    //RR实验室查询
    @RequestMapping("/findTestByTestItemRR")
    public ModelAndView findTestByTestItemRR(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "8") int size) throws Exception {

        List<Test> all = iTestService.findTestByTestItemRR(page, size);
        List<TestClietTyre> aLL = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateRR();
        PageInfo pageInfo = new PageInfo(all);

        for (int j = 0; j < list.size(); j++) {
            aLL.add(list.get(j));
            if (aLL.size() > 60) {
                break;
            }
        }
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        //        查询RR已排现在一共的已排记录
        int count = iTestService.findStateCountRR();
        //        查询RR待排现在一共的已排记录
        int countDai = iTestService.findCountDaiRR();
        modelAndView.addObject("countDaiRR", countDai);
        modelAndView.addObject("count", count);
        modelAndView.addObject("testList", pageInfo);
        modelAndView.addObject("RR", aLL);
        modelAndView.setViewName("test-list-RR");
        return modelAndView;

    }

    //    RR实验室待排
    @RequestMapping("/findByStateRR")
    public ModelAndView findByStateRR() throws Exception {
        //        先把紧急程度赋予数字  便于排序
        iTestService.updatePriorityDesNumber();
        List<TestClietTyre> aLL = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateRR();
        System.out.println(list);
        for (int j = 0; j < list.size(); j++) {
            aLL.add(list.get(j));
            if (aLL.size() > 60) {
                break;
            }
        }
//        查询出待排一共多少条记录
        int countDai = iTestService.findCountDaiRR();
        modelAndView.addObject("countDaiRR", countDai);
        modelAndView.addObject("RR", aLL);
        modelAndView.setViewName("test-list-RR");
        return modelAndView;

    }


    //RR实验室待排数据导出
    @RequestMapping("/ShuJuDaoChuDaiPaiRR")
    public ResponseEntity<byte[]> ShuJuDaoChuDaiPaiRR()throws Exception{
        List<TestClietTyre> all = iTestService.findByStateRR();
        return PoiUtilsDai.exportUser2Excel(all);
    }


    //RR实验室已排数据导出
    @RequestMapping("/ShuJuDaoChuRR")
    public ResponseEntity<byte[]> exportUser()throws Exception{
        System.out.println("sssss");
        List<Test> all = iTestService.ShuJuDaoChuRR();
        System.out.println(all);
      return   PoiUtils.exportUser2Excel(all);
    }

    //    RR需求编号搜索(待排)
    @RequestMapping("/findByTestRequestIdDaiPaiRR")
    public ModelAndView findByTestRequestIdDaiPaiRR(String RequestId) throws Exception {
        System.out.println("我是数据" + RequestId);
        List<TestClietTyre> all = iTestService.findByTestRequestIdDaiPaiRR(RequestId);
        modelAndView.addObject("RR", all);
        modelAndView.setViewName("test-list-RR");
        return modelAndView;
    }

    //RR实验室操作
    @RequestMapping("/TestNumberRR")
    public String TestNumberRR(int number, @RequestParam(name = "ids", required = true) int numberS, @RequestParam(name = "id", required = true) String id) throws Exception {
        //  查询RR已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoRR();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        if (number <= numberS) {
            iTestService.TestNumberRR(number, numberS, id);
//            PaichengRRCaoZuoUtils.paichengRR(kaishishijian);
        } else {
            iTestService.TestNumberDownRR(number, numberS, id);
//            PaichengRRCaoZuoUtils.paichengRR(kaishishijian);
        }
        return "redirect:/test/findTestByTestItemRR";

    }

    //RR实验室排程刷新
//    @RequestMapping("/renovateRR")
//    public String renovateRR() throws Exception {
//        PaichengShuaXinRRUtils.paichengSP();
//        return "redirect:/test/findTestByTestItemRR";
//    }

    //    RR实验室开始
    @Transactional
    @RequestMapping("/BeginRR")
    public String BeginRR(int number) throws Exception {
        //        开始的时候把错误信息删除(先查id)RR
        String ID = iTestService.findTestIdRR(number);
        //        开始的时候把错误信息删除(再去删除数据)
        iTestService.deleteAbnormal(ID);
        //        开始的时候把颜色改成0
        iTestService.updateredRR(ID);
//        改变隐藏按钮number
        iTestService.spbhNumberRR(number);

//        当点击开始的时候  时间变成现在的时间  如果点击第二条的开始  那么第一条的时间不变  只改变第二条和 后面的时间  以此类推
////   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
//        List<Test> all = iTestService.findByNoStateNumberRR(number);
//        //        存放实验项目
//        List<String> listTestItem = new ArrayList<>();
//        //        存放number
//        List<Integer> list = new ArrayList<>();
////        存放法规
//        List<String> listStandard = new ArrayList<>();
////        存放时间
//        List<Long> durationS = new ArrayList<>();
//
////        存放工时
//        List<long[]> workTime = new ArrayList<>();
//        for (int i = 0; i < all.size(); i++) {
//            list.add(all.get(i).getNumber()); //存的是数据的number
//            listStandard.add(all.get(i).getStandard());//存的是数据的法规
//            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
//        }
////        根据法规去表里查询实验时间
//        String Standard = new String();
//        String TestItem = new String();//实验项目
//        for (int a = 0; a < listStandard.size(); a++) {
//            Standard = listStandard.get(a);
//            TestItem = listTestItem.get(a);
////            System.out.println(Standard);
//            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);
//
//            durationS.add((long) listDate.get(0).getDuration());
//        }
////    获取设定的时间
//        List<WorkTimeRR> works = iTestService.findworktimeRR();
//
//        for (int s = 0; s < works.size(); s++) {
//            long q = works.get(s).getGotowork();
//            long w = works.get(s).getGetoffwork();
//            long[] e = new long[]{q, w};
//            workTime.add(e);
//        }
//
//        long ttt = 0;
////            String id=new String();
//        Integer numberA = 0;//设置number
//        long ExpectedDate = 0;
//
      long current = System.currentTimeMillis();//当前时间毫秒数
       long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
      long zeroA = zero / 1000;
//
//        //        当前时间
     long currentA = current / 1000;
//      long q = currentA;
//
     SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        long[] f = new long[10];
//        //        最后一个没有循环的number
//        int numberMax = 0;
//        //   里面存的是已经循环的number
//        List<Integer> list1 = new ArrayList<>();
//        int r;
//        int p = 0;
//        int o = 0;
//
//        //假如现在时间在工时的区域内  那就把现在时间当成工时开始时间
//
//        for (int i = 0; i < workTime.size(); i++) {
//            f = workTime.get(i);
//            if (currentA < f[0]) {
//                continue;
//            }
//            if (f[0] <= currentA && currentA < f[1]) {
//
//                for (r = 0; r < durationS.size(); r++) {
//                    ttt = durationS.get(r);//ttt代表每一个试验需要的时间
//                    for (int t = 0; t < workTime.size(); t++) {
//                        f = workTime.get(t);
//                        if (f[0] < zeroA) {
//                            continue;
//                        } else {
//                            if (f[0] < f[1]) {
//                                f[0] = (int) (q + ttt);
//                                q += ttt;
////                  System.out.println(f[0]);
//                                ExpectedDate = f[0] - ttt;
////                        System.out.println("我是循环里面的"+r);
//                                list1.add(r);
//                                numberA = list.get(r);
                              String result1a = result1.format((currentA * 1000));
////                        System.out.println("转换时间为：" + result1a);
                               iTestService.updateHSUExpectedDateUPRR(number, result1a);
//                                o++;
//                                break;
//                            } else {
//                                continue;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        for (int j = 0; j < list1.size(); j++) {
//            if (list1.get(j) > numberMax)   // 判断最大值
//                numberMax = list1.get(j);
//        }
////      RR  去查询数据库有几个已经开始不需要进行循环的
     int spbhOne = iTestService.findByspbhOneRR();
//
//        //        RR把数据库里面的操作更改数据
       iTestService.updateCaoZuoRR(spbhOne);
//
//        int numberN = numberMax + 1 + spbhOne;
//        //                    去数据库把没有循环到的已经排好的数据变成待排(RR)
//        iTestService.updateSPnumberDaiPaiRR(numberN);
       return "redirect:/test/findTestByTestItemRR";
   }


    //    RR实验室完成
    @RequestMapping("/updateCompleteRR")
    public String updateCompleteRR(String id, @RequestParam(name = "number", required = true) int number) throws
            Exception {
        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCompleteRR(id, number, result1a);
        //        去查询数据库有几个已经开始的
        int spbhOne = iTestService.findByspbhOneRR();
        //        把数据库里面的操作更改数据
        iTestService.updateCaoZuoRR(spbhOne);
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        return "redirect:/test/findTestByTestItemRR";
    }

    //    RR  已排 实验室取消
    @RequestMapping("/updateCancelRR")
    public String updateCancelRR(String id, @RequestParam(name = "number", required = true) int number) throws
            Exception {
        //  查询RR已排并且没有开始的(按照number顺序)
//        List<Test> all = iTestService.findTestByStateNoZuoRR();
//        Date kaishishijian = null;
//        for (int i = 0; i < all.size(); i++) {
//            if (i == 0) {
//                kaishishijian = all.get(i).getExpectedDate();
//            }
//        }

        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCancelRR(id, number, result1a);
//        PaichengRRCaoZuoUtils.paichengRR(kaishishijian);
        return "redirect:/test/findTestByTestItemRR";
    }


    //    RR实验室已排批量操作
    @Transactional
    @RequestMapping("/PiLiangTestNumberRR")
    public String PiLiangTestNumberRR(int numberS,
                                      @RequestParam(name = "id", required = true) String id,
                                      @RequestParam(name = "number", required = true) String number) throws Exception {

        //  查询RR已排并且没有开始的(按照number顺序)
//        List<Test> all = iTestService.findTestByStateNoZuoRR();
//        Date kaishishijian = null;
//        for (int i = 0; i < all.size(); i++) {
//            if (i == 0) {
//                kaishishijian = all.get(i).getExpectedDate();
//            }
//        }
//    numberS代表输入    id就是本来id   number代表原来序号
        String[] split = id.split(",");
        String[] numberA = number.split(",");
        String Oldnumber = "";
        int parseInt = 0;
        //    代表总长
        int a = split.length;

        for (int d = 0; d < numberA.length; d++) {
            Oldnumber = numberA[d];
        }
        parseInt = Integer.parseInt(Oldnumber);
        if (numberS >= parseInt) {
            //    代表输入
         int nubmerOld;

            String IdID = "";

            for (int i = 0; i < split.length; i++) {
                IdID = split[i];
               nubmerOld  =Integer.parseInt(numberA[i]);
                System.out.println(i);
                if(i<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld-i;
                }
      iTestService.PiLiangOperateDownRR(numberS, nubmerOld, IdID);

            }
//            PaichengRRCaoZuoUtils.paichengRR(kaishishijian);

        } else {
          int nubmerOld;

            String IdID = "";
            int p=0;
            for (int i = a - 1; i >= 0; i--) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(p<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld+p;
                }
                System.out.println("输入"+numberS);
                System.out.println("本来"+nubmerOld);
               iTestService.PiLiangOperateUpRR(numberS, nubmerOld, IdID);
                System.out.println(p);
               p=p+1;

            }
//            PaichengRRCaoZuoUtils.paichengRR(kaishishijian);
        }
        return "redirect:/test/findTestByTestItemRR";
    }


    //RR取消已排  进入待排
    @Transactional
    @RequestMapping("/JinRuDaiPaiRR")
    public String JinRuDaiPaiRR(String id, @RequestParam(name = "number", required = true) String
            number) throws
            Exception {
        //  查询RR已排并且没有开始的(按照number顺序)
//        List<Test> all = iTestService.findTestByStateNoZuoRR();
//        Date kaishishijian = null;
//        for (int i = 0; i < all.size(); i++) {
//            if (i == 0) {
//                kaishishijian = all.get(i).getExpectedDate();
//            }
//        }
        String[] IDA = id.split(",");
        String[] numberA = number.split(",");
        String Anumber = "";
        String Aid = "";
        int idAA = IDA.length;
        for (int i = idAA - 1; i >= 0; i--) {
            Anumber = numberA[i];
            Aid = IDA[i];
            iTestService.JinRuDaiPaiRR(Anumber, Aid);
        }
//        PaichengRRCaoZuoUtils.paichengRR(kaishishijian);

        return "redirect:/test/findTestByTestItemRR";
    }


    //RR已排取消（批量）
    @Transactional
    @RequestMapping("/PiLiangQuXiaoYiPaiRR")
    public String PiLiangQuXiaoYiPaiRR(String QuXiaoidYiPai, @RequestParam(name = "QuXiaonumberYiPai", required = true) String QuXiaonumberYiPai) throws Exception {

//        System.out.println(QuXiaoidYiPai);
//        System.out.println(QuXiaonumberYiPai);
        //  查询RR已排并且没有开始的(按照number顺序)
//        List<Test> all = iTestService.findTestByStateNoZuoRR();
//        Date kaishishijian = null;
//        for (int i = 0; i < all.size(); i++) {
//            if (i == 0) {
//                kaishishijian = all.get(i).getExpectedDate();
//            }
//        }

        String[] splitID = QuXiaoidYiPai.split(",");
        String[] splitNumber = QuXiaonumberYiPai.split(",");
        String id;
        int number;
        for (int i = splitID.length - 1; i >= 0; i--) {
            id = splitID[i];
            number = Integer.valueOf(splitNumber[i]);

            System.out.println(id);
            System.out.println(number);
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);

            iTestService.updateCancelRR(id, number, result1a);
        }
//        PaichengRRCaoZuoUtils.paichengRR(kaishishijian);
        return "redirect:/test/findTestByTestItemRR";
    }

//    RR实验室取消开始
    @RequestMapping("/quxiaokaishiRR")
    public String quxiaokaishiRR()throws Exception{
        iTestService.quxiaokaishiRR();
        return "redirect:/test/findTestByTestItemRR";
    }

//    RR实验室已排  批量 设置排测日期
@Transactional
@RequestMapping("/TiaoZhengShiJianRR")
public String TiaoZhengShiJianRR(String TiaoZhengShiJianData, @RequestParam(name = "id", required = true) String id,@RequestParam(name = "number", required = true) String number) throws Exception {
    System.out.println(TiaoZhengShiJianData);
    String[] splitID = id.split(",");
    String[] splitNumber = number.split(",");
    String idA;
    int numberA;
    for (int i = splitID.length - 1; i >= 0; i--) {
        idA = splitID[i];
        numberA = Integer.valueOf(splitNumber[i]);
        System.out.println(id);
        System.out.println(number);

//      设置排测时间   RR
        iTestService.TiaoZhengShiJianRR(TiaoZhengShiJianData,idA,numberA);
    }

    return "redirect:/test/findTestByTestItemRR";
    }


    //RR实验室一键排程

    @Transactional
    @RequestMapping("/RRInstall")
    public String RRInstall() throws Exception {
        List<TestClietTyre> all = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateRR();
        for (int j = 0; j < list.size(); j++) {
            all.add(list.get(j));
        }

//        存放id
        List<String> list1 = new ArrayList<>();
//        存放法规
        List<String> listStandard = new ArrayList<>();
//        存放时间
        List<Long> durationS = new ArrayList<>();
        //        存放实验项目
        List<String> listTestItem = new ArrayList<>();
//        存放工时
        List<long[]> workTime = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            list1.add(all.get(i).getIdRctest());
            listStandard.add(all.get(i).getStandard());
            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
        }

//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);
            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);

            durationS.add((long) listDate.get(0).getDuration());
        }

        //        RR查询工时
        List<WorkTimeRR> works = iTestService.findworktimeRR();
        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
        }

//RR查询出目前有没有已经排好的计划
        int numberSSS = iTestService.findNumberRR();
        int numberSP = numberSSS + 1;
//        System.out.println(numberSSS);

        if (numberSSS == 0) {
            long ttt = 0;
            String id = new String();
            long ExpectedDate = 0;
            long current = System.currentTimeMillis();//当前时间毫秒数
            long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
            long zeroA = zero / 1000;

//            System.out.println("我是今天零点的时间："+zeroA);
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                ttt = durationS.get(r);//ttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[0] < zeroA) {

                        continue;

                    } else {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
                            id = list1.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.updateExpectedDateRR(id, result1a, numberSP);

//                             System.out.println("id" + list.get(r));
//                            System.out.println("时间" + (f[0]));
//                            System.out.println("初始顺位" + numberSP);
                            numberSP++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }
        } else {
//      查询出一共有多少条已经排好的
            int count = iTestService.findNumberRR();

//        查出sp已排最后一条数据
            Test test = iTestService.findBycount(count);

            //   System.out.println("我是最后一条的时间"+ExpectedDate);
            Date ExpectedDate = test.getExpectedDate();

            //最后一条的法规和项目
            String ATestItem = test.getTestItem();
            String AStandard = test.getStandard();
//          查询出最后一条所需的时间
            List<TimeTest> timeTest = iTestService.findDataStandard(AStandard, ATestItem);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //       最后一条时间变成时间戳
            String timeFormat = sdf.format(ExpectedDate);
            long timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeFormat).getTime() / 1000;
//            System.out.println(timeFormat);

//    最后一条时间戳加上最后一条的工时就是待排第一条的开始时间。
            long endTime = timeTest.get(0).getDuration();
            long beginTime = timestamp + endTime;
            long tttt = 0;
            int p = 0;
            String id = new String();
            long AExpectedDate = 0;
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                tttt = durationS.get(r);//tttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[1] <= beginTime) {
                        continue;
                    } else {
                        if (f[0] < f[1]) {
                            if (beginTime > f[0]) {
                                if (p == 0) {
                                    f[0] = beginTime;
                                    p++;
                                }
                            }
                            f[0] = (int) (f[0] + tttt);
//                  System.out.println(f[0]);
                            AExpectedDate = f[0] - tttt;
                            id = list1.get(r);
                            String result1a = result1.format((AExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.updateExpectedDateRR(id, result1a, numberSP);
//                            System.out.println("id" + list.get(r));
//                            System.out.println("时间" + (f[0]));
//                            System.out.println("初始顺位" + numberSP);
                            numberSP++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }

        }

        return "redirect:/test/findTestByTestItemRR";
    }


    //    RR实验室顺位排程
    @RequestMapping("/updateStateRR")
    public String updateStateRR(String id) throws Exception {
        //  查询RR已排并且没有开始的(按照number顺序)
//        List<Test> all = iTestService.findTestByStateNoZuoRR();
//        if (all.size() != 0) {
//            Date kaishishijian = null;
//            for (int i = 0; i < all.size(); i++) {
//                if (i == 0) {
//                    kaishishijian = all.get(i).getExpectedDate();
//                }
//            }
//            iTestService.updateStateRR(id);
//            PaichengRRCaoZuoUtils.paichengRR(kaishishijian);
//        } else {
            iTestService.updateStateRR(id);
//            PaichengRRUtils.paichengRR();
//        }
        return "redirect:/test/findTestByTestItemRR";
    }

    //    RR实验室插位
    @RequestMapping("/TestNumberRRR")
    public String TestNumberRRR(int numberB, @RequestParam(name = "idB", required = true) String id) throws
            Exception {
        //  查询RR已排并且没有开始的(按照number顺序)
//        List<Test> all = iTestService.findTestByStateNoZuoRR();
//        Date kaishishijian = null;
//        for (int i = 0; i < all.size(); i++) {
//            if (i == 0) {
//                kaishishijian = all.get(i).getExpectedDate();
//            }
//        }

        iTestService.TestNumberRRR(numberB, id);
//        PaichengRRCaoZuoUtils.paichengRR(kaishishijian);
        return "redirect:/test/findTestByTestItemRR";
    }


    //    RR实验室待排取消
    @RequestMapping("/TestCancelRR")
    public String TestCancelRR(String id) throws Exception {
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.TestCancel(id, result1a);
        return "redirect:/test/findByStateRR";
    }


    //    RR实验室待排批量操作（插位排程）
    @Transactional
    @RequestMapping("/PiLiangChaWeiRR")
    public String PiLiangChaWeiRR(int numberS, @RequestParam(name = "id", required = true) String id) throws
            Exception {
        //  查询RR已排并且没有开始的(按照number顺序)
//        List<Test> all = iTestService.findTestByStateNoZuoRR();
//        Date kaishishijian = null;
//        for (int i = 0; i < all.size(); i++) {
//            if (i == 0) {
//                kaishishijian = all.get(i).getExpectedDate();
//            }
//        }
        String[] split = id.split(",");
        int count = split.length;

        String idA;
        for (int i = count - 1; i >= 0; i--) {
            idA = split[i];
            iTestService.TestNumberRRR(numberS, idA);
        }
//        PaichengRRCaoZuoUtils.paichengRR(kaishishijian);
        return "redirect:/test/findTestByTestItemRR";
    }

    //    RR实验室待排批量操作（顺位排程）
    @Transactional
    @RequestMapping("/PiLiangShunWeiRR")
    public String PiLiangShunWeiRR(String id) throws Exception {

        //  查询RR已排并且没有开始的(按照number顺序)
//        List<Test> all = iTestService.findTestByStateNoZuoRR();
//
//        if (all.size() != 0) {
//            Date kaishishijian = null;
//            for (int i = 0; i < all.size(); i++) {
//                if (i == 0) {
//                    kaishishijian = all.get(i).getExpectedDate();
//                }
//            }

            String[] split = id.split(",");
            String idA;
            for (int i = 0; i < split.length; i++) {
                idA = split[i];
                System.out.println(idA);
                iTestService.updateStateRR(idA);
            }
//            PaichengRRCaoZuoUtils.paichengRR(kaishishijian);
//        } else {
//
//            String[] split = id.split(",");
//            String idA;
//            for (int i = 0; i < split.length; i++) {
//                idA = split[i];
//                iTestService.updateStateRR(idA);
//
//            }
//            PaichengRRUtils.paichengRR();
//        }
        return "redirect:/test/findTestByTestItemRR";
    }

    //  RR实验室待排批量操作（取消）
    @Transactional
    @RequestMapping("/PiLiangQuXiaoRR")
    public String PiLiangQuXiaoRR(String QuXiaoid) throws Exception {

        String[] split = QuXiaoid.split(",");
        String id;

        for (int i = 0; i < split.length; i++) {
            id = split[i];
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);
            iTestService.TestCancel(id, result1a);
        }
        return "redirect:/test/findByStateRR";
    }

    //    RR实验室完成查询（按时间查询）
    @RequestMapping("/findByendTimeRR")
    public ModelAndView findByendTimeRR(String start, @RequestParam(name = "end", required = true) String end) throws Exception {
//        List<Test> test = iTestService.findByendTimeCompleteRR(start, end);
//        int a=1;
//        modelAndView.addObject("testList", test);
//        modelAndView.addObject("A",a);
//        modelAndView.addObject("testListA",JSONArray.toJSONString(test));
//        modelAndView.setViewName("ShiYanchaxunRR");
//        return modelAndView;
        List<Test> test = iTestService.findByendTimeCompleteRR(start, end);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
        }
        int a=1;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListA",JSONArray.toJSONString(test));
        modelAndView.setViewName("ShiYanchaxunRR");
        return modelAndView;
    }

    //    RR实验室取消查询（按时间查询）
    @RequestMapping("/findByCancellationTimeRR")
    public ModelAndView findByCancellationTimeRR(String startQuXiao, @RequestParam(name = "endQuXiao", required = true) String endQuXiao) throws Exception {
//            根据时间去查  RR 已经取消的
        List<Test> test = iTestService.findByCancellationTimeCancelRR(startQuXiao, endQuXiao);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
            test.get(i).getTyre().getCliet().setRemark(test.get(i).getTyre().getCliet().getRemark().replaceAll("\"","%22"));
        }
        int a=2;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListB",JSONArray.toJSONString(test));
//
        modelAndView.setViewName("ShiYanchaxunRR");
        return modelAndView;

    }
//RR实验室完成和取消数据导出
    @RequestMapping("/shujudaochuRRWanCheng")
    public  ResponseEntity<byte[]> shujudaochuRRWanCheng(String testList)throws Exception{
//      System.out.println(testList);
       testList = new String(testList.replaceAll("\n","\\\\n"));
      List<Test> all = JSON.parseObject(testList, new TypeReference<List<Test>>(){});
//        System.out.println("aaaaaaaaa");
//        System.out.println(all);
     return  PoiUtilsCha.exportUser2ExcelCha(all);
//        return null;
}

    //    HSU实验室
    @RequestMapping("/findTestByTestItemHSU")
    public ModelAndView findTestByTestItemHSU(
            @RequestParam(name = "page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "size", required = true, defaultValue = "8") int size) throws Exception {

        List<Test> all = iTestService.findTestByTestItemHSU(page, size);
        List<TestClietTyre> aLL = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateHSU();
        for (int j = 0; j < list.size(); j++) {
            aLL.add(list.get(j));
            if (aLL.size() > 60) {
                break;
            }
        }
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        //        查询RR已排现在一共的已排记录
        int count = iTestService.findStateCountHSU();
        //        查询RR待排现在一共的记录
        int countDai = iTestService.findCountDaiHSU();
        PageInfo pageInfo = new PageInfo(all);
        modelAndView.addObject("countDaiHSU", countDai);
        modelAndView.addObject("count", count);
        modelAndView.addObject("HSU", aLL);
        modelAndView.addObject("testListHSU", pageInfo);
        modelAndView.setViewName("test-list-HSU");
        return modelAndView;
    }

    //    HSU待排计划
    @RequestMapping("/findByStateHSU")
    public ModelAndView findByStateHSU() throws Exception {
        //  先把紧急程度赋予数字  便于排序
        iTestService.updatePriorityDesNumber();
        List<TestClietTyre> aLL = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateHSU();
        for (int j = 0; j < list.size(); j++) {
            aLL.add(list.get(j));
            if (aLL.size() > 60) {
                break;
            }
        }
        //        查询出HSU待排一共多少条记录
        int countDai = iTestService.findCountDaiHSU();
        modelAndView.addObject("countDaiHSU", countDai);
        modelAndView.addObject("HSU", aLL);
        modelAndView.setViewName("test-list-HSU");
        return modelAndView;
    }


    //HSU实验室待排数据导出
    @RequestMapping("/ShuJuDaoChuDaiPaiHSU")
    public ResponseEntity<byte[]> ShuJuDaoChuDaiPaiHSU()throws Exception{
        List<TestClietTyre> all = iTestService.findByStateHSU();
        return PoiUtilsDai.exportUser2Excel(all);
    }


    //    HSU实验室完成查询（按时间查询）
    @RequestMapping("/findByendTimeHSU")
    public ModelAndView findByendTimeHSU(String start, @RequestParam(name = "end", required = true) String end) throws Exception {
//        List<Test> test = iTestService.findByendTimeCompleteHSU(start, end);
//        modelAndView.addObject("testList", test);
//        modelAndView.setViewName("ShiYanchaxunHSU");
//        return modelAndView;
        List<Test> test = iTestService.findByendTimeCompleteHSU(start, end);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
        }
        int a=1;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListA",JSONArray.toJSONString(test));
        modelAndView.setViewName("ShiYanchaxunHSU");
        return modelAndView;
    }


    //  HSU实验室取消查询（按时间查询）
    @RequestMapping("/findByCancellationTimeHSU")
    public ModelAndView findByCancellationTimeHSU(String startQuXiao, @RequestParam(name = "endQuXiao", required = true) String endQuXiao) throws Exception {
//            根据时间去查  HSU  已经取消的
//        List<Test> test = iTestService.findByCancellationTimeCancelHSU(startQuXiao, endQuXiao);
//        modelAndView.addObject("testList", test);
//        modelAndView.setViewName("ShiYanchaxunHSU");
//        return modelAndView;
//    }
        List<Test> test = iTestService.findByCancellationTimeCancelHSU(startQuXiao, endQuXiao);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
            test.get(i).getTyre().getCliet().setRemark(test.get(i).getTyre().getCliet().getRemark().replaceAll("\"","%22"));
        }
        int a=2;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListB",JSONArray.toJSONString(test));
//
        modelAndView.setViewName("ShiYanchaxunHSU");
        return modelAndView;

    }
    //HSU实验室完成和取消数据导出
    @RequestMapping("/shujudaochuHSUWanCheng")
    public  ResponseEntity<byte[]> shujudaochuHSUWanCheng(String testList)throws Exception{
//      System.out.println(testList);
        testList = new String(testList.replaceAll("\n","\\\\n"));
        List<Test> all = JSON.parseObject(testList, new TypeReference<List<Test>>(){});
//        System.out.println("aaaaaaaaa");
//        System.out.println(all);
        return  PoiUtilsCha.exportUser2ExcelCha(all);
//        return null;
    }

    //    HSU需求编号搜索(待排)
    @RequestMapping("/findByTestRequestIdDaiPaiHSU")
    public ModelAndView findByTestRequestIdDaiPaiHSU(String RequestId) throws Exception {
        System.out.println("我是数据" + RequestId);
        List<TestClietTyre> all = iTestService.findByTestRequestIdDaiPaiHSU(RequestId);
        modelAndView.addObject("HSU", all);
        modelAndView.setViewName("test-list-HSU");
        return modelAndView;
    }



    //   HSU实验室顺位排程
    @Transactional
    @RequestMapping("/updateStateHSU")
    public String updateStateHSU(String id) throws Exception {

        //  查询HSU已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoHSU();
//        System.out.println(all.size());
        if (all.size() != 0) {
            Date kaishishijian = null;
            for (int i = 0; i < all.size(); i++) {
                if (i == 0) {
                    kaishishijian = all.get(i).getExpectedDate();
                }
            }
            iTestService.updateStateHSU(id);
            PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);
        } else {
            iTestService.updateStateHSU(id);
            PaichengHSUUtils.paichengHSU();
        }
        return "redirect:/test/findTestByTestItemHSU";
    }

    //   HSU实验室插位
    @Transactional
    @RequestMapping("/TestNumberChaHSU")
    public String TestNumberChaHSU(int numberB, @RequestParam(name = "idB", required = true) String id) throws
            Exception {
        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoHSU();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        iTestService.TestNumberHSUU(numberB, id);
        PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);
        return "redirect:/test/findTestByTestItemHSU";

    }


    //    HSU待排取消
    @RequestMapping("/TestCancelHSU")
    public String TestCancelHSU(String id) throws Exception {
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.TestCancel(id, result1a);
        return "redirect:/test/findByStateHSU";
    }

    //    HSU实验室完成
    @Transactional
    @RequestMapping("/updateCompleteHSU")
    public String   updateCompleteHSU(@RequestParam(name = "number", required = true) int number,
                                      @RequestParam(name = "id", required = true) String id) throws Exception {
        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCompleteHSU(number, id, result1a);
        //        去查询数据库有几个已经开始的
        int spbhOne = iTestService.findByspbhOneHSU();
        //       SStiffness 把数据库里面的操作更改数据
        iTestService.updateCaoZuoHSU(spbhOne);
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        return "redirect:/test/findTestByTestItemHSU";
    }


    //HSU实验室操作 number代表输入的数字   numberS代表本来的序号    id代表该数据id
    @RequestMapping("/TestNumberHSU")
    public String TestNumberHSU(int number, @RequestParam(name = "ids", required = true) int numberS,
                                @RequestParam(name = "id", required = true) String id) throws Exception {
        //  查询HSU已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoHSU();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        if (number <= numberS) {

            iTestService.TestNumberHSU(number, numberS, id);
//            System.out.println(numberS);
            PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);
        } else {
            iTestService.TestNumberDownHSU(number, numberS, id);
            PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);
        }
        return "redirect:/test/findTestByTestItemHSU";
    }

    //   HSU 已排 实验室取消
    @RequestMapping("/updateCancelHSU")
    public String updateCancelHSU(String id, @RequestParam(name = "number", required = true) int number) throws
            Exception {
        //  查询HSU已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoHSU();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }

        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCancelHSU(id, number, result1a);
        PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);
        return "redirect:/test/findTestByTestItemHSU";
    }



    //   HSU实验室取消开始
    @RequestMapping("/quxiaokaishiHSU")
    public String quxiaokaishiHSU()throws Exception{
        iTestService.quxiaokaishiHSU();
        return "redirect:/test/findTestByTestItemHSU";
    }


    //    HSU排程更新
    @RequestMapping("/renovateHSU")
    public String renovateHSU() throws Exception {
        PaichengShuaXinHSUUtils.paichengHSU();
        return "redirect:/test/findTestByTestItemHSU";
    }



    //HSU实验室一键排程
    @RequestMapping("/HSUInstall")
    public String HSUInstall() throws Exception {
            List<TestClietTyre> all = new ArrayList<>();
            List<TestClietTyre> listS = iTestService.findByStateHSU();
            for (int j = 0; j < listS.size(); j++) {
                all.add(listS.get(j));
            }
//        存放id
            List<String> list = new ArrayList<>();
//        存放法规
            List<String> listStandard = new ArrayList<>();
//        存放时间
            List<Long> durationS = new ArrayList<>();

            //        存放实验项目
            List<String> listTestItem = new ArrayList<>();
//        存放工时
            List<long[]> workTime = new ArrayList<>();

            for (int i = 0; i < all.size(); i++) {
                list.add(all.get(i).getIdRctest());
                listStandard.add(all.get(i).getStandard());
                listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
            }
//        根据法规去表里查询实验时间
            String Standard = new String();
            String TestItem = new String();//实验项目
            for (int a = 0; a < listStandard.size(); a++) {
                Standard = listStandard.get(a);
                TestItem = listTestItem.get(a);

                List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);
                    durationS.add((long) listDate.get(0).getDuration());
            }

//            HSU查询工时
            List<WorkTimeHSU> works = iTestService.findworktime();
            for (int s = 0; s < works.size(); s++) {
                long q = works.get(s).getGotowork();
                long w = works.get(s).getGetoffwork();
                long[] e = new long[]{q, w};
                workTime.add(e);
            }

        // 获取HSU实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
        int numberHSU = iTestService.findByTestnumber();
        System.out.println("目前实验初始顺位是：" + numberHSU);
        //查询出目前有没有已经排好的计划
        int numberSSS = iTestService.findNumber();
        int numberSP = numberSSS + 1;
        if (numberSSS == 0) {
            long ttt = 0;
            String id = new String();
            long ExpectedDate = 0;
            long current = System.currentTimeMillis();//当前时间毫秒数
            long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
            long zeroA = zero / 1000;
//            System.out.println("我是今天零点的时间："+zeroA);
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                ttt = durationS.get(r);//ttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[0] < zeroA) {
                        continue;
                    } else {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
                            id = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.updateHSUExpectedDate(id, result1a, numberHSU);
//                            System.out.println("id" + list.get(r));
//                            System.out.println("时间" + (f[0]));
//                            System.out.println("初始顺位" + numberHSU);
                            numberHSU++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }
        } else {
            //   查询出HSU一共有多少条已经排好的
            int count = iTestService.findNumber();
            //        查出HSU已排最后一条数据
            Test test = iTestService.findBycountHSU(count);
//         最后一条的时间
            Date ExpectedDate = test.getExpectedDate();
//            //  查询hsu待排排
//            List<Test> All = iTestService.findByStateHSU();
            //最后一条的法规和项目
            String ATestItem = test.getTestItem();
            String AStandard = test.getStandard();
//          查询出最后一条所需的时间
            List<TimeTest> timeTest = iTestService.findDataStandard(AStandard, ATestItem);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //       最后一条时间变成时间戳
            String timeFormat = sdf.format(ExpectedDate);
            long timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeFormat).getTime() / 1000;
//            System.out.println(timeFormat);

//    最后一条时间戳加上最后一条的工时就是待排第一条的开始时间。
            long endTime = timeTest.get(0).getDuration();
            long beginTime = timestamp + endTime;
            long tttt = 0;
            int p = 0;
            String id = new String();
            long AExpectedDate = 0;
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                tttt = durationS.get(r);//tttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[1] <= beginTime) {
                        continue;
                    } else {
                        if (f[0] < f[1]) {
                            if (beginTime > f[0]) {
                                if (p == 0) {
                                    f[0] = beginTime;
                                    p++;
                                }
                            }
                            f[0] = (int) (f[0] + tttt);
//                  System.out.println(f[0]);
                            AExpectedDate = f[0] - tttt;
                            id = list.get(r);
                            String result1a = result1.format((AExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.updateHSUExpectedDate(id, result1a, numberHSU);
//                            System.out.println("id" + list.get(r));
//                            System.out.println("时间" + (f[0]));
//                            System.out.println("初始顺位" + numberSP);
                            numberHSU++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }

        }
        return "redirect:/test/findTestByTestItemHSU";
    }


    //    HSU实验室时间查询
    @RequestMapping("/findWorkTime")
    public ModelAndView findWorkTime() throws Exception {
        List<WorkTimeHSU> Worktime = iTestService.findWorkTime();
//        System.out.println("查询时间为：" + Worktime);
        modelAndView.addObject("WorkTime", Worktime);
        modelAndView.setViewName("set");
        return modelAndView;
    }



    //    HSU实验室已排批量操作
    @Transactional
    @RequestMapping("/PiLiangTestNumberHSU")
    public String PiLiangTestNumberHSU(int numberS,
                                      @RequestParam(name = "id", required = true) String id,
                                      @RequestParam(name = "number", required = true) String number) throws Exception {

        //  查询HSU已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoHSU();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
//    numberS代表输入    id就是本来id   number代表原来序号
        String[] split = id.split(",");
        String[] numberA = number.split(",");
        String Oldnumber = "";
        int parseInt = 0;
        //    代表总长
        int a = split.length;

        for (int d = 0; d < numberA.length; d++) {
            Oldnumber = numberA[d];
        }
        parseInt = Integer.parseInt(Oldnumber);
        if (numberS >= parseInt) {
            //    代表输入
            int nubmerOld;
            String IdID = "";

            for (int i = 0; i < split.length; i++) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(i<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld-i;
                }
                iTestService.PiLiangOperateDownHSU(numberS, nubmerOld, IdID);
            }

            PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);

        } else {

            int nubmerOld;
            String IdID = "";
            int p=0;
            for (int i = a - 1; i >= 0; i--) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(p<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld+p;
                }
                iTestService.PiLiangOperateUpHSU(numberS, nubmerOld, IdID);
                p=p+1;
            }
            PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);
        }
        return "redirect:/test/findTestByTestItemHSU";
    }


    //HSU取消已排  进入待排
    @Transactional
    @RequestMapping("/JinRuDaiPaiHSU")
    public String JinRuDaiPaiHSU(String id, @RequestParam(name = "number", required = true) String number) throws
            Exception {
        //  查询HSU已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoHSU();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        String[] IDA = id.split(",");
        String[] numberA = number.split(",");
        String Anumber = "";
        String Aid = "";
        int idAA = IDA.length;
        for (int i = idAA - 1; i >= 0; i--) {
            Anumber = numberA[i];
            Aid = IDA[i];
            iTestService.JinRuDaiPaiHSU(Anumber, Aid);
        }
        PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);

        return "redirect:/test/findTestByTestItemHSU";
    }

    //HSU已排取消（批量）
    @Transactional
    @RequestMapping("/PiLiangQuXiaoYiPaiHSU")
    public String PiLiangQuXiaoYiPaiHSU(String QuXiaoidYiPai, @RequestParam(name = "QuXiaonumberYiPai", required = true) String QuXiaonumberYiPai) throws
            Exception {
        //  查询HSU已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoHSU();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }

        String[] splitID = QuXiaoidYiPai.split(",");
        String[] splitNumber = QuXiaonumberYiPai.split(",");
        String id;
        int number;
        for (int i = splitID.length - 1; i <= 0; i--) {
            id = splitID[i];
            number = Integer.valueOf(splitNumber[i]);
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);

            iTestService.updateCancelHSU(id, number, result1a);
        }
        PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);
        return "redirect:/test/findTestByTestItemHSU";
    }


    //     HSU实验室开始
    @Transactional
    @RequestMapping("/BeginHSU")
    public String BeginHSU(int number) throws Exception {
        //        HSU开始的时候把错误信息删除(先查id)
        String ID = iTestService.findTestIdHSU(number);
        //        开始的时候把错误信息删除(再去删除数据)
        iTestService.deleteAbnormal(ID);
        //        HSU开始的时候把颜色改成0
        iTestService.updateredHSU(ID);
//       HSU 改变隐藏按钮number
        iTestService.spbhNumberHSU(number);

//        当点击开始的时候  时间变成现在的时间  如果点击第二条的开始  那么第一条的时间不变  只改变第二条和 后面的时间  以此类推
//   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
        List<Test> all = iTestService.findByNoStateNumberHSU(number);

        //        存放实验项目
        List<String> listTestItem = new ArrayList<>();
        //        存放number
        List<Integer> list = new ArrayList<>();
//        存放法规
        List<String> listStandard = new ArrayList<>();
//        存放时间
        List<Long> durationS = new ArrayList<>();

//        存放工时
        List<long[]> workTime = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            list.add(all.get(i).getNumber()); //存的是数据的number
            listStandard.add(all.get(i).getStandard());//存的是数据的法规
            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
        }
//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);
//            System.out.println(Standard);
            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);

            durationS.add((long) listDate.get(0).getDuration());
        }
//    获取设定的时间
        List<WorkTimeHSU> works = iTestService.findworktime();

        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
        }
        long ttt = 0;
//            String id=new String();
        Integer numberA = 0;//设置number
        long ExpectedDate = 0;

        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long zeroA = zero / 1000;

        //        当前时间
        long currentA = current / 1000;

        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long[] f = new long[10];
        //        最后一个没有循环的number
        int numberMax = 0;
        //   里面存的是已经循环的number
        List<Integer> list1 = new ArrayList<>();
        int r;
        int p = 0;
        int o = 0;
        long q = currentA;

        for (r = 0; r < durationS.size(); r++) {
            ttt = durationS.get(r);//ttt代表每一个试验需要的时间
            for (int i = 0; i < workTime.size(); i++) {
                f = workTime.get(i);
                if (f[0] < zeroA) {
                    continue;
                } else {
                    //        开始时间小于工时时间的话  直接取工时时间
                    if (currentA < f[0]) {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            System.out.println("aaaaaaaaaaa");
                            iTestService.updateHSUExpectedDateUP(numberA, result1a);

                            break;
                        } else {
                            continue;
                        }
                    }

                    //假如现在时间在工时的区域内  那就把现在时间当成工时开始时间
                    if (f[0] <= currentA && currentA < f[1]) {
                        if (f[0] < f[1]) {
                            if (p == 0) {
                                f[0] = currentA;
                                p++;
                            }
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            iTestService.updateHSUExpectedDateUP(numberA, result1a);
                            o++;
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (currentA > f[1]) {
                        continue;
                    }
                }
            }
        }

        for (int j = 0; j < list1.size(); j++) {
            if (list1.get(j) > numberMax)   // 判断最大值
                numberMax = list1.get(j);
        }
//        去查询数据库有几个已经开始不需要进行循环的
        int spbhOne = iTestService.findByspbhOneHSU();

        //        HSU把数据库里面的操作更改数据
        iTestService.updateCaoZuoHSU(spbhOne);

        int numberN = numberMax + 2 + spbhOne;
        System.out.println("numberN" + numberN);
//                    去数据库把没有循环到的已经排好的数据变成待排(hsu)
        iTestService.updateHSUDaiPai(numberN);
        return "redirect:/test/findTestByTestItemHSU";
    }


    //    HSU实验室待排批量操作（插位排程）
    @Transactional
    @RequestMapping("/PiLiangChaWeiHSU")
    public String PiLiangChaWeiHSU(int numberS, @RequestParam(name = "id", required = true) String id) throws
            Exception {
        //  查询HSU已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoHSU();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        String[] split = id.split(",");
        int count = split.length;
        String idA;
        for (int i = count - 1; i >= 0; i--) {
            idA = split[i];
            iTestService.TestNumberChaHSU(numberS, idA);
        }
        PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);
        return "redirect:/test/findTestByTestItemHSU";
    }

    //    HSU实验室待排批量操作（顺位排程）
    @Transactional
    @RequestMapping("/PiLiangShunWeiHSU")
    public String PiLiangShunWeiHSU(String id) throws Exception {

        //  查询SStiffness已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoHSU();

        if (all.size() != 0) {
            Date kaishishijian = null;
            for (int i = 0; i < all.size(); i++) {
                if (i == 0) {
                    kaishishijian = all.get(i).getExpectedDate();
                }
            }

            String[] split = id.split(",");
            String idA;
            for (int i = 0; i < split.length; i++) {
                idA = split[i];
                System.out.println(idA);
                iTestService.updateStateHSU(idA);
            }
            PaichengHSUCaoZuoUtils.paichengHSU(kaishishijian);
        } else {

            String[] split = id.split(",");
            String idA;
            for (int i = 0; i < split.length; i++) {
                idA = split[i];
                System.out.println(idA);
                iTestService.updateStateHSU(idA);

            }
            PaichengHSUUtils.paichengHSU();
        }
        return "redirect:/test/findTestByTestItemHSU";
    }

    // HSU实验室待排批量操作（取消）
    @Transactional
    @RequestMapping("/PiLiangQuXiaoHSU")
    public String PiLiangQuXiaoHSU(String QuXiaoid) throws Exception {

        String[] split = QuXiaoid.split(",");
        String id;

        for (int i = 0; i < split.length; i++) {
            id = split[i];
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);
            iTestService.TestCancel(id, result1a);
        }
        return "redirect:/test/findByStateHSU";
    }

    //HSU实验室已排数据导出
    @RequestMapping("/ShuJuDaoChuHSU")
    public ResponseEntity<byte[]> exportUserHSU()throws Exception{
        System.out.println("sssss");
        List<Test> all = iTestService.ShuJuDaoChuHSU();
        System.out.println(all);
        return   PoiUtils.exportUser2Excel(all);
    }


    //    3D实验室
    @RequestMapping("/findTestByTestItem3D")
    public ModelAndView findTestByTestItem3D(
            @RequestParam(name = "page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "size", required = true, defaultValue = "8") int size) throws Exception {
        List<Test> all = iTestService.findTestByTestItem3D(page, size);
        List<TestClietTyre> aLL = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByState3D();
        for (int j = 0; j < list.size(); j++) {
            aLL.add(list.get(j));
            if (aLL.size() > 60){
                break;
            }
        }
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        //        查询3D已排现在一共的已排记录
        int count = iTestService.findStateCount3D();
        //        查询3D待排现在一共的记录
        int countDai = iTestService.findCountDai3D();
        PageInfo pageInfo = new PageInfo(all);
        modelAndView.addObject("testList3D", pageInfo);
        modelAndView.setViewName("test-list-3D");
        modelAndView.addObject("countDai3D", countDai);
        modelAndView.addObject("count", count);
        modelAndView.addObject("D", aLL);
        return modelAndView;
    }

    //    BC实验室调整 number代表输入的数字   numberS代表本来的序号    id代表该数据id
    //3D实验室操作
    @RequestMapping("/TestNumber3D")
    public String TestNumber3D(int number, @RequestParam(name = "ids", required = true) int numberS,
                               @RequestParam(name = "id", required = true) String id) throws Exception {
        //  查询3D已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuo3D();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        if (number <= numberS) {
            iTestService.TestNumber3D(number, numberS, id);
            Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);
            System.out.println(numberS);
        } else {
            iTestService.TestNumberDown3D(number, numberS, id);
            Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);
        }
        return "redirect:/test/findTestByTestItem3D";

    }
//水水水水水水
//sssssss
//测试
    //    3D实验室待排
    @RequestMapping("/findByState3D")
    public ModelAndView findByState3D() throws Exception {
        //  先把紧急程度赋予数字  便于排序
        iTestService.updatePriorityDesNumber();
        List<TestClietTyre> all = iTestService.findByState3D();
        modelAndView.addObject("D", all);
        modelAndView.setViewName("test-list-3D");
        return modelAndView;
    }


    //SP实验室待排数据导出
    @RequestMapping("/ShuJuDaoChuDaiPai3D")
    public ResponseEntity<byte[]> ShuJuDaoChuDaiPai3D()throws Exception{
        List<TestClietTyre> all = iTestService.findByState3D();
        return PoiUtilsDai.exportUser2Excel(all);
    }

    //    3D实验室完成查询（按时间查询）
    @RequestMapping("/findByendTime3D")
    public ModelAndView findByendTime3D(String start, @RequestParam(name = "end", required = true) String end) throws Exception {
//        List<Test> test = iTestService.findByendTimeComplete3D(start, end);
//        modelAndView.addObject("testList", test);
//        modelAndView.setViewName("ShiYanchaxun3D");
//        return modelAndView;
        List<Test> test = iTestService.findByendTimeComplete3D(start, end);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
        }
        int a=1;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListA",JSONArray.toJSONString(test));
        modelAndView.setViewName("ShiYanchaxun3D");
        return modelAndView;
    }




    //  3D实验室取消查询（按时间查询）
    @RequestMapping("/findByCancellationTime3D")
    public ModelAndView findByCancellationTime3D(String startQuXiao, @RequestParam(name = "endQuXiao", required = true) String endQuXiao) throws Exception {
//            根据时间去查  3D 已经取消的
//        List<Test> test = iTestService.findByCancellationTimeCancel3D(startQuXiao, endQuXiao);
//        modelAndView.addObject("testList", test);
//        modelAndView.setViewName("ShiYanchaxun3D");
//        return modelAndView;
//    }
        List<Test> test = iTestService.findByCancellationTimeCancel3D(startQuXiao, endQuXiao);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
            test.get(i).getTyre().getCliet().setRemark(test.get(i).getTyre().getCliet().getRemark().replaceAll("\"","%22"));
        }
        int a=2;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListB",JSONArray.toJSONString(test));
//
        modelAndView.setViewName("ShiYanchaxun3D");
        return modelAndView;

    }
    //RR实验室完成和取消数据导出
    @RequestMapping("/shujudaochu3DWanCheng")
    public  ResponseEntity<byte[]> shujudaochu3DWanCheng(String testList)throws Exception{
//      System.out.println(testList);
        testList = new String(testList.replaceAll("\n","\\\\n"));
        List<Test> all = JSON.parseObject(testList, new TypeReference<List<Test>>(){});
//        System.out.println("aaaaaaaaa");
//        System.out.println(all);
        return  PoiUtilsCha.exportUser2ExcelCha(all);
//        return null;
    }


    //       3D实验室一键排程
    @RequestMapping("/3DInstall")
    public String InstallD() throws Exception {
        List<TestClietTyre> all = new ArrayList<>();
        List<TestClietTyre> listS = iTestService.findByState3D();
        for (int j = 0; j < listS.size(); j++) {
            all.add(listS.get(j));
        }
//        存放id
        List<String> list = new ArrayList<>();
//        存放法规
        List<String> listStandard = new ArrayList<>();
//        存放时间
        List<Long> durationS = new ArrayList<>();

        //        存放实验项目
        List<String> listTestItem = new ArrayList<>();
//        存放工时
        List<long[]> workTime = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            list.add(all.get(i).getIdRctest());
            listStandard.add(all.get(i).getStandard());
            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
        }
//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);

            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);
            durationS.add((long) listDate.get(0).getDuration());
        }

//            3D查询工时
        List<WorkTime3D> works = iTestService.findworktime3D();
        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
        }

        // 获取3D实验室目前有没有已经排序的 如果已经排了2条数据  那么一键排程的时候number就从3开始
        int number3D = iTestService.findByTestnumber3D();
        System.out.println("目前实验初始顺位是：" + number3D);
        //3D查询出目前有没有已经排好的计划
        int numberSSS = iTestService.findNumber3D();
        int numberSP = numberSSS + 1;
        if (numberSSS == 0) {
            long ttt = 0;
            String id = new String();
            long ExpectedDate = 0;
            long current = System.currentTimeMillis();//当前时间毫秒数
            long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
            long zeroA = zero / 1000;
//            System.out.println("我是今天零点的时间："+zeroA);
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                ttt = durationS.get(r);//ttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[0] < zeroA) {
                        continue;
                    } else {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
                            id = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.update3DExpectedDate(id, result1a, number3D);
//                            System.out.println("id" + list.get(r));
//                            System.out.println("时间" + (f[0]));
//                            System.out.println("初始顺位" + numberHSU);
                            number3D++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }
        } else {
            //   查询出3D一共有多少条已经排好的
            int count = iTestService.findNumber3D();
            //        查出3D已排最后一条数据
            Test test = iTestService.findBycount3D(count);
//         最后一条的时间
            Date ExpectedDate = test.getExpectedDate();
//            //  查询hsu待排排
//            List<Test> All = iTestService.findByStateHSU();
            //最后一条的法规和项目
            String ATestItem = test.getTestItem();
            String AStandard = test.getStandard();
//          查询出最后一条所需的时间
            List<TimeTest> timeTest = iTestService.findDataStandard(AStandard, ATestItem);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //       最后一条时间变成时间戳
            String timeFormat = sdf.format(ExpectedDate);
            long timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeFormat).getTime() / 1000;
//            System.out.println(timeFormat);

//    最后一条时间戳加上最后一条的工时就是待排第一条的开始时间。
            long endTime = timeTest.get(0).getDuration();
            long beginTime = timestamp + endTime;
            long tttt = 0;
            int p = 0;
            String id = new String();
            long AExpectedDate = 0;
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                tttt = durationS.get(r);//tttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[1] <= beginTime) {
                        continue;
                    } else {
                        if (f[0] < f[1]) {
                            if (beginTime > f[0]) {
                                if (p == 0) {
                                    f[0] = beginTime;
                                    p++;
                                }
                            }
                            f[0] = (int) (f[0] + tttt);
//                  System.out.println(f[0]);
                            AExpectedDate = f[0] - tttt;
                            id = list.get(r);
                            String result1a = result1.format((AExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.update3DExpectedDate(id, result1a, number3D);
//                            System.out.println("id" + list.get(r));
//                            System.out.println("时间" + (f[0]));
//                            System.out.println("初始顺位" + numberSP);
                            number3D++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }

        }
        return "redirect:/test/findTestByTestItem3D ";
    }


    //    3D排程更新
    @RequestMapping("/renovate3D")
    public String renovate3D() throws Exception {
        PaichengShuaXin3DUtils.paicheng3D();
        return "redirect:/test/findTestByTestItem3D";
    }


    //    3D实验室开始
    @Transactional
    @RequestMapping("/Begin3D")
    public String Begin3D(int number) throws Exception {
        //        3D开始的时候把错误信息删除(先查id)
        String ID = iTestService.findTestId3D(number);
        //        开始的时候把错误信息删除(再去删除数据)
        iTestService.deleteAbnormal(ID);
        //      3D开始的时候把颜色改成0
        iTestService.updatered3D(ID);
//     3D改变隐藏按钮number
        iTestService.spbhNumber3D(number);

//        当点击开始的时候  时间变成现在的时间  如果点击第二条的开始  那么第一条的时间不变  只改变第二条和 后面的时间  以此类推
//   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的 3D
        List<Test> all = iTestService.findByNoStateNumber3D(number);

        //        存放实验项目
        List<String> listTestItem = new ArrayList<>();
        //        存放number
        List<Integer> list = new ArrayList<>();
//        存放法规
        List<String> listStandard = new ArrayList<>();
//        存放时间
        List<Long> durationS = new ArrayList<>();

//        存放工时
        List<long[]> workTime = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            list.add(all.get(i).getNumber()); //存的是数据的number
            listStandard.add(all.get(i).getStandard());//存的是数据的法规
            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
        }
//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);
//            System.out.println(Standard);
            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);

            durationS.add((long) listDate.get(0).getDuration());
        }
//    获取设定的时间
        List<WorkTime3D> works = iTestService.findworktime3D();

        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
        }
        long ttt = 0;
//            String id=new String();
        Integer numberA = 0;//设置number
        long ExpectedDate = 0;

        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long zeroA = zero / 1000;

        //        当前时间
        long currentA = current / 1000;

        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long[] f = new long[10];
        //        最后一个没有循环的number
        int numberMax = 0;
        //   里面存的是已经循环的number
        List<Integer> list1 = new ArrayList<>();
        int r;
        int p = 0;
        int o = 0;
        long q = currentA;

//        for (int i = 0; i < workTime.size(); i++) {
//            f = workTime.get(i);
//            if (currentA < f[0]) {
//                continue;
//            }
//            if (f[0] <= currentA && currentA < f[1]) {
//                for (r = 0; r < durationS.size(); r++) {
//                    ttt = durationS.get(r);//ttt代表每一个试验需要的时间
//                    for (int t = 0; t < workTime.size(); t++) {
//                        f = workTime.get(t);
//                        if (f[0] < zeroA) {
//                            continue;
//                        } else {
//                            if (f[0] < f[1]) {
//                                System.out.println(f[0]);
//                                f[0] = (int) (q + ttt);
//                                q += ttt;
//                                System.out.println(f[0]);
////                  System.out.println(f[0]);
//                                ExpectedDate = f[0] - ttt;
////                        System.out.println("我是循环里面的"+r);
//                                list1.add(r);
//                                numberA = list.get(r);
//                                String result1a = result1.format((ExpectedDate * 1000));
////                        System.out.println("转换时间为：" + result1a);
//                                iTestService.update3DExpectedDateUP(numberA, result1a);
//                                o++;
//                                break;
//                            } else {
//                                continue;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        for (r = 0; r < durationS.size(); r++) {
            ttt = durationS.get(r);//ttt代表每一个试验需要的时间
            for (int i = 0; i < workTime.size(); i++) {
                f = workTime.get(i);
                if (f[0] < zeroA) {
                    continue;
                } else {
                    //        开始时间小于工时时间的话  直接取工时时间
                    if (currentA < f[0]) {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            System.out.println("aaaaaaaaaaa");
                            iTestService.update3DExpectedDateUP(numberA, result1a);
                            System.out.println("怎么回事1");
                            break;
                        } else {
                            continue;
                        }
                    }

                    //假如现在时间在工时的区域内  那就把现在时间当成工时开始时间
                    if (f[0] <= currentA && currentA < f[1]) {
                        System.out.println(currentA);
                        if (f[0] < f[1]) {
                            if (p == 0) {
                                f[0] = currentA;
                                p++;
                            }
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            System.out.println("怎么回事");
                            System.out.println(result1a);
                            iTestService.update3DExpectedDateUP(numberA, result1a);
                            o++;
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (currentA > f[1]) {
                        continue;
                    }
                }
            }
        }



        for (int j = 0; j < list1.size(); j++) {
            if (list1.get(j) > numberMax)   // 判断最大值
                numberMax = list1.get(j);
        }
//        去查询数据库有几个已经开始不需要进行循环的
        int spbhOne = iTestService.findByspbhOne3D();

        //        3D把数据库里面的操作更改数据
        iTestService.updateCaoZuo3D(spbhOne);

        int numberN = numberMax + 1 + spbhOne;
        System.out.println("numberN" + numberN);
//                    去数据库把没有循环到的已经排好的数据变成待排(3D)
        iTestService.update3DDaiPai(numberN);
        return "redirect:/test/findTestByTestItem3D";
    }


    //   BC实验室完成
    @Transactional
    @RequestMapping("/updateComplete3D")
    public String   updateComplete3D( @RequestParam(name = "id", required = true) String id,@RequestParam(name = "number", required = true) int number) throws Exception {
        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateComplete3D(id,number,result1a);
        //       3D 去查询数据库有几个已经开始的
        int spbhOne = iTestService.findByspbhOne3D();
        //       3D 把数据库里面的操作更改数据
        iTestService.updateCaoZuo3D(spbhOne);
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        return "" +
                "redirect:/test/findTestByTestItem3D";
    }



    //   3D已排 实验室取消
    @RequestMapping("/ValuesQuXiao3D")
    public String ValuesQuXiao3D(String id, @RequestParam(name = "number", required = true) int number) throws Exception {
        //  查询3D已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuo3D();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCancel3D(id, number, result1a);
        Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);
        return "redirect:/test/findTestByTestItem3D";
    }

    //   3D实验室取消开始
    @RequestMapping("/quxiaokaishi3D")
    public String quxiaokaishi3D()throws Exception{
        iTestService.quxiaokaishi3D();
        return "redirect:/test/findTestByTestItem3D";
    }



    //    3D实验室已排批量操作
    @Transactional
    @RequestMapping("/PiLiangTestNumber3D")
    public String PiLiangTestNumber3D(int numberS,
                                      @RequestParam(name = "id", required = true) String id,
                                      @RequestParam(name = "number", required = true) String number) throws Exception {

        //  查询3D已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuo3D();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
//    numberS代表输入    id就是本来id   number代表原来序号
        String[] split = id.split(",");
        String[] numberA = number.split(",");
        String Oldnumber = "";
        int parseInt = 0;
        //    代表总长
        int a = split.length;

        for (int d = 0; d < numberA.length; d++) {
            Oldnumber = numberA[d];
        }
        parseInt = Integer.parseInt(Oldnumber);
        if (numberS >= parseInt) {
            //    代表输入
            int nubmerOld;
            String IdID = "";

            for (int i = 0; i < split.length; i++) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(i<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld-i;
                }
                iTestService.PiLiangOperateDown3D(numberS, nubmerOld, IdID);
            }

            Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);

        } else {

            int nubmerOld;
            String IdID = "";
            int p=0;
            for (int i = a - 1; i >= 0; i--) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(p<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld+p;
                }
                iTestService.PiLiangOperateUp3D(numberS, nubmerOld, IdID);
                p=p+1;
            }
            Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);
        }
        return "redirect:/test/findTestByTestItem3D";
    }



    //3D取消已排  进入待排
    @Transactional
    @RequestMapping("/JinRuDaiPai3D")
    public String JinRuDaiPai3D(String id, @RequestParam(name = "number", required = true) String number) throws
            Exception {
        //  查询3D已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuo3D();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        String[] IDA = id.split(",");
        String[] numberA = number.split(",");
        String Anumber = "";
        String Aid = "";
        int idAA = IDA.length;
        for (int i = idAA - 1; i >= 0; i--) {
            Anumber = numberA[i];
            Aid = IDA[i];
            iTestService.JinRuDaiPai3D(Anumber, Aid);
        }
        Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);

        return "redirect:/test/findTestByTestItem3D";
    }


    //3D已排取消（批量）
    @Transactional
    @RequestMapping("/PiLiangQuXiaoYiPai3D")
    public String PiLiangQuXiaoYiPai3D(String QuXiaoidYiPai, @RequestParam(name = "QuXiaonumberYiPai", required = true) String QuXiaonumberYiPai) throws
            Exception {
        //  查询3D已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuo3D();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }

        String[] splitID = QuXiaoidYiPai.split(",");
        String[] splitNumber = QuXiaonumberYiPai.split(",");
        String id;
        int number;
        for (int i = splitID.length - 1; i <= 0; i--) {
            id = splitID[i];
            number = Integer.valueOf(splitNumber[i]);
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);

            iTestService.updateCancel3D(id, number, result1a);
        }
        Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);
        return "redirect:/test/findTestByTestItem3D";
    }



    //   3D实验室顺位排程
    @Transactional
    @RequestMapping("/updateState3D")
    public String updateState3D(String id) throws Exception {

        //  查询3D已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuo3D();
//        System.out.println(all.size());
        if (all.size() != 0) {
            Date kaishishijian = null;
            for (int i = 0; i < all.size(); i++) {
                if (i == 0) {
                    kaishishijian = all.get(i).getExpectedDate();
                }
            }
            iTestService.updateState3D(id);
            Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);
        } else {
            iTestService.updateState3D(id);
            Paicheng3DUtils.paicheng3D();
        }
        return "redirect:/test/findTestByTestItem3D";
    }

    //  3D实验室待排插位
    @Transactional
    @RequestMapping("/TestNumberCha3D")
    public String TestNumberCha3D(int numberB, @RequestParam(name = "idB", required = true) String id) throws
            Exception {
        //  查询3D已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuo3D();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {

            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        iTestService.TestNumberDaiCha3D(numberB, id);
        Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);
        return "redirect:/test/findTestByTestItem3D";
    }


    //   3D待排取消
    @RequestMapping("/TestCancel3D")
    public String TestCancelD(String id) throws Exception{
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.TestCancel(id, result1a);
        return "redirect:/test/findByState3D";
    }


    //    3D实验室待排批量操作（插位排程）
    @Transactional
    @RequestMapping("/PiLiangChaWei3D")
    public String PiLiangChaWei3D(int numberS, @RequestParam(name = "id", required = true) String id) throws
            Exception {
        //  查询3D已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuo3D();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        String[] split = id.split(",");
        int count = split.length;
        String idA;
        for (int i = count - 1; i >= 0; i--) {
            idA = split[i];
            iTestService.TestNumberDaiCha3D(numberS, idA);
        }
        Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);
        return "redirect:/test/findTestByTestItem3D";
    }

    //    3D实验室待排批量操作（顺位排程）
    @Transactional
    @RequestMapping("/PiLiangShunWei3D")
    public String PiLiangShunWei3D(String id) throws Exception {

        //  查询3D已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuo3D();

        if (all.size() != 0) {
            Date kaishishijian = null;
            for (int i = 0; i < all.size(); i++) {
                if (i == 0) {
                    kaishishijian = all.get(i).getExpectedDate();
                }
            }

            String[] split = id.split(",");
            String idA;
            for (int i = 0; i < split.length; i++) {
                idA = split[i];
                System.out.println(idA);
                iTestService.updateState3D(idA);
            }
            Paicheng3DCaoZuoUtils.paicheng3D(kaishishijian);
        } else {

            String[] split = id.split(",");
            String idA;
            for (int i = 0; i < split.length; i++) {
                idA = split[i];
                System.out.println(idA);
                iTestService.updateState3D(idA);

            }
            Paicheng3DUtils.paicheng3D();
        }
        return "redirect:/test/findTestByTestItem3D";
    }

    // 3D实验室待排批量操作（取消）
    @Transactional
    @RequestMapping("/PiLiangQuXiao3D")
    public String PiLiangQuXiao3D(String QuXiaoid) throws Exception {

        String[] split = QuXiaoid.split(",");
        String id;

        for (int i = 0; i < split.length; i++) {
            id = split[i];
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);
            iTestService.TestCancel(id, result1a);
        }
        return "redirect:/test/findByState3D";
    }

    //    3D需求编号搜索(待排)
    @RequestMapping("/findByTestRequestIdDaiPai3D")
    public ModelAndView findByTestRequestIdDaiPai3D(String RequestId) throws Exception {
        System.out.println("我是数据" + RequestId);
        List<TestClietTyre> all = iTestService.findByTestRequestIdDaiPai3D(RequestId);
        modelAndView.addObject("D", all);
        modelAndView.setViewName("test-list-3D");
        return modelAndView;
    }

    //3D实验室已排数据导出
    @RequestMapping("/ShuJuDaoChu3D")
    public ResponseEntity<byte[]> exportUser3D()throws Exception{
        List<Test> all = iTestService.ShuJuDaoChu3D();
        return   PoiUtils.exportUser2Excel(all);
    }

    //    SP实验室已排
    @Transactional
    @RequestMapping("/findTestByTestItemSP")
    public ModelAndView findTestByTestItemSP(
            @RequestParam(name = "page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "size", required = true, defaultValue = "8") int size) throws Exception {
        List<Test> all = iTestService.findTestByTestItemSP(page, size);
        List<TestClietTyre> aLL = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateSP();

        for (int j = 0; j < list.size(); j++) {
            aLL.add(list.get(j));
            if (aLL.size() > 60) {
                break;
            }
        }
//把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        System.out.println("我执行了1111");
        PageInfo pageInfo = new PageInfo(all);
        //        查询SP已排现在一共的已排记录
        int count = iTestService.findStateCount();
        //        查询SP已排现在一共的已排记录
        int countDai = iTestService.findCountDai();
        modelAndView.addObject("countDai", countDai);
        modelAndView.addObject("count", count);
        modelAndView.addObject("testList", pageInfo);
        modelAndView.addObject("SP", aLL);
        modelAndView.setViewName("test-list-SP");
        return modelAndView;

    }

    //    SP实验室开始
    @Transactional
    @RequestMapping("/BeginSP")
    public String BeginSP(int number) throws Exception {
        //        开始的时候把错误信息删除(先查id)
        String ID = iTestService.findTestId(number);
        //        开始的时候把错误信息删除(再去删除数据)
        iTestService.deleteAbnormal(ID);
        //        开始的时候把颜色改成0
        iTestService.updatered(ID);
//        改变隐藏按钮number
        iTestService.spbhNumber(number);

//        当点击开始的时候  时间变成现在的时间  如果点击第二条的开始  那么第一条的时间不变  只改变第二条和 后面的时间  以此类推
//   查询已排 并且是查询还没有开始的已排  就是当前按钮number的后面的已经排的
        List<Test> all = iTestService.findByNoStateNumber(number);

        //        存放实验项目
        List<String> listTestItem = new ArrayList<>();
        //        存放number
        List<Integer> list = new ArrayList<>();
//        存放法规
        List<String> listStandard = new ArrayList<>();
//        存放时间
        List<Long> durationS = new ArrayList<>();

//        存放工时
        List<long[]> workTime = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            list.add(all.get(i).getNumber()); //存的是数据的number
            listStandard.add(all.get(i).getStandard());//存的是数据的法规
            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
        }
//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);
//            System.out.println(Standard);
            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);

            durationS.add((long) listDate.get(0).getDuration());
        }
//    获取设定的时间
        List<WorkTimeSp> works = iTestService.findworktimeSP();

        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
        }
        long ttt = 0;
//            String id=new String();
        Integer numberA = 0;//设置number
        long ExpectedDate = 0;

        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        long zeroA = zero / 1000;

        //        当前时间
        long currentA = current / 1000;

        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long[] f = new long[10];
        //        最后一个没有循环的number
        int numberMax = 0;
        //   里面存的是已经循环的number
        List<Integer> list1 = new ArrayList<>();
        int r;
        int p = 0;
        int o = 0;
        long q = currentA;

        for (r = 0; r < durationS.size(); r++) {
            ttt = durationS.get(r);//ttt代表每一个试验需要的时间
            for (int i = 0; i < workTime.size(); i++) {
                f = workTime.get(i);
                if (f[0] < zeroA) {
                    continue;
                } else {
                    //        开始时间小于工时时间的话  直接取工时时间
                    if (currentA < f[0]) {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            System.out.println("aaaaaaaaaaa");
                            iTestService.updateHSUExpectedDateUPSP(numberA, result1a);

                            break;
                        } else {
                            continue;
                        }
                    }

                    //假如现在时间在工时的区域内  那就把现在时间当成工时开始时间
                    if (f[0] <= currentA && currentA < f[1]) {
                        if (f[0] < f[1]) {
                            if (p == 0) {
                                f[0] = currentA;
                                p++;
                            }
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            iTestService.updateHSUExpectedDateUPSP(numberA, result1a);
                            o++;
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (currentA > f[1]) {
                        continue;
                    }
                }
            }
        }


        for (int j = 0; j < list1.size(); j++) {
            if (list1.get(j) > numberMax)   // 判断最大值
                numberMax = list1.get(j);
        }
//        去查询数据库有几个已经开始不需要进行循环的
        int spbhOne = iTestService.findByspbhOne();

        //        把数据库里面的操作更改数据
        iTestService.updateCaoZuo(spbhOne);

        int numberN = numberMax + 1 + spbhOne;
        System.out.println("numberN" + numberN);
//                    去数据库把没有循环到的已经排好的数据变成待排(SP)
        iTestService.updateSPnumberDaiPai(numberN);
        return "redirect:/test/findTestByTestItemSP";
    }


    //    SP实验室取消开始
    @RequestMapping("/quxiaokaishiSP")
    public String quxiaokaishiSP()throws Exception{
        iTestService.quxiaokaishiSP();
        return "redirect:/test/findTestByTestItemSP";
    }

    //    SP实验室完成
    @Transactional
    @RequestMapping("/updateCompleteSP")
    public String updateCompleteSP(@RequestParam(name = "number", required = true) int number,
                                   @RequestParam(name = "id", required = true) String id) throws Exception {

        //       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCompleteSP(number, id, result1a);
        //        去查询数据库有几个已经开始的
        int spbhOne = iTestService.findByspbhOne();
        //        把数据库里面的操作更改数据
        iTestService.updateCaoZuo(spbhOne);
        //把第一条的开始放开  可以操作
        iTestService.updateKaiShi();
        return "redirect:/test/findTestByTestItemSP";
    }


    //SP实验室已排数据导出
    @RequestMapping("/ShuJuDaoChuSP")
    public ResponseEntity<byte[]> exportUserSP()throws Exception{
        List<Test> all = iTestService.ShuJuDaoChuSP();
        return   PoiUtils.exportUser2Excel(all);
    }

    //    SP实验室操作
    @Transactional
    @RequestMapping("/TestNumberSPP")
    public String TestNumberSPP(@RequestParam(name = "number", required = true) int number,
                                @RequestParam(name = "ids", required = true) int numberS,
                                @RequestParam(name = "id", required = true) String id) throws
            Exception {
        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSP();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        if (number <= numberS) {
            iTestService.TestNumberSPP(number, numberS, id);
            PaichengSpCaoZuoUtils.paichengSP(kaishishijian);
//            System.out.println(numberS);
        } else {
            iTestService.TestNumberDownSPP(number, numberS, id);
            PaichengSpCaoZuoUtils.paichengSP(kaishishijian);

        }
        return "redirect:/test/findTestByTestItemSP";

    }

    //    SP实验室已排批量操作
    @Transactional
    @RequestMapping("/PiLiangTestNumberSP")
    public String PiLiangTestNumberSP(int numberS,
                                      @RequestParam(name = "id", required = true) String id,
                                      @RequestParam(name = "number", required = true) String number) throws Exception {

        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSP();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
//    numberS代表输入    id就是本来id   number代表原来序号
        String[] split = id.split(",");
        String[] numberA = number.split(",");
        String Oldnumber = "";
        int parseInt = 0;
        //    代表总长
        int a = split.length;

        for (int d = 0; d < numberA.length; d++) {
            Oldnumber = numberA[d];
        }
        parseInt = Integer.parseInt(Oldnumber);
        if (numberS >= parseInt) {
            //    代表输入
            int nubmerOld;
            String IdID = "";

            for (int i = 0; i < split.length; i++) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(i<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld-i;
                }
                iTestService.PiLiangOperateDown(numberS, nubmerOld, IdID);
            }

            PaichengSpCaoZuoUtils.paichengSP(kaishishijian);

        } else {

            int nubmerOld;
            String IdID = "";
             int p=0;
            for (int i = a - 1; i >= 0; i--) {
                IdID = split[i];
                nubmerOld = Integer.parseInt(numberA[i]);
                if(p<1){
                    nubmerOld=nubmerOld;
                }else {
                    nubmerOld=nubmerOld+p;
                }
                iTestService.PiLiangOperateUp(numberS, nubmerOld, IdID);
                p=p+1;
            }
            PaichengSpCaoZuoUtils.paichengSP(kaishishijian);
        }
        return "redirect:/test/findTestByTestItemSP";
    }


    //取消已排  进入待排
    @Transactional
    @RequestMapping("/JinRuDaiPai")
    public String JinRuDaiPai(String id, @RequestParam(name = "number", required = true) String number) throws
            Exception {
        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSP();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        String[] IDA = id.split(",");
        String[] numberA = number.split(",");
        String Anumber = "";
        String Aid = "";
        int idAA = IDA.length;
        for (int i = idAA - 1; i >= 0; i--) {
            Anumber = numberA[i];
            Aid = IDA[i];
            iTestService.JinRuDaiPai(Anumber, Aid);
        }
        PaichengSpCaoZuoUtils.paichengSP(kaishishijian);

        return "redirect:/test/findTestByTestItemSP";
    }


    //SP已排取消
    @Transactional
    @RequestMapping("/updateCancelSP")
    public String updateCancelSP(String id, @RequestParam(name = "number", required = true) int number) throws
            Exception {
        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSP();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
//       得出当前时间
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.updateCancelSP(id, number, result1a);
        PaichengSpCaoZuoUtils.paichengSP(kaishishijian);
        return "redirect:/test/findTestByTestItemSP";
    }

    //SP已排取消（批量）
    @Transactional
    @RequestMapping("/PiLiangQuXiaoYiPai")
    public String PiLiangQuXiaoYiPai(String QuXiaoidYiPai, @RequestParam(name = "QuXiaonumberYiPai", required = true) String QuXiaonumberYiPai) throws
            Exception {
        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSP();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }

        String[] splitID = QuXiaoidYiPai.split(",");
        String[] splitNumber = QuXiaonumberYiPai.split(",");
        String id;
        int number;
        for (int i = splitID.length - 1; i <= 0; i--) {
            id = splitID[i];
            number = Integer.valueOf(splitNumber[i]);
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);

            iTestService.updateCancelSP(id, number, result1a);
        }
        PaichengSpCaoZuoUtils.paichengSP(kaishishijian);
        return "redirect:/test/findTestByTestItemSP";
    }


    //    SP实验室待排计划
    @Transactional
    @RequestMapping("/findByStateSP")
    public ModelAndView findByStateSP() throws Exception {

//        先把紧急程度赋予数字  便于排序
        iTestService.updatePriorityDesNumber();
        List<TestClietTyre> aLL = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateSP();
        for (int j = 0; j < list.size(); j++) {
            aLL.add(list.get(j));
            if (aLL.size() > 60) {
                break;
            }
        }
//        查询出待排一共多少条记录
        int countDai = iTestService.findCountDai();
        modelAndView.addObject("countDai", countDai);
        modelAndView.addObject("SP", aLL);
        modelAndView.setViewName("test-list-SP");
        return modelAndView;
    }

    //SP实验室待排数据导出
    @RequestMapping("/ShuJuDaoChuDaiPaiSP")
    public ResponseEntity<byte[]> ShuJuDaoChuDaiPaiSP()throws Exception{
        List<TestClietTyre> all = iTestService.findByStateSP();
        return PoiUtilsDai.exportUser2Excel(all);
    }


    //SP实验室一键排程
    @Transactional
    @RequestMapping("/SPInstall")
    public String SPInstall() throws Exception {
        List<TestClietTyre> all = new ArrayList<>();
        List<TestClietTyre> list = iTestService.findByStateSP();
        for (int j = 0; j < list.size(); j++) {
            all.add(list.get(j));
        }
//        存放id
        List<String> list1 = new ArrayList<>();
//        存放法规
        List<String> listStandard = new ArrayList<>();
//        存放时间
        List<Long> durationS = new ArrayList<>();
        //        存放实验项目
        List<String> listTestItem = new ArrayList<>();
//        存放工时
        List<long[]> workTime = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            list1.add(all.get(i).getIdRctest());
            listStandard.add(all.get(i).getStandard());
            listTestItem.add(all.get(i).getTestItem());//存的是数据的实验项目
        }

//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);
            List<TimeTest> listDate = iTestService.findDataStandard(Standard, TestItem);

            durationS.add((long) listDate.get(0).getDuration());
        }

        //        SP查询工时
        List<WorkTimeSp> works = iTestService.findworktimeSP();


        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
        }
//SP查询出目前有没有已经排好的计划
        int numberSSS = iTestService.findNumberSP();
        int numberSP = numberSSS + 1;
//        System.out.println(numberSSS);

        if (numberSSS == 0) {
            long ttt = 0;
            String id = new String();
            long ExpectedDate = 0;
            long current = System.currentTimeMillis();//当前时间毫秒数
            long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
            long zeroA = zero / 1000;

//            System.out.println("我是今天零点的时间："+zeroA);
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                ttt = durationS.get(r);//ttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[0] < zeroA) {

                        continue;

                    } else {
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
                            id = list1.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.updateHSUExpectedDateSP(id, result1a, numberSP);

//                            System.out.println("id" + list.get(r));
//                            System.out.println("时间" + (f[0]));
//                            System.out.println("初始顺位" + numberSP);
                            numberSP++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }
        } else {
//      查询出一共有多少条已经排好的
            int count = iTestService.findStateCount();

//        查出sp已排最后一条数据
            Test test = iTestService.findBycount(count);

            //   System.out.println("我是最后一条的时间"+ExpectedDate);
            Date ExpectedDate = test.getExpectedDate();

            //最后一条的法规和项目
            String ATestItem = test.getTestItem();
            String AStandard = test.getStandard();
//          查询出最后一条所需的时间
            List<TimeTest> timeTest = iTestService.findDataStandard(AStandard, ATestItem);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //       最后一条时间变成时间戳
            String timeFormat = sdf.format(ExpectedDate);
            long timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeFormat).getTime() / 1000;
//            System.out.println(timeFormat);

//    最后一条时间戳加上最后一条的工时就是待排第一条的开始时间。
            long endTime = timeTest.get(0).getDuration();
            long beginTime = timestamp + endTime;
            long tttt = 0;
            int p = 0;
            String id = new String();
            long AExpectedDate = 0;
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int r = 0; r < durationS.size(); r++) {
                tttt = durationS.get(r);//tttt代表每一个试验需要的时间
                long[] f = new long[10];
                for (int i = 0; i < workTime.size(); i++) {
                    f = workTime.get(i);
                    if (f[1] <= beginTime) {
                        continue;
                    } else {
                        if (f[0] < f[1]) {
                            if (beginTime > f[0]) {
                                if (p == 0) {
                                    f[0] = beginTime;
                                    p++;
                                }
                            }
                            f[0] = (int) (f[0] + tttt);
//                  System.out.println(f[0]);
                            AExpectedDate = f[0] - tttt;
                            id = list1.get(r);
                            String result1a = result1.format((AExpectedDate * 1000));
//                            System.out.println("转换时间为：" + result1a);
                            iTestService.updateHSUExpectedDateSP(id, result1a, numberSP);
//                            System.out.println("id" + list.get(r));
//                            System.out.println("时间" + (f[0]));
//                            System.out.println("初始顺位" + numberSP);
                            numberSP++;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }

        }

        return "redirect:/test/findTestByTestItemSP";
    }

    //    SP实验室待排批量操作（顺位排程）
    @Transactional
    @RequestMapping("/PiLiangShunWei")
    public String PiLiangShunWei(String id) throws Exception {

        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSP();

        if (all.size() != 0) {
            Date kaishishijian = null;
            for (int i = 0; i < all.size(); i++) {
                if (i == 0) {
                    kaishishijian = all.get(i).getExpectedDate();
                }
            }

            String[] split = id.split(",");
            String idA;
            for (int i = 0; i < split.length; i++) {
                idA = split[i];
                System.out.println(idA);
                iTestService.updateStateSP(idA);
            }
            System.out.println("运行到这里");
            PaichengSpCaoZuoUtils.paichengSP(kaishishijian);
        } else {
            System.out.println("运行到这里11111");
            String[] split = id.split(",");
            String idA;
            for (int i = 0; i < split.length; i++) {
                idA = split[i];
                iTestService.updateStateSP(idA);

            }
            PaichengSpUtils.paichengSP();
        }
        return "redirect:/test/findTestByTestItemSP";
    }

    //    SP实验室待排批量操作（插位排程）
    @Transactional
    @RequestMapping("/PiLiangChaWei")
    public String PiLiangChaWei(int numberS, @RequestParam(name = "id", required = true) String id) throws
            Exception {
        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSP();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        String[] split = id.split(",");
        int count = split.length;

        String idA;
        for (int i = count - 1; i >= 0; i--) {
            idA = split[i];
            iTestService.TestNumberSP(numberS, idA);
        }
        PaichengSpCaoZuoUtils.paichengSP(kaishishijian);
        return "redirect:/test/findTestByTestItemSP";
    }

    //  SP实验室待排批量操作（取消）
    @Transactional
    @RequestMapping("/PiLiangQuXiao")
    public String PiLiangQuXiao(String QuXiaoid) throws Exception {

        String[] split = QuXiaoid.split(",");
        String id;

        for (int i = 0; i < split.length; i++) {
            id = split[i];
            //       得出当前时间
            long current = System.currentTimeMillis();//当前时间毫秒数
            SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result1a = result1.format(current);
            iTestService.TestCancel(id, result1a);
        }
        return "redirect:/test/findByStateSP";
    }

    //SP实验室排程刷新
    @RequestMapping("/renovateSP")
    public String renovateSP() throws Exception {
        PaichengShuaXinSpUtils.paichengSP();
        return "redirect:/test/findTestByTestItemSP";
    }


    //    SP实验室顺位排程
    @Transactional
    @RequestMapping("/updateStateSP")
    public String updateStateSP(String id) throws Exception {

        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSP();
        System.out.println(all.size());
        if (all.size() != 0) {
            Date kaishishijian = null;
            for (int i = 0; i < all.size(); i++) {
                if (i == 0) {
                    kaishishijian = all.get(i).getExpectedDate();
                }
            }
            iTestService.updateStateSP(id);
            PaichengSpCaoZuoUtils.paichengSP(kaishishijian);
        } else {
            iTestService.updateStateSP(id);
            PaichengSpUtils.paichengSP();
        }
        return "redirect:/test/findTestByTestItemSP";
    }

    //    SP实验室插位
    @Transactional
    @RequestMapping("/TestNumberSP")
    public String TestNumberSP(int numberB, @RequestParam(name = "idB", required = true) String id) throws
            Exception {
        //  查询sp已排并且没有开始的(按照number顺序)
        List<Test> all = iTestService.findTestByStateNoZuoSP();
        Date kaishishijian = null;
        for (int i = 0; i < all.size(); i++) {
            if (i == 0) {
                kaishishijian = all.get(i).getExpectedDate();
            }
        }
        iTestService.TestNumberSP(numberB, id);
        PaichengSpCaoZuoUtils.paichengSP(kaishishijian);
        return "redirect:/test/findTestByTestItemSP";

    }

    //    SP实验室待排取消
    @Transactional
    @RequestMapping("/TestCancelSP")
    public String TestCancelSP(String id) throws Exception {
        long current = System.currentTimeMillis();//当前时间毫秒数
        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result1a = result1.format(current);
        iTestService.TestCancel(id, result1a);
        return "redirect:/test/findByStateSP";
    }



    //    SP需求编号搜索(待排)
    @RequestMapping("/findByTestRequestIdDaiPai")
    public ModelAndView findByTestRequestIdDaiPai(String RequestId) throws Exception {
        System.out.println("我是数据" + RequestId);
        List<TestClietTyre> all = iTestService.findByTestRequestIdDaiPai(RequestId);
        modelAndView.addObject("SP", all);
        modelAndView.setViewName("test-list-SP");
        return modelAndView;
    }
        //    SP实验室完成查询（按时间查询）
    @RequestMapping("/findByendTime")
    public ModelAndView findByendTimeSP(String start, @RequestParam(name = "end", required = true) String end) throws Exception {
//        List<Test> test = iTestService.findByendTimeComplete(start, end);
//        modelAndView.addObject("testList", test);
//        modelAndView.setViewName("ShiYanchaxun");
//        return modelAndView;

        List<Test> test = iTestService.findByendTimeComplete(start, end);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
        }
        int a=1;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListA",JSONArray.toJSONString(test));
        modelAndView.setViewName("ShiYanchaxun");
        return modelAndView;
    }


    //  SP实验室取消查询（按时间查询）
    @RequestMapping("/findByCancellationTime")
    public ModelAndView findByCancellationTimeSP(String startQuXiao, @RequestParam(name = "endQuXiao", required = true) String endQuXiao) throws Exception {
//            根据时间去查  SP 已经取消的
//        List<Test> test = iTestService.findByCancellationTimeCancel(startQuXiao, endQuXiao);
//        modelAndView.addObject("testList", test);
//        modelAndView.setViewName("ShiYanchaxun");
//        return modelAndView;
//            根据时间去查  SP 已经取消的
        List<Test> test = iTestService.findByCancellationTimeCancel(startQuXiao, endQuXiao);
        List<Test> cloneTestList = DeepCopyUtil.deepCopy(test);
        for(int i = 0; i < test.size(); i++){
            test.get(i).getTyre().getCliet().setPurpose(test.get(i).getTyre().getCliet().getPurpose().replaceAll("\"","%22"));
            test.get(i).getTyre().getCliet().setRemark(test.get(i).getTyre().getCliet().getRemark().replaceAll("\"","%22"));
        }
        int a=2;
        modelAndView.addObject("testList", cloneTestList);
        modelAndView.addObject("A",a);
        modelAndView.addObject("testListB",JSONArray.toJSONString(test));
//
        modelAndView.setViewName("ShiYanchaxun");
        return modelAndView;

    }

    //SP实验室完成和取消数据导出
    @RequestMapping("/shujudaochuSPWanCheng")
    public  ResponseEntity<byte[]> shujudaochuSPWanCheng(String testList)throws Exception{
//      System.out.println(testList);
        testList = new String(testList.replaceAll("\n","\\\\n"));
        List<Test> all = JSON.parseObject(testList, new TypeReference<List<Test>>(){});
//        System.out.println("aaaaaaaaa");
//        System.out.println(all);
        return  PoiUtilsCha.exportUser2ExcelCha(all);
//        return null;
    }



    //    //    委托查询  筛选
    @RequestMapping("/findByWeiTuoB")
    public ModelAndView findByWeiTuoB(
//            @RequestParam(name = "page", required = true, defaultValue = "1") int page,
//            @RequestParam(name = "size", required = true, defaultValue = "20") int size,
            @RequestParam(name = "startWT", required = false) String startWT,
            @RequestParam(name = "endWT", required = false) String endWT,
            @RequestParam(name = "FruitA", required = true) String FruitA,
            @RequestParam(name = "zhuangtaiA", required = true) int zhuangtaiA) throws Exception {
        List<TestClietTyre> listAAA = new ArrayList<>();
        List<String> FruitAA = new ArrayList<>();
        String[] fruit = FruitA.split(",");
        for (int i = 0; i < fruit.length; i++) {
            FruitAA.add(fruit[i]);
        }
        if (zhuangtaiA == 2) {
//          委托方  查询  未完成查询
            listAAA = iTestService.findByWeiTuoWei(FruitAA);

        } else if (zhuangtaiA == 1) {
            //          委托方  查询  完成查询
            listAAA = iTestService.findByWeiTuoWan(startWT, endWT, FruitAA);
        } else {
            //          委托方  查询  取消查询
            listAAA = iTestService.findByWeiTuoQu(startWT, endWT, FruitAA);
        }
        modelAndView.addObject("listAAA", listAAA);
        modelAndView.setViewName("WeiTuoChaXun");
        return modelAndView;
    }


    /******************************************/

    public void updateOrderAfterOrgOrders(List<String> orgOrders,String site){
        Collections.sort(orgOrders, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if((Integer.parseInt(o1)-Integer.parseInt(o2))>0){
                    return -1;
                }else if ((Integer.parseInt(o1)-Integer.parseInt(o2))<0){
                    return 1;
                }
                return 0;
            }
        });
        switch (site){
            case "QA":
//                iTestService.updateOrderAfterOrgOrders(orgOrders);
                break;
            case "OD":
                iTestService.updateOrderAfterOrgOrdersOD(orgOrders);
                break;
            default:
                break;
        }
    }

    public Long getCostTimeByTestItem(String testItem){
        //暂定30min
        if(testItem.equals("run")){
            return (long)30*60*1000;
        }else{
            return (long)30*60*1000;
        }

    }


    public List formatArrayToList(String [] fromArray){
        List<String> returnList = new ArrayList<>();
        for(String eachItem:fromArray){
            returnList.add(eachItem);
        }
        return returnList;
    }
    /**outdoor**/
    @RequestMapping("/adjustOrderByUserOD")
    @ResponseBody
    public void adjustOrderByUserOD(String [] testInfoIdList,String [] purposeOrderList){
        List<String> testInfoIds = formatArrayToList(testInfoIdList);
        List<String> purposeOrders = formatArrayToList(purposeOrderList);
        List<ODAdjustOrderObj> odAdjustOrderObjList = new ArrayList<>();
        for(int i = 0 ; i < testInfoIds.size() ; i++){
            ODAdjustOrderObj odAdjustOrderObj = new ODAdjustOrderObj(testInfoIds.get(i),purposeOrders.get(i));
            odAdjustOrderObjList.add(odAdjustOrderObj);
        }
        iTestService.adjustOrderByUserOD(odAdjustOrderObjList);
    }

    @RequestMapping("/getAllSortInfoOD")
    @ResponseBody
    public List<ODTestInfo> getAllSortInfoOD(){
        return iTestService.getAllSortInfoOD();
    }

    @RequestMapping("/sortedSatrtTestOD")
    @ResponseBody
    public void sortedSatrtTestOD(String []testInfoIdList){
        List<String> testInfoIds = formatArrayToList(testInfoIdList);
        iTestService.sortedSatrtTestOD(testInfoIds);
    }

    @RequestMapping("/sortedFinishTestOD")
    @ResponseBody
    public void sortedFinishTestOD(String [] testInfoIdList,String []orgOrderList){
        List<String> testInfoIds = formatArrayToList(testInfoIdList);
        iTestService.sortedFinishTestOD(testInfoIds);
        List<String> orgOrders = formatArrayToList(orgOrderList);
        updateOrderAfterOrgOrders(orgOrders,"OD");
    }

    @RequestMapping("/sortedTestCancelOD")
    @ResponseBody
    public void sortedTestCancelOD(String [] testInfoIdList,String []orgOrderList){
        unSortTestCancelOD(testInfoIdList);
        List<String> orgOrders = formatArrayToList(orgOrderList);
        updateOrderAfterOrgOrders(orgOrders,"OD");
    }

    @RequestMapping("/unSortTestCancelOD")
    @ResponseBody
    public void unSortTestCancelOD(String [] testInfoIdList){
        List<String> testInfoIds = new ArrayList<>();
        for(String eachId:testInfoIdList){
            testInfoIds.add(eachId);
        }
        iTestService.unSortTestCancelOD(testInfoIds);
    }

    @RequestMapping("/sortedToUnSortOD")
    @ResponseBody
    public void sortedToUnSortOD(String [] testInfoIdList,String []orgOrderList){
        List<String> testInfoIds = formatArrayToList(testInfoIdList);
        iTestService.sortedToUnSortOD(testInfoIds);
        List<String> orgOrders = formatArrayToList(orgOrderList);
        updateOrderAfterOrgOrders(orgOrders,"OD");
    }

    @RequestMapping("/unSortToSortByPurposeOrderOD")
    @ResponseBody
    public void unSortToSortByPurposeOrderOD(int purposeOrder, String []operationList){
        List<String> testInfoIds = formatArrayToList(operationList);
        int operateNum = testInfoIds.size();
        iTestService.unSortToSortByPurposeOrderOD(purposeOrder,testInfoIds,operateNum);
    }

    @RequestMapping("/sortedReSortByOrderOD")
    @ResponseBody
    public void sortedReSortByOrderOD(int purposeOrder, String []operationList, String []orgOrderList){
        List<String> testInfoIds = formatArrayToList(operationList);
        int operateNum = testInfoIds.size();
        List<String> orgOrders = formatArrayToList(orgOrderList);
        iTestService.sortedReSortByOrderOD(purposeOrder,testInfoIds,operateNum,orgOrders);
    }

    @RequestMapping("/unSortToSortByOriginalOrderOD")
    @ResponseBody
    public void unSortToSortByOriginalOrderOD(String [] testInfoIdList) throws ParseException {
        List<String> testInfoIds = new ArrayList<>();
        for(String eachId:testInfoIdList){
            testInfoIds.add(eachId);
        }
        int maxOrder = 0;
        try{
            maxOrder = iTestService.getMaxOrderOD();
        }catch(Exception e){
            maxOrder = 0;
        }
        iTestService.unSortToSortByOriginalOrderOD(maxOrder,testInfoIds);
    }

    @RequestMapping("/getCountByStateOD")
    @ResponseBody
    public int getCountByStateOD(String [] stateslist){
        List<String> states = new ArrayList<>();
        for(String state:stateslist){
            states.add(state);
        }
        return iTestService.getUnSortCountOD(states);
    }

    @RequestMapping("/getOnePageDataOD")
    @ResponseBody
    public List<ODTestInfo> getOnePageDataOD(String [] statesList, int pageNum){
        List<String> states = new ArrayList<>();
        for(String state:statesList){
            states.add(state);
        }
        return iTestService.getOnePageDataOD(states,(pageNum-1)*10+1,pageNum*10);
    }

    @RequestMapping("/updateWorkTimeOD")
    @ResponseBody
    public void updateWorkTimeOD(String startTime,String endTime,String workTimeID){
        iTestService.updateWorkTimeOD(startTime,endTime,workTimeID);
    }

    @RequestMapping("/deleteUnFinishedWorkTimeOD")
    @ResponseBody
    public void deleteUnFinishedWorkTimeOD(int workTimeID){
        iTestService.deleteUnFinishedWorkTimeOD(workTimeID);
    }

    @RequestMapping("/getUnFinishedWorkTimeOD")
    @ResponseBody
    public List<ODWorkTime> getUnFinishedWorkTimeOD(String nowTime){
        return iTestService.getUnFinishedWorkTimeOD(nowTime);
    }

    @RequestMapping("/isTimeConflictOD")
    @ResponseBody
    public boolean isTimeConflictOD(String startTime,String endTime,String nowTime,String operateID) throws ParseException {
        List<ODWorkTime> list = iTestService.isTimeConflictOD(startTime,endTime,nowTime,operateID);
        return (list.size()<= 0);
    }

    @RequestMapping("/insertWorkTimeOD")
    @ResponseBody
    public boolean insertWorkTimeOD(String startTime,String endTime) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
        iTestService.insertWorkTimeOD(startDate,endDate);
        return true;
    }


}

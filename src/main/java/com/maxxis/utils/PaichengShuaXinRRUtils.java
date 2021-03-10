package com.maxxis.utils;

import com.maxxis.domain.Test;
import com.maxxis.domain.TimeTest;
import com.maxxis.domain.WorkTimeRR;
import com.maxxis.domain.WorkTimeSp;
import com.maxxis.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Component
public class PaichengShuaXinRRUtils {
    @Autowired
    private ITestService iTestService;

    private static PaichengShuaXinRRUtils paichengShuaXinRRUtils;

    @PostConstruct
    public void init() {
        paichengShuaXinRRUtils = this;
        paichengShuaXinRRUtils.iTestService = this.iTestService;
    }


    public static void paichengSP() throws Exception {
        //  查询RR已排并且没有再做的(按照number顺序)
        List<Test> all = paichengShuaXinRRUtils.iTestService.findTestByStateNoZuoRR();
//
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
//        System.out.println(list);
//        System.out.println(listStandard);
//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);
//            System.out.println(Standard);
            List<TimeTest> listDate = paichengShuaXinRRUtils.iTestService.findDataStandard(Standard, TestItem);

//      durationS.add(listDate.get(0).getDuration());
            durationS.add((long) listDate.get(0).getDuration());
        }
//        System.out.println(durationS);


//    获取设定的时间
        List<WorkTimeRR> works = paichengShuaXinRRUtils.iTestService.findworktimeRR();
//        System.out.println(works);

        for (int s = 0; s < works.size(); s++) {
            long q = works.get(s).getGotowork();
            long w = works.get(s).getGetoffwork();
            long[] e = new long[]{q, w};
            workTime.add(e);
//            System.out.println(q);
//            System.out.println(w);
        }
//        System.out.println(workTime);

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
        for (r = 0; r < durationS.size(); r++) {
            ttt = durationS.get(r);//ttt代表每一个试验需要的时间
            for (int i = 0; i < workTime.size(); i++) {
                f = workTime.get(i);
                if (f[0] < zeroA) {
                    continue;
                } else {
                    //        现在时间小于工时时间的话  直接取工时时间
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
                            paichengShuaXinRRUtils.iTestService.updateHSUExpectedDateUPRR(numberA, result1a);

//                        System.out.println("number" + list.get(r));
//                        System.out.println("时间" + (f[0]));
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
                            System.out.println("bbbbbbbbbbbbbb");
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            paichengShuaXinRRUtils.iTestService.updateHSUExpectedDateUPRR(numberA, result1a);
                            o++;
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (currentA > f[1]) {
                        System.out.println("ccccccccccccc");
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
        int spbhOne = paichengShuaXinRRUtils.iTestService.findByspbhOneRR();

        int number = numberMax + 2 + spbhOne;
//            System.out.println(number);
//        去数据库把没有循环到的已经排好的数据变成待排(SP)
        paichengShuaXinRRUtils.iTestService.updateSPnumberDaiPaiRR(number);


    }
}

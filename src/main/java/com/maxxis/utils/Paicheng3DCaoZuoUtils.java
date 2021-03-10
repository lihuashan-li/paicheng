package com.maxxis.utils;

import com.maxxis.domain.Test;
import com.maxxis.domain.TimeTest;
import com.maxxis.domain.WorkTime3D;
import com.maxxis.domain.WorkTimeBC;
import com.maxxis.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Component
public class Paicheng3DCaoZuoUtils {
    @Autowired
    private ITestService iTestService;

    private static Paicheng3DCaoZuoUtils paicheng3DCaoZuoUtils;

    @PostConstruct
    public void init() {
        paicheng3DCaoZuoUtils = this;
        paicheng3DCaoZuoUtils.iTestService = this.iTestService;
    }


    public static void paicheng3D(Date kaishishijian) throws Exception {
        //  查询3D已排并且没有开始的(按照number顺序)
        List<Test> all = paicheng3DCaoZuoUtils.iTestService.findTestByStateNoZuo3D();
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //       第一条时间变成时间戳
        String timeFormat = sdf.format(kaishishijian);
        long kaishishijian01 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeFormat).getTime() / 1000;

//        根据法规去表里查询实验时间
        String Standard = new String();
        String TestItem = new String();//实验项目
        for (int a = 0; a < listStandard.size(); a++) {
            Standard = listStandard.get(a);
            TestItem = listTestItem.get(a);
//            System.out.println(Standard);
            List<TimeTest> listDate = paicheng3DCaoZuoUtils.iTestService.findDataStandard(Standard, TestItem);

//      durationS.add(listDate.get(0).getDuration());
            durationS.add((long) listDate.get(0).getDuration());
        }

//    获取设定的时间
        List<WorkTime3D> works = paicheng3DCaoZuoUtils.iTestService.findworktime3D();
//        System.out.println(works);

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

        SimpleDateFormat result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long[] f = new long[10];

        //        最后一个没有循环的number
        int numberMax = 0;

        //   里面存的是已经循环的number
        List<Integer> list1 = new ArrayList<>();
        int r;
        int p = 0;
        for (r = 0; r < durationS.size(); r++) {
            ttt = durationS.get(r);//ttt代表每一个试验需要的时间
            for (int i = 0; i < workTime.size(); i++) {
                f = workTime.get(i);
                if (f[0] < zeroA) {
                    continue;
                }else {
                    if (f[0] <= kaishishijian01 && kaishishijian01 < f[1]) {
                        if (f[0] < f[1]) {
                            if (p == 0) {
                                f[0] = kaishishijian01;
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
                            paicheng3DCaoZuoUtils.iTestService.update3DExpectedDateUP(numberA, result1a);
                            break;
                        } else {
                            continue;

                        }
                    }
                    if (f[0]>kaishishijian01){
                        if (f[0] < f[1]) {
                            f[0] = (int) (f[0] + ttt);
//                  System.out.println(f[0]);
                            ExpectedDate = f[0] - ttt;
//                        System.out.println("我是循环里面的"+r);
                            list1.add(r);
                            numberA = list.get(r);
                            String result1a = result1.format((ExpectedDate * 1000));
//                        System.out.println("转换时间为：" + result1a);
                            paicheng3DCaoZuoUtils.iTestService.update3DExpectedDateUP(numberA, result1a);
                            break;
                        } else {
                            continue;

                        }
                    }
                }

            }
        }

        for (int j = 0; j < list1.size(); j++) {
            if (list1.get(j) > numberMax)   // 判断最大值
                numberMax = list1.get(j);
        }

        //        3D去查询数据库有几个已经开始不需要进行循环的
        int spbhOne = paicheng3DCaoZuoUtils.iTestService.findByspbhOne3D();

        int number = numberMax + 2 + spbhOne;

//        System.out.println(number);
//        去数据库把没有循环到的已经排好的数据变成待排(3D)
        paicheng3DCaoZuoUtils.iTestService.update3DDaiPai(number);


    }
}

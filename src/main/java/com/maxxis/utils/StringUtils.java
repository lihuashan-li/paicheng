package com.maxxis.utils;

import java.text.SimpleDateFormat;

public class StringUtils {
//时间戳转字符串时间类型
        public  static  String LongString(long l,String s){
            SimpleDateFormat result1 =  new SimpleDateFormat(s); //设置格式
            String timeText=result1.format(l);                                //获得带格式的字符串

            return timeText;

        }

}

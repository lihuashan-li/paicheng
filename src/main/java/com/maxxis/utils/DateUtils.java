package com.maxxis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public  static  String dateString(Date date,String www){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(www);
        String format = simpleDateFormat.format(date);
        return format;

    }
}

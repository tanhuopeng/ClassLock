package com.jci.classlock.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lin
 * @version 1.0
 * @date 16-2-24
 */
public class DateFormat {


    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    private static final SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatter3 = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat formatter4 = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat formatter5 = new SimpleDateFormat("mm:ss");
    

    public static String getFormatDate(Date date){
        return formatter.format(date);
    }
    
    public static String getFormatDate2(Date date){
        return formatter2.format(date);
    }

    public static String getFormatDate3(Date date){
        return formatter3.format(date);
    }

    public static String getFormatDate4(Date date){
        return formatter4.format(date);
    }

    public static String getFormatDate5(Date date){
        return formatter5.format(date);
    }

    /**
     * 获取格式：N月N号
     * @param date
     * @return
     */
    public static String getFriendlyDate(Date date){
        return date.getMonth()+1+"月"+date.getDate()+"日";
    }

    
    /**
     * 获取当前日期是星期几
     * @param date
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }
    
}

package com.yufeimen.application.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 默认日期格式
     */
    public static String DEFAULT_FORMAT = "yyyy-MM-dd";


    public static long getSubMillisecond(Date newData,Date oldDate){
        return newData.getTime()-oldDate.getTime();
    }

    public static long getSubDays(Date newDate,Date oldDate){
        return getSubMillisecond(newDate,oldDate)/(1000 * 60 * 60 * 24);
    }

    public static long getSubHours(Date newDate,Date oldDate){
       return (getSubMillisecond(newDate,oldDate)-getSubDays(newDate,oldDate)*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
    }

    public static long getSubMinute(Date newDate,Date oldDate){
        return(getSubMillisecond(newDate,oldDate)-getSubDays(newDate,oldDate)*(1000 * 60 * 60 * 24)-getSubHours(newDate,oldDate)*(1000* 60 * 60))/(1000* 60);
    }

    /**
     * 格式化日期
     * @param date 日期对象
     * @return String 日期字符串
     */
    public static String formatDate(Date date){
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
        String sDate = f.format(date);
        return sDate;
    }
    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }
    /**
     * 获取当年的最后一天
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }
    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 获取当年某月的第一天
     * @param date
     * @return
     */
    public static Date getMonthFirst(Date date){
        Calendar temp=Calendar.getInstance();
        temp.setTime(date);
        int month=temp.get(Calendar.MONTH);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getCurrYearLast());
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();

    }

    /**
     * 获取当年某月的第一天
     * @param date
     * @return
     */
    public static Date getMonthLast(Date date){

        Calendar temp=Calendar.getInstance();
        temp.setTime(date);
        int month=temp.get(Calendar.MONTH);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getCurrYearFirst());
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

}

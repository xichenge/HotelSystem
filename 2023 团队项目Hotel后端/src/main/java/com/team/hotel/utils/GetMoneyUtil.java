package com.team.hotel.utils;

import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GetMoneyUtil {
    public static Double getMoney(String startDate,String endDate,Double price){

        Integer i=0;
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date star = dft.parse(startDate);//开始时间
            Date endDay=dft.parse(endDate);//结束时间
            Date nextDay=star;
            while(nextDay.before(endDay)){//当明天不在结束时间之前是终止循环book
                Calendar cld = Calendar.getInstance();
                cld.setTime(star);
                cld.add(Calendar.DATE, 1);
                star = cld.getTime();
                //获得下一天日期字符串
                nextDay = star;
                i++;
            }
            System.out.println("相差天数为："+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price*i;

    }
    public static Integer getdays(String startDate,String endDate){
        Integer i=0;
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date star = dft.parse(startDate);//开始时间
            Date endDay=dft.parse(endDate);//结束时间
            Date nextDay=star;
            while(nextDay.before(endDay)){//当明天不在结束时间之前是终止循环
                Calendar cld = Calendar.getInstance();
                cld.setTime(star);
                cld.add(Calendar.DATE, 1);
                star = cld.getTime();
                //获得下一天日期字符串
                nextDay = star;
                i++;
            }
//            System.out.println("相差天数为："+i);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }
    public static List<String> getSevenDate() {

        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 7; i++) {

            Date date = DateUtils.addDays(new Date(), -i);
            String formatDate = sdf.format(date);
            dateList.add(formatDate);
        }
        return dateList;
    }



}
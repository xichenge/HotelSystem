package com.team.hotel.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransform {
    public Date transferString2Date(String s) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            //LOGGER.error("时间转换错误, string = {}", s, e);
        }
        return date;
    }
}

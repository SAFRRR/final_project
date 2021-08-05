package com.safronova.webproject.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHandler {
    private DateHandler(){}

    public static String prepareDate(int timeUnits, int type){
        String banDate;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.setTime(date);
        calendar.add(type, timeUnits);
        banDate = dateFormat.format(calendar.getTime());
        return banDate;
    }

    public static boolean isExpired(String expirationDateString){
        try{
            Date currentDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date expirationDate = formatter.parse(expirationDateString);
            return currentDate.compareTo(expirationDate) > 0;
        } catch (ParseException e) {
            return false;
        }
    }
}




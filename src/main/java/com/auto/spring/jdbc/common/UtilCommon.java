package com.auto.spring.jdbc.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UtilCommon {

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date stringToDate(String date,String format) throws ParseException {
    	//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") ;
    	
    	DateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }

   /* public static java.sql.Date stringToSqlDate(String date, String format) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(format);
        java.util.Date date1 = formatter.parse(date);
        return formatter.parse(date);
    }*/

    public static int DaysRemaining(){
        LocalDate today = LocalDate.now();
        Calendar cal = Calendar.getInstance();
        int daysinMonth = cal.getActualMaximum(Calendar.DATE);
        return today.getDayOfMonth()-daysinMonth;
    }


}

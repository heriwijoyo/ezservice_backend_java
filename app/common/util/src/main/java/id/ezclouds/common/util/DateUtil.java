/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DateUtil.java, v 0.1 2023‐12‐11 11:09 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class DateUtil {

    public static final String FORMAT_DATETIME_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    public static String getFormattedDate(Date date, String dateFormat) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        return dateFormatter.format(date);
    }

    public static Date parseFormattedDate(String formattedDate) {
        return parseFormattedDate(formattedDate, FORMAT_DATETIME_DEFAULT);
    }

    public static Date parseFormattedDate(String formattedDate, String format) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        Date date;
        try {
            date = dateFormatter.parse(formattedDate);
        } catch (Exception e) {
            date = new Date();
        }
        return date;
    }

    public static long getTimeNow() {
        return new Date().getTime();
    }

    public static String getTimeNowToString() {
        return String.valueOf(getTimeNow());
    }

    public static String getFormattedDate(Date date) {
        return getFormattedDate(date, FORMAT_DATETIME_DEFAULT);
    }

    public static String getCurrentFormattedDate() {
        return getFormattedDate(new Date());
    }

    public static String getFormattedDateFromDateTime(String dateTime) {
        Date date = parseFormattedDate(dateTime, FORMAT_DATETIME_DEFAULT);
        return getFormattedDate(date, FORMAT_DATE);
    }

    public static Date getDateAfterDays(Date originalDate, int daysAfter) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originalDate);
        calendar.add(Calendar.DATE, daysAfter);
        return calendar.getTime();
    }

    public static Date getDateAfterMins(Date originalDate, int minsAfter) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originalDate);
        calendar.add(Calendar.MINUTE, minsAfter);
        return calendar.getTime();
    }
}
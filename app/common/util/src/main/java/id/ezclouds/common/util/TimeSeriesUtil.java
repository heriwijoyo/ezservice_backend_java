/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.util;

import id.ezclouds.common.util.enums.BizTimePeriod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: TimeSeriesUtil.java, v 0.1 2024‐07‐29 5:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class TimeSeriesUtil {

    public static List<String> getTimeSeries(BizTimePeriod timePeriod, Date dateStart, Date dateEnd) {
        switch (timePeriod) {
            case DAILY:
                return getDailySeries(dateStart, dateEnd);
            default:
                return new ArrayList<>();
        }
    }

    public static List<String> getDailySeriesFrom(String fStartDate) {
        return getDailySeriesFrom(fStartDate, 0);
    }

    public static List<String> getDailySeriesFrom(String fStartDate, int offset) {
        Date startDate = DateUtil.parseFormattedDate(fStartDate, DateUtil.FORMAT_DATE);
        Date endDate = DateUtil.getDateAfterDays(new Date(), -offset);
        return getDailySeries(startDate, endDate);
    }

    public static List<String> getLastNDailySeries(int nDays) {
        return getLastNDailySeries(nDays, 0);
    }

    public static List<String> getLastNDailySeries(int nDays, int offset) {
        Date endDate = DateUtil.getDateAfterDays(new Date(), 1-offset);
        Date startDate = DateUtil.getDateAfterDays(endDate, -nDays);
        return getTimeSeries(BizTimePeriod.DAILY, startDate, endDate);
    }

    private static List<String> getDailySeries(Date oriStartDate, Date endDate) {
        List<String> series = new ArrayList<>();
        Date startDate = new Date(oriStartDate.getTime());
        while (startDate.before(endDate)) {
            series.add(DateUtil.getFormattedDate(startDate, DateUtil.FORMAT_DATE));
            startDate = DateUtil.getDateAfterDays(startDate, 1);
        }
        return series;
    }
}
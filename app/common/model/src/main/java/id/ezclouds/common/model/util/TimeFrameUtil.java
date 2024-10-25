/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

import id.ezclouds.common.model.biz.report.BizTimeFrame;
import id.ezclouds.common.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: TimeFrameUtil.java, v 0.1 2024‐10‐25 3:46 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class TimeFrameUtil {

    public static List<String> generateTimePeriods(BizTimeFrame timeFrame, int nPrevTimeFrame) {
        List<String> periods = new ArrayList<>();

        Date today = new Date();

        for (int i = nPrevTimeFrame; i >= 1; i--) {
            switch (timeFrame) {
                case DAILY:
                    Date prevDate = DateUtil.getDateAfterDays(today, -i);
                    periods.add(DateUtil.getFormattedDate(prevDate, DateUtil.FORMAT_DATE));
                    break;
            }
        }

        return periods;
    }
}
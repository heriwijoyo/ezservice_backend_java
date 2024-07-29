/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.report;

import id.ezclouds.common.facade.biz.BizReportService;
import id.ezclouds.common.model.report.BizMainReport;
import id.ezclouds.common.model.report.BizTimeSeriesData;
import id.ezclouds.common.model.report.BizTimeSeriesReport;
import id.ezclouds.common.util.TimeSeriesUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzBizReportService.java, v 0.1 2024‐07‐29 6:46 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzBizReportService implements BizReportService {

    @Override
    public BizMainReport getMainReport(String orgId) {
        BizMainReport mainReport = new BizMainReport();
        mainReport.setTotalMember(13638);
        mainReport.setTotalSubOrg(7);
        mainReport.setTotalTps(412);
        mainReport.setMemberToday(87);

        List<String> timeSeries = TimeSeriesUtil.getLastNDailySeries(5, 1);
        BizTimeSeriesReport timeSeriesReport = new BizTimeSeriesReport();
        timeSeriesReport.setTitle("Progress Harian per Komunitas");
        timeSeriesReport.setLabels(timeSeries);

        BizTimeSeriesData seriesDataA = new BizTimeSeriesData();
        seriesDataA.setName("Komunitas A");
        seriesDataA.setType("line");
        seriesDataA.setData(Arrays.asList(8,4,7,3,4));

        BizTimeSeriesData seriesDataB = new BizTimeSeriesData();
        seriesDataB.setName("Komunitas B");
        seriesDataB.setType("line");
        seriesDataB.setData(Arrays.asList(4,7,5,2,9));

        timeSeriesReport.getSeries().add(seriesDataA);
        timeSeriesReport.getSeries().add(seriesDataB);

        mainReport.setTimeSeriesReports(Collections.singletonList(timeSeriesReport));

        return mainReport;
    }
}
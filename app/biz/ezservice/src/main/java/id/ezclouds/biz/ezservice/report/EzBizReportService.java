/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.report;

import id.ezclouds.common.facade.biz.BizReportService;
import id.ezclouds.common.model.chart.BizApexChartConfig;
import id.ezclouds.common.model.constant.BizReportConstant;
import id.ezclouds.common.model.report.BizMainReport;
import id.ezclouds.common.model.report.BizTimeSeriesData;
import id.ezclouds.common.util.TimeSeriesUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

        mainReport
                .getTimeSeriesReportMap()
                .put(BizReportConstant.TS_SUB_ORG, getChart("Progress Harian per Komunitas", timeSeries));

        mainReport
                .getTimeSeriesReportMap()
                .put(BizReportConstant.TS_DISTRICT, getChart("Progress Harian per Kecamatan", timeSeries));

        return mainReport;
    }

    private BizApexChartConfig getChart(String title, List<String> labels) {
        BizApexChartConfig timeSeriesReport = new BizApexChartConfig();
        timeSeriesReport.setEzTitle(title);
        timeSeriesReport.setLabels(labels);

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
        return timeSeriesReport;
    }
}
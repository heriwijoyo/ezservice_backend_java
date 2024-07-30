/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.report;

import id.ezclouds.common.facade.biz.BizReportService;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.chart.BizApexChartConfig;
import id.ezclouds.common.model.constant.BizReportConstant;
import id.ezclouds.common.model.report.BizMainReport;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.common.model.report.BizTimeSeriesData;
import id.ezclouds.common.util.TimeSeriesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzBizReportService.java, v 0.1 2024‐07‐29 6:46 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzBizReportService implements BizReportService {

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Override
    public BizMainReport getMainReport(String orgId) {
        List<BizReportOverall> reportOveralls = bizReportOverallDAO
                .getAllReport(orgId);

        BizMainReport mainReport = new BizMainReport();
        mainReport.setTotalMember(getOverallCount(reportOveralls, BizReportOverallKey.TOTAL_MEMBER_UNION));
        mainReport.setTotalSubOrg(getOverallCount(reportOveralls, BizReportOverallKey.TOTAL_SUB_ORGANIZATION));
        mainReport.setTotalTps(getOverallCount(reportOveralls, BizReportOverallKey.TOTAL_TPS));
        mainReport.setMemberToday(getOverallCount(reportOveralls, BizReportOverallKey.MEMBER_TODAY));

        List<String> timeSeries = TimeSeriesUtil.getLastNDailySeries(5, 1);

        mainReport
                .getTimeSeriesReportMap()
                .put(BizReportConstant.TS_SUB_ORG, getChart("Progress Harian per Komunitas", timeSeries));

        mainReport
                .getTimeSeriesReportMap()
                .put(BizReportConstant.TS_DISTRICT, getChart("Progress Harian per Kecamatan", timeSeries));

        return mainReport;
    }

    private int getOverallCount(List<BizReportOverall> reportOveralls, BizReportOverallKey key) {
        for (BizReportOverall reportOverall : reportOveralls) {
            if (key.getCode().equals(reportOverall.getKeyId())) {
                return reportOverall.getCount();
            }
        }
        return 0;
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
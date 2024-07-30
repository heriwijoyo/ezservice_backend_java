/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.report;

import id.ezclouds.biz.ezservice.enums.BizReportByTime;
import id.ezclouds.common.facade.biz.BizReportService;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.facade.dal.report.BizReportTimeSeriesDAO;
import id.ezclouds.common.model.chart.BizApexChartConfig;
import id.ezclouds.common.model.constant.BizReportConstant;
import id.ezclouds.common.model.report.*;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.TimeSeriesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzBizReportService.java, v 0.1 2024‐07‐29 6:46 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzBizReportService implements BizReportService {

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Autowired
    private BizReportTimeSeriesDAO bizReportTimeSeriesDAO;

    @Override
    public BizMainReport getMainReport(String orgId) {
        List<BizReportOverall> reportOveralls = bizReportOverallDAO
                .getAllReport(orgId);

        BizMainReport mainReport = new BizMainReport();
        mainReport.setTotalMember(getOverallCount(reportOveralls, BizReportOverallKey.TOTAL_MEMBER_UNION));
        mainReport.setTotalSubOrg(getOverallCount(reportOveralls, BizReportOverallKey.TOTAL_SUB_ORGANIZATION));
        mainReport.setTotalTps(getOverallCount(reportOveralls, BizReportOverallKey.TOTAL_TPS));
        mainReport.setMemberToday(getOverallCount(reportOveralls, BizReportOverallKey.MEMBER_TODAY));


        List<String> timeFrames = TimeSeriesUtil.getLastNDailySeries(10, 1);
        List<BizReportTimeSeries> timeSeriesSubOrg = bizReportTimeSeriesDAO
                .getReports(orgId, BizReportByTime.DAILY_SUB_ORG_PERFORMANCE.getId(), timeFrames);
        List<BizReportTimeSeries> timeSeriesDistrict = bizReportTimeSeriesDAO
                .getReports(orgId, BizReportByTime.DAILY_AREA_PERFORMANCE.getId(), timeFrames);

        mainReport
                .getTimeSeriesReportMap()
                .put(BizReportConstant.TS_SUB_ORG, generateTimeSeriesChart("Progress Harian per Komunitas", timeSeriesSubOrg, timeFrames));

        mainReport
                .getTimeSeriesReportMap()
                .put(BizReportConstant.TS_DISTRICT, generateTimeSeriesChart("Progress Harian per Kecamatan", timeSeriesDistrict, timeFrames));

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

    private BizApexChartConfig generateTimeSeriesChart(String title, List<BizReportTimeSeries> data, List<String> timeFrames) {
        BizApexChartConfig apexChartConfig = new BizApexChartConfig();
        apexChartConfig.setEzTitle(title);
        apexChartConfig.setLabels(timeFrames);
        apexChartConfig
                .getSeries()
                .addAll(parseTimeSeriesData(timeFrames, data));
        return apexChartConfig;
    }

    private List<BizTimeSeriesData> parseTimeSeriesData(List<String> timeFrames, List<BizReportTimeSeries> reportTimeSeries) {
        List<BizTimeSeriesData> data = new ArrayList<>();

        for (BizReportTimeSeries timeSeries : reportTimeSeries) {
            String groupValue = timeSeries.getGroupValue();
            BizTimeSeriesData timeSeriesData = new BizTimeSeriesData();
            timeSeriesData.setName(groupValue);
            timeSeriesData.setType("line");
            timeSeriesData.setData(getTimeValues(timeFrames, reportTimeSeries, groupValue));
            data.add(timeSeriesData);
        }
        return data;
    }

    private List<Integer> getTimeValues(List<String> timeFrames, List<BizReportTimeSeries> reportTimeSeries, String groupValue) {
        List<Integer> timeValues = new ArrayList<>();
        for (String timeFrame : timeFrames) {
            int value = 0;
            for (BizReportTimeSeries timeSeries : reportTimeSeries) {
                if (StringUtil.equalsNotNull(groupValue, timeSeries.getGroupValue())
                        && StringUtil.equalsNotNull(timeFrame, timeSeries.getTimeFrame())) {
                    value = timeSeries.getTimeValue();
                }
            }
            timeValues.add(value);
        }
        return timeValues;
    }
}
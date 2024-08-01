/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.report;

import id.ezclouds.biz.ezservice.enums.BizReportByTime;
import id.ezclouds.common.facade.biz.BizReportService;
import id.ezclouds.common.facade.dal.report.BizReportByAreaDAO;
import id.ezclouds.common.facade.dal.report.BizReportBySubOrgDAO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private BizReportBySubOrgDAO bizReportBySubOrgDAO;

    @Autowired
    private BizReportByAreaDAO bizReportByAreaDAO;

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

        List<BizReportBySubOrg> reportBySubOrgs = bizReportBySubOrgDAO
                .getReportAllSource(orgId);
        mainReport
                .getBizReportBySubOrgs()
                .addAll(mergeAllSubOrg(reportBySubOrgs));

        List<BizReportByArea> reportByAreas = bizReportByAreaDAO
                .getReportDistrictAllSource(orgId);
        mainReport
                .getBizReportByAreas()
                .addAll(mergeAllSource(reportByAreas));

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

        for (String groupValue : distinctGroupValue(reportTimeSeries)) {
            List<Integer> timeValues = getTimeValues(timeFrames, reportTimeSeries, groupValue);
            if (isNotEmptyValue(timeValues)) {
                BizTimeSeriesData timeSeriesData = new BizTimeSeriesData();
                timeSeriesData.setName(groupValue);
                timeSeriesData.setType("line");
                timeSeriesData.setData(timeValues);
                data.add(timeSeriesData);
            }
        }
        return data;
    }

    private List<String> distinctGroupValue(List<BizReportTimeSeries> reportTimeSeries) {
        List<String> distinctValues = new ArrayList<>();
        for (BizReportTimeSeries timeSeries : reportTimeSeries) {
            if (!distinctValues.contains(timeSeries.getGroupValue())) {
                distinctValues.add(timeSeries.getGroupValue());
            }
        }
        return distinctValues;
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

    private boolean isNotEmptyValue(List<Integer> values) {
        int total = 0;
        for (Integer item : values) {
            total += item;
        }
        return total > 0;
    }

    private List<BizReportBySubOrg> mergeAllSubOrg(List<BizReportBySubOrg> origin) {
        Map<String, BizReportBySubOrg> reportMap = new HashMap<>();
        for (BizReportBySubOrg reportBySubOrg : origin) {
            String reportKey = reportBySubOrg.getSubOrgName();
            if (reportMap.get(reportKey) == null) {
                reportMap.put(reportKey, reportBySubOrg);
            } else {
                mergeSubOrgValue(reportMap.get(reportKey), reportBySubOrg);
            }
        }

        return new ArrayList<>(reportMap.values());
    }

    private void mergeSubOrgValue(BizReportBySubOrg origin, BizReportBySubOrg addition) {
        origin.addVoterTotal(addition.getVoterTotal());
        origin.addVoterStrong(addition.getVoterStrong());
        origin.addVoterLazy(addition.getVoterLazy());
        origin.addGenderMale(addition.getGenderMale());
        origin.addGenderFemale(addition.getGenderFemale());
        origin.addGenderOther(addition.getGenderOther());
    }

    private List<BizReportByArea> mergeAllSource(List<BizReportByArea> origin) {
        Map<String, BizReportByArea> reportMap = new HashMap<>();
        for (BizReportByArea reportByArea : origin) {
            String reportKey = reportByArea.getDistrictName();
            if (reportMap.get(reportKey) == null) {
                reportMap.put(reportKey, reportByArea);
            } else {
                mergeValue(reportMap.get(reportKey), reportByArea);
            }
        }

        return new ArrayList<>(reportMap.values());
    }

    private void mergeValue(BizReportByArea origin, BizReportByArea addition) {
        origin.addVoterTotal(addition.getVoterTotal());
        origin.addVoterStrong(addition.getVoterStrong());
        origin.addVoterLazy(addition.getVoterLazy());
        origin.addGenderMale(addition.getGenderMale());
        origin.addGenderFemale(addition.getGenderFemale());
        origin.addGenderOther(addition.getGenderOther());
    }
}
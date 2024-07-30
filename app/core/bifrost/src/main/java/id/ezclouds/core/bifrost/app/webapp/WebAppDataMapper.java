/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.common.model.constant.BizReportConstant;
import id.ezclouds.common.model.report.BizMainReport;
import id.ezclouds.common.model.report.BizTimeSeriesReport;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppDataMapper.java, v 0.1 2024‐07‐30 12:33 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebAppDataMapper {

    public static Map<String, String> getDataMap(BizMainReport mainReport) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put(BizReportConstant.TOTAL_SUB_ORG, ""+ mainReport.getTotalSubOrg());
        dataMap.put(BizReportConstant.TOTAL_MEMBER, ""+ mainReport.getTotalMember());
        dataMap.put(BizReportConstant.TOTAL_TPS, ""+ mainReport.getTotalTps());
        dataMap.put(BizReportConstant.MEMBER_TODAY, ""+ mainReport.getMemberToday());

        BizTimeSeriesReport subOrgTsReport = mainReport
                .getTimeSeriesReportMap()
                .get(BizReportConstant.TS_SUB_ORG);
        dataMap.put(BizReportConstant.REPORT_TS_TITLE_SUB_ORG, subOrgTsReport.getEzTitle());
        dataMap.put(BizReportConstant.TS_DATA_SUB_ORG, getJsonValue(subOrgTsReport));

        BizTimeSeriesReport districtTsReport = mainReport
                .getTimeSeriesReportMap()
                .get(BizReportConstant.TS_DISTRICT);
        dataMap.put(BizReportConstant.REPORT_TS_TITLE_DISTRICT, districtTsReport.getEzTitle());
        dataMap.put(BizReportConstant.TS_DATA_DISTRICT, getJsonValue(districtTsReport));

        return dataMap;
    }

    private static String getJsonValue(Object object) {
        String jsonValue = "";
        try {
            jsonValue = new ObjectMapper().writeValueAsString(object);
        } catch (Exception ignored) {}
        return jsonValue;
    }
}
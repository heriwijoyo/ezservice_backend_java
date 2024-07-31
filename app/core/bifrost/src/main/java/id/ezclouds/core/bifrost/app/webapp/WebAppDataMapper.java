/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.common.model.constant.BizReportConstant;
import id.ezclouds.common.model.report.BizMainReport;
import id.ezclouds.common.model.report.BizReportByArea;
import id.ezclouds.common.model.report.BizReportBySubOrg;
import id.ezclouds.common.model.report.BizTimeSeriesReport;
import id.ezclouds.common.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppDataMapper.java, v 0.1 2024‐07‐30 12:33 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebAppDataMapper {

    public static Map<String, String> getDataMap(BizMainReport mainReport) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put(BizReportConstant.TOTAL_SUB_ORG, StringUtil.thousandFormat(mainReport.getTotalSubOrg()));
        dataMap.put(BizReportConstant.TOTAL_MEMBER, StringUtil.thousandFormat(mainReport.getTotalMember()));
        dataMap.put(BizReportConstant.TOTAL_TPS, StringUtil.thousandFormat(mainReport.getTotalTps()));
        dataMap.put(BizReportConstant.MEMBER_TODAY, StringUtil.thousandFormat(mainReport.getMemberToday()));

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

        dataMap.put(BizReportConstant.RECAP_SUB_ORG, parseRowSubOrgHTML(mainReport.getBizReportBySubOrgs()));
        dataMap.put(BizReportConstant.RECAP_DISTRICT, parseRowDistrictHTML(mainReport.getBizReportByAreas()));

        return dataMap;
    }

    private static String getJsonValue(Object object) {
        String jsonValue = "";
        try {
            jsonValue = new ObjectMapper().writeValueAsString(object);
        } catch (Exception ignored) {}
        return jsonValue;
    }

    private static String parseRowSubOrgHTML(List<BizReportBySubOrg> reports) {
        StringBuilder sb = new StringBuilder();
        int number = 1;
        for (BizReportBySubOrg reportByArea : reports) {
            sb.append("<tr>");
            sb.append("<td>");
            sb.append(number);
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getSubOrgName());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getVoterTotal());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getVoterStrong());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getVoterLazy());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getGenderMale());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getGenderFemale());
            sb.append("</td>");

            sb.append("</tr>");

            number++;
        }
        return sb.toString();
    }

    private static String parseRowDistrictHTML(List<BizReportByArea> reports) {
        StringBuilder sb = new StringBuilder();
        int number = 1;
        for (BizReportByArea reportByArea : reports) {
            sb.append("<tr>");
            sb.append("<td>");
            sb.append(number);
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getDistrictName());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getVoterTotal());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getVoterStrong());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getVoterLazy());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getGenderMale());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(reportByArea.getGenderFemale());
            sb.append("</td>");

            sb.append("<td>");
            sb.append(StringUtil.defaultIfNull(reportByArea.getTpsData()));
            sb.append("</td>");

            sb.append("</tr>");

            number++;
        }
        return sb.toString();
    }
}
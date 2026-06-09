/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMainReport.java, v 0.1 2024‐07‐29 3:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class BizMainReport {

    private int totalSubOrg;
    private int totalMember;
    private int totalTps;
    private int memberToday = 0;

    private final Map<String, BizTimeSeriesReport> timeSeriesReportMap = new HashMap<>();

    private final List<BizReportBySubOrg> bizReportBySubOrgs = new ArrayList<>();
    private final List<BizReportByArea> bizReportByAreas = new ArrayList<>();

    public int getTotalSubOrg() {
        return totalSubOrg;
    }

    public void setTotalSubOrg(int totalSubOrg) {
        this.totalSubOrg = totalSubOrg;
    }

    public int getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(int totalMember) {
        this.totalMember = totalMember;
    }

    public int getTotalTps() {
        return totalTps;
    }

    public void setTotalTps(int totalTps) {
        this.totalTps = totalTps;
    }

    public int getMemberToday() {
        return memberToday;
    }

    public void setMemberToday(int memberToday) {
        this.memberToday = memberToday;
    }

    public Map<String, BizTimeSeriesReport> getTimeSeriesReportMap() {
        return timeSeriesReportMap;
    }

    public List<BizReportBySubOrg> getBizReportBySubOrgs() {
        return bizReportBySubOrgs;
    }

    public List<BizReportByArea> getBizReportByAreas() {
        return bizReportByAreas;
    }
}
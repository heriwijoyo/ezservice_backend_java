/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.converter;

import id.ezclouds.common.model.report.BizReportByArea;
import id.ezclouds.common.model.report.BizReportBySubOrg;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportTimeSeries;
import id.ezclouds.core.dal.report.dataobject.CoreReportByAreaDO;
import id.ezclouds.core.dal.report.dataobject.CoreReportBySubOrgDO;
import id.ezclouds.core.dal.report.dataobject.CoreReportOverallDO;
import id.ezclouds.core.dal.report.dataobject.CoreReportTimeSeriesDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportConverter.java, v 0.1 2024‐07‐28 8:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportConverter {

    public static BizReportOverall convert(CoreReportOverallDO modelDO) {
        if (modelDO == null) { return null; }
        BizReportOverall reportOverall = new BizReportOverall();
        reportOverall.setOrgId(modelDO.getOrgId());
        reportOverall.setKeyId(modelDO.getKeyId());
        reportOverall.setCount(modelDO.getCount());
        reportOverall.setUpdatedTime(modelDO.getUpdatedTime());
        reportOverall.setId(modelDO.getId());
        return reportOverall;
    }

    public static BizReportTimeSeries convert(CoreReportTimeSeriesDO modelDO) {
        if (modelDO == null) { return null; }
        BizReportTimeSeries reportTimeSeries = new BizReportTimeSeries();
        reportTimeSeries.setId(modelDO.getId());
        reportTimeSeries.setOrgId(modelDO.getOrgId());
        reportTimeSeries.setReportId(modelDO.getReportId());
        reportTimeSeries.setGroupValue(modelDO.getGroupValue());
        reportTimeSeries.setTimeFrame(modelDO.getTimeFrame());
        reportTimeSeries.setTimeValue((int)modelDO.getTimeValue());
        return reportTimeSeries;
    }

    public static BizReportByArea convert(CoreReportByAreaDO modelDO) {
        if (modelDO == null) { return null; }
        BizReportByArea reportByArea = new BizReportByArea();
        reportByArea.setOrgId(modelDO.getOrgId());
        reportByArea.setSource(modelDO.getSource());
        reportByArea.setDistrictName(modelDO.getDistrictName());
        reportByArea.setVillageName(modelDO.getVillageName());
        reportByArea.setVoterTotal(modelDO.getVoterTotal());
        reportByArea.setVoterStrong(modelDO.getVoterStrong());
        reportByArea.setVoterLazy(modelDO.getVoterLazy());
        reportByArea.setGenderMale(modelDO.getGenderMale());
        reportByArea.setGenderFemale(modelDO.getGenderFemale());
        reportByArea.setGenderOther(modelDO.getGenderOther());
        reportByArea.setTpsData(modelDO.getTpsData());
        return reportByArea;
    }

    public static BizReportBySubOrg convert(CoreReportBySubOrgDO modelDO) {
        if (modelDO == null) { return null; }
        BizReportBySubOrg reportBySubOrg = new BizReportBySubOrg();
        reportBySubOrg.setOrgId(modelDO.getOrgId());
        reportBySubOrg.setSource(modelDO.getSource());
        reportBySubOrg.setSubOrgName(modelDO.getSubOrgName());
        reportBySubOrg.setVoterTotal(modelDO.getVoterTotal());
        reportBySubOrg.setVoterStrong(modelDO.getVoterStrong());
        reportBySubOrg.setVoterLazy(modelDO.getVoterLazy());
        reportBySubOrg.setGenderMale(modelDO.getGenderMale());
        reportBySubOrg.setGenderFemale(modelDO.getGenderFemale());
        reportBySubOrg.setGenderOther(modelDO.getGenderOther());
        return reportBySubOrg;
    }
}
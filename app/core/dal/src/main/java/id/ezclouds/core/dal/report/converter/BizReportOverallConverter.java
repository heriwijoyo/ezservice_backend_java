/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.converter;

import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportTimeSeries;
import id.ezclouds.core.dal.report.dataobject.CoreReportOverallDO;
import id.ezclouds.core.dal.report.dataobject.CoreReportTimeSeriesDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportOverallConverter.java, v 0.1 2024‐07‐28 8:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportOverallConverter {

    public static CoreReportOverallDO convert(BizReportOverall model) {
        CoreReportOverallDO dataObject = new CoreReportOverallDO();
        dataObject.setId(model.getId());
        dataObject.setOrgId(model.getOrgId());
        dataObject.setKeyId(model.getKeyId());
        dataObject.setCount(model.getCount());
        dataObject.setCreatedTime(model.getCreatedTime());
        return dataObject;
    }

    public static BizReportOverall convert(CoreReportOverallDO modelDO) {
        if (modelDO == null) { return null; }
        BizReportOverall reportOverall = new BizReportOverall();
        reportOverall.setOrgId(modelDO.getOrgId());
        reportOverall.setKeyId(modelDO.getKeyId());
        reportOverall.setCount(modelDO.getCount());
        reportOverall.setCreatedTime(modelDO.getCreatedTime());
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
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.converter;

import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.core.dal.report.dataobject.CoreReportOverallDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportOverallConverter.java, v 0.1 2024‐09‐09 11:19 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportOverallConverter extends CommonDOModelConverter<CoreReportOverallDO, BizReportOverall> {

    @Override
    protected BizReportOverall safeConvertQuery(CoreReportOverallDO dataObject) {
        BizReportOverall reportOverall = new BizReportOverall();
        reportOverall.setId(dataObject.getId());
        reportOverall.setOrgId(dataObject.getOrgId());
        reportOverall.setKeyId(dataObject.getKeyId());
        reportOverall.setCount(dataObject.getCount());
        reportOverall.setUpdatedTime(dataObject.getUpdatedTime());
        return reportOverall;
    }

    @Override
    protected CoreReportOverallDO safeConvertStore(BizReportOverall model) {
        CoreReportOverallDO reportOverallDO = new CoreReportOverallDO();
        reportOverallDO.setId(model.getId());
        reportOverallDO.setOrgId(model.getOrgId());
        reportOverallDO.setKeyId(model.getKeyId());
        reportOverallDO.setCount(model.getCount());
        reportOverallDO.setUpdatedTime(model.getUpdatedTime());
        return reportOverallDO;
    }
}
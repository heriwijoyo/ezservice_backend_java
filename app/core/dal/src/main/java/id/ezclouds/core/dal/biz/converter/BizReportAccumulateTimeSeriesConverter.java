/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.report.BizReportAccumulateTimeSeries;
import id.ezclouds.common.model.biz.report.BizTimeSeriesScene;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.BizReportAccumulateTimeSeriesDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateTimeSeriesConverter.java, v 0.1 2024‐10‐25 8:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportAccumulateTimeSeriesConverter extends CommonDOModelConverter<BizReportAccumulateTimeSeriesDO, BizReportAccumulateTimeSeries> {

    @Override
    protected BizReportAccumulateTimeSeries safeConvertQuery(BizReportAccumulateTimeSeriesDO dataObject) {
        BizReportAccumulateTimeSeries timeSeries = new BizReportAccumulateTimeSeries();
        timeSeries.setReportTimeSeriesId(dataObject.getReportTimeSeriesId());
        timeSeries.setOrgId(dataObject.getOrgId());
        timeSeries.setScene(BizTimeSeriesScene.getByCode(dataObject.getScene()));
        timeSeries.setSceneId(dataObject.getSceneId());
        timeSeries.setSceneLabel(dataObject.getSceneLabel());
        timeSeries.setTimeFrame(dataObject.getTimeFrame());
        timeSeries.setAccumulateCount(dataObject.getAccumulateCount());
        timeSeries.setModifiedTime(dataObject.getModifiedTime());
        return timeSeries;
    }

    @Override
    protected BizReportAccumulateTimeSeriesDO safeConvertStore(BizReportAccumulateTimeSeries model) {
        BizReportAccumulateTimeSeriesDO timeSeriesDO = new BizReportAccumulateTimeSeriesDO();
        timeSeriesDO.setReportTimeSeriesId(model.getReportTimeSeriesId());
        timeSeriesDO.setOrgId(model.getOrgId());
        timeSeriesDO.setScene(model.getScene().getCode());
        timeSeriesDO.setSceneId(model.getSceneId());
        timeSeriesDO.setSceneLabel(model.getSceneLabel());
        timeSeriesDO.setTimeFrame(model.getTimeFrame());
        timeSeriesDO.setAccumulateCount(model.getAccumulateCount());
        timeSeriesDO.setModifiedTime(model.getModifiedTime());
        return timeSeriesDO;
    }
}
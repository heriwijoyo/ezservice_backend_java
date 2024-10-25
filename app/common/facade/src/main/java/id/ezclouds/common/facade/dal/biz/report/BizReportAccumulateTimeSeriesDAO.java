/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz.report;

import id.ezclouds.common.model.biz.report.BizReportAccumulateTimeSeries;
import id.ezclouds.common.model.biz.report.BizTimeSeriesScene;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateTimeSeriesDAO.java, v 0.1 2024‐10‐25 8:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportAccumulateTimeSeriesDAO {

    BizReportAccumulateTimeSeries getAndLock(String orgId, BizTimeSeriesScene scene, String sceneId);

    void store(BizReportAccumulateTimeSeries accumulateTimeSeries);
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.report;

import id.ezclouds.common.model.biz.report.BizTimeSeriesScene;
import id.ezclouds.common.model.biz.report.chart.BizCommonChart;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateTimeSeriesService.java, v 0.1 2024‐10‐25 2:35 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportAccumulateTimeSeriesService {

    BizCommonChart fetchTimeSeriesChart(String orgId, BizTimeSeriesScene scene, int nPrevTimeFrame);
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.report;

import id.ezclouds.common.model.biz.report.chart.BizPieChart;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportDemographicService.java, v 0.1 2024‐10‐14 2:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportDemographicService {

    List<BizPieChart> fetchDemographicCharts(String orgId);
}
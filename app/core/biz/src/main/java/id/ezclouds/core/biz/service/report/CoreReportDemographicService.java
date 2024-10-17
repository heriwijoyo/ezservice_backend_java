/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report;

import id.ezclouds.common.facade.biz.report.BizReportDemographicService;
import id.ezclouds.common.model.biz.report.chart.BizPieChart;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportDemographicService.java, v 0.1 2024‐10‐14 2:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreReportDemographicService implements BizReportDemographicService {

    @Override
    public List<BizPieChart> fetchDemographicCharts(String orgId) {
        return null;
    }
}
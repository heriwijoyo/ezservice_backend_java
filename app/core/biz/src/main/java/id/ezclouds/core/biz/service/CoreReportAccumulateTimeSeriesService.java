/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service;

import id.ezclouds.common.facade.biz.report.BizReportAccumulateTimeSeriesService;
import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateTimeSeriesDAO;
import id.ezclouds.common.model.biz.report.BizReportAccumulateTimeSeries;
import id.ezclouds.common.model.biz.report.BizTimeSeriesScene;
import id.ezclouds.common.model.biz.report.chart.BizCommonChart;
import id.ezclouds.common.model.util.TimeFrameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportAccumulateTimeSeriesService.java, v 0.1 2024‐10‐25 2:38 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreReportAccumulateTimeSeriesService implements BizReportAccumulateTimeSeriesService {

    @Autowired
    private BizReportAccumulateTimeSeriesDAO bizReportAccumulateTimeSeriesDAO;

    @Override
    public BizCommonChart fetchTimeSeriesChart(String orgId, BizTimeSeriesScene scene, int nPrevTimeFrame) {
        BizCommonChart bizCommonChart = new BizCommonChart();
        bizCommonChart.setScene(scene.getCode());
        bizCommonChart.getOption().defaultLineChart();

        List<String> timeFrames = TimeFrameUtil.generateTimePeriods(scene.getTimeFrame(), nPrevTimeFrame);
        List<BizReportAccumulateTimeSeries> accumulateTimeSeries = bizReportAccumulateTimeSeriesDAO
                .getNPrevTimeFrame(orgId, scene, nPrevTimeFrame);
        bizCommonChart.getOption().setTimeSeriesData(timeFrames, accumulateTimeSeries);

        return bizCommonChart;
    }
}
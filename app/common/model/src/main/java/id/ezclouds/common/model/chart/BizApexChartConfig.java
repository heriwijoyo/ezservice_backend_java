/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.chart;

import id.ezclouds.common.model.report.BizTimeSeriesReport;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizApexChartConfig.java, v 0.1 2024‐07‐30 9:56 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizApexChartConfig extends BizTimeSeriesReport {

    private Map<String, Object> chart;
    private Map<String, Object> stroke;
    private Map<String, Object> legend;
    private Map<String, Object> yaxis;

    public BizApexChartConfig() {
        chart = new HashMap<>();
        stroke = new HashMap<>();
        legend = new HashMap<>();
        yaxis = new HashMap<>();
        initValue();
    }

    private void initValue() {
        chart.put("height", 300);
        chart.put("type", "line");

        stroke.put("width", Arrays.asList(2,2,2,2,2));
        legend.put("show", true);
        yaxis.put("min", 0);
    }

    public Map<String, Object> getChart() {
        return chart;
    }

    public Map<String, Object> getStroke() {
        return stroke;
    }

    public Map<String, Object> getLegend() {
        return legend;
    }

    public Map<String, Object> getYaxis() {
        return yaxis;
    }
}
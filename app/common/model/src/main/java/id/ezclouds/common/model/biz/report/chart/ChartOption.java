/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report.chart;

import id.ezclouds.common.model.biz.report.BizReportAccumulateTimeSeries;

import java.util.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ChartOption.java, v 0.1 2024‐10‐25 4:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ChartOption {

    private List<ChartDataSeries> series = new ArrayList<>();
    private ChartXAxis xaxis = new ChartXAxis();
    private Map<String, Object> chart = new HashMap<>();
    private Map<String, Object> stroke = new HashMap<>();
    private Map<String, Object> legend = new HashMap<>();
    private Map<String, Object> yaxis = new HashMap<>();

    public void defaultLineChart() {
        chart.put("height", 300);
        chart.put("type", "line");

        stroke.put("width", Arrays.asList(2,2,2,2,2));
        stroke.put("curve", "smooth");
        legend.put("show", true);
        yaxis.put("min", 0);
    }

    public void setTimeSeriesData(List<String> timeFrames, List<BizReportAccumulateTimeSeries> accumulateTimeSeries) {
        getXaxis().getCategories().addAll(timeFrames);

        List<String> allSceneLabels = new ArrayList<>();
        Map<String, Map<String, Integer>> seriesDataMap = new HashMap<>();
        for (BizReportAccumulateTimeSeries accTimeSeries : accumulateTimeSeries) {
            if (!allSceneLabels.contains(accTimeSeries.getSceneLabel())) {
                allSceneLabels.add(accTimeSeries.getSceneLabel());
            }

            if (seriesDataMap.get(accTimeSeries.getSceneLabel()) == null) {
                seriesDataMap.put(accTimeSeries.getSceneLabel(), new HashMap<>());

                seriesDataMap.get(accTimeSeries.getSceneLabel()).put(accTimeSeries.getTimeFrame(), accTimeSeries.getAccumulateCount());
            }
        }

        for (String sceneLabel : allSceneLabels) {
            ChartDataSeries chartDataSeries = new ChartDataSeries();
            chartDataSeries.setName(sceneLabel);
            chartDataSeries.getData().addAll(parseChartDataSeries(timeFrames, seriesDataMap, sceneLabel));
            getSeries().add(chartDataSeries);
        }
    }

    private List<Integer> parseChartDataSeries(List<String> timeFrames, Map<String, Map<String, Integer>> seriesDataMap, String sceneLabel) {
        List<Integer> result = new ArrayList<>();
        for (String timeFrame: timeFrames) {
            if (seriesDataMap.get(sceneLabel) != null && seriesDataMap.get(sceneLabel).get(timeFrame) != null) {
                result.add(seriesDataMap.get(sceneLabel).get(timeFrame));
            }
            else {
                result.add(0);
            }
        }
        return result;
    }

    public List<ChartDataSeries> getSeries() {
        return series;
    }

    public ChartXAxis getXaxis() {
        return xaxis;
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
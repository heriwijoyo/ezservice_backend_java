/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.report;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizTimeSeriesReport.java, v 0.1 2024‐07‐29 3:53 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizTimeSeriesReport {

    private String ezTitle;
    private final List<BizTimeSeriesData> series = new ArrayList<>();
    private List<String> labels;

    public String getEzTitle() {
        return ezTitle;
    }

    public void setEzTitle(String ezTitle) {
        this.ezTitle = ezTitle;
    }

    public List<BizTimeSeriesData> getSeries() {
        return series;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
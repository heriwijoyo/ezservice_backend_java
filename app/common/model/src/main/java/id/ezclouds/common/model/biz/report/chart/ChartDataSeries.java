/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ChartDataSeries.java, v 0.1 2024‐10‐25 4:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ChartDataSeries {

    private String name;
    private List<Integer> data = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getData() {
        return data;
    }
}
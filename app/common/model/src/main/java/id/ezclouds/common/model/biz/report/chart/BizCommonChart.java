/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report.chart;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCommonChart.java, v 0.1 2024‐10‐25 4:20 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizCommonChart {

    private String scene;
    private ChartOption option = new ChartOption();

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public ChartOption getOption() {
        return option;
    }
}
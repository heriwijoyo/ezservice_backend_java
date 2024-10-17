/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportArea.java, v 0.1 2024‐10‐14 4:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportArea {

    private String areaLevel;
    private List<BizReportAreaData> areaData;

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public List<BizReportAreaData> getAreaData() {
        return areaData;
    }

    public void setAreaData(List<BizReportAreaData> areaData) {
        this.areaData = areaData;
    }
}
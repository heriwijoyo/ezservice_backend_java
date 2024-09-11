/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.table;

import id.ezclouds.common.model.report.BizReportScene;
import id.ezclouds.common.model.report.BizReportSection;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportTable.java, v 0.1 2024‐09‐11 1:12 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportTable extends BizSmartTable {

    private String reportTableId;
    private String orgId;
    private BizReportSection reportSection;
    private BizReportScene reportScene;
    private String sceneId;

    public String getReportTableId() {
        return reportTableId;
    }

    public void setReportTableId(String reportTableId) {
        this.reportTableId = reportTableId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public BizReportSection getReportSection() {
        return reportSection;
    }

    public void setReportSection(BizReportSection reportSection) {
        this.reportSection = reportSection;
    }

    public BizReportScene getReportScene() {
        return reportScene;
    }

    public void setReportScene(BizReportScene reportScene) {
        this.reportScene = reportScene;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }
}
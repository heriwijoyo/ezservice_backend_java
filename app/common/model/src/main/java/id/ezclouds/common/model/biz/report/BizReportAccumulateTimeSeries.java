/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateTimeSeries.java, v 0.1 2024‐10‐25 8:33 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportAccumulateTimeSeries {

    private String reportTimeSeriesId;
    private String orgId;
    private BizTimeSeriesScene scene;
    private String sceneId;
    private String sceneLabel;
    private String timeFrame;
    private int accumulateCount;
    private String modifiedTime;

    public String getReportTimeSeriesId() {
        return reportTimeSeriesId;
    }

    public void setReportTimeSeriesId(String reportTimeSeriesId) {
        this.reportTimeSeriesId = reportTimeSeriesId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public BizTimeSeriesScene getScene() {
        return scene;
    }

    public void setScene(BizTimeSeriesScene scene) {
        this.scene = scene;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneLabel() {
        return sceneLabel;
    }

    public void setSceneLabel(String sceneLabel) {
        this.sceneLabel = sceneLabel;
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(String timeFrame) {
        this.timeFrame = timeFrame;
    }

    public int getAccumulateCount() {
        return accumulateCount;
    }

    public void setAccumulateCount(int accumulateCount) {
        this.accumulateCount = accumulateCount;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
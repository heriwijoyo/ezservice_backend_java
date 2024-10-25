/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateTimeSeriesDO.java, v 0.1 2024‐10‐25 8:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_report_accumulate_time_series")
public class BizReportAccumulateTimeSeriesDO {

    @Id
    @Column(name = "report_time_series_id")
    private String reportTimeSeriesId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "scene")
    private String scene;
    @Column(name = "scene_id")
    private String sceneId;
    @Column(name = "scene_label")
    private String sceneLabel;
    @Column(name = "time_frame")
    private String timeFrame;
    @Column(name = "accumulate_count")
    private int accumulateCount;
    @Column(name = "modified_time")
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

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
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
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SmartDataSource.java, v 0.1 2024‐09‐06 12:03 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SmartDataSource {

    private String sessionId;
    private String orgId;
    private String event;
    private String tableCode;
    private String tableScene;

    private boolean success;
    private List<SmartRowData> payload = new ArrayList<>();

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public String getTableScene() {
        return tableScene;
    }

    public void setTableScene(String tableScene) {
        this.tableScene = tableScene;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<SmartRowData> getPayload() {
        return payload;
    }

    public void setPayload(List<SmartRowData> payload) {
        this.payload = payload;
    }

    public SmartDataEvent getEvent() {
        return SmartDataEvent.getByCode(event);
    }

    public DataSourceTable getDataSourceTable() {
        return DataSourceTable.getByCodeAndScene(tableCode, tableScene);
    }
}
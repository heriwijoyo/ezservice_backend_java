/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request.admin;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CommonTableCreateRequest.java, v 0.1 2024‐08‐16 12:56 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class
CommonTableCreateRequest {

    private String orgId;
    private String code;
    private String title;
    private String columns;
    private String config;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
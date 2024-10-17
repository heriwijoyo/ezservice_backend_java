/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizUnique.java, v 0.1 2024‐08‐31 3:13 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizUnique {

    private String bizUniqueId;
    private String orgId;
    private String bizScenario;
    private String paramValue;
    private String createdTime;

    public String getBizUniqueId() {
        return bizUniqueId;
    }

    public void setBizUniqueId(String bizUniqueId) {
        this.bizUniqueId = bizUniqueId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getBizScenario() {
        return bizScenario;
    }

    public void setBizScenario(String bizScenario) {
        this.bizScenario = bizScenario;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
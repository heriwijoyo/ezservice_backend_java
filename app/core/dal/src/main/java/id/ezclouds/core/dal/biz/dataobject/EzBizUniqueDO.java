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
 * @version $Id: EzBizUniqueDO.java, v 0.1 2024‐08‐31 3:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_core_biz_unique")
public class EzBizUniqueDO {

    @Id
    @Column(name = "biz_unique_id")
    private String bizUniqueId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "biz_scenario")
    private String bizScenario;

    @Column(name = "param_value")
    private String paramValue;

    @Column(name = "created_time")
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
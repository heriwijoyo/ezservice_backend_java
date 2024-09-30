/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizValidationDO.java, v 0.1 2024‐10‐01 2:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_core_biz_validation")
public class CoreBizValidationDO {

    @Id
    @Column(name = "biz_validation_id")
    private String bizValidationId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "validation_scene")
    private String validationScene;

    @Column(name = "validation_rules")
    private String validationRules;

    @Column(name = "form_config")
    private String formConfig;

    @Column(name = "status")
    private Integer status;

    public String getBizValidationId() {
        return bizValidationId;
    }

    public void setBizValidationId(String bizValidationId) {
        this.bizValidationId = bizValidationId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getValidationScene() {
        return validationScene;
    }

    public void setValidationScene(String validationScene) {
        this.validationScene = validationScene;
    }

    public String getValidationRules() {
        return validationRules;
    }

    public void setValidationRules(String validationRules) {
        this.validationRules = validationRules;
    }

    public String getFormConfig() {
        return formConfig;
    }

    public void setFormConfig(String formConfig) {
        this.formConfig = formConfig;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
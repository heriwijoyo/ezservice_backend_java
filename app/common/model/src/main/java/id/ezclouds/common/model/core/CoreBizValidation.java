/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core;

import id.ezclouds.common.model.biz.BizValidationRule;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizValidation.java, v 0.1 2024‐10‐01 2:09 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreBizValidation {

    private String bizValidationId;
    private String orgId;
    private BizValidationScene validationScene;
    private List<BizValidationRule> validationRules;
    private String formConfig;
    private int status;

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

    public BizValidationScene getValidationScene() {
        return validationScene;
    }

    public void setValidationScene(BizValidationScene validationScene) {
        this.validationScene = validationScene;
    }

    public List<BizValidationRule> getValidationRules() {
        return validationRules;
    }

    public void setValidationRules(List<BizValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public String getFormConfig() {
        return formConfig;
    }

    public void setFormConfig(String formConfig) {
        this.formConfig = formConfig;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isActive() {
        return status == 1;
    }
}
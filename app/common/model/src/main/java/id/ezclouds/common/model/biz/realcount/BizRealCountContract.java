/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.realcount;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizRealCountContract.java, v 0.1 2024‐09‐19 1:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizRealCountContract {

    private String contractId;
    private String orgId;
    private String code;
    private String mapConfig;
    private String activeStartDate;
    private String activeEndDate;
    private int status;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

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

    public String getMapConfig() {
        return mapConfig;
    }

    public void setMapConfig(String mapConfig) {
        this.mapConfig = mapConfig;
    }

    public String getActiveStartDate() {
        return activeStartDate;
    }

    public void setActiveStartDate(String activeStartDate) {
        this.activeStartDate = activeStartDate;
    }

    public String getActiveEndDate() {
        return activeEndDate;
    }

    public void setActiveEndDate(String activeEndDate) {
        this.activeEndDate = activeEndDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.util.HashUtil;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAccumulateAreaExt.java, v 0.1 2024‐10‐03 1:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAccumulateAreaExt {

    private String accumulateAreaExtId;
    private String accumulateAreaId;
    private String orgId;
    private CoreAreaLevel areaLevel;
    private BizAccumulateKey accumulateKey;
    private String accumulateVariable;
    private int accumulateCount;
    private String modifiedTime;

    public void generateId() {
        String genId = HashUtil.createHash(accumulateAreaId, orgId, accumulateKey.getCode(), accumulateVariable);
        setAccumulateAreaExtId(genId);
    }

    public String getAccumulateAreaExtId() {
        return accumulateAreaExtId;
    }

    public void setAccumulateAreaExtId(String accumulateAreaExtId) {
        this.accumulateAreaExtId = accumulateAreaExtId;
    }

    public String getAccumulateAreaId() {
        return accumulateAreaId;
    }

    public void setAccumulateAreaId(String accumulateAreaId) {
        this.accumulateAreaId = accumulateAreaId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public CoreAreaLevel getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(CoreAreaLevel areaLevel) {
        this.areaLevel = areaLevel;
    }

    public BizAccumulateKey getAccumulateKey() {
        return accumulateKey;
    }

    public void setAccumulateKey(BizAccumulateKey accumulateKey) {
        this.accumulateKey = accumulateKey;
    }

    public String getAccumulateVariable() {
        return accumulateVariable;
    }

    public void setAccumulateVariable(String accumulateVariable) {
        this.accumulateVariable = accumulateVariable;
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
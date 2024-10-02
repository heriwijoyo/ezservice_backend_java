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
 * @version $Id: BizAccumulateAreaExtDO.java, v 0.1 2024‐10‐03 2:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_report_accumulate_area_ext")
public class BizAccumulateAreaExtDO {

    @Id
    @Column(name = "accumulate_area_ext_id")
    private String accumulateAreaExtId;

    @Column(name = "accumulate_area_id")
    private String accumulateAreaId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "area_level")
    private String areaLevel;

    @Column(name = "accumulate_key")
    private String accumulateKey;

    @Column(name = "accumulate_variable")
    private String accumulateVariable;

    @Column(name = "accumulate_count")
    private int accumulateCount;

    @Column(name = "modified_time")
    private String modifiedTime;

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

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getAccumulateKey() {
        return accumulateKey;
    }

    public void setAccumulateKey(String accumulateKey) {
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
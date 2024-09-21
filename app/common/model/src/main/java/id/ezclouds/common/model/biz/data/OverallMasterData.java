/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: OverallMasterData.java, v 0.1 2024‐09‐21 11:53 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class OverallMasterData {

    private String bizMasterId;
    private String overallKey;
    private String overallName;
    private Integer valueCount;

    public String getBizMasterId() {
        return bizMasterId;
    }

    public void setBizMasterId(String bizMasterId) {
        this.bizMasterId = bizMasterId;
    }

    public String getOverallKey() {
        return overallKey;
    }

    public void setOverallKey(String overallKey) {
        this.overallKey = overallKey;
    }

    public String getOverallName() {
        return overallName;
    }

    public void setOverallName(String overallName) {
        this.overallName = overallName;
    }

    public Integer getValueCount() {
        return valueCount;
    }

    public void setValueCount(Integer valueCount) {
        this.valueCount = valueCount;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMasterDataUpdateNumber.java, v 0.1 2024‐09‐20 3:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMasterDataUpdateNumber {

    private String bizMasterId;
    private String column;
    private Integer value;

    public BizMasterDataUpdateNumber(String bizMasterId, String column, int value) {
        this.bizMasterId = bizMasterId;
        this.column = column;
        this.value = value;
    }

    public String getBizMasterId() {
        return bizMasterId;
    }

    public String getColumn() {
        return column;
    }

    public Integer getValue() {
        return value;
    }
}
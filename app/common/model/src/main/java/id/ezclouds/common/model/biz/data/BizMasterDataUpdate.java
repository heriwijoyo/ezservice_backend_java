/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMasterDataUpdate.java, v 0.1 2024‐09‐20 3:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMasterDataUpdate {

    private String bizMasterId;
    private String column;
    private String value;

    public BizMasterDataUpdate(String bizMasterId, String column, String value) {
        this.bizMasterId = bizMasterId;
        this.column = column;
        this.value = value;
    }

    public BizMasterDataUpdate(String bizMasterId, String value) {
        this(bizMasterId, null, value);
    }

    public String getBizMasterId() {
        return bizMasterId;
    }

    public String getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }

    public int getIntValue() {
        return Integer.parseInt(value);
    }

    public boolean isValidNumber() {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
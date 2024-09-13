/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: TableSource.java, v 0.1 2024‐09‐13 10:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum TableSource {

    BIZ_TABLE_REPORT("bizTableReport")

    ;

    private final String tableId;

    TableSource(String tableId) {
        this.tableId = tableId;
    }

    public String getTableId() {
        return tableId;
    }
}
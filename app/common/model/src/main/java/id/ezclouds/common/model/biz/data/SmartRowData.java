/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SmartRowData.java, v 0.1 2024‐09‐06 11:26 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SmartRowData {

    private String rowId;
    private List<String> colData;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public List<String> getColData() {
        return colData;
    }

    public void setColData(List<String> colData) {
        this.colData = colData;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.table;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSmartTable.java, v 0.1 2024‐09‐11 11:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSmartTable {

    private String title;
    private TableConfig config;
    private List<TableColumn> columns;
    private List<List<String>> rowData;
    private String updatedTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TableConfig getConfig() {
        return config;
    }

    public void setConfig(TableConfig config) {
        this.config = config;
    }

    public List<TableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumn> columns) {
        this.columns = columns;
    }

    public List<List<String>> getRowData() {
        return rowData;
    }

    public void setRowData(List<List<String>> rowData) {
        this.rowData = rowData;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
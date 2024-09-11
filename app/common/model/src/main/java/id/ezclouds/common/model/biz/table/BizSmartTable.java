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
    private List<BizSmartTableColumn> columns;
    private String data;
    private String updatedTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BizSmartTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<BizSmartTableColumn> columns) {
        this.columns = columns;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
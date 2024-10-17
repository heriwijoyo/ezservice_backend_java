/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: TableConfig.java, v 0.1 2024‐09‐13 10:16 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class TableConfig {

    private String csvFileName;
    private boolean csvHeaders = false;
    private boolean search = false;
    private boolean columnSorting = false;
    private boolean includeHeadersOnDownload = false;
    private int pagination = 100;

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public boolean isCsvHeaders() {
        return csvHeaders;
    }

    public void setCsvHeaders(boolean csvHeaders) {
        this.csvHeaders = csvHeaders;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public boolean isColumnSorting() {
        return columnSorting;
    }

    public void setColumnSorting(boolean columnSorting) {
        this.columnSorting = columnSorting;
    }

    public boolean isIncludeHeadersOnDownload() {
        return includeHeadersOnDownload;
    }

    public void setIncludeHeadersOnDownload(boolean includeHeadersOnDownload) {
        this.includeHeadersOnDownload = includeHeadersOnDownload;
    }

    public int getPagination() {
        return pagination;
    }

    public void setPagination(int pagination) {
        this.pagination = pagination;
    }
}
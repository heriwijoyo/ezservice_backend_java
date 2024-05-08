/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CorePageInfo.java, v 0.1 2024‐04‐26 3:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CorePageInfo<T> {

    private List<T> bizData;
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private int numberRecord;
    private int totalRecord;
    private boolean hasNext;

    public List<T> getBizData() {
        return bizData;
    }

    public void setBizData(List<T> bizData) {
        this.bizData = bizData;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getNumberRecord() {
        return numberRecord;
    }

    public void setNumberRecord(int numberRecord) {
        this.numberRecord = numberRecord;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
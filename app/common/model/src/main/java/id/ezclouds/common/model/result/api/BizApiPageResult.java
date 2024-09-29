/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.result.api;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizPageInfo.java, v 0.1 2024‐04‐26 1:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizApiPageResult<T> {

    private static final String RESULT_SUCCESS = "RESULT_SUCCESS";

    private boolean success;
    private String timestamp;
    private ErrorResult errorResult;

    private List<T> bizData;
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private int numberRecord;
    private int totalRecord;
    private boolean hasNext;

    public BizApiPageResult() {
        success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorResult getErrorResult() {
        return errorResult;
    }

    public void setErrorResult(ErrorResult errorResult) {
        this.errorResult = errorResult;
    }

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

    public String getResultCode() {
        if (success) {
            return RESULT_SUCCESS;
        }
        return  errorResult.getErrorCode();
    }
}
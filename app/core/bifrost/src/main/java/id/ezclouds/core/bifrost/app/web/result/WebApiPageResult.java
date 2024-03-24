/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.result;

import id.ezclouds.biz.ezservice.service.result.PageResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebApiResult.java, v 0.1 2024‐02‐11 6:48 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebApiPageResult<T> {

    private boolean success;
    private boolean isSessionExpired;
    private String message;
    private PageResult<T> pageResult;

    public WebApiPageResult() {
        success = false;
        isSessionExpired = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSessionExpired() {
        return isSessionExpired;
    }

    public void setSessionExpired(boolean sessionExpired) {
        isSessionExpired = sessionExpired;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageResult<T> getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult<T> pageResult) {
        this.pageResult = pageResult;
    }
}
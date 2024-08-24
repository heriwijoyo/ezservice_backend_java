/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.model;

import id.ezclouds.common.util.exception.EzErrorCode;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPageResult.java, v 0.1 2024‐08‐22 10:47 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebPageResult {

    private boolean success;
    private EzErrorCode errorCode;

    public WebPageResult() {
        success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorCode(EzErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getResultCode() {
        if (success) {
            return "SUCCESS";
        }
        if (errorCode == null) {
            return EzErrorCode.SYSTEM_ERROR.getCode();
        }
        return errorCode.getCode();
    }
}
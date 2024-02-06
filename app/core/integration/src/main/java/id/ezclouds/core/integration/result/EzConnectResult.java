/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.result;

import id.ezclouds.common.util.exception.EzErrorCode;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzConnectResult.java, v 0.1 2024‐02‐05 1:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzConnectResult {

    private boolean success;
    private EzErrorCode errorCode;
    private Object data;

    public EzConnectResult() {
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public EzErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(EzErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
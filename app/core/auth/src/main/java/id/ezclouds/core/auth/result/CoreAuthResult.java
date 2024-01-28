/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.result;

import id.ezclouds.common.util.exception.EzErrorCode;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthResult.java, v 0.1 2024‐01‐28 6:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class CoreAuthResult<T> {

    private boolean success;
    private EzErrorCode ezErrorCode;
    private T data;

    public CoreAuthResult() {
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public EzErrorCode getEzErrorCode() {
        return ezErrorCode;
    }

    public void setEzErrorCode(EzErrorCode ezErrorCode) {
        this.ezErrorCode = ezErrorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
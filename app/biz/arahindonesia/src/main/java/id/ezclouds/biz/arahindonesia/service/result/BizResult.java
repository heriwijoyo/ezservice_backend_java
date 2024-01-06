/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.result;

import id.ezclouds.common.util.error.EzErrorCode;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizResult.java, v 0.1 2023‐12‐31 2:12 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizResult {

    private boolean success;
    private EzErrorCode errorCode;
    private Object object;

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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
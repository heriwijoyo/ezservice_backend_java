/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.result;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthResult.java, v 0.1 2024‐01‐28 6:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class CoreAuthResult<T> {

    private boolean success;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
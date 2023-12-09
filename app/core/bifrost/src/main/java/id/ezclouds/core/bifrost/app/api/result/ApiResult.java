/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.result;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiResult.java, v 0.1 2023‐12‐09 10:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ApiResult<T> {

    private boolean success;
    private ErrorResult errorResult;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ErrorResult getErrorResult() {
        return errorResult;
    }

    public void setErrorResult(ErrorResult errorResult) {
        this.errorResult = errorResult;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
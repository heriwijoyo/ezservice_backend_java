/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.result;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebApiResult.java, v 0.1 2024‐02‐11 6:48 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebApiResult<T> {

    private boolean success;
    private boolean isSessionExpired;
    private String message;
    private T data;

    public WebApiResult() {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
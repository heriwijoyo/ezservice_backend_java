/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.web.datasource;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DataSourceResult.java, v 0.1 2024‐09‐05 11:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class DataSourceResult {

    private boolean success;
    private String event;
    private String resultCode;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
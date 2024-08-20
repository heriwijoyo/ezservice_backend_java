/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.result;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BaseResult.java, v 0.1 2024‐07‐26 6:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BaseResult {

    private boolean success;
    private String resultCode;
    private String errorMessage;
    private Object object;

    public BaseResult() {
        this.success = false;
        this.resultCode = "UNKNOWN";
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        if (this.success) {
            this.resultCode = "SUCCESS";
        }
    }

    public String getSuccessCode() {
        return this.success ? "Y" : "N";
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
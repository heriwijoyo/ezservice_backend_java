/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.model.result.api;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ErrorResult.java, v 0.1 2023‐12‐09 12:50 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ErrorResult {

    private String errorCode;
    private String errorContext;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorContext() {
        return errorContext;
    }

    public void setErrorContext(String errorContext) {
        this.errorContext = errorContext;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
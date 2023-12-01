/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.result;

import id.ezclouds.common.util.error.ErrorContext;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ServiceBaseResult.java, v 0.1 2023‐06‐19 1:54 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ServiceBaseResult {

    private boolean success;
    private ErrorContext errorContext;

    /**
     * Getter method for property <tt>success</tt>.
     *
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>errorContext</tt>.
     *
     * @return property value of errorContext
     */
    public ErrorContext getErrorContext() {
        return errorContext;
    }

    /**
     * Setter method for property <tt>errorContext</tt>.
     *
     * @param errorContext value to be assigned to property errorContext
     */
    public void setErrorContext(ErrorContext errorContext) {
        this.errorContext = errorContext;
    }
}
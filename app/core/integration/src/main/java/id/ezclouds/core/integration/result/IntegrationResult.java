/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.result;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: IntegrationResult.java, v 0.1 2024‐02‐05 1:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class IntegrationResult {

    private boolean success;
    private Object data;

    public IntegrationResult() {
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
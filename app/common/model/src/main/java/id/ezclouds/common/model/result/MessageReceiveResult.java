/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.result;

import id.ezclouds.common.model.process.ProcessMode;
import id.ezclouds.common.util.exception.EzErrorCode;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MessageReceiveResult.java, v 0.1 2024‐08‐31 12:52 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MessageReceiveResult {

    private boolean success = false;
    private ProcessMode processMode;
    private String processTime;
    private EzErrorCode errorCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ProcessMode getProcessMode() {
        return processMode;
    }

    public void setProcessMode(ProcessMode processMode) {
        this.processMode = processMode;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public EzErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(EzErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
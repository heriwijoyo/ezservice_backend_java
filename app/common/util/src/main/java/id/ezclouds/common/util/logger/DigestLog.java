/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.logger;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DigestLog.java, v 0.1 2023‐06‐19 2:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class DigestLog {

    private final boolean success;
    private final String resultCode;
    private String errorMessage;
    private String digestMessage;

    public DigestLog(boolean success, String resultCode) {
        this.success = success;
        this.resultCode = resultCode;
    }

    public String getSuccessFlag() {
        return success ? "Y" : "N";
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setDigestMessage(String digestMessage) {
        this.digestMessage = digestMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getDigestMessage() {
        return digestMessage;
    }
}
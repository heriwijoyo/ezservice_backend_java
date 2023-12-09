/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.error;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzErrorException.java, v 0.1 2023‐12‐09 4:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzErrorException extends RuntimeException {

    private EzErrorCode ezErrorCode;
    private String errorMessage;

    public EzErrorException(EzErrorCode ezErrorCode, String errorMessage) {
        this.ezErrorCode = ezErrorCode;
        this.errorMessage = errorMessage;
    }

    public EzErrorCode getEzErrorCode() {
        return ezErrorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
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

    public EzErrorException(EzErrorCode ezErrorCode) {
        this(ezErrorCode, null);
    }

    public EzErrorException(EzErrorCode ezErrorCode, String... errorMessage) {
        this.ezErrorCode = ezErrorCode;
        this.errorMessage = composeErrorMessage(errorMessage);
    }

    public EzErrorCode getEzErrorCode() {
        return ezErrorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private static String composeErrorMessage(String... message) {
        String errorMessage = null;
        if (message != null) {
            errorMessage = "";

            for (String errMessage : message) {
                if (errMessage != null) {
                    errorMessage += errMessage;
                }
            }
        }
        return errorMessage;
    }
}
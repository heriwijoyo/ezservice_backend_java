/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.assertion;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.common.util.error.EzErrorException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AssertUtil.java, v 0.1 2023‐12‐09 2:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AssertUtil {

    public static void notNull(Object object, EzErrorCode ezErrorCode, String... message) throws EzErrorException {
        if (object == null) {
            throw new EzErrorException(ezErrorCode, composeErrorMessage(message));
        }
    }

    public static void notBlank(String value, EzErrorCode ezErrorCode, String... message) throws EzErrorException {
        if (StringUtil.isBlank(value)) {
            throw new EzErrorException(ezErrorCode, composeErrorMessage(message));
        }
    }

    public static void isTrue(boolean state, EzErrorCode ezErrorCode, String... message) throws EzErrorException {
        if (!state) {
            throw new EzErrorException(ezErrorCode, composeErrorMessage(message));
        }
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
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.assertion;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AssertUtil.java, v 0.1 2023‐12‐09 2:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AssertUtil {

    public static void isNull(Object object, EzErrorCode ezErrorCode, String... message) throws EzErrorException {
        if (object != null) {
            throw new EzErrorException(ezErrorCode, StringUtil.concateStrings(message));
        }
    }

    public static void notNull(Object object, EzErrorCode ezErrorCode, String... message) throws EzErrorException {
        if (object == null) {
            throw new EzErrorException(ezErrorCode, StringUtil.concateStrings(message));
        }
    }

    public static void notBlank(String value, EzErrorCode ezErrorCode, String... message) throws EzErrorException {
        if (StringUtil.isBlank(value)) {
            throw new EzErrorException(ezErrorCode, StringUtil.concateStrings(message));
        }
    }

    public static void isTrue(boolean state, EzErrorCode ezErrorCode, String... message) throws EzErrorException {
        if (!state) {
            throw new EzErrorException(ezErrorCode, StringUtil.concateStrings(message));
        }
    }

    public static void isNotTrue(boolean state, EzErrorCode ezErrorCode, String... message) throws EzErrorException {
        isTrue(!state, ezErrorCode, message);
    }

    public static void equals(String str1, String str2, EzErrorCode ezErrorCode) throws EzErrorException {
        isTrue(StringUtil.equalsNotNull(str1, str2), ezErrorCode);
    }

    public static void isNumber(String value, EzErrorCode ezErrorCode) throws EzErrorException {
        try {
            Integer.parseInt(value);
        } catch (Exception ignored) {
            throw new EzErrorException(ezErrorCode);
        }
    }
}
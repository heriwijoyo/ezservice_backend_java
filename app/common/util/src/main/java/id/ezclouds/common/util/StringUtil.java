/**
 * Ezclouds.id
 * Copyright (c) 2020‐2022 All Rights Reserved.
 */
package id.ezclouds.common.util;

import java.util.Objects;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: StringUtil.java, v 0.1 2022‐11‐04 7:58 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class StringUtil {

    public static final String EMPTY = "";

    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        if (str.length() < 1) {
            return true;
        }
        int strLen;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() < 1;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    public static boolean equalsNotNull(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }
}
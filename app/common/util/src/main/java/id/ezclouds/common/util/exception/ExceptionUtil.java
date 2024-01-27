/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.util.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ExceptionUtil.java, v 0.1 2024‐01‐28 2:04 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ExceptionUtil {

    public static String getStackTrace(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);

        return stringWriter.toString();
    }
}
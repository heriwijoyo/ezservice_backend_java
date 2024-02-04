/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.util.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public static String getErrorContext(Exception exception) {
        List<String> classNames = Arrays.stream(exception.getStackTrace())
                .map(StackTraceElement::toString)
                .filter(elementStr -> elementStr.contains("id.ezclouds"))
                .filter(elementStr -> !elementStr.contains("<generated>"))
                .filter(elementStr -> !elementStr.contains("AppController"))
                .filter(elementStr -> !elementStr.contains("ApiBizProcessor"))
                .filter(elementStr -> !elementStr.contains("BizServiceTemplate"))
                .map(elementStr -> {
                    int indexOfBracket = elementStr.indexOf("(");
                    if (indexOfBracket > 0) {
                        return elementStr.substring(0, indexOfBracket);
                    }
                    return elementStr;
                })
                .map(elementStr -> {
                    String[] elementKeys = elementStr.split("[.]");
                    if (elementKeys.length >= 2) {
                        int indexClass = elementKeys.length - 2;
                        int indexMethod = elementKeys.length - 1;

                        String className = elementKeys[indexClass];
                        String methodName = elementKeys[indexMethod];
                        return className + "::" + methodName;
                    }
                    return elementStr;
                })
                .collect(Collectors.toList());

        Collections.reverse(classNames);

        return String.join("@", classNames);
    }
}
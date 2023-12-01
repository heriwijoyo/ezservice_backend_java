/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.error;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ErrorContext.java, v 0.1 2023‐06‐19 1:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ErrorContext {

    private ErrorCodeEnum errorCodeEnum;
    private String location;
    private String message;

    public ErrorContext(ErrorCodeEnum errorCodeEnum, String location, String message) {
        this.errorCodeEnum = errorCodeEnum;
        this.location = location;
        this.message = message;
    }

    /**
     * Getter method for property <tt>errorCodeEnum</tt>.
     *
     * @return property value of errorCodeEnum
     */
    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    /**
     * Getter method for property <tt>location</tt>.
     *
     * @return property value of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }
}
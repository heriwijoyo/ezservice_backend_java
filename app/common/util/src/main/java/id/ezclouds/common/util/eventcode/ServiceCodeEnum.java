/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.eventcode;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ServiceCodeEnum.java, v 0.1 2023‐06‐19 1:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum ServiceCodeEnum {

    HEIMDALL_HTTP_ROUTE("HEIMDALL_HTTP_ROUTE", "Http route on Heimdall service")

    ;

    private String code;
    private String description;

    ServiceCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }
}
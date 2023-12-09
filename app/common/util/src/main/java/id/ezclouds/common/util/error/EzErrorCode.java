/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.error;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ErrorCodeEnum.java, v 0.1 2023‐06‐19 1:56 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum EzErrorCode {

    SYSTEM_ERROR("001", "SYSTEM_ERROR", "Unknown system error"),

    PARAM_ILLEGAL("002", "PARAM_ILLEGAL", "Parameter illegal"),

    UNAUTHORIZED("003", "UNAUTHORIZED", "Unauthorized"),

    ;

    private String code;
    private String innerCode;
    private String description;

    EzErrorCode(String code, String innerCode, String description) {
        this.code = code;
        this.innerCode = innerCode;
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
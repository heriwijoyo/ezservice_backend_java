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

    SYSTEM_ERROR("E001", "SYSTEM_ERROR", "Unknown system error"),
    PARAM_ILLEGAL("E002", "PARAM_ILLEGAL", "Parameter illegal"),
    UNAUTHORIZED("E003", "UNAUTHORIZED", "Unauthorized"),
    MEMBER_LOGIN_FAILED("E005", "MEMBER_LOGIN_FAILED", "Member login failed"),
    MEMBER_NOT_FOUND("E006", "MEMBER_NOT_FOUND", "Member not found"),
    SESSION_INVALID("E301", "SESSION_INVALID", "Session invalid"),
    SESSION_EXPIRED("E302", "SESSION_EXPIRED", "User session expired"),
    CORE_SEQUENCE_ERROR("E007", "CORE_SEQUENCE_ERROR", "core sequence error"),

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

    public String getInnerCode() {
        return innerCode;
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
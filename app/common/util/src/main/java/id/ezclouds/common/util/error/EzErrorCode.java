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

    SYSTEM_ERROR("SYSTEM_ERROR", "Unknown system error"),
    ILLEGAL_ACTION("ILLEGAL_ACTION", "Illegal action"),
    ILLEGAL_PARAM("ILLEGAL_PARAM", "Illegal parameter"),
    UNAUTHORIZED("UNAUTHORIZED", "Unauthorized"),
    MEMBER_LOGIN_FAILED("MEMBER_LOGIN_FAILED", "Member login failed"),
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "Member not found"),
    MEMBER_CLIENT_NOT_FOUND("MEMBER_CLIENT_NOT_FOUND", "Member client not found"),
    SESSION_INVALID("SESSION_INVALID", "Session invalid"),
    SESSION_EXPIRED("SESSION_EXPIRED", "User session expired"),
    CORE_SEQUENCE_ERROR("CORE_SEQUENCE_ERROR", "Core sequence error"),
    IDEMPOTENT_ERROR("IDEMPOTENT_ERROR", "Idempotent error"),

    ;

    private String code;
    private String description;

    EzErrorCode(String code, String description) {
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
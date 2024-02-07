/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.exception;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ErrorCodeEnum.java, v 0.1 2023‐06‐19 1:56 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum EzErrorCode {

    SYSTEM_ERROR("SYSTEM_ERROR", "Unknown System Error"),
    ILLEGAL_ACTION("ILLEGAL_ACTION", "Illegal Action"),
    ILLEGAL_PARAM("ILLEGAL_PARAM", "Illegal Parameter"),
    UNAUTHORIZED("UNAUTHORIZED", "Unauthorized"),
    MEMBER_LOGIN_FAILED("MEMBER_LOGIN_FAILED", "Member Login Failed"),
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "Member Not Found"),
    MEMBER_CLIENT_NOT_FOUND("MEMBER_CLIENT_NOT_FOUND", "Member Client Not Found"),
    MEMBER_CLIENT_NOT_ACTIVE("MEMBER_CLIENT_NOT_ACTIVE", "Member Client Not Active"),
    MEMBER_CLIENT_FROZEN("MEMBER_CLIENT_FROZEN", "Member Client Frozen"),
    MEMBER_CLIENT_ABNORMAL("MEMBER_CLIENT_ABNORMAL", "Member Client Abnormal"),
    SESSION_INVALID("SESSION_INVALID", "Session Invalid"),
    SESSION_EXPIRED("SESSION_EXPIRED", "Session Expired"),
    SESSION_UNAVAILABLE("SESSION_UNAVAILABLE", "Session Unavailable"),
    SESSION_VERIFY_FAILED("SESSION_VERIFY_FAILED", "Session Verify Failed"),

    MULTIPARTFILE_EMPTY("MULTIPARTFILE_EMPTY", "MultipartFile Empy"),
    MULTIPARTFILE_TYPE_UNDEFINED("MULTIPARTFILE_TYPE_UNDEFINED", "MultipartFile Type Undefined"),
    MULTIPARTFILE_TYPE_UNSUPPORTED("MULTIPARTFILE_TYPE_UNSUPPORTED", "MultipartFile Type Unsupported"),

    SYSTEM_STORE_FILE_FAILED("SYSTEM_STORE_FILE_FAILED", "System Store File Failed"),
    SYSTEM_FILE_ERROR("SYSTEM_FILE_ERROR", "System File Error"),
    CORE_SEQUENCE_ERROR("CORE_SEQUENCE_ERROR", "Core Sequence Error"),
    IDEMPOTENT_ERROR("IDEMPOTENT_ERROR", "Idempotent Error"),

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
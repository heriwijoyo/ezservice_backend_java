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
    UNSUPPORTED_DATA_ERROR("UNSUPPORTED_DATA_ERROR", "Unsupported Data Response"),
    ILLEGAL_ACTION("ILLEGAL_ACTION", "Illegal Action"),
    ILLEGAL_PARAM("ILLEGAL_PARAM", "Illegal Parameter"),
    UNAUTHORIZED("UNAUTHORIZED", "Unauthorized"),
    BIZ_PROCESS_ERROR("BIZ_PROCESS_ERROR", "Unknown Biz Process Error"),

    MEMBER_LOGIN_FAILED("MEMBER_LOGIN_FAILED", "Member Login Failed"),
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "Member Not Found"),
    MEMBER_CLIENT_NOT_FOUND("MEMBER_CLIENT_NOT_FOUND", "Member Client Not Found"),
    MEMBER_CLIENT_NOT_ACTIVE("MEMBER_CLIENT_NOT_ACTIVE", "Member Client Not Active"),
    MEMBER_CLIENT_FROZEN("MEMBER_CLIENT_FROZEN", "Member Client Frozen"),
    MEMBER_CLIENT_ABNORMAL("MEMBER_CLIENT_ABNORMAL", "Member Client Abnormal"),
    MEMBER_UNAUTHORIZED("MEMBER_UNAUTHORIZED", "Member Unauthorized"),

    SESSION_CODE_INVALID("SESSION_CODE_INVALID", "Session Code Invalid"),
    SESSION_INVALID("SESSION_INVALID", "Session Invalid"),
    SESSION_EXPIRED("SESSION_EXPIRED", "Session Expired"),
    SESSION_UNAVAILABLE("SESSION_UNAVAILABLE", "Session Unavailable"),
    SESSION_VERIFY_FAILED("SESSION_VERIFY_FAILED", "Session Verify Failed"),

    DATA_NOT_FOUND("DATA_NOT_FOUND", "Data Not Found"),
    OPERATION_DATA_STORE_FAILED("OPERATION_DATA_STORE_FAILED", "Operation Data Store Failed"),

    UPLOAD_SCENE_EMPTY("UPLOAD_SCENE_EMPTY", "Upload Scene Empty"),
    UPLOAD_SCENE_NOT_ALLOWED("UPLOAD_SCENE_NOT_ALLOWED", "Upload Scene Not Allowed"),
    MULTIPARTFILE_EMPTY("MULTIPARTFILE_EMPTY", "MultipartFile Empy"),
    MULTIPARTFILE_TYPE_UNDEFINED("MULTIPARTFILE_TYPE_UNDEFINED", "MultipartFile Type Undefined"),
    MULTIPARTFILE_TYPE_UNSUPPORTED("MULTIPARTFILE_TYPE_UNSUPPORTED", "MultipartFile Type Unsupported"),
    MEDIA_NOT_FOUND("MEDIA_NOT_FOUND", "Media Not Found"),

    ACTION_NOT_ALLOWED("ACTION_NOT_ALLOWED", "Action Not Allowed"),
    BIZ_UNIQUE_FAILED("BIZ_UNIQUE_FAILED", "Biz Unique Failed"),
    BIZ_VALIDATION_FAILED("BIZ_VALIDATION_FAILED", "Biz Validation Failed"),
    DATA_INVALID("DATA_INVALID", "Data Invalid"),
    INVALID_NUMBER_FORMAT("INVALID_NUMBER_FORMAT", "Invalid Number Format"),

    SYSTEM_STORE_FILE_FAILED("SYSTEM_STORE_FILE_FAILED", "System Store File Failed"),
    SYSTEM_FILE_ERROR("SYSTEM_FILE_ERROR", "System File Error"),
    CORE_SEQUENCE_ERROR("CORE_SEQUENCE_ERROR", "Core Sequence Error"),
    IDEMPOTENT_ERROR("IDEMPOTENT_ERROR", "Idempotent Error"),
    WEB_PAGE_ERROR("WEB_PAGE_ERROR", "Web Page Error"),
    WEB_BIZ_PAGE_NOT_FOUND("WEB_BIZ_PAGE_NOT_FOUND", "Web Page Not Found"),
    WEB_BIZ_PAGE_UNAUTHORIZED("WEB_BIZ_PAGE_UNAUTHORIZED", "Web Page Unauthorized"),

    SCHEDULER_PRIORITY_OVERLAP("SCHEDULER_PRIORITY_OVERLAP", "Scheduler Overlap"),

    /* Biz Validation */
    BIZ_POLL_STATION_INVALID("BIZ_POLL_STATION_INVALID", "Invalid Poll Station"),

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
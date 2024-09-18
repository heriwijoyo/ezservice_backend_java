/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.config;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreOrgConfigType.java, v 0.1 2024‐09‐01 10:47 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreOrgConfigType implements CoreConfigType {

    BIZ_REAL_COUNT_SWITCH_MODE("BIZ_REAL_COUNT_SWITCH_MODE"),
    BIZ_REAL_COUNT_ID_ACTIVE("BIZ_REAL_COUNT_ID_ACTIVE"),

    CORE_AREA_LEVEL_ROOT("CORE_AREA_LEVEL_ROOT"),
    CORE_AREA_ROOT_LEVEL("CORE_AREA_ROOT_LEVEL"),
    CORE_AREA_ROOT_IDS("CORE_AREA_ROOT_IDS"),
    CORE_AREA_ROOT_NAMES("CORE_AREA_ROOT_NAMES"),
    CORE_DEFAULT_SURVEY_ID("CORE_DEFAULT_SURVEY_ID"),

    MEMBER_CLIENT_REQUIRE_LOGIN("MEMBER_CLIENT_REQUIRE_LOGIN"),
    MEMBER_CLIENT_ALLOW_MULTIPLE_SESSION("MEMBER_CLIENT_ALLOW_MULTIPLE_SESSION"),
    MEMBER_CLIENT_SESSION_EXPIRY_DAYS("MEMBER_CLIENT_SESSION_EXPIRY_DAYS"),
    MEMBER_COMMON_SESSION_EXPIRY_MINS("MEMBER_COMMON_SESSION_EXPIRY_MINS"),
    ADMIN_COMMON_SESSION_EXPIRY_MINS("ADMIN_COMMON_SESSION_EXPIRY_MINS"),

    WATZAP_API_KEY("WATZAP_API_KEY"),
    WATZAP_NUMBER_KEY("WATZAP_NUMBER_KEY"),
    WATZAP_SEND_ENABLE("WATZAP_SEND_ENABLE"),

    ;

    private final String code;

    CoreOrgConfigType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public boolean isOrgSpecific() {
        return true;
    }

    public static CoreOrgConfigType getByCode(String code) {
        for (CoreOrgConfigType configType : values()) {
            if (configType.code.equals(code)) {
                return configType;
            }
        }
        return null;
    }
}
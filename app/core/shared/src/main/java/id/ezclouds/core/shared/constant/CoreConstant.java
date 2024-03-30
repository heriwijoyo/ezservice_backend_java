/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.constant;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreConstant.java, v 0.1 2024‐02‐08 3:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreConstant {

    public static final String SU_ORG_ID = "SU00";
    public static final String SU_ORG_CODE = "101";
    public static final int SU_WEB_SESSION_EXPIRY_MINS = 30;

    public static final class ConfigKey {
        public static final String MEMBER_CLIENT_ALLOW_MULTIPLE_SESSION = "MEMBER_CLIENT_ALLOW_MULTIPLE_SESSION";
        public static final String MEMBER_CLIENT_SESSION_EXPIRY_DAYS = "MEMBER_CLIENT_SESSION_EXPIRY_DAYS";
        public static final String MEMBER_COMMON_SESSION_EXPIRY_MINS = "MEMBER_COMMON_SESSION_EXPIRY_MINS";
        public static final String ADMIN_COMMON_SESSION_EXPIRY_MINS = "ADMIN_COMMON_SESSION_EXPIRY_MINS";

        public static final String WATZAP_SEND_ENABLE = "WATZAP_SEND_ENABLE";
        public static final String WATZAP_API_KEY = "WATZAP_API_KEY";
        public static final String WATZAP_NUMBER_KEY = "WATZAP_NUMBER_KEY";
        public static final String WATZAP_API_URI = "WATZAP_API_URI";

        public static final String CORE_AREA_LEVEL_ROOT = "CORE_AREA_LEVEL_ROOT";
        public static final String CORE_AREA_ROOT_IDS = "CORE_AREA_ROOT_IDS";
    }

    public static final class CacheKey {
        public static final String CORE_CONFIG = "CORE_CONFIG";
        public static final String CORE_ORG_CONFIG = "CORE_ORG_CONFIG";
    }
}
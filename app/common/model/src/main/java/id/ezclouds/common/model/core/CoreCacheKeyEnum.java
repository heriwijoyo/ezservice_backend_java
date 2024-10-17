/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreCacheKeyEnum.java, v 0.1 2024‐09‐26 2:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreCacheKeyEnum {

    ORGANIZATIONS(CoreCacheKey.ORGANIZATIONS),
    AUTH_APP_CLIENTS(CoreCacheKey.AUTH_APP_CLIENTS),
    CORE_CONFIGS(CoreCacheKey.CORE_CONFIGS),

    ;
    private final String code;

    CoreCacheKeyEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
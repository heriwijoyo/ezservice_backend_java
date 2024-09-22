/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.core;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCacheEnum.java, v 0.1 2024‐04‐09 2:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizCacheEnum {

    ORGANIZATION_ALL(BizCacheKey.ORGANIZATION_ALL),
    SUB_ORGANIZATION_ALL(BizCacheKey.SUB_ORGANIZATION_ALL),
    APP_BUILD_PACKAGE_ALL(BizCacheKey.APP_BUILD_PACKAGE_ALL),
    APP_CONFIG_ALL(BizCacheKey.APP_CONFIG_ALL),
    APP_MESSAGE_TEMPLATE(BizCacheKey.APP_MESSAGE_TEMPLATE),
    NEWS_HIGHLIGHT(BizCacheKey.NEWS_HIGHLIGHT),
    APP_IMAGE_GALLERY_ALL(BizCacheKey.APP_IMAGE_GALLERY_ALL),
    VIDEO_CARD_GALLERY_ALL(BizCacheKey.VIDEO_CARD_GALLERY_ALL),
    CANDIDATE_PROFILE(BizCacheKey.CANDIDATE_PROFILE),
    CANDIDATE_BIOGRAPHY(BizCacheKey.CANDIDATE_BIOGRAPHY),

    WEBAPP_VIDEO_CARD(BizCacheKey.WEBAPP_VIDEO_CARD),
    WEBAPP_PROFILE(BizCacheKey.WEBAPP_PROFILE),

    UNKNOWN("UNKNOWN")
    ;

    private final String code;

    BizCacheEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizCacheEnum getByCode(String cacheKey) {
        for (BizCacheEnum bizCacheEnum : values()) {
            if (bizCacheEnum.code.equals(cacheKey)) {
                return bizCacheEnum;
            }
        }
        return UNKNOWN;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.config;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizPublicConfig.java, v 0.1 2024‐02‐08 6:13 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizPublicConfig {

    private static final String SLASH = "/";
    private static final String PATH_IMAGE = "/image";
    private static final String PATH_AVATAR = "/avatar";
    private static final String PATH_IDCARD = "/idcard";
    private static final String PATH_FAMCARD = "/famcard";

    private final String orgCode;
    private final String memberId;
    private final String rootPublicImageUrl;

    public BizPublicConfig(String orgCode, String memberId, String rootPublicImageUrl) {
        this.orgCode = orgCode;
        this.memberId = memberId;
        this.rootPublicImageUrl = rootPublicImageUrl;
    }

    public String getAvatarRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_AVATAR + SLASH + orgCode + SLASH + memberId + SLASH;
    }

    public String getIdCardRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_IDCARD + SLASH + orgCode + SLASH + memberId + SLASH;
    }

    public String getFamCardRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_FAMCARD + SLASH + orgCode + SLASH + memberId + SLASH;
    }
}
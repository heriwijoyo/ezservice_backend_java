/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.config;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizPublicUrlResolverImpl.java, v 0.1 2024‐02‐13 12:07 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizPublicUrlResolverImpl implements BizPublicUrlResolver {

    private static final String SLASH = "/";
    private static final String PATH_IMAGE = "/image";
    private static final String PATH_AVATAR = "/private/avatar";
    private static final String PATH_IDCARD = "/private/idcard";
    private static final String PATH_FAMCARD = "/private/famcard";

    private static final String PATH_APP_GALLERY = "/public/app";
    private static final String PATH_NEWS_GALLERY = "/public/news";
    private static final String PATH_EVENT_GALLERY = "/public/event";
    private static final String PATH_VIDEO_CARD_GALLERY = "/public/video";
    private static final String PATH_OTHER_GALLERY = "/public/other";

    private final String rootPublicImageUrl;
    private final String orgCode;
    private final String memberId;

    public BizPublicUrlResolverImpl(String rootPublicImageUrl, String orgCode, String memberId) {
        this.rootPublicImageUrl = rootPublicImageUrl;
        this.orgCode = orgCode;
        this.memberId = memberId;
    }

    public BizPublicUrlResolverImpl(String rootPublicImageUrl, String orgCode) {
        this(rootPublicImageUrl, orgCode, null);
    }

    @Override
    public String getAvatarRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_AVATAR + SLASH + orgCode + SLASH + memberId + SLASH;
    }

    @Override
    public String getIdCardRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_IDCARD + SLASH + orgCode + SLASH + memberId + SLASH;
    }

    @Override
    public String getFamCardRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_FAMCARD + SLASH + orgCode + SLASH + memberId + SLASH;
    }

    @Override
    public String getAppGalleryRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_APP_GALLERY + SLASH + orgCode + SLASH;
    }

    @Override
    public String getNewsGalleryRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_NEWS_GALLERY + SLASH + orgCode + SLASH;
    }

    @Override
    public String getEventGalleryRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_EVENT_GALLERY + SLASH + orgCode + SLASH;
    }

    @Override
    public String getVideoCardGalleryRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_VIDEO_CARD_GALLERY + SLASH + orgCode + SLASH;
    }

    @Override
    public String getOtherGalleryRootImageUrl() {
        return rootPublicImageUrl + PATH_IMAGE + PATH_OTHER_GALLERY + SLASH + orgCode + SLASH;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.config;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizPublicUrlResolver.java, v 0.1 2024‐02‐08 6:13 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizPublicUrlResolver {

    String getAvatarRootImageUrl();
    String getIdCardRootImageUrl();
    String getFamCardRootImageUrl();

    String getAppGalleryRootImageUrl();
    String getNewsGalleryRootImageUrl();
    String getEventGalleryRootImageUrl();
    String getVideoCardGalleryRootImageUrl();
    String getDocumentGalleryRootImageUrl();

    String getAppDownloadRootUrl();
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

import id.ezclouds.core.shared.member.PublicFileResolver;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PublicFileResolverImpl.java, v 0.1 2024‐02‐12 9:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class PublicFileResolverImpl implements PublicFileResolver {

    private static final String DIR_APP_GALLERY = "APP_GALLERY";
    private static final String DIR_NEWS_GALLERY = "NEWS_GALLERY";
    private static final String DIR_EVENT_GALLERY = "EVENT_GALLERY";
    private static final String DIR_VIDEO_CARD_GALLERY = "VIDEO_CARD_GALLERY";
    private static final String DIR_OTHER_GALLERY = "OTHER_GALLERY";

    private final String orgId;
    private final String uploadRootDir;

    public PublicFileResolverImpl(String orgId, String uploadRootDir) {
        this.orgId = orgId;
        this.uploadRootDir = uploadRootDir;
    }

    private String getOrgFiledDir() {
        return uploadRootDir + "/" + orgId;
    }

    public Path getOrgPath() {
        return Paths.get(getOrgFiledDir()).toAbsolutePath().normalize();
    }

    public Path getAppGalleryPath() {
        return Paths.get(getOrgFiledDir(), DIR_APP_GALLERY).toAbsolutePath().normalize();
    }

    public Path getNewsGalleryPath() {
        return Paths.get(getOrgFiledDir(), DIR_NEWS_GALLERY).toAbsolutePath().normalize();
    }

    public Path getEventGalleryPath() {
        return Paths.get(getOrgFiledDir(), DIR_EVENT_GALLERY).toAbsolutePath().normalize();
    }

    public Path getVideoCardGalleryPath() {
        return Paths.get(getOrgFiledDir(), DIR_VIDEO_CARD_GALLERY).toAbsolutePath().normalize();
    }

    public Path getOtherGalleryPath() {
        return Paths.get(getOrgFiledDir(), DIR_OTHER_GALLERY).toAbsolutePath().normalize();
    }

    @Override
    public Path getAppGalleryPath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_APP_GALLERY, fileName).toAbsolutePath().normalize();
    }

    @Override
    public Path getNewsGalleryPath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_NEWS_GALLERY, fileName).toAbsolutePath().normalize();
    }

    @Override
    public Path getEventGalleryPath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_EVENT_GALLERY, fileName).toAbsolutePath().normalize();
    }

    @Override
    public Path getVideoCardGalleryPath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_VIDEO_CARD_GALLERY, fileName).toAbsolutePath().normalize();
    }

    @Override
    public Path getOtherGalleryPath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_OTHER_GALLERY, fileName).toAbsolutePath().normalize();
    }
}
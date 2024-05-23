/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.file;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PublicFileResolverImpl.java, v 0.1 2024‐02‐12 9:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class PublicFileResolverImpl implements PublicFileResolver {

    private static final String DIR_APP_PACKAGE         = "APP_PACKAGE";
    private static final String DIR_APP_GALLERY         = PublicFileInitializer.DIR_APP_GALLERY;
    private static final String DIR_NEWS_GALLERY        = PublicFileInitializer.DIR_NEWS_GALLERY;
    private static final String DIR_EVENT_GALLERY       = PublicFileInitializer.DIR_EVENT_GALLERY;
    private static final String DIR_VIDEO_CARD_GALLERY  = PublicFileInitializer.DIR_VIDEO_CARD_GALLERY;
    private static final String DIR_DOCS_GALLERY        = PublicFileInitializer.DIR_DOCS_GALLERY;
    private static final String DIR_REPORT              = PublicFileInitializer.DIR_REPORT;
    private static final String DIR_REPORT_IMAGE        = PublicFileInitializer.DIR_REPORT_IMAGE;
    private static final String DIR_REPORT_VIDEO        = PublicFileInitializer.DIR_REPORT_VIDEO;
    private static final String DIR_REPORT_VOICE        = PublicFileInitializer.DIR_REPORT_VOICE;
    private static final String DIR_REPORT_RECAP        = PublicFileInitializer.DIR_REPORT_RECAP;

    private final String orgId;
    private final String uploadRootDir;

    public PublicFileResolverImpl(String uploadRootDir, String orgId) {
        this.orgId = orgId;
        this.uploadRootDir = uploadRootDir;
    }

    private String getOrgFiledDir() {
        return uploadRootDir + "/" + orgId;
    }

    @Override
    public Path getAppBuildPackagePath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_APP_PACKAGE, fileName).toAbsolutePath().normalize();
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
    public Path getDocsGalleryPath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_DOCS_GALLERY, fileName).toAbsolutePath().normalize();
    }

    @Override
    public Path getReportImagePath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_IMAGE, fileName).toAbsolutePath().normalize();
    }

    @Override
    public Path getReportVideoPath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_VIDEO, fileName).toAbsolutePath().normalize();
    }

    @Override
    public Path getReportVoicePath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_VOICE, fileName).toAbsolutePath().normalize();
    }

    @Override
    public Path getReportRecapPath(String fileName) {
        return Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_RECAP, fileName).toAbsolutePath().normalize();
    }
}
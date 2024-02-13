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

    private static final String DIR_APP_GALLERY         = "GALLERY_APP";
    private static final String DIR_NEWS_GALLERY        = "GALLERY_NEWS";
    private static final String DIR_EVENT_GALLERY       = "GALLERY_EVENT";
    private static final String DIR_VIDEO_CARD_GALLERY  = "GALLERY_VIDEO_CARD";
    private static final String DIR_OTHER_GALLERY       = "GALLERY_OTHER";
    private static final String DIR_REPORT              = "REPORT";
    private static final String DIR_REPORT_IMAGE        = "IMAGE";
    private static final String DIR_REPORT_VIDEO        = "VIDEO";
    private static final String DIR_REPORT_VOICE        = "VOICE";

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

    public Path getReportPath() {
        return Paths.get(getOrgFiledDir(), DIR_REPORT).toAbsolutePath().normalize();
    }

    public Path getReportImagePath() {
        return Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_IMAGE).toAbsolutePath().normalize();
    }

    public Path getReportVideoPath() {
        return Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_VIDEO).toAbsolutePath().normalize();
    }

    public Path getReportVoicePath() {
        return Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_VOICE).toAbsolutePath().normalize();
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
}
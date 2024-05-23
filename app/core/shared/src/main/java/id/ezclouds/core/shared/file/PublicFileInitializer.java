/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PublicFileInitializer.java, v 0.1 2024‐02‐14 2:07 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class PublicFileInitializer {

    public static final String DIR_APP_PACKAGE         = "APP_PACKAGE";
    public static final String DIR_APP_GALLERY         = "GALLERY_APP";
    public static final String DIR_NEWS_GALLERY        = "GALLERY_NEWS";
    public static final String DIR_EVENT_GALLERY       = "GALLERY_EVENT";
    public static final String DIR_VIDEO_CARD_GALLERY  = "GALLERY_VIDEO_CARD";
    public static final String DIR_DOCS_GALLERY        = "GALLERY_DOCS";
    public static final String DIR_REPORT              = "REPORT";
    public static final String DIR_REPORT_IMAGE        = "IMAGE";
    public static final String DIR_REPORT_VIDEO        = "VIDEO";
    public static final String DIR_REPORT_VOICE        = "VOICE";
    public static final String DIR_REPORT_RECAP        = "RECAP";

    public static final String DIR_MEMBER_ROOT         = "0MEMBER";

    private final String uploadRootDir;
    private final String orgId;
    private List<Path> publicPaths;

    public PublicFileInitializer(String uploadRootDir, String orgId) {
        this.uploadRootDir = uploadRootDir;
        this.orgId = orgId;
    }

    public String getOrgFiledDir() {
        return uploadRootDir + "/" + orgId;
    }

    public void initPublicPaths() {
        publicPaths = new ArrayList<>();
        publicPaths.add(Paths.get(getOrgFiledDir()).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_APP_PACKAGE).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_APP_GALLERY).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_NEWS_GALLERY).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_EVENT_GALLERY).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_VIDEO_CARD_GALLERY).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_DOCS_GALLERY).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_REPORT).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_IMAGE).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_VIDEO).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_VOICE).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_RECAP).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_MEMBER_ROOT).toAbsolutePath().normalize());
    }

    public List<Path> getPublicPaths() {
        return publicPaths;
    }
}
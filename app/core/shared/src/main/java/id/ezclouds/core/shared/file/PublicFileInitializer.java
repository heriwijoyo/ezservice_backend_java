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

    private static final String DIR_APP_GALLERY         = "GALLERY_APP";
    private static final String DIR_NEWS_GALLERY        = "GALLERY_NEWS";
    private static final String DIR_EVENT_GALLERY       = "GALLERY_EVENT";
    private static final String DIR_VIDEO_CARD_GALLERY  = "GALLERY_VIDEO_CARD";
    private static final String DIR_OTHER_GALLERY       = "GALLERY_OTHER";
    private static final String DIR_REPORT              = "REPORT";
    private static final String DIR_REPORT_IMAGE        = "IMAGE";
    private static final String DIR_REPORT_VIDEO        = "VIDEO";
    private static final String DIR_REPORT_VOICE        = "VOICE";

    private static final String DIR_MEMBER_ROOT         = "0MEMBER";

    private final String orgId;
    private final String uploadRootDir;
    private List<Path> publicPaths;

    public PublicFileInitializer(String orgId, String uploadRootDir) {
        this.orgId = orgId;
        this.uploadRootDir = uploadRootDir;
        initPublicPaths();
    }

    private String getOrgFiledDir() {
        return uploadRootDir + "/" + orgId;
    }

    private void initPublicPaths() {
        publicPaths = new ArrayList<>();
        publicPaths.add(Paths.get(getOrgFiledDir()).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_APP_GALLERY).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_NEWS_GALLERY).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_EVENT_GALLERY).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_VIDEO_CARD_GALLERY).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_OTHER_GALLERY).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_REPORT).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_IMAGE).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_VIDEO).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_REPORT, DIR_REPORT_VOICE).toAbsolutePath().normalize());
        publicPaths.add(Paths.get(getOrgFiledDir(), DIR_MEMBER_ROOT).toAbsolutePath().normalize());
    }

    public List<Path> getPublicPaths() {
        return publicPaths;
    }
}
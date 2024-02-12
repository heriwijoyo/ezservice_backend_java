/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

import id.ezclouds.core.shared.constant.CoreConstant;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberFileInfo.java, v 0.1 2024‐02‐08 3:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMemberFileInfo {

    private static final String PATH_ROOT_MEMBER = "0MEMBER";

    private String uploadRootDir;

    private final String orgId;
    private final String memberId;

    public CoreMemberFileInfo(String orgId, String memberId) {
        this.orgId = orgId;
        this.memberId = memberId;
    }

    private String getOrgFileDir() {
        return uploadRootDir + "/" + orgId;
    }

    public void setUploadRootDir(String uploadRootDir) {
        this.uploadRootDir = uploadRootDir;
    }

    public Path getOrgFilePath() {
        return Paths.get(getOrgFileDir()).toAbsolutePath().normalize();
    }

    public Path getMemberRootPath() {
        return Paths.get(getOrgFileDir(), PATH_ROOT_MEMBER).toAbsolutePath().normalize();
    }

    public Path getMemberFilePath() {
        return Paths.get(getMemberRootPath().toString(), memberId).toAbsolutePath().normalize();
    }

    public String getAvatarLocationWithPrefix() {
        return getMemberFilePath().toString() +"/"+ CoreConstant.File.PREFIX_AVATAR;
    }

    public String getIdCardLocationWithPrefix() {
        return getMemberFilePath().toString() +"/"+ CoreConstant.File.PREFIX_ID_CARD;
    }

    public String getFamilyCardLocationWithPrefix() {
        return getMemberFilePath().toString() +"/"+ CoreConstant.File.PREFIX_FAMILY_CARD;
    }
}
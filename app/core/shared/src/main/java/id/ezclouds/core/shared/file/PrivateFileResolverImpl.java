/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.file;

import id.ezclouds.core.shared.file.PrivateFileResolver;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PrivateFileResolverImpl.java, v 0.1 2024‐02‐08 3:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class PrivateFileResolverImpl implements PrivateFileResolver {

    private static final String PATH_ROOT_MEMBER = "0MEMBER";
    private static final String PREFIX_AVATAR = "AVATAR_";
    private static final String PREFIX_ID_CARD = "IDCARD_";
    private static final String PREFIX_FAMILY_CARD = "FAMCARD_";

    private final String uploadRootDir;
    private final String orgId;
    private final String memberId;

    public PrivateFileResolverImpl(String uploadRootDir, String orgId, String memberId) {
        this.uploadRootDir = uploadRootDir;
        this.orgId = orgId;
        this.memberId = memberId;
    }

    @Override
    public Path getAvatarPath(String fileName) {
        return Paths.get(getMemberFilePath().toString(), PREFIX_AVATAR + fileName)
                .toAbsolutePath().normalize();
    }

    @Override
    public Path getIdCardPath(String fileName) {
        return Paths.get(getMemberFilePath().toString(), PREFIX_ID_CARD + fileName)
                .toAbsolutePath().normalize();
    }

    @Override
    public Path getFamilyCardPath(String fileName) {
        return Paths.get(getMemberFilePath().toString(), PREFIX_FAMILY_CARD + fileName)
                .toAbsolutePath().normalize();
    }

    private String getOrgFileDir() {
        return uploadRootDir + "/" + orgId;
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
}
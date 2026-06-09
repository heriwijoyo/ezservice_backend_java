/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.file;

import id.ezclouds.common.model.file.PrivateFileResolver;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PrivateFileResolverImpl.java, v 0.1 2024‐02‐08 3:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class PrivateFileResolverImpl implements PrivateFileResolver {

    private static final String DIR_MEMBER_ROOT = PublicFileInitializer.DIR_MEMBER_ROOT;

    private static final String PREFIX_AVATAR = "AVATAR_";
    private static final String PREFIX_ID_CARD = "IDCARD_";
    private static final String PREFIX_FAMILY_CARD = "FAMCARD_";

    private PublicFileInitializer publicFileInitializer;
    private final String memberId;

    public PrivateFileResolverImpl(String uploadRootDir, String orgId, String memberId) {
        this.publicFileInitializer = new PublicFileInitializer(uploadRootDir, orgId);
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

    private Path getMemberRootPath() {
        return Paths.get(publicFileInitializer.getOrgFiledDir(), DIR_MEMBER_ROOT).toAbsolutePath().normalize();
    }

    public Path getMemberFilePath() {
        return Paths.get(getMemberRootPath().toString(), memberId).toAbsolutePath().normalize();
    }
}
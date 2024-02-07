/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberFileInfo.java, v 0.1 2024‐02‐08 3:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberFileInfo {

    private final String avatarLocationWithPrefix;
    private final String idCardLocationWithPrefix;
    private final String familyCardLocationWithPrefix;

    public MemberFileInfo(String avatarLocationWithPrefix, String idCardLocationWithPrefix, String familyCardLocationWithPrefix) {
        this.avatarLocationWithPrefix = avatarLocationWithPrefix;
        this.idCardLocationWithPrefix = idCardLocationWithPrefix;
        this.familyCardLocationWithPrefix = familyCardLocationWithPrefix;
    }

    public Path getAvatarPath(String fileName) {
        return Paths.get(avatarLocationWithPrefix + fileName)
                .toAbsolutePath()
                .normalize();
    }

    public Path getIdCardPath(String fileName) {
        return Paths.get(idCardLocationWithPrefix + fileName)
                .toAbsolutePath()
                .normalize();
    }

    public Path getFamilyCardPath(String fileName) {
        return Paths.get(familyCardLocationWithPrefix + fileName)
                .toAbsolutePath()
                .normalize();
    }
}
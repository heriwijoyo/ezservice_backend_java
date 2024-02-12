/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.shared.member.PublicFileInfo;
import id.ezclouds.core.shared.model.CoreMemberFileInfo;
import id.ezclouds.core.shared.member.MemberFileInfo;
import id.ezclouds.core.shared.model.PublicFileInfoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreFileService.java, v 0.1 2024‐02‐08 3:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreFileService {

    @Value("${ezserviceapp.dir.upload.root}")
    private String uploadRootDir;

    public MemberFileInfo resolveMemberFileInfo(String orgId, String memberId) throws EzErrorException {
        CoreMemberFileInfo fileInfo = new CoreMemberFileInfo(orgId, memberId);
        fileInfo.setUploadRootDir(uploadRootDir);

        try {
            if (Files.notExists(fileInfo.getOrgFilePath())) {
                Files.createDirectory(fileInfo.getOrgFilePath());
            }
            if (Files.notExists(fileInfo.getMemberFilePath())) {
                Files.createDirectory(fileInfo.getMemberFilePath());
            }
        } catch (IOException exception) {
            throw new EzErrorException(EzErrorCode.SYSTEM_FILE_ERROR, "Error creating member directory");
        }

        MemberFileInfo memberFileInfo = new MemberFileInfo(
                fileInfo.getAvatarLocationWithPrefix(),
                fileInfo.getIdCardLocationWithPrefix(),
                fileInfo.getFamilyCardLocationWithPrefix()
        );
        return memberFileInfo;
    }

    public PublicFileInfo resolvePublicFileInfo(String orgId) {
        PublicFileInfoImpl fileInfo = new PublicFileInfoImpl(orgId, uploadRootDir);

        try {
            if (Files.notExists(fileInfo.getOrgPath())) {
                Files.createDirectory(fileInfo.getOrgPath());
            }
            if (Files.notExists(fileInfo.getAppGalleryPath())) {
                Files.createDirectory(fileInfo.getAppGalleryPath());
            }
            if (Files.notExists(fileInfo.getNewsGalleryPath())) {
                Files.createDirectory(fileInfo.getNewsGalleryPath());
            }
            if (Files.notExists(fileInfo.getEventGalleryPath())) {
                Files.createDirectory(fileInfo.getEventGalleryPath());
            }
            if (Files.notExists(fileInfo.getOtherGalleryPath())) {
                Files.createDirectory(fileInfo.getOtherGalleryPath());
            }
        } catch (IOException exception) {
            throw new EzErrorException(EzErrorCode.SYSTEM_FILE_ERROR, "Error creating public directory");
        }

        return fileInfo;
    }

    public void storeFile(InputStream inputStream, Path targetPath) throws EzErrorException {
        try {
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new EzErrorException(EzErrorCode.SYSTEM_STORE_FILE_FAILED, "Failed to store member file");
        }
    }
}
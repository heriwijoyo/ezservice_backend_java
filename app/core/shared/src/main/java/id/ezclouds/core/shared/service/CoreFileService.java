/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.shared.file.PrivateFileResolver;
import id.ezclouds.core.shared.file.PrivateFileResolverImpl;
import id.ezclouds.core.shared.file.PublicFileResolver;
import id.ezclouds.core.shared.file.PublicFileResolverImpl;
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

    public PrivateFileResolver resolveMemberFileInfo(String orgId, String memberId) throws EzErrorException {
        PrivateFileResolverImpl privateFileResolver = new PrivateFileResolverImpl(uploadRootDir, orgId, memberId);

        try {
            if (Files.notExists(privateFileResolver.getOrgFilePath())) {
                Files.createDirectory(privateFileResolver.getOrgFilePath());
            }
            if (Files.notExists(privateFileResolver.getMemberRootPath())) {
                Files.createDirectory(privateFileResolver.getMemberRootPath());
            }
            if (Files.notExists(privateFileResolver.getMemberFilePath())) {
                Files.createDirectory(privateFileResolver.getMemberFilePath());
            }
        } catch (IOException exception) {
            throw new EzErrorException(EzErrorCode.SYSTEM_FILE_ERROR, "Error creating member directory");
        }

        return privateFileResolver;
    }

    public PublicFileResolver resolvePublicFileInfo(String orgId) {
        PublicFileResolverImpl fileInfo = new PublicFileResolverImpl(orgId, uploadRootDir);

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
            if (Files.notExists(fileInfo.getVideoCardGalleryPath())) {
                Files.createDirectory(fileInfo.getVideoCardGalleryPath());
            }
            if (Files.notExists(fileInfo.getOtherGalleryPath())) {
                Files.createDirectory(fileInfo.getOtherGalleryPath());
            }
            if (Files.notExists(fileInfo.getReportPath())) {
                Files.createDirectory(fileInfo.getReportPath());
            }
            if (Files.notExists(fileInfo.getReportImagePath())) {
                Files.createDirectory(fileInfo.getReportImagePath());
            }
            if (Files.notExists(fileInfo.getReportVideoPath())) {
                Files.createDirectory(fileInfo.getReportVideoPath());
            }
            if (Files.notExists(fileInfo.getReportVoicePath())) {
                Files.createDirectory(fileInfo.getReportVoicePath());
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
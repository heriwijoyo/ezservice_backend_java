/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.model.file.PrivateFileResolver;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.shared.file.*;
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
            if (Files.notExists(privateFileResolver.getMemberFilePath())) {
                Files.createDirectory(privateFileResolver.getMemberFilePath());
            }
        } catch (IOException exception) {
            throw new EzErrorException(EzErrorCode.SYSTEM_FILE_ERROR, "Error creating member directory");
        }

        return privateFileResolver;
    }

    public PublicFileResolver resolvePublicFileInfo(String orgId) {
        return new PublicFileResolverImpl(uploadRootDir, orgId);
    }

    public void storeFile(InputStream inputStream, Path targetPath) throws EzErrorException {
        try {
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new EzErrorException(EzErrorCode.SYSTEM_STORE_FILE_FAILED, "Failed to store member file");
        }
    }

    public void initPublicFileDirectory(String orgId) {
        PublicFileInitializer fileInitializer = new PublicFileInitializer(uploadRootDir, orgId);
        fileInitializer.initPublicPaths();

        for (Path publicPath : fileInitializer.getPublicPaths()) {
            if (Files.notExists(publicPath)) {
                try {
                    Files.createDirectory(publicPath);
                    System.out.println("Directory Created Success: " + publicPath.toString());
                } catch (IOException exception) {
                    System.out.println("Directory Created Failed: " + publicPath.toString());
                }
            } else {
                System.out.println("Directory Exist: " + publicPath.toString());
            }
        }
    }
}
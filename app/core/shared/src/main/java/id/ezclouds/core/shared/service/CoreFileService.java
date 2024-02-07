/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.shared.model.CoreMemberFileInfo;
import id.ezclouds.core.shared.model.MemberFileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

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

        System.out.println(fileInfo.getMemberFilePath().toString());

        try {
            if (Files.notExists(fileInfo.getOrgFilePath())) {
                Files.createDirectory(fileInfo.getOrgFilePath());
            }
            if (Files.notExists(fileInfo.getMemberFilePath())) {
                Files.createDirectory(fileInfo.getMemberFilePath());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new EzErrorException(EzErrorCode.SYSTEM_FILE_ERROR, "Error creating member directory");
        }

        MemberFileInfo memberFileInfo = new MemberFileInfo(
                fileInfo.getAvatarLocationWithPrefix(),
                fileInfo.getIdCardLocationWithPrefix(),
                fileInfo.getFamilyCardLocationWithPrefix()
        );
        return memberFileInfo;
    }
}
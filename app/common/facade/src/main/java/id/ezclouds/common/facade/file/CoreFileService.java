/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.file;

import id.ezclouds.common.model.file.PrivateFileResolver;
import id.ezclouds.common.model.file.PublicFileResolver;
import id.ezclouds.common.util.exception.EzErrorException;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreFileService.java, v 0.1 2024‐09‐21 6:03 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreFileService {

    PrivateFileResolver resolveMemberFileInfo(String orgId, String memberId) throws EzErrorException;

    PublicFileResolver resolvePublicFileInfo(String orgId);

    void storeFile(InputStream inputStream, Path targetPath) throws EzErrorException;

    void initPublicFileDirectory(String orgId);
}
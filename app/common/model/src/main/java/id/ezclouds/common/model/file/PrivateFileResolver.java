/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.file;

import java.nio.file.Path;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PrivateFileResolver.java, v 0.1 2024‐02‐08 3:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface PrivateFileResolver {

    Path getAvatarPath(String fileName);

    Path getIdCardPath(String fileName);

    Path getFamilyCardPath(String fileName);
}
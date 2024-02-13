/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.member;

import java.nio.file.Path;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PublicFileResolver.java, v 0.1 2024‐02‐09 2:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface PublicFileResolver {

    Path getAppGalleryPath(String fileName);

    Path getNewsGalleryPath(String fileName);

    Path getEventGalleryPath(String fileName);

    Path getVideoCardGalleryPath(String fileName);

    Path getOtherGalleryPath(String fileName);
}
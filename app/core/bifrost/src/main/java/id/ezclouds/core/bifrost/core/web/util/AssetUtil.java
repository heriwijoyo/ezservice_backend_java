/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.web.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AssetUtil.java, v 0.1 2024‐08‐25 9:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class AssetUtil {

    public static String parseAssetContent(String assetFile) throws IOException {
        Resource resource = new ClassPathResource(assetFile);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
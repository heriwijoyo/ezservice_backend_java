/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppConfig.java, v 0.1 2024‐09‐21 7:42 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Configuration
public class WebAppConfig {

    @Value("${ezserviceapp.web.config.release.mode}")
    private String webReleaseMode;

    @Value("${ezserviceapp.web.config.resource.dir}")
    private String webResourceDir;

    public String getWebReleaseMode() {
        return webReleaseMode;
    }

    public void setWebReleaseMode(String webReleaseMode) {
        this.webReleaseMode = webReleaseMode;
    }

    public String getWebResourceDir() {
        return webResourceDir;
    }

    public void setWebResourceDir(String webResourceDir) {
        this.webResourceDir = webResourceDir;
    }
}
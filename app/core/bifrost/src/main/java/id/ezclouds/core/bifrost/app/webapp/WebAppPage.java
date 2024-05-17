/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppPage.java, v 0.1 2024‐04‐27 9:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public enum WebAppPage {

    VIDEO_CARD("classpath:webapp/videocard.htm"),
    PROFILE("classpath:webapp/profile.htm"),
    WHATSAPP("classpath:webapp/whatsapp.htm"),

    ;

    private final String assetFile;

    WebAppPage(String assetFile) {
        this.assetFile = assetFile;
    }

    public String getAssetFile() {
        return assetFile;
    }
}
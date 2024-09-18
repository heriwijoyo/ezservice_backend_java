/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.webapp;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzWebAppContent.java, v 0.1 2024‐09‐19 1:57 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzWebAppContent {

    private String assetFileId;
    private String content;

    public String getAssetFileId() {
        return assetFileId;
    }

    public void setAssetFileId(String assetFileId) {
        this.assetFileId = assetFileId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
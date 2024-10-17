/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.web.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreWebAppContentDO.java, v 0.1 2024‐09‐19 2:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_core_webapp_content")
public class EzCoreWebAppContentDO {

    @Id
    @Column(name = "asset_file_id")
    private String assetFileId;

    @Column(name = "content")
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
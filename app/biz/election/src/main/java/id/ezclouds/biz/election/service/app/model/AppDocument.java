/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.model;

import id.ezclouds.biz.election.constant.AppConstant;
import id.ezclouds.common.model.annotation.PublicImageUrl;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppDocument.java, v 0.1 2024‐05‐25 3:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppDocument {

    private String id;
    private String type;
    private String title;
    @PublicImageUrl(name = AppConstant.Annotation.DOCUMENT_GALLERY_URL)
    private String docUrl;
    private String createdTime;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
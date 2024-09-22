/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ezclouds.biz.election.constant.AppConstant;
import id.ezclouds.biz.election.model.annotation.PublicImageUrl;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VideoCard.java, v 0.1 2023‐12‐10 12:12 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class VideoCard {

    private String id;
    @JsonIgnore
    private String orgId;
    private String section;
    private String sectionName;
    @PublicImageUrl(name = AppConstant.Annotation.VIDEO_CARD_GALLERY_URL)
    private String thumbnail;
    private String targetType;
    private String targetUrl;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
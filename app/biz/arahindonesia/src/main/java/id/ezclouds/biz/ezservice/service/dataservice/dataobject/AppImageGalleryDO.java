/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice.dataobject;

import javax.persistence.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppImageGalleryDO.java, v 0.1 2024‐02‐12 10:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "app_image_gallery")
public class AppImageGalleryDO {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "title")
    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "target_type")
    private String targetType;

    @Column(name = "target_url")
    private String targetUrl;

    @Column(name = "flag_home_slide")
    private int flagHomeSlide;

    @Column(name = "flag_portfolio_slide")
    private int flagPortfolioSlide = 0;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "sorting")
    private int sorting = 0;

    @Column(name = "status")
    private int status = 1;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public int getFlagHomeSlide() {
        return flagHomeSlide;
    }

    public void setFlagHomeSlide(int flagHomeSlide) {
        this.flagHomeSlide = flagHomeSlide;
    }

    public int getFlagPortfolioSlide() {
        return flagPortfolioSlide;
    }

    public void setFlagPortfolioSlide(int flagPortfolioSlide) {
        this.flagPortfolioSlide = flagPortfolioSlide;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getSorting() {
        return sorting;
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
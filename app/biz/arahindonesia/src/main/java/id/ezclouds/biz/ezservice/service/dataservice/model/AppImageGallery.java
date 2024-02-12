/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.biz.ezservice.model.annotation.PublicImageUrl;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppImageGallery.java, v 0.1 2024‐02‐12 11:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppImageGallery {

    @JsonIgnore
    private String orgId;
    private String title;
    @PublicImageUrl(name = AppConstant.Annotation.APP_GALLERY_URL)
    private String imageUrl;
    private String targetType;
    private String targetUrl;

    @JsonIgnore
    private int flagHomeSlide = 0;
    @JsonIgnore
    private int flagPortfolioSlide = 0;
    @JsonIgnore
    private int sorting = 0;

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

    public int getSorting() {
        return sorting;
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }
}
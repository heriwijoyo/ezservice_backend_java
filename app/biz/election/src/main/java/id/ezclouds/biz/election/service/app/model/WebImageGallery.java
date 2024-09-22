/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.model;

import id.ezclouds.biz.election.constant.AppConstant;
import id.ezclouds.biz.election.model.annotation.PublicImageUrl;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppImageGallery.java, v 0.1 2024‐02‐12 11:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebImageGallery {

    private String id;
    private String title;
    @PublicImageUrl(name = AppConstant.Annotation.APP_GALLERY_URL)
    private String imageUrl;
    private String targetType;
    private String targetUrl;

    private int flagHomeSlide;
    private int flagPortfolioSlide;
    private int flagMidBanner;
    private String createdTime;
    private int sorting;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getFlagMidBanner() {
        return flagMidBanner;
    }

    public void setFlagMidBanner(int flagMidBanner) {
        this.flagMidBanner = flagMidBanner;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
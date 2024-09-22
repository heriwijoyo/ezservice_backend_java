/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppImageGalleryRequest.java, v 0.1 2024‐02‐12 10:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppImageGalleryRequest {

    private String orgId;
    private String title;
    private String imageUrl;
    private String targetType;
    private String targetUrl;
    private int flagHomeSlide = 0;
    private int flagPortfolioSlide = 0;
    private int sorting = 0;
    private int status = 1;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.model.news;

import id.ezclouds.biz.election.constant.AppConstant;
import id.ezclouds.biz.election.model.annotation.PublicImageUrl;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSimpleNews.java, v 0.1 2023‐12‐10 11:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class BizSimpleNews {

    private String newsId;
    private String orgId;
    private String title;
    @PublicImageUrl(name = AppConstant.Annotation.NEWS_GALLERY_URL)
    private String imageUrl;
    private String description;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
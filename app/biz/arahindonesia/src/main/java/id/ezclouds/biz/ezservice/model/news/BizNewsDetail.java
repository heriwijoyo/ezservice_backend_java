/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.news;

import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.biz.ezservice.model.annotation.PublicImageUrl;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizNewsDetail.java, v 0.1 2024‐05‐05 7:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class BizNewsDetail {

    private String title;
    @PublicImageUrl(name = AppConstant.Annotation.NEWS_GALLERY_URL)
    private String imageUrl;
    private String content;
    private String source;
    private String sourceUrl;
    private String publishDate;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
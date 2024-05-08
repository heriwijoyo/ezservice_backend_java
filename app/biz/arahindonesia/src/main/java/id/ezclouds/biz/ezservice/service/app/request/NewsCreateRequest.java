/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: NewsCreateRequest.java, v 0.1 2024‐02‐13 2:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class NewsCreateRequest {

    private String orgId;
    private String category;
    private String title;
    private String imageUrl;
    private String description;
    private String content;
    private String source;
    private String sourceUrl;
    private String publishDate;
    private int status = 0;
    private int highlight = 0;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHighlight() {
        return highlight;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }
}
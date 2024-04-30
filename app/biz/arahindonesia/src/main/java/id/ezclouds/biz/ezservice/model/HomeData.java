/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model;

import id.ezclouds.biz.ezservice.model.news.BizSimpleNews;
import id.ezclouds.biz.ezservice.model.profile.BizCandidateProfile;
import id.ezclouds.biz.ezservice.service.dataservice.model.AppImageGallery;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: HomeData.java, v 0.1 2023‐12‐10 10:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class HomeData {

    private List<AppImageGallery> highlightBanners;
    private List<BizSimpleNews> highlightNews;
    private List<AppImageGallery> homePosters;
    private List<VideoSection> videoSections;
    private String pemiluDeadline;
    private List<String> communities = new ArrayList<>();
    private String midBannerUrl;
    private BizCandidateProfile bizCandidateProfile;

    public List<String> getCommunities() {
        return communities;
    }

    public void setCommunities(List<String> communities) {
        this.communities = communities;
    }

    public List<AppImageGallery> getHighlightBanners() {
        return highlightBanners;
    }

    public void setHighlightBanners(List<AppImageGallery> highlightBanners) {
        this.highlightBanners = highlightBanners;
    }

    public List<BizSimpleNews> getHighlightNews() {
        return highlightNews;
    }

    public void setHighlightNews(List<BizSimpleNews> highlightNews) {
        this.highlightNews = highlightNews;
    }

    public List<AppImageGallery> getHomePosters() {
        return homePosters;
    }

    public void setHomePosters(List<AppImageGallery> homePosters) {
        this.homePosters = homePosters;
    }

    public List<VideoSection> getVideoSections() {
        return videoSections;
    }

    public void setVideoSections(List<VideoSection> videoSections) {
        this.videoSections = videoSections;
    }

    public String getPemiluDeadline() {
        return pemiluDeadline;
    }

    public void setPemiluDeadline(String pemiluDeadline) {
        this.pemiluDeadline = pemiluDeadline;
    }

    public String getMidBannerUrl() {
        return midBannerUrl;
    }

    public void setMidBannerUrl(String midBannerUrl) {
        this.midBannerUrl = midBannerUrl;
    }

    public BizCandidateProfile getBizCandidateProfile() {
        return bizCandidateProfile;
    }

    public void setBizCandidateProfile(BizCandidateProfile bizCandidateProfile) {
        this.bizCandidateProfile = bizCandidateProfile;
    }
}
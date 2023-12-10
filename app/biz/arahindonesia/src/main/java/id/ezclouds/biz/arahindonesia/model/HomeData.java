/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.model;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: HomeData.java, v 0.1 2023‐12‐10 10:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class HomeData {

    private List<ImageSlide> highlightBanners;
    private List<SimpleNews> highlightNews;

    public List<ImageSlide> getHighlightBanners() {
        return highlightBanners;
    }

    public void setHighlightBanners(List<ImageSlide> highlightBanners) {
        this.highlightBanners = highlightBanners;
    }

    public List<SimpleNews> getHighlightNews() {
        return highlightNews;
    }

    public void setHighlightNews(List<SimpleNews> highlightNews) {
        this.highlightNews = highlightNews;
    }
}
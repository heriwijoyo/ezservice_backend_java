/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VideoSection.java, v 0.1 2023‐12‐10 12:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class VideoSection {

    private String sectionName;
    private List<VideoCard> videoCards = new ArrayList<>();

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<VideoCard> getVideoCards() {
        return videoCards;
    }

    public void setVideoCards(List<VideoCard> videoCards) {
        this.videoCards = videoCards;
    }
}
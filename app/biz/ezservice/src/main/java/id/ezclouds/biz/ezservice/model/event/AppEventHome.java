/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.event;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppEventHome.java, v 0.1 2024‐05‐09 11:36 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppEventHome {

    private AppEvent highlights;
    private List<AppEvent> events;

    public AppEvent getHighlights() {
        return highlights;
    }

    public void setHighlights(AppEvent highlights) {
        this.highlights = highlights;
    }

    public List<AppEvent> getEvents() {
        return events;
    }

    public void setEvents(List<AppEvent> events) {
        this.events = events;
    }
}
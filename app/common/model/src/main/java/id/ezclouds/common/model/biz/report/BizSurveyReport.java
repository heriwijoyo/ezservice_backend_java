/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyReport.java, v 0.1 2024‐11‐25 1:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSurveyReport {

    private String title;
    private final List<List<String>> data;

    public BizSurveyReport() {
        this.data = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<List<String>> getData() {
        return data;
    }
}
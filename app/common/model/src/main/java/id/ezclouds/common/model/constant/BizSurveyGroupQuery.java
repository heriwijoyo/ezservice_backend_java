/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.constant;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyGroupQuery.java, v 0.1 2024‐11‐25 3:24 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSurveyGroupQuery {

    private String title;
    private SurveyGroupQuery groupQuery;

    public BizSurveyGroupQuery(String title, SurveyGroupQuery groupQuery) {
        this.title = title;
        this.groupQuery = groupQuery;
    }

    public String getTitle() {
        return title;
    }

    public SurveyGroupQuery getGroupQuery() {
        return groupQuery;
    }
}
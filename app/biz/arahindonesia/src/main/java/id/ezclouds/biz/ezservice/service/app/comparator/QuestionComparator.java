/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.comparator;

import id.ezclouds.biz.ezservice.service.app.dataobject.BizSurveyQuestionDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: QuestionComparator.java, v 0.1 2024‐02‐17 6:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class QuestionComparator implements java.util.Comparator<BizSurveyQuestionDO> {
    @Override
    public int compare(BizSurveyQuestionDO o1, BizSurveyQuestionDO o2) {
        return o1.getSorting() - o2.getSorting();
    }
}
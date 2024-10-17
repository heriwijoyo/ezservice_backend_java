/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.comparator;

import id.ezclouds.biz.election.service.app.dataobject.BizSurveyAnswerOptionDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AnswerOptionComparator.java, v 0.1 2024‐02‐17 6:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AnswerOptionComparator implements java.util.Comparator<BizSurveyAnswerOptionDO> {
    @Override
    public int compare(BizSurveyAnswerOptionDO o1, BizSurveyAnswerOptionDO o2) {
        return o1.getSorting() - o2.getSorting();
    }
}
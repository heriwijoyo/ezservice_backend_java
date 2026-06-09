/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.survey.AppCommonDataSurvey;
import id.ezclouds.common.model.query.BizGroupQueryCount;
import id.ezclouds.common.model.query.BizSurveyGroupQueryParam;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppCommonDataSurveyDAO.java, v 0.1 2024‐08‐18 10:34 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AppCommonDataSurveyDAO {

    void store(AppCommonDataSurvey dataSurvey);

    List<AppCommonDataSurvey> getData(String orgId, String surveyId);

    List<BizGroupQueryCount> getGroupQueryCount(BizSurveyGroupQueryParam param);
}
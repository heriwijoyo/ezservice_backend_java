/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.survey.BizSurveyResponse;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyResponseDAO.java, v 0.1 2024‐08‐19 6:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizSurveyResponseDAO {

    List<BizSurveyResponse> getResponse(String orgId, String surveyId);
}
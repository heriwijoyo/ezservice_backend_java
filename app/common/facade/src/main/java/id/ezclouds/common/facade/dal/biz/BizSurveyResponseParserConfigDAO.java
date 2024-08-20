/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.survey.BizSurveyResponseParserConfig;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyResponseParserConfigDAO.java, v 0.1 2024‐08‐19 8:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizSurveyResponseParserConfigDAO {

    List<BizSurveyResponseParserConfig> getParserConfig(String orgId, String surveyId);
}
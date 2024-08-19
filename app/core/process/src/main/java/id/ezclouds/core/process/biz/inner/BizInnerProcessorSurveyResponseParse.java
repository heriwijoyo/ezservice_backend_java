/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz.inner;

import id.ezclouds.common.facade.dal.biz.BizSurveyResponseDAO;
import id.ezclouds.common.model.biz.survey.BizSurveyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizInnerProcessorSurveyResponseParse.java, v 0.1 2024‐08‐19 6:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizInnerProcessorSurveyResponseParse {

    @Autowired
    private BizSurveyResponseDAO bizSurveyResponseDAO;

    public List<BizSurveyResponse> getSurveyResponses(String orgId, String surveyId) {
        return bizSurveyResponseDAO.getResponse(orgId, surveyId);
    }
}
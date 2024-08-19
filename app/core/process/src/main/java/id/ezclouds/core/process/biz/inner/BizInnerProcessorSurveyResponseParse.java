/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz.inner;

import id.ezclouds.common.facade.dal.biz.AppCommonDataSurveyDAO;
import id.ezclouds.common.facade.dal.biz.BizSurveyResponseDAO;
import id.ezclouds.common.facade.dal.biz.BizSurveyResponseParserConfigDAO;
import id.ezclouds.common.model.biz.survey.BizSurveyResponse;
import id.ezclouds.common.model.biz.survey.BizSurveyResponseParserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizInnerProcessorSurveyResponseParse.java, v 0.1 2024‐08‐19 6:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizInnerProcessorSurveyResponseParse {

    @Autowired
    private BizSurveyResponseDAO bizSurveyResponseDAO;

    @Autowired
    private BizSurveyResponseParserConfigDAO bizSurveyResponseParserConfigDAO;

    @Autowired
    private AppCommonDataSurveyDAO appCommonDataSurveyDAO;

    public List<BizSurveyResponse> getSurveyResponses(String orgId, String surveyId) {
        return bizSurveyResponseDAO.getResponse(orgId, surveyId);
    }

    public Map<String, BizSurveyResponseParserConfig> getParserConfigMap(String orgId, String surveyId) {
        Map<String, BizSurveyResponseParserConfig> parserConfigMap = new HashMap<>();
        List<BizSurveyResponseParserConfig> parserConfigs = bizSurveyResponseParserConfigDAO
                .getParserConfig(orgId, surveyId);

        for (BizSurveyResponseParserConfig parserConfig : parserConfigs) {
            parserConfigMap.put(parserConfig.getId(), parserConfig);
        }

        return parserConfigMap;
    }

    @Transactional
    public void parseAndStore(BizSurveyResponse response, BizSurveyResponseParserConfig parserConfig) {
        if (parserConfig == null) {
            System.out.println("parserConfig IS NULL");
            return;
        }

        //convert response to data by config map
        appCommonDataSurveyDAO.store(null);
    }
}
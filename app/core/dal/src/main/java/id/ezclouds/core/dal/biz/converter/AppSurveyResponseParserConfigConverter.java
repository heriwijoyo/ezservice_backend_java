/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.survey.BizSurveyResponseParserConfig;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.EzSurveyResponseParserConfigDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyResponseParserConfigConverter.java, v 0.1 2024‐08‐19 8:57 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppSurveyResponseParserConfigConverter extends CommonDOModelConverter<EzSurveyResponseParserConfigDO, BizSurveyResponseParserConfig> {

    @Override
    protected BizSurveyResponseParserConfig safeConvertQuery(EzSurveyResponseParserConfigDO dataObject) {
        BizSurveyResponseParserConfig parserConfig = new BizSurveyResponseParserConfig();
        parserConfig.setId(dataObject.getId());
        parserConfig.setOrgId(dataObject.getOrgId());
        parserConfig.setSurveyId(dataObject.getSurveyId());
        parserConfig.setQuestionVersion(dataObject.getQuestionVersion());
        parserConfig.setParserMapping(dataObject.getParserMapping());
        return parserConfig;
    }

    @Override
    protected EzSurveyResponseParserConfigDO safeConvertStore(BizSurveyResponseParserConfig model) {
        return null;
    }
}
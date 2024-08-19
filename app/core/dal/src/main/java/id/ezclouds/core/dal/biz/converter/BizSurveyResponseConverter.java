/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.survey.BizSurveyResponse;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.EzSurveyResponseDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyResponseConverter.java, v 0.1 2024‐08‐19 6:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSurveyResponseConverter extends CommonDOModelConverter<EzSurveyResponseDO, BizSurveyResponse> {

    @Override
    protected BizSurveyResponse safeConvertQuery(EzSurveyResponseDO dataObject) {
        BizSurveyResponse response = new BizSurveyResponse();
        response.setId(dataObject.getId());
        response.setOrgId(dataObject.getOrgId());
        response.setSurveyId(dataObject.getSurveyId());
        response.setQuestionVersion(dataObject.getQuestionVersion());
        response.setSubmitterMemberId(dataObject.getSubmitterMemberId());
        response.setResponderData(dataObject.getResponderData());
        response.setResponseData(dataObject.getResponseData());
        return response;
    }

    @Override
    protected EzSurveyResponseDO safeConvertStore(BizSurveyResponse model) {
        return null;
    }
}
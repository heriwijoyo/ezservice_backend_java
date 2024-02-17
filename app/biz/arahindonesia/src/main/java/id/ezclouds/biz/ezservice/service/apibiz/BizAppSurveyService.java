/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.model.survey.BizSurveyForm;
import id.ezclouds.biz.ezservice.service.dataservice.AppSurveyDataService;
import id.ezclouds.biz.ezservice.service.dataservice.request.AppSurveyResponseRequest;
import id.ezclouds.biz.ezservice.service.request.BizSurveySubmitRequest;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAppSurveyService.java, v 0.1 2024‐02‐14 2:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAppSurveyService extends BizBaseService {

    @Autowired
    private AppSurveyDataService appSurveyDataService;

    public BizResult getSurveyForm(String surveyId) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(surveyId, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                authMemberSession();
                BizSurveyForm bizSurveyForm = appSurveyDataService.getBizSurveyForm(surveyId);
                AssertUtil.notNull(bizSurveyForm, EzErrorCode.DATA_NOT_FOUND);

                bizResult.setSuccess(true);
                bizResult.setObject(bizSurveyForm);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult submitSurvey(BizSurveySubmitRequest request) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSurveyId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getQuestionVersion(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getResponderDataEncoded(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getResponseDataEncoded(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = authMemberSession();
                AppSurveyResponseRequest surveyRequest = new AppSurveyResponseRequest();
                surveyRequest.setOrgId(getOrgId());
                surveyRequest.setSurveyId(request.getSurveyId());
                surveyRequest.setSubmitterMemberId(sessionInfo.getMemberId());
                surveyRequest.setQuestionVersion(request.getQuestionVersion());
                surveyRequest.setResponderDataEncoded(request.getResponderDataEncoded());
                surveyRequest.setResponseDataEncoded(request.getResponseDataEncoded());

                appSurveyDataService.submitSurvey(surveyRequest);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.apibiz;

import id.ezclouds.biz.election.service.app.request.AppSurveyResponseRequest;
import id.ezclouds.biz.election.service.request.BizSurveySubmitRequest;
import id.ezclouds.biz.election.enums.BizUniqueScene;
import id.ezclouds.biz.election.model.survey.BizSurveyForm;
import id.ezclouds.biz.election.service.app.AppSurveyDataService;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import id.ezclouds.core.shared.result.CoreResult;
import id.ezclouds.core.shared.service.CoreUniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAppSurveyService.java, v 0.1 2024‐02‐14 2:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAppSurveyService extends BizBaseService {

    @Autowired
    private CoreUniqueService coreUniqueService;

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
                authAppMemberSession();
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
                AssertUtil.notBlank(request.getRequestId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.isTrue(request.getRequestId().length() <= 52, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSurveyId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getQuestionVersion(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getResponderDataEncoded(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getResponseDataEncoded(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = authAppMemberSession();

                CoreResult<Boolean> uniqueResult = coreUniqueService.insertUnique(
                        getOrgId(), BizUniqueScene.BIZ_SURVEY_RESPONSE.getCode(), request.getRequestId());

                if (uniqueResult.isSuccess()) {
                    AppSurveyResponseRequest surveyRequest = new AppSurveyResponseRequest();
                    surveyRequest.setRequestId(request.getRequestId());
                    surveyRequest.setOrgId(getOrgId());
                    surveyRequest.setSurveyId(request.getSurveyId());
                    surveyRequest.setSubmitterMemberId(sessionInfo.getMemberId());
                    surveyRequest.setQuestionVersion(request.getQuestionVersion());
                    surveyRequest.setResponderDataEncoded(request.getResponderDataEncoded());
                    surveyRequest.setResponseDataEncoded(request.getResponseDataEncoded());

                    try {
                        String submitId = appSurveyDataService.submitSurvey(surveyRequest);
                        bizResult.setSuccess(true);
                        bizResult.setObject(submitId);
                    } catch (Exception e) {
                        coreUniqueService.revertUnique(
                                getOrgId(), BizUniqueScene.BIZ_SURVEY_RESPONSE.getCode(), request.getRequestId());
                        throw new EzErrorException(EzErrorCode.SYSTEM_ERROR);
                    }

                } else {
                    if (uniqueResult.getErrorCode() == EzErrorCode.IDEMPOTENT_ERROR) {
                        bizResult.setSuccess(true);
                        bizResult.setObject(appSurveyDataService.getSubmitIdByRequestId(request.getRequestId()));
                    } else {
                        throw new EzErrorException(EzErrorCode.SYSTEM_ERROR);
                    }
                }

                if (bizResult.isSuccess()) {
                    appSurveyDataService.processResponseAsync((String)bizResult.getObject());
                }
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }
}
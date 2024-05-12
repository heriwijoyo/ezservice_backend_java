/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.model.survey.*;
import id.ezclouds.biz.ezservice.service.app.comparator.AnswerOptionComparator;
import id.ezclouds.biz.ezservice.service.app.comparator.QuestionComparator;
import id.ezclouds.biz.ezservice.service.app.comparator.ResponderComparator;
import id.ezclouds.biz.ezservice.service.app.converter.AppModelConverter;
import id.ezclouds.biz.ezservice.service.app.dataobject.*;
import id.ezclouds.biz.ezservice.service.app.model.AppAsyncScene;
import id.ezclouds.biz.ezservice.service.app.processor.request.AppSurveyResponseProcessRequest;
import id.ezclouds.biz.ezservice.service.app.processor.result.ProcessResult;
import id.ezclouds.biz.ezservice.service.app.repo.*;
import id.ezclouds.biz.ezservice.service.app.request.AppAsyncRequest;
import id.ezclouds.biz.ezservice.service.app.request.AppSurveyResponseRequest;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyDataService.java, v 0.1 2024‐02‐16 8:04 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppSurveyDataService {

    private static final int N_TOP_SURVEY = 2;

    @Autowired
    private BizSurveyRepository bizSurveyRepository;

    @Autowired
    private BizSurveyResponderRepository bizSurveyResponderRepository;

    @Autowired
    private BizSurveyQuestionRepository bizSurveyQuestionRepository;

    @Autowired
    private BizSurveyAnswerOptionRepository bizSurveyAnswerOptionRepository;

    @Autowired
    private BizSurveyResponseRepository bizSurveyResponseRepository;

    @Autowired
    private AppSurveyProcessorConfigRepository appSurveyProcessorConfigRepository;

    @Autowired
    private AppAsyncService appAsyncService;

    @Transactional
    public String submitSurvey(AppSurveyResponseRequest request) {
        BizSurveyResponseDO responseDO = new BizSurveyResponseDO();
        responseDO.setId(HashUtil.createHash(request.getRequestId()));
        responseDO.setRequestId(request.getRequestId());
        responseDO.setOrgId(request.getOrgId());
        responseDO.setSurveyId(request.getSurveyId());
        responseDO.setQuestionVersion(request.getQuestionVersion());
        responseDO.setSubmitterMemberId(request.getSubmitterMemberId());
        responseDO.setResponderData(request.getResponderDataEncoded());
        responseDO.setResponseData(request.getResponseDataEncoded());
        responseDO.setCreatedTime(DateUtil.getCurrentFormattedDate());
        bizSurveyResponseRepository.saveAndFlush(responseDO);

        return responseDO.getId();
    }

    public String getSubmitIdByRequestId(String requestId) {
        BizSurveyResponseDO responseDO = bizSurveyResponseRepository
                .findByRequestId(requestId);
        AssertUtil.notNull(responseDO, EzErrorCode.DATA_NOT_FOUND);
        return responseDO.getId();
    }

    @Async
    public void processResponseAsync(String responseId) {
        if (StringUtil.isBlank(responseId)) {
            return;
        }

        BizSurveyResponseDO responseDO = bizSurveyResponseRepository
                .findById(responseId)
                .orElse(null);
        if (responseDO == null) {
            System.out.println("BizSurveyResponseDO is NULL");
            return;
        }
        if (StringUtil.isNotBlank(responseDO.getProcessId()) && StringUtil.isNotBlank(responseDO.getProcessTime())) {
            System.out.println("BizSurveyResponseDO.processId or processTime is BLANK");
            return;
        }

        AppSurveyProcessorConfigDO processorConfigDO = appSurveyProcessorConfigRepository
                .findByOrgIdAndSurveyIdAndQuestionVersion(
                        responseDO.getOrgId(),
                        responseDO.getSurveyId(),
                        responseDO.getQuestionVersion()
                );
        if (processorConfigDO == null || processorConfigDO.getStatus() < 1) {
            return;
        }

        AppSurveyResponseProcessRequest processRequest = new AppSurveyResponseProcessRequest();
        processRequest.setOrgId(responseDO.getOrgId());
        processRequest.setSurveyId(responseDO.getSurveyId());
        processRequest.setResponseId(responseDO.getId());
        processRequest.setSubmitterId(responseDO.getSubmitterMemberId());
        processRequest.setQuestionVersion(responseDO.getQuestionVersion());
        processRequest.setParserCode(processorConfigDO.getParserCode());
        processRequest.setParserMap(processorConfigDO.getParserMapping());

        try {
            Map<String, Object> responderData = new ObjectMapper()
                    .readValue(responseDO.getResponderData(), new TypeReference<Map<String, Object>>(){});
            processRequest.setResponderData(responderData);
        } catch (Exception e) {
            System.out.println("Failed to parse RESPONDER DATA");
        }

        try {
            List<QuestionnaireData> responseData = new ObjectMapper()
                    .readValue(responseDO.getResponseData(), new TypeReference<List<QuestionnaireData>>(){});
            processRequest.setResponseData(responseData);
        } catch (Exception e) {
            System.out.println("Failed to parse RESPONSE DATA");
        }

        AppAsyncRequest request = new AppAsyncRequest();
        request.setAppAsyncScene(AppAsyncScene.RJL_COMMON_SURVEY_PROCESS);
        request.setData(processRequest);

        ProcessResult result = appAsyncService.process(request);
        if (result.isSuccess()) {
            responseDO.setProcessId(result.getProcessId());
            responseDO.setProcessTime(result.getProcessTime());
            updateResponseData(responseDO);
        }
    }

    @Transactional
    public void updateResponseData(BizSurveyResponseDO responseDO) {
        bizSurveyResponseRepository.saveAndFlush(responseDO);
    }

    public BizSurveyForm getBizSurveyForm(String surveyId) {
        return getTopSurveyAllOrg().get(surveyId);
    }

    @Cacheable(value = "topBizSurveyData")
    public Map<String, BizSurveyForm> getTopSurveyAllOrg() {
        Map<String, BizSurveyForm> bizSurveyFormMap = new HashMap<>();

        List<BizSurveyDO> bizSurveys = bizSurveyRepository
                .findTopSurveyActive(PageRequest.of(0, N_TOP_SURVEY));

        List<String> availSurveyIds = bizSurveys
                .stream()
                .map(BizSurveyDO::getSurveyId)
                .collect(Collectors.toList());

        final List<BizSurveyResponderDO> allResponders = fetchAllResponder(availSurveyIds);
        final List<BizSurveyQuestionDO> allQuestions = fetchAllSurveysQuestion(availSurveyIds);
        final List<BizSurveyAnswerOptionDO> allAnswerOptions = fetchAllSurveysAnswerOption(availSurveyIds);

        for (BizSurveyDO surveyDO : bizSurveys) {
            BizSurveyForm bizSurveyForm = new BizSurveyForm();
            bizSurveyForm.setSurveyId(surveyDO.getSurveyId());
            bizSurveyForm.setResponderForms(getSortedResponderForm(surveyDO.getSurveyId(), allResponders));
            bizSurveyForm.setQuestionnaireVersion(surveyDO.getQuestionVersion());
            bizSurveyForm.setQuestionnaireForms(getSortedQuestionForm(surveyDO.getSurveyId(), allQuestions, allAnswerOptions));

            bizSurveyFormMap.put(surveyDO.getSurveyId(), bizSurveyForm);
        }

        return bizSurveyFormMap;
    }

    private List<BizSurveyResponderDO> fetchAllResponder(List<String> surveyIds) {
        return bizSurveyResponderRepository
                .findBySurveyIdInAndStatus(surveyIds, 1);
    }

    private List<BizSurveyQuestionDO> fetchAllSurveysQuestion(List<String> surveyIds) {
        return bizSurveyQuestionRepository
                .findBySurveyIdInAndStatus(surveyIds, 1);
    }

    private List<BizSurveyAnswerOptionDO> fetchAllSurveysAnswerOption(List<String> surveyIds) {
        return bizSurveyAnswerOptionRepository
                .findBySurveyIdInAndStatus(surveyIds, 1);
    }

    private List<ResponderForm> getSortedResponderForm(String surveyId, List<BizSurveyResponderDO> responders) {
        return responders
                .stream()
                .filter(responder -> surveyId.equals(responder.getSurveyId()))
                .sorted(new ResponderComparator())
                .map(AppModelConverter::convert)
                .collect(Collectors.toList());
    }

    private List<QuestionForm> getSortedQuestionForm(String surveyId, List<BizSurveyQuestionDO> questions, List<BizSurveyAnswerOptionDO> answerOptions) {
        List<QuestionForm> sortedQuestionForm = questions
                .stream()
                .filter(question -> question.getSurveyId().equals(surveyId))
                .sorted(new QuestionComparator())
                .map(AppModelConverter::convert)
                .collect(Collectors.toList());

        int lblCount = 1;
        for (QuestionForm questionForm : sortedQuestionForm) {
            questionForm.setOptions(getSortedOptions(surveyId, questionForm.getQid(), answerOptions));
            questionForm.setQuestionnaireNumber(String.valueOf(lblCount));
            questionForm.setQuestionnaireNumberLabel("Kuesioner No " + lblCount);
            lblCount++;
        }

        return sortedQuestionForm;
    }

    private List<AnswerOption> getSortedOptions(String surveyId, String questionId, List<BizSurveyAnswerOptionDO> answerOptions) {
        return answerOptions
                .stream()
                .filter(answer -> answer.getSurveyId().equals(surveyId) && answer.getQuestionId().equals(questionId))
                .sorted(new AnswerOptionComparator())
                .map(answerDO -> {
                    AnswerOption answerOption = new AnswerOption();
                    answerOption.setKey(answerDO.getKey());
                    answerOption.setValue(answerDO.getValue());
                    return answerOption;
                })
                .collect(Collectors.toList());
    }
}
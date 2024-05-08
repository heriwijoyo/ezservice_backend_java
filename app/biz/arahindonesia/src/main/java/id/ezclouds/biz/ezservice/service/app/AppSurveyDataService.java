/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app;

import id.ezclouds.biz.ezservice.model.survey.AnswerOption;
import id.ezclouds.biz.ezservice.model.survey.BizSurveyForm;
import id.ezclouds.biz.ezservice.model.survey.QuestionForm;
import id.ezclouds.biz.ezservice.model.survey.ResponderForm;
import id.ezclouds.biz.ezservice.service.app.comparator.AnswerOptionComparator;
import id.ezclouds.biz.ezservice.service.app.comparator.QuestionComparator;
import id.ezclouds.biz.ezservice.service.app.comparator.ResponderComparator;
import id.ezclouds.biz.ezservice.service.app.converter.AppModelConverter;
import id.ezclouds.biz.ezservice.service.app.dataobject.*;
import id.ezclouds.biz.ezservice.service.app.repo.*;
import id.ezclouds.biz.ezservice.service.app.request.AppSurveyResponseRequest;
import id.ezclouds.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
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

    @Transactional
    public void submitSurvey(AppSurveyResponseRequest request) {
        BizSurveyResponseDO responseDO = new BizSurveyResponseDO();
        responseDO.setOrgId(request.getOrgId());
        responseDO.setSurveyId(request.getSurveyId());
        responseDO.setQuestionVersion(request.getQuestionVersion());
        responseDO.setSubmitterMemberId(request.getSubmitterMemberId());
        responseDO.setResponderData(request.getResponderDataEncoded());
        responseDO.setResponseData(request.getResponseDataEncoded());
        responseDO.setCreatedTime(DateUtil.getCurrentFormattedDate());

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
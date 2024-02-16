/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.model.survey.AnswerOption;
import id.ezclouds.biz.ezservice.model.survey.BizSurveyForm;
import id.ezclouds.biz.ezservice.model.survey.QuestionForm;
import id.ezclouds.biz.ezservice.service.dataservice.comparator.AnswerOptionComparator;
import id.ezclouds.biz.ezservice.service.dataservice.comparator.QuestionComparator;
import id.ezclouds.biz.ezservice.service.dataservice.converter.DataObjectConverter;
import id.ezclouds.biz.ezservice.service.dataservice.dataobject.BizSurveyAnswerOptionDO;
import id.ezclouds.biz.ezservice.service.dataservice.dataobject.BizSurveyDO;
import id.ezclouds.biz.ezservice.service.dataservice.dataobject.BizSurveyQuestionDO;
import id.ezclouds.biz.ezservice.service.dataservice.repo.BizSurveyAnswerOptionRepository;
import id.ezclouds.biz.ezservice.service.dataservice.repo.BizSurveyQuestionRepository;
import id.ezclouds.biz.ezservice.service.dataservice.repo.BizSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    private BizSurveyQuestionRepository bizSurveyQuestionRepository;

    @Autowired
    private BizSurveyAnswerOptionRepository bizSurveyAnswerOptionRepository;

    @Cacheable(value = "topBizSurveyData")
    public Map<String, BizSurveyForm> getTopSurveyAllOrg() {
        Map<String, BizSurveyForm> bizSurveyFormMap = new HashMap<>();

        List<BizSurveyDO> bizSurveys = bizSurveyRepository
                .findTopSurveyActive(PageRequest.of(0, N_TOP_SURVEY));

        List<String> availSurveyIds = bizSurveys
                .stream()
                .map(BizSurveyDO::getSurveyId)
                .collect(Collectors.toList());

        final List<BizSurveyQuestionDO> allQuestions = fetchAllSurveysQuestion(availSurveyIds);
        final List<BizSurveyAnswerOptionDO> allAnswerOptions = fetchAllSurveysAnswerOption(availSurveyIds);

        for (BizSurveyDO surveyDO : bizSurveys) {
            BizSurveyForm bizSurveyForm = new BizSurveyForm();
            bizSurveyForm.setSurveyId(surveyDO.getSurveyId());
            bizSurveyForm.setQuestionnaireVersion(surveyDO.getQuestionVersion());
            bizSurveyForm.setQuestionnaireForms(getSortedQuestionForm(surveyDO.getSurveyId(), allQuestions, allAnswerOptions));

            bizSurveyFormMap.put(surveyDO.getSurveyId(), bizSurveyForm);
        }

        return bizSurveyFormMap;
    }

    private List<BizSurveyQuestionDO> fetchAllSurveysQuestion(List<String> surveyIds) {
        return bizSurveyQuestionRepository
                .findBySurveyIdInAndStatus(surveyIds, 1);
    }

    private List<BizSurveyAnswerOptionDO> fetchAllSurveysAnswerOption(List<String> surveyIds) {
        return bizSurveyAnswerOptionRepository
                .findBySurveyIdInAndStatus(surveyIds, 1);
    }

    private List<QuestionForm> getSortedQuestionForm(String surveyId, List<BizSurveyQuestionDO> questions, List<BizSurveyAnswerOptionDO> answerOptions) {
        List<QuestionForm> sortedQuestionForm = questions
                .stream()
                .filter(question -> question.getSurveyId().equals(surveyId))
                .sorted(new QuestionComparator())
                .map(DataObjectConverter::convert)
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
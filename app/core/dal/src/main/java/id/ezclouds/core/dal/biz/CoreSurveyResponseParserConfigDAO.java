/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizSurveyResponseParserConfigDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.survey.BizSurveyResponseParserConfig;
import id.ezclouds.core.dal.biz.converter.AppSurveyResponseParserConfigConverter;
import id.ezclouds.core.dal.biz.repo.EzSurveyResponseParserConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSurveyResponseParserConfigDAO.java, v 0.1 2024‐08‐19 8:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreSurveyResponseParserConfigDAO implements BizSurveyResponseParserConfigDAO {

    @Autowired
    private EzSurveyResponseParserConfigRepository ezSurveyResponseParserConfigRepository;

    @EzDAOLogger
    @Override
    public List<BizSurveyResponseParserConfig> getParserConfig(String orgId, String surveyId) {
        AppSurveyResponseParserConfigConverter converter = new AppSurveyResponseParserConfigConverter();
        return ezSurveyResponseParserConfigRepository
                .findByOrgIdAndSurveyId(orgId, surveyId)
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());
    }
}
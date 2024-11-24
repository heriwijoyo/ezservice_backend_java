/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.AppCommonDataSurveyDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.survey.AppCommonDataSurvey;
import id.ezclouds.common.model.query.BizGroupQueryCount;
import id.ezclouds.common.model.query.BizSurveyGroupQueryParam;
import id.ezclouds.core.dal.biz.converter.AppCommonDataSurveyConverter;
import id.ezclouds.core.dal.biz.repo.EzCommonDataSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreCommonDataSurveyDAO.java, v 0.1 2024‐08‐18 10:35 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreCommonDataSurveyDAO implements AppCommonDataSurveyDAO {

    @Autowired
    private EzCommonDataSurveyRepository ezCommonDataSurveyRepository;

    @Override
    public void store(AppCommonDataSurvey dataSurvey) {
        ezCommonDataSurveyRepository
                .saveAndFlush(
                        AppCommonDataSurveyConverter
                                .convert(dataSurvey)
                );
    }

    @Override
    @EzDAOLogger
    public List<AppCommonDataSurvey> getData(String orgId, String surveyId) {
        return ezCommonDataSurveyRepository
                .findByOrgIdAndSurveyId(orgId, surveyId)
                .stream()
                .map(AppCommonDataSurveyConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @EzDAOLogger
    public List<BizGroupQueryCount> getGroupQueryCount(BizSurveyGroupQueryParam param) {
        switch (param.getGroupQuery()) {
            case BY_SUBMITTER:
                return ezCommonDataSurveyRepository
                        .queryGroupSubmitter(param.getOrgId(), param.getSurveyId());
        }
        return new ArrayList<>();
    }
}
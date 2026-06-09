/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizSurveyResponseDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.survey.BizSurveyResponse;
import id.ezclouds.core.dal.biz.converter.BizSurveyResponseConverter;
import id.ezclouds.core.dal.biz.dataobject.EzSurveyResponseDO;
import id.ezclouds.core.dal.biz.repo.EzSurveyResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSurveyResponseDAO.java, v 0.1 2024‐08‐19 6:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreSurveyResponseDAO implements BizSurveyResponseDAO {

    @Autowired
    private EzSurveyResponseRepository ezSurveyResponseRepository;

    @EzDAOLogger
    @Override
    public List<BizSurveyResponse> getResponse(String orgId, String surveyId) {
        BizSurveyResponseConverter converter = new BizSurveyResponseConverter();
        return ezSurveyResponseRepository
                .findByOrgIdAndSurveyId(orgId, surveyId)
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());
    }

    @Override
    @EzDAOLogger
    public BizSurveyResponse getById(String responseId) {
        return new BizSurveyResponseConverter()
                .convertQuery(
                        ezSurveyResponseRepository
                                .findById(responseId)
                                .orElse(null)
                );
    }

    @Override
    @EzDAOLogger
    public void updateResponse(String responseId, String processId, String processTime, String processMessage) {
        EzSurveyResponseDO response = ezSurveyResponseRepository
                .findById(responseId)
                .orElse(null);

        if (response != null) {
            response.setProcessId(processId);
            response.setProcessTime(processTime);
            response.setProcessMessage(processMessage);

            ezSurveyResponseRepository.saveAndFlush(response);
        }
    }
}
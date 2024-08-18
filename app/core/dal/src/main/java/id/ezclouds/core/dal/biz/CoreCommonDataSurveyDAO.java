/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.AppCommonDataSurveyDAO;
import id.ezclouds.common.model.biz.survey.AppCommonDataSurvey;
import id.ezclouds.core.dal.biz.converter.AppCommonDataSurveyConverter;
import id.ezclouds.core.dal.biz.repo.EzCommonDataSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
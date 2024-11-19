/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizSurveyTableDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.survey.BizSurveyTable;
import id.ezclouds.core.dal.biz.converter.BizSurveyTableConverter;
import id.ezclouds.core.dal.biz.repo.BizSurveyTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyTableDAOImpl.java, v 0.1 2024‐11‐20 1:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class BizSurveyTableDAOImpl implements BizSurveyTableDAO {

    @Autowired
    private BizSurveyTableRepository bizSurveyTableRepository;

    @Override
    @EzDAOLogger
    public BizSurveyTable getByTableId(String tableId) {
        return new BizSurveyTableConverter()
                .convertQuery(
                        bizSurveyTableRepository
                                .findById(tableId)
                                .orElse(null)
                );
    }
}
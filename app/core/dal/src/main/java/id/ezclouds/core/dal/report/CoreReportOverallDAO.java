/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report;

import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.core.dal.report.converter.BizReportOverallConverter;
import id.ezclouds.core.dal.report.dataobject.CoreReportOverallDO;
import id.ezclouds.core.dal.report.repo.CoreReportOverallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportOverallDAO.java, v 0.1 2024‐07‐28 7:54 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreReportOverallDAO implements BizReportOverallDAO {

    @Autowired
    private CoreReportOverallRepository coreReportOverallRepository;

    @EzDAOLogger
    @Override
    public void store(BizReportOverall bizReportOverall) {
        CoreReportOverallDO coreReportOverallDO = BizReportOverallConverter.convert(bizReportOverall);
        coreReportOverallRepository.saveAndFlush(coreReportOverallDO);
    }

    @EzDAOLogger
    @Override
    public long deleteAll(String orgId) {
        return coreReportOverallRepository.deleteByOrgId(orgId);
    }
}
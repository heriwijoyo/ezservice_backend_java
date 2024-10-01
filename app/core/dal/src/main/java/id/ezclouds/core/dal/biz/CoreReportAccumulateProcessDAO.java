/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateProcessDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.report.BizReportAccumulateProcess;
import id.ezclouds.core.dal.biz.converter.BizReportAccumulateProcessConverter;
import id.ezclouds.core.dal.biz.repo.BizReportAccumulateProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportAccumulateProcessDAO.java, v 0.1 2024‐10‐02 2:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreReportAccumulateProcessDAO implements BizReportAccumulateProcessDAO {

    @Autowired
    private BizReportAccumulateProcessRepository bizReportAccumulateProcessRepository;

    @Override
    @EzDAOLogger
    public void store(BizReportAccumulateProcess accumulateProcess) {
        bizReportAccumulateProcessRepository
                .saveAndFlush(
                        new BizReportAccumulateProcessConverter()
                                .convertStore(accumulateProcess)
                );
    }

    @Override
    @EzDAOLogger
    public BizReportAccumulateProcess getAndLock(String processId) {
        return new BizReportAccumulateProcessConverter()
                .convertQuery(
                        bizReportAccumulateProcessRepository
                                .findAndLockById(processId)
                );
    }
}
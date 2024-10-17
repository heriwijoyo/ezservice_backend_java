/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateMemberDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.report.BizReportAccumulateMember;
import id.ezclouds.core.dal.biz.converter.BizReportAccumulateMemberConverter;
import id.ezclouds.core.dal.biz.repo.BizReportAccumulateMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizReportAccumulateMemberDAO.java, v 0.1 2024‐10‐10 2:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizReportAccumulateMemberDAO implements BizReportAccumulateMemberDAO {

    @Autowired
    private BizReportAccumulateMemberRepository bizReportAccumulateMemberRepository;

    @Override
    @EzDAOLogger
    public BizReportAccumulateMember getAndLock(String accumulateId) {
        return new BizReportAccumulateMemberConverter()
                .convertQuery(
                        bizReportAccumulateMemberRepository
                                .findAndLockById(accumulateId)
                );
    }

    @Override
    @EzDAOLogger
    public void store(BizReportAccumulateMember accumulateMember) {
        bizReportAccumulateMemberRepository
                .saveAndFlush(
                        new BizReportAccumulateMemberConverter()
                                .convertStore(accumulateMember)
                );
    }
}
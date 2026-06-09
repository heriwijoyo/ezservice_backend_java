/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report.accumulate;

import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.common.model.process.ProcessStatus;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateMemberRegister.java, v 0.1 2024‐10‐23 5:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope("prototype")
@Qualifier("bizReportAccumulateMemberRegister")
public class BizReportAccumulateMemberRegister implements ReportAccumulateProcessor {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Override
    public void process(String orgId, Object payload, ReportAccumulateProcessHandler handler) {
        CoreMember coreMember = (CoreMember) payload;

        try {

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {

                    BizReportOverall reportOverall = bizReportOverallDAO
                            .getAndLock(orgId, BizReportOverallKey.VOTER_BASE_MEMBER_COUNT);
                    int updateCount = reportOverall.getCount() + 1;
                    reportOverall.setCount(updateCount);
                    reportOverall.setUpdatedTime(DateUtil.getCurrentFormattedDateMillis());
                    bizReportOverallDAO.store(reportOverall);

                }
            });
            handler.onFinished(ProcessStatus.SUCCESS, null);

        } catch (Exception e) {
            handler.onFinished(ProcessStatus.EXCEPTION, ExceptionUtil.getStackTrace(e));
        }
    }
}
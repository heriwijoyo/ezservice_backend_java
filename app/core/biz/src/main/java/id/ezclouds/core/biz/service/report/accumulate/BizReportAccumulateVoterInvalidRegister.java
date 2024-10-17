/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report.accumulate;

import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateMemberDAO;
import id.ezclouds.common.model.biz.election.BizVoterInvalid;
import id.ezclouds.common.model.biz.report.BizAccumulateMemberKey;
import id.ezclouds.common.model.biz.report.BizReportAccumulateMember;
import id.ezclouds.common.model.process.ProcessStatus;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
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
 * @version $Id: BizReportAccumulateVoterInvalidRegister.java, v 0.1 2024‐10‐10 4:09 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope("prototype")
@Qualifier("bizReportAccumulateVoterInvalidRegister")
public class BizReportAccumulateVoterInvalidRegister implements ReportAccumulateProcessor {

    @Autowired
    private BizReportAccumulateMemberDAO bizReportAccumulateMemberDAO;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void process(String orgId, Object payload, ReportAccumulateProcessHandler handler) {
        BizVoterInvalid bizVoter = (BizVoterInvalid) payload;

        try {

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    processAccumulateMember(bizVoter);
                    handler.onFinished(ProcessStatus.SUCCESS, null);
                }
            });

        } catch (Exception e) {
            handler.onFinished(ProcessStatus.EXCEPTION, ExceptionUtil.getStackTrace(e));
        }
    }

    private void processAccumulateMember(BizVoterInvalid bizVoter) {
        BizAccumulateMemberKey accumulateMemberKey = StringUtil.equals(bizVoter.getInvalidMessage(), "Data Duplikat")
                ? BizAccumulateMemberKey.VOTER_DUPLICATE : BizAccumulateMemberKey.VOTER_INVALID;

        String accumulateId = HashUtil.createHash(
                bizVoter.getOrgId(),
                bizVoter.getReferrerId(),
                accumulateMemberKey.getKey(),
                "DEFAULT"
        );

        BizReportAccumulateMember accumulateMember = bizReportAccumulateMemberDAO
                .getAndLock(accumulateId);
        if (accumulateMember == null) {
            accumulateMember = new BizReportAccumulateMember();
            accumulateMember.setAccumulateMemberId(accumulateId);
            accumulateMember.setOrgId(bizVoter.getOrgId());
            accumulateMember.setMemberId(bizVoter.getReferrerId());
            accumulateMember.setAccumulateKey(accumulateMemberKey);
            accumulateMember.setAccumulateVariable("DEFAULT");
            accumulateMember.setAccumulateCount(0);
        }

        int increasedCount = accumulateMember.getAccumulateCount() + 1;
        accumulateMember.setAccumulateCount(increasedCount);
        accumulateMember.setModifiedTime(DateUtil.getCurrentFormattedDateMillis());

        bizReportAccumulateMemberDAO.store(accumulateMember);
    }
}
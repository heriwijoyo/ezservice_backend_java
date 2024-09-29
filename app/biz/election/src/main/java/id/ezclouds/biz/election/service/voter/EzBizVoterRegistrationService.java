/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.voter;

import id.ezclouds.common.facade.auth.AuthBizMemberService;
import id.ezclouds.common.facade.biz.election.BizVoterRegistrationService;
import id.ezclouds.common.facade.biz.election.VoterRegistrationService;
import id.ezclouds.common.facade.biz.util.BizContextUtil;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.auth.AuthMemberClientSession;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.common.util.exception.BizErrorMessageHelper;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzBizVoterRegistrationService.java, v 0.1 2024‐09‐28 11:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzBizVoterRegistrationService implements BizVoterRegistrationService {

    @Autowired
    private VoterRegistrationService voterRegistrationService;

    @Autowired
    private AuthBizMemberService authBizMemberService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public BizResult registerVoter(BizVoter bizVoter) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(null, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(bizVoter, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {

                String memberSessionId = BizContextUtil.getMemberSessionId();
                AssertUtil.notBlank(memberSessionId, EzErrorCode.UNAUTHORIZED);

                authBizMemberService.authMemberAppSession(memberSessionId, AuthRole.ADMIN_ORG);

                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        String voterId = voterRegistrationService.registerVoter(bizVoter);
                        bizVoter.setVoterId(voterId);
                    }
                });

                result.setObject(bizVoter);
                result.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return result;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.voter;

import id.ezclouds.common.facade.biz.election.BizCanvassRecordService;
import id.ezclouds.common.facade.biz.election.CanvassRecordService;
import id.ezclouds.common.facade.biz.election.VoterRegistrationService;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.biz.election.BizCanvassRecord;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.request.biz.election.BizCanvasRecordCreateRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
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
 * @version $Id: EzBizCanvassRecordService.java, v 0.1 2024‐09‐23 10:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzBizCanvassRecordService implements BizCanvassRecordService {

    @Autowired
    private VoterRegistrationService voterRegistrationService;

    @Autowired
    private CanvassRecordService canvassRecordService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public BizResult canvassOrderCreate(BizCanvasRecordCreateRequest request) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(request, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {

                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        String bizVoterId = voterRegistrationService.registerVoter(request.getBizVoter());
                        request.getBizVoter().setVoterId(bizVoterId);

                    }
                });

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
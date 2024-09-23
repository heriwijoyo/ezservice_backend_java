/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.voter;

import id.ezclouds.common.facade.biz.election.BizCanvassOrderService;
import id.ezclouds.common.facade.biz.election.CanvassOrderService;
import id.ezclouds.common.facade.biz.election.VoterRegistrationService;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.biz.election.BizCanvassOrder;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.request.biz.election.BizCanvasOrderCreateRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.BizErrorMessageHelper;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzBizCanvassOrderService.java, v 0.1 2024‐09‐23 10:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzBizCanvassOrderService implements BizCanvassOrderService {

    @Autowired
    private VoterRegistrationService voterRegistrationService;

    @Autowired
    private CanvassOrderService canvassOrderService;

    @Override
    public BizResult canvassOrderCreate(BizCanvasOrderCreateRequest request) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(request, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                BizVoter bizVoter = request.getBizVoter();
                String bizVoterId = voterRegistrationService.registerVoter(bizVoter);
                bizVoter.setVoterId(bizVoterId);

                BizCanvassOrder canvassOrder = canvassOrderService.createCanvassOrder(bizVoter);

                result.setSuccess(true);
                result.setObject(null);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return result;
    }
}
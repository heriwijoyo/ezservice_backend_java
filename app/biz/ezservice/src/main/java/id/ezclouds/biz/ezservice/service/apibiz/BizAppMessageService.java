/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.model.app.AppMessage;
import id.ezclouds.biz.ezservice.model.app.SimpleAppMessage;
import id.ezclouds.biz.ezservice.service.app.AppMessageService;
import id.ezclouds.biz.ezservice.service.request.BizDetailRequest;
import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.core.shared.result.BizPageInfo;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAppMessageService.java, v 0.1 2024‐05‐09 1:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAppMessageService extends BizBaseService {

    @Autowired
    private AppMessageService appMessageService;

    public BizResult getAppMessageMember(BizPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo session = authAppMemberSession();
                BizPageInfo<SimpleAppMessage> appMessages = appMessageService
                        .getAppMessage(getOrgId(), session.getMemberId(), request);

                bizResult.setSuccess(true);
                bizResult.setBizPageInfo(appMessages);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult detailAppMessageMember(BizDetailRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                authAppMemberSession();
                AppMessage appMessage = appMessageService
                        .getAppMessage(getOrgId(), request.getDetailId());
                bizResult.setSuccess(true);
                bizResult.setObject(appMessage);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }
}
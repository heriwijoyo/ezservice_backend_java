/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.admin;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.biz.admin.BizAdminWhatsappService;
import id.ezclouds.common.facade.integration.EzConnectService;
import id.ezclouds.common.facade.member.MemberBackOfficeService;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.integration.WhatsappSendRequest;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.message.CommonMessageConstant;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.BizErrorMessageHelper;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizAdminWhatsappService.java, v 0.1 2024‐08‐29 2:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBizAdminWhatsappService implements BizAdminWhatsappService {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private MemberBackOfficeService memberBackOfficeService;

    @Autowired
    private EzConnectService ezConnectService;

    @Override
    public BizResult sendMessage(WebBizUpdateRequest<String> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.ADMIN_ORG);

                MemberBackOffice member = memberBackOfficeService
                        .getMemberDetail(session.getMemberId());

                WhatsappSendRequest sendRequest = new WhatsappSendRequest();
                sendRequest.setOrgId(session.getOrgId());
                sendRequest.setMessage(request.getObject());
                sendRequest.setPhoneNumber(member.getPhone());
                ezConnectService.sendWhatsappMessage(sendRequest);

                bizResult.setSuccess(true);
                bizResult.setObject(CommonMessageConstant.BIZ_OPERATION_SUCCESS);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }
}
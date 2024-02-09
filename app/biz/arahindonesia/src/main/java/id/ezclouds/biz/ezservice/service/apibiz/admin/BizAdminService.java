/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz.admin;

import id.ezclouds.biz.ezservice.model.BizStatus;
import id.ezclouds.biz.ezservice.model.admin.BizAdminSession;
import id.ezclouds.biz.ezservice.service.apibiz.BizBaseService;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.model.CoreAuthAdminSession;
import id.ezclouds.core.auth.request.CoreAdminCommonSessionCreateRequest;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.service.CoreMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminService.java, v 0.1 2024‐02‐10 3:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAdminService extends BizBaseService {

    @Autowired
    private CoreMemberService coreMemberService;

    public BizResult createWebSession() {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {

            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = authMemberSession();
                CoreMember coreMember = coreMemberService.getOptimisticCoreMember(sessionInfo.getMemberId());

                CoreAdminCommonSessionCreateRequest createRequest = new CoreAdminCommonSessionCreateRequest();
                createRequest.setOrgId(getOrgId());
                createRequest.setOrgCode(getOrgCode());
                createRequest.setScene("WEB_LOGIN_SESSION");
                createRequest.setAppId(getAppId());
                createRequest.setClientId(sessionInfo.getClientId());
                createRequest.setDeviceId(null);
                createRequest.setMemberId(sessionInfo.getMemberId());
                createRequest.setMemberRoles(coreMember.getRoles());

                CoreAuthAdminSession adminSession = coreAuthService.adminCreateSession(createRequest);
                BizAdminSession bizAdminSession = new BizAdminSession();
                bizAdminSession.setSessionId(adminSession.getSessionId());
                bizAdminSession.setSessionCode(adminSession.getSessionCode());
                bizAdminSession.setExpiryTime(adminSession.getExpiryTime());
                bizAdminSession.setStatus(BizStatus.getByCode(adminSession.getStatus()).getDescription());

                bizResult.setSuccess(true);
                bizResult.setObject(bizAdminSession);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }
}
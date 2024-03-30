/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz.admin;

import id.ezclouds.biz.ezservice.service.apibiz.BizBaseService;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.model.CoreAuthAdminSession;
import id.ezclouds.core.auth.request.CoreAdminCommonSessionCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSuperAdminService.java, v 0.1 2024‐03‐31 1:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizSuperAdminService extends BizBaseService {

    public BizResult createSuperAdminSession() {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                final String SU00 = "SU00";
                final String orgCode = "101";
                final String scene = "WEB_LOGIN_SESSION";
                final String role = "SUPERUSER";

                List<CoreAuthAdminSession> currentSessions = coreAuthService.adminGetSession(SU00, SU00);
                for (CoreAuthAdminSession session : currentSessions) {
                    coreAuthService.adminLogoutSession(session.getSessionId());
                }

                CoreAdminCommonSessionCreateRequest createRequest = new CoreAdminCommonSessionCreateRequest();
                createRequest.setOrgId(SU00);
                createRequest.setOrgCode(orgCode);
                createRequest.setScene(scene);
                createRequest.setAppId(SU00);
                createRequest.setClientId(SU00);
                createRequest.setMemberId(SU00);
                createRequest.setMemberRoles(role);

                coreAuthService.adminCreateSession(createRequest);

                bizResult.setObject("SUCCESS");
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

}
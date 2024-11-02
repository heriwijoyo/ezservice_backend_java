/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.biz.report.BizReportSubOrganizationService;
import id.ezclouds.common.facade.organization.SubOrganizationService;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.auth.AuthSession;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.BizErrorMessageHelper;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportSubOrganizationService.java, v 0.1 2024‐10‐30 8:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreReportSubOrganizationService implements BizReportSubOrganizationService {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private SubOrganizationService subOrganizationService;

    @Override
    public BizResult getActiveSubOrganizations(String sessionId) {
        final BizResult result = new BizResult();

        BizServiceTemplate.execute(null, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthSession authSession = authAdminService.authorizeWebPublicSession(sessionId);

                result.setObject(
                        subOrganizationService.getSubOrganizationAll(authSession.getOrgId())
                );
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
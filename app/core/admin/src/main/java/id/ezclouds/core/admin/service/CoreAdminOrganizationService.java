/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.admin.service;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.biz.admin.BizAdminOrganizationService;
import id.ezclouds.common.facade.core.CoreOrganizationService;
import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.facade.process.AsyncProcessExecutor;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.message.CommonMessageConstant;
import id.ezclouds.common.model.process.ProcessName;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.BizErrorMessageHelper;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminOrganizationService.java, v 0.1 2024‐09‐23 2:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAdminOrganizationService implements BizAdminOrganizationService {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private AsyncProcessExecutor asyncProcessExecutor;

    @Override
    public BizResult getOrganizations(String sessionId) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(null, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(sessionId);
                authAdminService.authorizeSessionForRole(session, AuthRole.SUPERUSER);

                result.setObject(coreOrganizationService.getOrganizations());
                result.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return result;
    }

    @Override
    public BizResult initSystemSequence(String sessionId, String orgId) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(null, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(orgId, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(sessionId);
                authAdminService.authorizeSessionForRole(session, AuthRole.SUPERUSER);

                coreSequenceService.initSequenceConfig(orgId);

                result.setSuccess(true);
                result.setObject(CommonMessageConstant.BIZ_OPERATION_SUCCESS);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return result;
    }

    @Override
    public BizResult initMigrateMember(String sessionId, String orgId, String date) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(null, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(orgId, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(sessionId);
                authAdminService.authorizeSessionForRole(session, AuthRole.SUPERUSER);

                asyncProcessExecutor.execute(ProcessName.INIT_MIGRATE_MEMBER, orgId +","+ date);

                result.setSuccess(true);
                result.setObject(CommonMessageConstant.BIZ_OPERATION_SUCCESS);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return result;
    }
}
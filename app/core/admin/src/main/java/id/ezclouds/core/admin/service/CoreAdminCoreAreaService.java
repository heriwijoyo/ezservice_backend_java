/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.admin.service;

import id.ezclouds.common.facade.area.CoreWorkingAreaService;
import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.biz.admin.BizAdminCoreAreaService;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.BizErrorMessageHelper;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminCoreAreaService.java, v 0.1 2024‐09‐20 12:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAdminCoreAreaService implements BizAdminCoreAreaService {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private CoreWorkingAreaService coreWorkingAreaService;

    @Override
    public BizResult getWorkingAreaDistricts(String sessionId) {
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
                authAdminService.authorizeSessionForRole(session, AuthRole.ADMIN_ORG);

                result.setObject(
                        coreWorkingAreaService
                                .fetchCoreAreas(session.getOrgId(), CoreAreaLevel.DISTRICT)
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
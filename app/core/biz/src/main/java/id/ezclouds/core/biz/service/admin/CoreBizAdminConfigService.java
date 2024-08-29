/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.admin;

import id.ezclouds.common.facade.admin.AdminConfigService;
import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.biz.admin.BizAdminConfigService;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.config.CoreConfig;
import id.ezclouds.common.model.message.CommonMessageConstant;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.request.admin.CommonTableCreateRequest;
import id.ezclouds.common.model.request.admin.WebAdminRequest;
import id.ezclouds.common.model.request.admin.WebBizDetailRequest;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.model.util.BizWebPageRequestValidator;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.BizErrorMessageHelper;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizAdminConfigService.java, v 0.1 2024‐08‐17 1:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBizAdminConfigService implements BizAdminConfigService {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private AdminConfigService adminConfigService;

    @Override
    public BizResult getBizCommonTables(WebBizPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                BizWebPageRequestValidator.validate(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.SUPERUSER);

                PageResult<BizCommonTable> result = adminConfigService
                        .getBizCommonTables(request);

                bizResult.setSuccess(true);
                bizResult.setObject(result);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    @Override
    public BizResult createBizCommonTable(CommonTableCreateRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.SUPERUSER);

                String currentTime = DateUtil.getCurrentFormattedDate();
                BizCommonTable bizCommonTable = new BizCommonTable();
                bizCommonTable.setTableId(HashUtil.createHash(request.getOrgId(), request.getCode(), currentTime));
                bizCommonTable.setOrgId(request.getOrgId());
                bizCommonTable.setCode(request.getCode());
                bizCommonTable.setTitle(request.getTitle());
                bizCommonTable.setColumns(request.getColumns());
                bizCommonTable.setConfig(request.getConfig());
                bizCommonTable.setCreatedTime(currentTime);
                bizCommonTable.setStatus(1);
                adminConfigService.createBizCommonTable(bizCommonTable);

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

    @Override
    public BizResult updateBizCommonTable(WebBizUpdateRequest<BizCommonTable> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getOrgId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getTableId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getCode(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getTitle(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getColumns(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getConfig(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService.authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.SUPERUSER);

                adminConfigService.updateBizCommonTable(request.getObject());

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

    @Override
    public BizResult getBizCommonTable(WebBizDetailRequest<String> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService.authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.SUPERUSER);

                BizCommonTable bizCommonTable = adminConfigService.getBizCommonTable(request.getObject());
                AssertUtil.notNull(bizCommonTable, EzErrorCode.DATA_NOT_FOUND);

                bizResult.setSuccess(true);
                bizResult.setObject(bizCommonTable);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    @Override
    public BizResult getWatzapNumberKey(WebAdminRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.ADMIN_ORG);

                CoreConfig coreConfig = adminConfigService
                        .getCoreConfig(session.getOrgId(), "WATZAP_NUMBER_KEY");

                bizResult.setObject(coreConfig.getConfigValue());
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    @Override
    public BizResult updateWatzapNumberKey(WebBizUpdateRequest<String> request) {
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

                adminConfigService
                        .updateConfigValue(session.getOrgId(), "WATZAP_NUMBER_KEY", request.getObject());

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
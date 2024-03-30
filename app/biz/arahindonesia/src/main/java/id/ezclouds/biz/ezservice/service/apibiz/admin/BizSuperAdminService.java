/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz.admin;

import id.ezclouds.biz.ezservice.enums.BizMemberRole;
import id.ezclouds.biz.ezservice.model.admin.BizApplicationConfig;
import id.ezclouds.biz.ezservice.model.admin.BizOrganization;
import id.ezclouds.biz.ezservice.model.admin.BizOrganizationDetail;
import id.ezclouds.biz.ezservice.service.apibiz.BizBaseService;
import id.ezclouds.biz.ezservice.service.core.BizAppCacheService;
import id.ezclouds.biz.ezservice.service.inner.service.BizAdminInnerService;
import id.ezclouds.biz.ezservice.service.request.web.BizWebCreateRequest;
import id.ezclouds.biz.ezservice.service.request.web.BizWebDetailRequest;
import id.ezclouds.biz.ezservice.service.request.web.BizWebPageRequest;
import id.ezclouds.biz.ezservice.service.request.web.BizWebUpdateRequest;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.result.PageResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.model.CoreAuthAdminSession;
import id.ezclouds.core.auth.request.CoreAdminCommonSessionCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSuperAdminService.java, v 0.1 2024‐03‐31 1:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizSuperAdminService extends BizBaseService {

    @Autowired
    private BizAdminInnerService bizAdminInnerService;

    @Autowired
    private BizAppCacheService bizAppCacheService;

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

    public BizResult getOrganization(BizWebPageRequest request) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getPageNumber(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getPageSize(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.isTrue(request.getPageNumber() > 0, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.isTrue(request.getPageSize() > 0, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(request.getSessionId());

                if (StringUtil.isBlank(request.getSortBy())) {
                    request.setSortBy("code");
                    request.setSort("asc");
                }

                PageResult<BizOrganization> pageResult = bizAdminInnerService.getOrganizationAll(
                        request.getPageNumber(),
                        request.getPageSize(),
                        request.getSortBy(),
                        request.getSort()
                );

                bizResult.setSuccess(true);
                bizResult.setObject(pageResult);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult getOrganizationDetail(BizWebDetailRequest<String> request) {
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
                authorizeSuperUserMember(request.getSessionId());

                BizOrganizationDetail detail = bizAdminInnerService.getOrganizationDetail(request.getObject());
                bizResult.setSuccess(true);
                bizResult.setObject(detail);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult createOrganization(BizWebCreateRequest<BizOrganization> request) {
        BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getData(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getOrgId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getCode(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getName(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(request.getSessionId());

                bizAdminInnerService.createOrganization(request.getData());
                bizResult.setSuccess(true);
                bizResult.setObject("SUCCESS");
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult updateOrganization(BizWebUpdateRequest<BizOrganization> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getOrgId(), EzErrorCode.ILLEGAL_PARAM);

            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(request.getSessionId());

                BizOrganization organization = bizAdminInnerService.getOrganizationById(request.getObject().getOrgId());
                organization.setAddress(request.getObject().getAddress());
                organization.setContactName(request.getObject().getContactName());
                organization.setContactPhone(request.getObject().getContactPhone());
                organization.setContactEmail(request.getObject().getContactEmail());
                organization.setStatus(request.getObject().getStatus());

                bizAdminInnerService.updateOrganization(organization);

                bizResult.setSuccess(true);
                bizResult.setObject("UPDATE SUCCESS");
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult updateAppConfig(BizWebUpdateRequest<BizApplicationConfig> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getOrgId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getAppId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getClientId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getClientSecret(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(request.getSessionId());
                BizApplicationConfig appConfig = request.getObject();
                if (StringUtil.isBlank(appConfig.getId())) {
                    String configId = HashUtil.createHash(appConfig.getOrgId(), DateUtil.getTimeNowToString());
                    appConfig.setId(configId);
                }
                if (StringUtil.isBlank(appConfig.getCreatedTime())) {
                    appConfig.setCreatedTime(DateUtil.getCurrentFormattedDate());
                }
                bizAdminInnerService.saveBizAppConfig(appConfig);
                bizResult.setSuccess(true);
                bizResult.setObject("UPDATE SUCCESS");
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult refreshAllCaches(String sessionId) {
        BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(sessionId);

                bizResult.setSuccess(true);
                bizResult.setObject(bizAppCacheService.refreshAllCaches());
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    private void authorizeSuperUserMember(String sessionId) throws Exception {
        CoreAuthAdminSession adminSession = coreAuthService.adminAuthWebSessionId(sessionId);
        AssertUtil.notBlank(adminSession.getMemberRoles(), EzErrorCode.MEMBER_UNAUTHORIZED);
        List<String> roles = Arrays.asList(adminSession.getMemberRoles().split(","));
        AssertUtil.isTrue(roles.contains(BizMemberRole.SUPERUSER.getCode()), EzErrorCode.MEMBER_UNAUTHORIZED);
    }
}
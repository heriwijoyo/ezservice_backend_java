/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz.admin;

import id.ezclouds.biz.ezservice.constant.BizConstant;
import id.ezclouds.biz.ezservice.converter.BizAdminConverter;
import id.ezclouds.biz.ezservice.model.admin.BizAdminAppData;
import id.ezclouds.biz.ezservice.model.admin.BizAdminSession;
import id.ezclouds.biz.ezservice.model.admin.BizDashboardData;
import id.ezclouds.biz.ezservice.service.apibiz.BizBaseService;
import id.ezclouds.biz.ezservice.service.dataservice.BizOrganizationService;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.model.CoreAuthAdminSession;
import id.ezclouds.core.auth.request.CoreAdminCommonSessionCreateRequest;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.model.CoreAdminBOMenu;
import id.ezclouds.core.shared.model.CoreAdminBOPermission;
import id.ezclouds.core.shared.model.CoreAdminDashboard;
import id.ezclouds.core.shared.model.CoreOrganization;
import id.ezclouds.core.shared.result.ListResult;
import id.ezclouds.core.shared.service.CoreAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminService.java, v 0.1 2024‐02‐10 3:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAdminService extends BizBaseService {

    @Autowired
    private CoreAdminService coreAdminService;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private BizOrganizationService bizOrganizationService;

    public BizResult createWebSession() {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {

            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = authMemberSession();
                authorizeAdminMember(sessionInfo.getMemberRoles());

                CoreAdminCommonSessionCreateRequest createRequest = new CoreAdminCommonSessionCreateRequest();
                createRequest.setOrgId(getOrgId());
                createRequest.setOrgCode(getOrgCode());
                createRequest.setScene("WEB_LOGIN_SESSION");
                createRequest.setAppId(getAppId());
                createRequest.setClientId(sessionInfo.getClientId());
                createRequest.setDeviceId(null);
                createRequest.setMemberId(sessionInfo.getMemberId());
                createRequest.setMemberRoles(sessionInfo.getMemberRoles());

                CoreAuthAdminSession adminSession = coreAuthService.adminCreateSession(createRequest);
                BizAdminSession bizAdminSession = BizAdminConverter.convert(adminSession);

                bizResult.setObject(bizAdminSession);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult getWebSession() {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = authMemberSession();
                authorizeAdminMember(sessionInfo.getMemberRoles());

                List<BizAdminSession> adminSessions = coreAuthService
                        .adminGetSession(getOrgId(), sessionInfo.getMemberId())
                        .stream()
                        .map(BizAdminConverter::convert)
                        .collect(Collectors.toList());

                ListResult<BizAdminSession> listResult = new ListResult<>();
                listResult.setPageNumber(1);
                listResult.setHasMore(false);
                listResult.setItems(adminSessions);

                bizResult.setSuccess(true);
                bizResult.setObject(listResult);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult logoutWebSession(String webSessionId) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(webSessionId, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = authMemberSession();
                authorizeAdminMember(sessionInfo.getMemberRoles());

                coreAuthService.adminLogoutSession(webSessionId);
                bizResult.setSuccess(true);
                bizResult.setObject(BizConstant.Message.SUCCESS_LOGOUT);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult loginWebSession(String sessionCode) {
        BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionCode, EzErrorCode.SESSION_CODE_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                String sessionId = coreAuthService.adminLoginBySessionCode(sessionCode);
                bizResult.setSuccess(true);
                bizResult.setObject(sessionId);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult validateWebSessionId(String sessionId) {
        BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                String respSessionId = coreAuthService.adminValidateSessionId(sessionId);
                bizResult.setSuccess(true);
                bizResult.setObject(respSessionId);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult getAppData(String sessionId) {
        BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession adminSession = coreAuthService.adminAuthWebSessionId(sessionId);
                CoreOrganization organization = bizOrganizationService.getOrganizationById(adminSession.getOrgId());
                CoreMember coreMember = coreMemberService.getOptimisticCoreMember(adminSession.getMemberId());

                List<String> memberRoles;
                if (StringUtil.isBlank(adminSession.getMemberRoles())) {
                    memberRoles = new ArrayList<>();
                } else {
                    memberRoles = Arrays.asList(adminSession.getMemberRoles().split(","));
                }

                List<CoreAdminBOPermission> permission = coreAdminService
                        .getPermissionByRoles(adminSession.getOrgId(), memberRoles);
                List<String> permissionMain = permission
                        .stream()
                        .map(CoreAdminBOPermission::getPermissionMain)
                        .collect(Collectors.toList());
                List<CoreAdminBOMenu> menu = coreAdminService
                        .getBOMenuByPermission(adminSession.getOrgId(), permissionMain);

                BizAdminAppData adminAppData = new BizAdminAppData();
                adminAppData.setOrgCode(adminSession.getOrgCode());
                adminAppData.setOrgName(organization.getName());
                adminAppData.setMemberId(adminSession.getMemberId());
                adminAppData.setMemberName(coreMember.getName());
                adminAppData.setMemberPhone(coreMember.getPhone());
                adminAppData.setPermission(permission);
                adminAppData.setMenu(menu);

                bizResult.setSuccess(true);
                bizResult.setObject(adminAppData);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult getDashboardData(String sessionId) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = coreAuthService.adminAuthWebSessionId(sessionId);
                List<CoreAdminDashboard> dashboards =  coreAdminService.getAdminDashboardAllActive(session.getOrgId());

                List<BizDashboardData> bizDashboard = dashboards
                        .stream()
                        .map(dashboard -> {
                            BizDashboardData bizData = new BizDashboardData();
                            bizData.setKeyName(dashboard.getKeyName());
                            bizData.setDisplayName(dashboard.getDisplayName());
                            bizData.setIcon(dashboard.getIcon());
                            bizData.setUrl(dashboard.getUrl());
                            bizData.setCountValue(dashboard.getCountValue());
                            bizData.setCountLabel(dashboard.getCountLabel());
                            bizData.setLastUpdate(dashboard.getLastUpdate());
                            return bizData;
                        })
                        .collect(Collectors.toList());

                bizResult.setSuccess(true);
                bizResult.setObject(bizDashboard);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    private void authorizeAdminMember(String memberRoles) throws EzErrorException {
        AssertUtil.notBlank(memberRoles, EzErrorCode.MEMBER_UNAUTHORIZED);
        List<String> roles = Arrays.asList(memberRoles.split(","));
        AssertUtil.isTrue(roles.contains(BizConstant.MemberRole.ORG_ADMIN), EzErrorCode.MEMBER_UNAUTHORIZED);
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.apibiz.admin;

import id.ezclouds.biz.election.model.admin.BizApplicationConfig;
import id.ezclouds.biz.election.model.admin.BizMemberRequiredData;
import id.ezclouds.common.model.core.BizOrganization;
import id.ezclouds.biz.election.service.request.web.BizWebCommonRequest;
import id.ezclouds.biz.election.service.request.web.BizWebCreateRequest;
import id.ezclouds.biz.election.constant.BizConstant;
import id.ezclouds.biz.election.enums.BizConnectType;
import id.ezclouds.biz.election.enums.BizMemberRole;
import id.ezclouds.biz.election.model.admin.BizOrganizationDetail;
import id.ezclouds.biz.election.model.member.BizMember;
import id.ezclouds.biz.election.service.apibiz.BizBaseService;
import id.ezclouds.biz.election.service.apibiz.BizLocalAreaService;
import id.ezclouds.biz.election.service.async.processor.BizOldCommonReportProcessor;
import id.ezclouds.biz.election.service.inner.service.BizConnectInnerService;
import id.ezclouds.biz.election.service.app.model.BizAppConfig;
import id.ezclouds.biz.election.service.inner.service.BizAdminInnerService;
import id.ezclouds.biz.election.service.request.BizLocalAreaRequest;
import id.ezclouds.biz.election.service.request.admin.BizAdminUploadRequest;
import id.ezclouds.biz.election.service.request.web.BizWebPageRequest;
import id.ezclouds.common.facade.file.CoreFileService;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.request.admin.WebBizDetailRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import id.ezclouds.core.auth.model.CoreAuthAdminScene;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.biz.election.util.BizExtendInfoUtil;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.core.auth.request.CoreAdminCommonSessionCreateRequest;
import id.ezclouds.core.shared.constant.CoreConstant;
import id.ezclouds.common.model.file.PublicFileResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSuperAdminService.java, v 0.1 2024‐03‐31 1:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizSuperAdminService extends BizBaseService {

    @Autowired
    private BizAdminInnerService bizAdminInnerService;

    @Autowired
    private CoreFileService coreFileService;

    @Autowired
    private BizConnectInnerService bizConnectInnerService;

    @Autowired
    private BizOldCommonReportProcessor bizCommonReportProcessor;

    public BizResult createSuperAdminSession(boolean shouldScrambleCode) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                List<AuthAdminSession> currentSessions = coreAuthService
                        .adminGetSession(CoreConstant.SU_ORG_ID, CoreConstant.SU_ORG_ID);
                for (AuthAdminSession session : currentSessions) {
                    coreAuthService.adminLogoutSession(session.getSessionId());
                }

                CoreAdminCommonSessionCreateRequest createRequest = new CoreAdminCommonSessionCreateRequest();
                createRequest.setOrgId(CoreConstant.SU_ORG_ID);
                createRequest.setOrgCode(CoreConstant.SU_ORG_CODE);
                createRequest.setScene(CoreAuthAdminScene.WEB_LOGIN_SESSION.getCode());
                createRequest.setAppId(CoreConstant.SU_ORG_ID);
                createRequest.setClientId(CoreConstant.SU_ORG_ID);
                createRequest.setMemberId(CoreConstant.SU_ORG_ID);
                createRequest.setMemberRoles("SUPERUSER");

                AuthAdminSession session = coreAuthService.adminCreateSession(createRequest);
                String sessionCode = session.getSessionCode();

                if (shouldScrambleCode) {
                    String scrambledCode = "";
                    for (int i = 0; i < sessionCode.length(); i++) {
                        String codePart = sessionCode.substring(i, i+1);
                        int codePartNumber = Integer.parseInt(codePart);
                        int newCodePart;
                        if (codePartNumber == 9) {
                            newCodePart = 0;
                        } else {
                            newCodePart = codePartNumber + 1;
                        }
                        scrambledCode += String.valueOf(newCodePart);
                    }
                    bizResult.setObject(scrambledCode);
                } else {
                    bizResult.setObject(sessionCode);
                    bizConnectInnerService.sendMessage(
                            BizConnectType.WHATSAPP,
                            "RJL0",
                            "6281281150355",
                            "Admin Login Code:\n\n"+ sessionCode
                    );
                }
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult createSuperAdminPublicSession(String orgCode) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                CoreAdminCommonSessionCreateRequest createRequest = new CoreAdminCommonSessionCreateRequest();
                createRequest.setOrgId(CoreConstant.SU_ORG_ID);
                createRequest.setOrgCode(orgCode);
                createRequest.setScene(CoreAuthAdminScene.WEB_PUBLIC_SESSION.getCode());
                createRequest.setAppId(CoreConstant.SU_ORG_ID);
                createRequest.setClientId(CoreConstant.SU_ORG_ID);
                createRequest.setMemberId(CoreConstant.SU_ORG_ID);
                createRequest.setMemberRoles("PUBLIC_ACCESS");

                AuthAdminSession session = coreAuthService.adminCreateSession(createRequest);

                bizResult.setObject("SUCCESS :: "+ session.getSessionId());
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

    public BizResult getOrganizationDetail(WebBizDetailRequest<String> request) {
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

                String orgId = bizAdminInnerService.createOrganization(request.getData());
                coreFileService.initPublicFileDirectory(orgId);
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

    public BizResult updateOrganization(WebBizUpdateRequest<BizOrganization> request) {
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
                organization.setExtendConfig(request.getObject().getExtendConfig());
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

    public BizResult getMemberRequiredData(WebBizDetailRequest<String> request) {
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

                BizMemberRequiredData requiredData = bizAdminInnerService.getMemberRequiredData(request.getObject());
                bizResult.setSuccess(true);
                bizResult.setObject(requiredData);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult updateAppConfig(WebBizUpdateRequest<BizApplicationConfig> request) {
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
                bizAdminInnerService.saveClientAppConfig(appConfig);
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

    public BizResult updateBizAppConfig(WebBizUpdateRequest<List<BizAppConfig>> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getOrgId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(request.getSessionId());
                bizAdminInnerService.saveBizAppConfigs(request.getOrgId(), request.getObject());
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

    public BizResult updateCoreOrgConfig(WebBizUpdateRequest<Map<String, String>> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getOrgId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(request.getSessionId());
                bizAdminInnerService.saveCoreOrgConfig(request.getOrgId(), request.getObject());
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

    public BizResult adminOrgCreateMember(BizWebCreateRequest<BizMember> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getData(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getOrgId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getName(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getPhone(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(request.getSessionId());
                bizAdminInnerService.adminOrgCreateMember(request.getOrgId(), request.getData());
                bizResult.setSuccess(true);
                bizResult.setObject("OPERATION SUCCESS");
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult createBizMember(BizWebCreateRequest<BizMember> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getData(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getOrgId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getReferrerId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getData().getSubOrganization(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getSubOrganization().getSubOrgId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getName(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getPhone(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(request.getSessionId());
                List<CoreMember> members = bizAdminInnerService
                        .getUniqueMember(request.getOrgId(), request.getData().getPhone());
                if (members != null) {
                    AssertUtil.isNotTrue(members.size() > 0, EzErrorCode.IDEMPOTENT_ERROR);
                }
                bizAdminInnerService
                        .createMember(request.getOrgId(), request.getData());
                bizResult.setSuccess(true);
                bizResult.setObject("OPERATION SUCCESS");
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult adminWhatsappSendMessage(BizWebCommonRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                BizExtendInfoUtil.validateExtendInfo(request.getExtendInfo(), "ORG_ID", "PHONE", "MESSAGE");
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(request.getSessionId());
                bizConnectInnerService.sendMessage(
                        BizConnectType.WHATSAPP,
                        request.getExtendInfo().get("ORG_ID"),
                        request.getExtendInfo().get("PHONE"),
                        request.getExtendInfo().get("MESSAGE")
                );
                bizResult.setSuccess(true);
                bizResult.setObject("Whatsapp send SUCCESS!");
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
                bizResult.setObject(Arrays.asList("TEMPORARY UNAVAILABLE"));
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult refreshAllDirectories(String sessionId) {
        BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(sessionId);
                bizAdminInnerService.refreshAllDirectories();
                bizResult.setSuccess(true);
                bizResult.setObject(BizConstant.Message.SUCCESS_COMMON);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult refreshAllMenus(String sessionId) {
        BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(sessionId);
                bizAdminInnerService.refreshAllMenus();
                bizResult.setSuccess(true);
                bizResult.setObject(BizConstant.Message.SUCCESS_COMMON);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult getCoreAreas(String sessionId, String level, String parentId) {
        BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizeSuperUserMember(sessionId);

                BizLocalAreaRequest request = new BizLocalAreaRequest();
                request.setAreaLevel(level);
                request.setParentIds(Collections.singletonList(parentId));
                BizResult result = BeanFacadeUtil
                        .getBean(BizLocalAreaService.class)
                        .getLocalArea(request);

                bizResult.setSuccess(result.isSuccess());
                bizResult.setObject(result.getObject());
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public boolean adminCommonPostWithFileUpload(BizAdminUploadRequest request) throws Exception {
        bizAdminInnerService.validateExtendInfo(request.getExtendInfo(), "ORG_ID");

        authorizeSuperUserMember(request.getSessionId());
        String extOrgId = request.getExtOrgId();
        PublicFileResolver fileInfo = coreFileService.resolvePublicFileInfo(extOrgId);

        Path filePath;
        switch (request.getScene()) {
            case ADMIN_APP_BUILD_PACKAGE:
                bizAdminInnerService.validateExtendInfo(request.getExtendInfo(), "PLATFORM", "VERSION_CODE", "VERSION_NAME");
                String platform = request.getExtendInfo().get("PLATFORM");
                String versionCode = request.getExtendInfo().get("VERSION_CODE");
                String versionName = request.getExtendInfo().get("VERSION_NAME");
                String fileName = versionName + ".apk";

                filePath = fileInfo.getAppBuildPackagePath(fileName);
                coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                bizAdminInnerService.createAppBuildPackage(extOrgId, platform, Integer.parseInt(versionCode), versionName);
                break;

            case ADMIN_APP_ICON:
                filePath = fileInfo.getAppGalleryPath("icon.png");
                coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                break;
        }
        return true;
    }

    private void authorizeSuperUserMember(String sessionId) throws Exception {
        AuthAdminSession adminSession = coreAuthService.adminAuthWebSessionId(sessionId);
        AssertUtil.notBlank(adminSession.getMemberRoles(), EzErrorCode.MEMBER_UNAUTHORIZED);
        List<String> roles = Arrays.asList(adminSession.getMemberRoles().split(","));
        AssertUtil.isTrue(roles.contains(BizMemberRole.SUPERUSER.getCode()), EzErrorCode.MEMBER_UNAUTHORIZED);
    }
}
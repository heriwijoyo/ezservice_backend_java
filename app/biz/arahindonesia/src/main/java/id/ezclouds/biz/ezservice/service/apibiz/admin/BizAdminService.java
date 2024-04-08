/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz.admin;

import id.ezclouds.biz.ezservice.config.BizPublicUrlResolver;
import id.ezclouds.biz.ezservice.config.BizPublicUrlResolverImpl;
import id.ezclouds.biz.ezservice.constant.BizConstant;
import id.ezclouds.biz.ezservice.enums.BizMemberRole;
import id.ezclouds.biz.ezservice.converter.BizAdminConverter;
import id.ezclouds.biz.ezservice.model.admin.*;
import id.ezclouds.biz.ezservice.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.ezservice.model.news.BizWebSimpleNews;
import id.ezclouds.biz.ezservice.service.apibiz.BizBaseService;
import id.ezclouds.biz.ezservice.service.core.BizAppCacheService;
import id.ezclouds.biz.ezservice.service.core.BizCacheKey;
import id.ezclouds.biz.ezservice.service.dataservice.BizOrganizationService;
import id.ezclouds.biz.ezservice.service.dataservice.model.WebImageGallery;
import id.ezclouds.biz.ezservice.service.inner.service.BizAdminInnerService;
import id.ezclouds.biz.ezservice.service.request.admin.BizAdminUploadRequest;
import id.ezclouds.biz.ezservice.service.request.web.*;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.result.PageResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.model.CoreAuthAdminSession;
import id.ezclouds.core.auth.request.CoreAdminCommonSessionCreateRequest;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.constant.CoreConstant;
import id.ezclouds.core.shared.file.PublicFileResolver;
import id.ezclouds.core.shared.model.CoreAdminBOMenu;
import id.ezclouds.core.shared.model.CoreAdminBOPermission;
import id.ezclouds.core.shared.model.CoreAdminDashboard;
import id.ezclouds.core.shared.model.CoreOrganization;
import id.ezclouds.core.shared.result.ListResult;
import id.ezclouds.core.shared.service.CoreAdminService;
import id.ezclouds.core.shared.service.CoreFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
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
    private CoreFileService coreFileService;

    @Autowired
    private BizOrganizationService bizOrganizationService;

    @Autowired
    private BizAdminInnerService bizAdminInnerService;

    @Autowired
    private BizAppCacheService bizAppCacheService;

    @Value("${ezserviceapp.url.public.root}")
    private String appRootPublicUrl;

    public BizResult createWebSession() {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {

            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = authAppMemberSession();
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
                CoreAuthMemberSessionInfo sessionInfo = authAppMemberSession();
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
                CoreAuthMemberSessionInfo sessionInfo = authAppMemberSession();
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
                authorizeSuperUserOrAdminMember(adminSession.getMemberRoles());
                CoreOrganization organization = bizOrganizationService.getOrganizationById(adminSession.getOrgId());

                String memberName, memberPhone;
                if (StringUtil.equals(organization.getOrgId(), CoreConstant.SU_ORG_ID)) {
                    memberName = BizMemberRole.SUPERUSER.getCode();
                    memberPhone = "-";
                } else {
                    CoreMember coreMember = coreMemberService.getOptimisticCoreMember(adminSession.getMemberId());
                    memberName = coreMember.getName();
                    memberPhone = coreMember.getPhone();
                }

                List<String> memberRoles;
                if (StringUtil.isBlank(adminSession.getMemberRoles())) {
                    memberRoles = new ArrayList<>();
                } else {
                    memberRoles = Arrays.asList(adminSession.getMemberRoles().split(","));
                }

                List<CoreAdminBOPermission> permission = coreAdminService
                        .getPermissionByRoles(organization.getOrgId(), memberRoles);
                List<String> permissionMain = permission
                        .stream()
                        .map(CoreAdminBOPermission::getPermissionMain)
                        .collect(Collectors.toList());
                List<CoreAdminBOMenu> menu = coreAdminService
                        .getBOMenuByPermission(organization.getOrgId(), permissionMain);

                BizAdminAppData adminAppData = new BizAdminAppData();
                adminAppData.setOrgCode(adminSession.getOrgCode());
                adminAppData.setOrgName(organization.getName());
                adminAppData.setMemberId(adminSession.getMemberId());
                adminAppData.setMemberName(memberName);
                adminAppData.setMemberPhone(memberPhone);
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
                CoreAuthAdminSession session = authorizedAdminSession(sessionId);
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

    public BizResult getAppGallery(BizWebPageRequest request) {
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
                CoreAuthAdminSession adminSession = coreAuthService.adminAuthWebSessionId(request.getSessionId());
                authorizeAdminMember(adminSession.getMemberRoles());

                if (StringUtil.isBlank(request.getSortBy())) {
                    request.setSortBy("createdTime");
                    request.setSort("desc");
                }

                BizPublicUrlResolver urlResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, adminSession.getOrgCode());
                PageResult<WebImageGallery> pageResult = bizAdminInnerService.getImageGalleryAll(
                        adminSession.getOrgId(),
                        request.getPageNumber(),
                        request.getPageSize(),
                        request.getSortBy(),
                        request.getSort()
                );
                pageResult.getData().forEach(gallery -> {
                    BizAnnotationProcessor.annotatePublicConfig(gallery, urlResolver);
                });

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

    public BizResult updateAppGallery(BizWebUpdateItemRequest request) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getItemId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSection(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getValue(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession adminSession = authorizedAdminSession(request.getSessionId());

                String result = bizAdminInnerService.updateImageGallery(
                        adminSession.getOrgId(),
                        request.getItemId(),
                        request.getSection(),
                        request.getValue()
                );

                bizResult.setSuccess(true);
                bizResult.setObject(result);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult getNews(BizWebPageRequest request) {
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
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                BizPublicUrlResolver urlResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, session.getOrgCode());
                PageResult<BizWebSimpleNews> newsResult = bizAdminInnerService.getSimpleNews(
                        session.getOrgId(),
                        request.getPageNumber(),
                        request.getPageSize(),
                        "publishDate",
                        "desc"
                );
                newsResult.getData().forEach(simpleNews -> {
                    BizAnnotationProcessor.annotatePublicConfig(simpleNews, urlResolver);
                });

                bizResult.setObject(newsResult);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult newsStatusSwitch(BizWebUpdateItemRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getItemId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSection(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getValue(), EzErrorCode.ILLEGAL_PARAM);
                try {
                    Integer.parseInt(request.getValue());
                } catch (Exception e) {
                    throw new EzErrorException(EzErrorCode.ILLEGAL_PARAM);
                }
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                bizAdminInnerService.newsFlagSwitch(
                        session.getOrgId(),
                        request.getItemId(),
                        request.getSection(),
                        Integer.parseInt(request.getValue())
                );
                bizAppCacheService.reloadCacheItem(BizCacheKey.NEWS_HIGHLIGHT);
                bizResult.setSuccess(true);
                bizResult.setObject(WebAdminConstant.OPERATION_SUCCESS);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult adminCommonPostWithFileUpload(BizAdminUploadRequest request) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.SESSION_INVALID);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.SESSION_INVALID);
                request.validateMultipartRequest();
                //TODO: add real mimeType validation (i.e with apache Tika)
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = coreAuthService.adminAuthWebSessionId(request.getSessionId());
                PublicFileResolver fileInfo = coreFileService.resolvePublicFileInfo(session.getOrgId());

                String fileName = DateUtil.getTimeNowToString() + "." + request.getFileExtension();
                String extOrgId;

                Path filePath;
                switch (request.getScene()) {
                    case ADMIN_APP_BUILD_PACKAGE:
                        AssertUtil.notNull(request.getExtendInfo(), EzErrorCode.ILLEGAL_PARAM);
                        extOrgId = request.getExtendInfo().get("ORG_ID");
                        String platform = request.getExtendInfo().get("PLATFORM");
                        String versionCode = request.getExtendInfo().get("VERSION_CODE");
                        String versionName = request.getExtendInfo().get("VERSION_NAME");
                        AssertUtil.notBlank(extOrgId, EzErrorCode.ILLEGAL_PARAM);
                        AssertUtil.notBlank(platform, EzErrorCode.ILLEGAL_PARAM);
                        AssertUtil.notBlank(versionCode, EzErrorCode.ILLEGAL_PARAM);
                        AssertUtil.notBlank(versionName, EzErrorCode.ILLEGAL_PARAM);

                        fileName = versionName + ".apk";

                        fileInfo = coreFileService.resolvePublicFileInfo(extOrgId);
                        filePath = fileInfo.getAppBuildPackagePath(fileName);
                        coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        bizAdminInnerService.createAppBuildPackage(extOrgId, platform, Integer.parseInt(versionCode), versionName);
                        break;
                    case ADMIN_APP_ICON:
                        AssertUtil.notNull(request.getExtendInfo(), EzErrorCode.ILLEGAL_PARAM);
                        extOrgId = request.getExtendInfo().get("ORG_ID");
                        AssertUtil.notBlank(extOrgId, EzErrorCode.ILLEGAL_PARAM);

                        fileInfo = coreFileService.resolvePublicFileInfo(extOrgId);
                        filePath = fileInfo.getAppGalleryPath("icon.png");
                        coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        break;

                    case ADMIN_APP_GALLERY:
                        filePath = fileInfo.getAppGalleryPath(fileName);
                        coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        bizAdminInnerService.createAppImageGallery(session.getOrgId(), fileName, request.getExtendInfo());
                        break;

                    case ADMIN_NEWS_GALLERY:
                        bizAdminInnerService.validateExtendInfo(request.getExtendInfo(), "TITLE", "DESCRIPTION", "PUBLISH_DATE", "CATEGORY", "CONTENT");
                        filePath = fileInfo.getNewsGalleryPath(fileName);
                        coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        bizAdminInnerService.createNews(session.getOrgId(), fileName, request.getExtendInfo());
                        bizAppCacheService.reloadCacheItem(BizCacheKey.NEWS_HIGHLIGHT);
                        break;

                    case ADMIN_EVENT_GALLERY:
                        filePath = fileInfo.getEventGalleryPath(fileName);
                        break;

                    case ADMIN_VIDEO_CARD_GALLERY:
                        filePath = fileInfo.getVideoCardGalleryPath(fileName);
                        coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        bizAdminInnerService.createVideoCard(session.getOrgId(), fileName, request.getExtendInfo());
                        break;

                    case ADMIN_OTHER_GALLERY:
                        filePath = fileInfo.getOtherGalleryPath(fileName);
                        break;

                    default:
                        filePath = null;
                }

                bizResult.setSuccess(true);
                bizResult.setObject(BizConstant.Message.SUCCESS_COMMON);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                if (ezErrorCode == EzErrorCode.IDEMPOTENT_ERROR) {
                    return ezErrorCode.getDescription();
                }
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    private CoreAuthAdminSession authorizedAdminSession(String sessionId) throws Exception {
        CoreAuthAdminSession session = coreAuthService.adminAuthWebSessionId(sessionId);
        AssertUtil.notNull(session, EzErrorCode.SESSION_INVALID);
        AssertUtil.notBlank(session.getMemberRoles(), EzErrorCode.MEMBER_UNAUTHORIZED);
        List<String> roles = Arrays.asList(session.getMemberRoles().split(","));
        AssertUtil.isTrue(roles.contains(BizMemberRole.ADMIN_ORG.getCode()), EzErrorCode.MEMBER_UNAUTHORIZED);
        return session;
    }

    private void authorizeAdminMember(String memberRoles) throws EzErrorException {
        AssertUtil.notBlank(memberRoles, EzErrorCode.MEMBER_UNAUTHORIZED);
        List<String> roles = Arrays.asList(memberRoles.split(","));
        AssertUtil.isTrue(roles.contains(BizMemberRole.ADMIN_ORG.getCode()), EzErrorCode.MEMBER_UNAUTHORIZED);
    }

    private void authorizeSuperUserOrAdminMember(String memberRoles) throws EzErrorException {
        AssertUtil.notBlank(memberRoles, EzErrorCode.MEMBER_UNAUTHORIZED);
        List<String> roles = Arrays.asList(memberRoles.split(","));
        boolean isAdminOrSuperUser = roles.contains(BizMemberRole.ADMIN_ORG.getCode()) || roles.contains(BizMemberRole.SUPERUSER.getCode());
        AssertUtil.isTrue(isAdminOrSuperUser, EzErrorCode.MEMBER_UNAUTHORIZED);
    }
}
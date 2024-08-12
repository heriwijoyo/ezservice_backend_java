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
import id.ezclouds.biz.ezservice.enums.BizSwitchFlagObject;
import id.ezclouds.biz.ezservice.enums.BizUploadScene;
import id.ezclouds.biz.ezservice.model.BizWhatsappLog;
import id.ezclouds.biz.ezservice.model.VideoCard;
import id.ezclouds.biz.ezservice.model.admin.*;
import id.ezclouds.biz.ezservice.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.ezservice.model.event.AppEvent;
import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.model.news.BizWebDetailNews;
import id.ezclouds.biz.ezservice.model.news.BizWebSimpleNews;
import id.ezclouds.biz.ezservice.model.profile.WebCandidateBio;
import id.ezclouds.biz.ezservice.service.apibiz.BizBaseService;
import id.ezclouds.biz.ezservice.service.apibiz.BizLocalAreaService;
import id.ezclouds.biz.ezservice.service.app.model.AppDocument;
import id.ezclouds.biz.ezservice.service.core.BizAppCacheService;
import id.ezclouds.biz.ezservice.service.core.BizCacheEnum;
import id.ezclouds.biz.ezservice.service.app.BizOrganizationService;
import id.ezclouds.biz.ezservice.service.app.model.WebImageGallery;
import id.ezclouds.biz.ezservice.service.inner.service.BizAdminInnerService;
import id.ezclouds.biz.ezservice.service.request.BizLocalAreaRequest;
import id.ezclouds.biz.ezservice.service.request.admin.BizAdminUploadRequest;
import id.ezclouds.biz.ezservice.service.request.web.*;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.service.AppSubOrganizationService;
import id.ezclouds.common.facade.member.MemberBackOfficeService;
import id.ezclouds.common.facade.organization.SubOrganizationService;
import id.ezclouds.common.facade.process.MemberImportProcessor;
import id.ezclouds.common.model.constant.PageSort;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.organization.SubOrganization;
import id.ezclouds.common.model.request.FileStreamImportRequest;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.result.BaseResult;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import id.ezclouds.core.integration.result.EzConnectResult;
import id.ezclouds.common.model.result.PageResult;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.*;
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
    private AppSubOrganizationService appSubOrganizationService;

    @Autowired
    private BizSuperAdminService bizSuperAdminService;

    @Autowired
    private BizAdminInnerService bizAdminInnerService;

    @Autowired
    private BizAppCacheService bizAppCacheService;

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

                List<CoreAdminBOMenu> specialMenu = new ArrayList<>();
                String hasSubOrg = organization.getExtendInfo().get("HAS_SUB_ORG");
                if (Boolean.parseBoolean(hasSubOrg)) {
                    CoreAdminBOMenu subOrgMenu = new CoreAdminBOMenu();
                    subOrgMenu.setMenuName("Communities");
                    subOrgMenu.setMenuUrl("subOrganizations.htm");
                    subOrgMenu.setMenuIcon("groups");

                    specialMenu.add(subOrgMenu);
                }
                CoreAdminBOMenu memberMenu = new CoreAdminBOMenu();
                memberMenu.setMenuName("Members");
                memberMenu.setMenuUrl("members.htm");
                memberMenu.setMenuIcon("group");
                specialMenu.add(memberMenu);

                CoreAdminBOMenu dataUploadMenu = new CoreAdminBOMenu();
                dataUploadMenu.setMenuName("Data Upload");
                dataUploadMenu.setMenuUrl("dataUpload.htm");
                dataUploadMenu.setMenuIcon("upload_file");
                specialMenu.add(dataUploadMenu);

                adminAppData.setSpecialMenu(specialMenu);

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
                validateBizPageRequest(request);
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
                bizAppCacheService.reloadCacheItem(BizCacheEnum.APP_IMAGE_GALLERY_ALL);
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
                validateBizPageRequest(request);
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

    public BizResult getNewsDetail(BizWebDetailRequest<String> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateBizDetailRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                BizWebDetailNews detailNews = bizAdminInnerService.getNewsDetail(session.getOrgId(), request.getObject());
                BizPublicUrlResolver urlResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, session.getOrgCode());
                BizAnnotationProcessor.annotatePublicConfig(detailNews, urlResolver);
                bizResult.setObject(detailNews);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult newsFlagSwitch(BizWebUpdateItemRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateBizDetailItemRequest(request);
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
                bizAppCacheService.reloadCacheItem(BizCacheEnum.NEWS_HIGHLIGHT);
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

    public BizResult getEvents(BizWebPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateBizPageRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                BizPublicUrlResolver urlResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, session.getOrgCode());
                PageResult<AppEvent> eventsResult = bizAdminInnerService.getEvents(
                        session.getOrgId(),
                        request.getPageNumber(),
                        request.getPageSize(),
                        "createdTime",
                        "desc"
                );
                eventsResult.getData().forEach(event -> {
                    BizAnnotationProcessor.annotatePublicConfig(event, urlResolver);
                });

                bizResult.setObject(eventsResult);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult getEventDetail(BizWebDetailRequest<String> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateBizDetailRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                AppEvent appEvent = bizAdminInnerService.getEventDetail(session.getOrgId(), request.getObject());
                BizPublicUrlResolver urlResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, session.getOrgCode());
                BizAnnotationProcessor.annotatePublicConfig(appEvent, urlResolver);
                bizResult.setObject(appEvent);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult eventFlagSwitch(BizWebUpdateItemRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateBizDetailItemRequest(request);
                try {
                    Integer.parseInt(request.getValue());
                } catch (Exception e) {
                    throw new EzErrorException(EzErrorCode.ILLEGAL_PARAM);
                }
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                bizAdminInnerService.eventFlagSwitch(
                        session.getOrgId(),
                        request.getItemId(),
                        request.getSection(),
                        Integer.parseInt(request.getValue())
                );
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

    public BizResult getVideoCards(BizWebPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateBizPageRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                BizPublicUrlResolver urlResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, session.getOrgCode());
                PageResult<VideoCard> vCardResult = bizAdminInnerService.getVideoCards(
                        session.getOrgId(),
                        request.getPageNumber(),
                        request.getPageSize(),
                        "createdTime",
                        "desc"
                );
                vCardResult.getData().forEach(vCard -> {
                    BizAnnotationProcessor.annotatePublicConfig(vCard, urlResolver);
                });

                bizResult.setObject(vCardResult);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult updateVideoCard(BizWebUpdateRequest<VideoCard> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateBizUpdateRequest(request);
                AssertUtil.notBlank(request.getObject().getId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getSection(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getSectionName(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getTargetUrl(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                authorizeAdminMember(session.getMemberRoles());
                request.getObject().setOrgId(session.getOrgId());
                bizAdminInnerService.updateVideoCard(request.getObject());

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

    public BizResult getWhatsappLog(BizWebPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateBizPageRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                PageResult<BizWhatsappLog> pageResult = bizAdminInnerService.getWhatsappLog(
                        session.getOrgId(),
                        request.getKeyword(),
                        request.getPageNumber(),
                        request.getPageSize(),
                        "createdTime",
                        "desc"
                );

                bizResult.setObject(pageResult);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult resendWhatsapp(BizWebCreateRequest<String> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                if (StringUtil.isBlank(request.getOrgId())) {
                    request.setOrgId(session.getOrgId());
                }
                EzConnectResult result = bizAdminInnerService.resendWhatsapp(request.getOrgId(), request.getData());
                bizResult.setSuccess(result.isSuccess());
                if (result.isSuccess()) {
                    bizResult.setObject(BizConstant.Message.SUCCESS_COMMON);
                } else {
                    bizResult.setObject(result.getErrorCode().getDescription());
                }
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult getAppDocuments(BizWebPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateBizPageRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                BizPublicUrlResolver urlResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, session.getOrgCode());
                PageResult<AppDocument> appDocsResult = bizAdminInnerService.getAppDocuments(
                        session.getOrgId(),
                        request.getPageNumber(),
                        request.getPageSize(),
                        "createdTime",
                        "desc"
                );
                appDocsResult.getData().forEach(document -> {
                    BizAnnotationProcessor.annotatePublicConfig(document, urlResolver);
                });

                bizResult.setObject(appDocsResult);
                bizResult.setSuccess(true);
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
                if (isSuperAdminCommonUpload(request.getScene())) {
                    boolean uploadResult = bizSuperAdminService.adminCommonPostWithFileUpload(request);
                    bizResult.setSuccess(uploadResult);
                    bizResult.setObject(BizConstant.Message.SUCCESS_COMMON);
                    return;
                }

                CoreAuthAdminSession session = coreAuthService.adminAuthWebSessionId(request.getSessionId());
                PublicFileResolver fileInfo = coreFileService.resolvePublicFileInfo(session.getOrgId());

                String fileName = DateUtil.getTimeNowToString() + "." + request.getFileExtension();

                Path filePath;
                switch (request.getScene()) {
                    case ADMIN_APP_GALLERY:
                        filePath = fileInfo.getAppGalleryPath(fileName);
                        coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        bizAdminInnerService.createAppImageGallery(session.getOrgId(), fileName, request.getExtendInfo());
                        bizAppCacheService.reloadCacheItem(BizCacheEnum.APP_IMAGE_GALLERY_ALL);
                        break;

                    case ADMIN_NEWS_GALLERY:
                        bizAdminInnerService.validateExtendInfo(request.getExtendInfo(), "TITLE", "DESCRIPTION", "PUBLISH_DATE", "CATEGORY", "CONTENT");
                        filePath = fileInfo.getNewsGalleryPath(fileName);
                        coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        bizAdminInnerService.createNews(session.getOrgId(), fileName, request.getExtendInfo());
                        bizAppCacheService.reloadCacheItem(BizCacheEnum.NEWS_HIGHLIGHT);
                        break;

                    case ADMIN_NEWS_GALLERY_UPDATE:
                        bizAdminInnerService.validateExtendInfo(request.getExtendInfo(), "NEWS_ID", "TITLE", "DESCRIPTION", "PUBLISH_DATE", "CATEGORY", "CONTENT");
                        if (request.getMultipartFile() != null && request.getMultipartFile().getSize() > 0) {
                            filePath = fileInfo.getNewsGalleryPath(fileName);
                            coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        } else {
                            fileName = null;
                        }
                        bizAdminInnerService.updateNews(session.getOrgId(), fileName, request.getExtendInfo());
                        bizAppCacheService.reloadCacheItem(BizCacheEnum.NEWS_HIGHLIGHT);
                        break;

                    case ADMIN_EVENT_GALLERY:
                        bizAdminInnerService.validateExtendInfo(request.getExtendInfo(), "TITLE", "DESCRIPTION", "CATEGORY", "DATE_START", "TIME_START", "LOCATION");
                        filePath = fileInfo.getEventGalleryPath(fileName);
                        coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        bizAdminInnerService.createEvent(session.getOrgId(), fileName, request.getExtendInfo());
                        break;

                    case ADMIN_EVENT_GALLERY_UPDATE:
                        bizAdminInnerService.validateExtendInfo(request.getExtendInfo(), "EVENT_ID", "TITLE", "DESCRIPTION", "CATEGORY", "DATE_START", "TIME_START", "LOCATION");
                        if (request.getMultipartFile() != null && request.getMultipartFile().getSize() > 0) {
                            filePath = fileInfo.getEventGalleryPath(fileName);
                            coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        } else {
                            fileName = null;
                        }
                        bizAdminInnerService.updateEvent(session.getOrgId(), fileName, request.getExtendInfo());
                        break;

                    case ADMIN_VIDEO_CARD_GALLERY:
                        bizAdminInnerService.validateExtendInfo(request.getExtendInfo(), "SECTION", "SECTION_LABEL", "TARGET_TYPE", "TARGET_URL");
                        filePath = fileInfo.getVideoCardGalleryPath(fileName);
                        coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        bizAdminInnerService.createVideoCard(session.getOrgId(), fileName, request.getExtendInfo());
                        bizAppCacheService.reloadCacheItem(BizCacheEnum.APP_IMAGE_GALLERY_ALL);
                        break;

                    case ADMIN_DOCS_GALLERY:
                        bizAdminInnerService.validateExtendInfo(request.getExtendInfo(), "TITLE");
                        filePath = fileInfo.getDocsGalleryPath(fileName);
                        coreFileService.storeFile(request.getMultipartFile().getInputStream(), filePath);
                        bizAdminInnerService.createDocumentGallery(session.getOrgId(), request.getFileType(), request.getExtendInfo().get("TITLE"), fileName);
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

    public BizResult adminCommonSwitchFlag(BizSwitchFlagObject switchFlagObject, BizWebUpdateItemRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(switchFlagObject, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.isNotTrue(switchFlagObject == BizSwitchFlagObject.UNKNOWN, EzErrorCode.ILLEGAL_PARAM);
                validateBizUpdateItemRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                authorizeAdminMember(session.getMemberRoles());
                request.setOrgId(session.getOrgId());
                bizAdminInnerService.commonSwitchFlag(switchFlagObject, request);
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

    public BizResult getProfileDetail(String sessionId) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(sessionId);
                authorizeAdminMember(session.getMemberRoles());
                bizResult.setSuccess(true);
                bizResult.setObject(bizAdminInnerService.getCandidateProfile(session.getOrgId(), session.getOrgCode()));
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult profileBioUpdate(BizWebCreateRequest<List<WebCandidateBio>> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getData(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                authorizeAdminMember(session.getMemberRoles());
                bizAdminInnerService.profileBioUpdate(session.getOrgId(), request.getData());
                bizResult.setSuccess(true);
                bizResult.setObject(BizConstant.Message.SUCCESS_COMMON);
                bizAppCacheService.reloadCacheItem(BizCacheEnum.CANDIDATE_PROFILE);
                bizAppCacheService.reloadCacheItem(BizCacheEnum.CANDIDATE_BIOGRAPHY);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult profileUpdate(BizWebCommonRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                bizAdminInnerService.validateExtendInfo(request.getExtendInfo(), "VISION", "MISSION", "CONTACT_NUMBER");
                bizAdminInnerService.profileUpdate(session.getOrgId(), request.getExtendInfo());
                bizResult.setSuccess(true);
                bizResult.setObject(BizConstant.Message.SUCCESS_COMMON);
                bizAppCacheService.reloadCacheItem(BizCacheEnum.CANDIDATE_PROFILE);
                bizAppCacheService.reloadCacheItem(BizCacheEnum.CANDIDATE_BIOGRAPHY);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult getSubOrganizations(BizWebPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateBizPageRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                PageResult<BizSubOrganization> appDocsResult = bizAdminInnerService.getSubOrganizations(
                        session.getOrgId(),
                        request.getPageNumber(),
                        request.getPageSize(),
                        "createdTime",
                        "desc"
                );

                bizResult.setObject(appDocsResult);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult getSubOrganizationAll(String sessionId) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(sessionId);
                List<SubOrganization> subOrganizations = BeanFacadeUtil
                        .getBean(SubOrganizationService.class)
                        .getSubOrganizationAll(session.getOrgId());

                bizResult.setSuccess(true);
                bizResult.setObject(subOrganizations);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult createSubOrganization(BizWebCreateRequest<BizSubOrganization> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getData(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getName(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getAddress(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                request.getData().setOrgId(session.getOrgId());
                request.getData().setOrgCode(session.getOrgCode());

                appSubOrganizationService.createSubOrganization(request.getData());

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

    public BizResult getMembersPage(WebBizPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                validateWebBizPageRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                request.setOrgId(session.getOrgId());
                request.setPageSort(PageSort.NEWEST);

                PageResult<MemberBackOffice> memberResult = BeanFacadeUtil
                        .getBean(MemberBackOfficeService.class)
                        .getMemberPage(request);

                bizResult.setObject(memberResult);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult getMemberRequiredData(BizWebDetailRequest<String> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());

                BizMemberRequiredData requiredData = bizAdminInnerService.getMemberRequiredData(session.getOrgId());
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

    public BizResult getCoreAreas(String sessionId, String level, String parentId) {
        BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                authorizedAdminSession(sessionId);

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

    public BizResult createBizMember(BizWebCreateRequest<BizMember> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getData(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getData().getSubOrganization(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getSubOrganization().getSubOrgId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getName(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getData().getPhone(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(request.getSessionId());
                request.setOrgId(session.getOrgId());
                request.getData().setOrgId(session.getOrgId());
                request.getData().setReferrerId(session.getMemberId());

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

    public BizResult getMemberBackOffice(String sessionId, String memberId) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(memberId, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(sessionId);
                MemberBackOffice member = BeanFacadeUtil
                        .getBean(MemberBackOfficeService.class)
                        .getMemberDetail(memberId);

                AssertUtil.notNull(member, EzErrorCode.DATA_NOT_FOUND);
                AssertUtil.isTrue(StringUtil.equals(session.getOrgId(), member.getOrgId()), EzErrorCode.DATA_NOT_FOUND);

                bizResult.setSuccess(true);
                bizResult.setObject(member);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult uploadMemberData(String sessionId, String subOrgId, MultipartFile multipartFile) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(subOrgId, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(multipartFile, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthAdminSession session = authorizedAdminSession(sessionId);
                FileStreamImportRequest importRequest = new FileStreamImportRequest();
                importRequest.setOrgId(session.getOrgId());
                importRequest.setSubOrgId(subOrgId);
                importRequest.setInputStream(multipartFile.getInputStream());

                BaseResult baseResult = BeanFacadeUtil
                        .getBean(MemberImportProcessor.class)
                        .process(importRequest);

                bizResult.setSuccess(baseResult.isSuccess());
                bizResult.setObject(baseResult.getObject());
                bizResult.setErrorMessage(baseResult.getErrorMessage());
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    private void validateWebBizPageRequest(WebBizPageRequest request) throws EzErrorException {
        AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notNull(request.getPageNumber(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.isTrue(request.getPageNumber() > 0, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notNull(request.getPageSize(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.isTrue(request.getPageSize() > 0, EzErrorCode.ILLEGAL_PARAM);
    }

    private void validateBizPageRequest(BizWebPageRequest request) throws EzErrorException {
        AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notNull(request.getPageNumber(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notNull(request.getPageSize(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.isTrue(request.getPageNumber() > 0, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.isTrue(request.getPageSize() > 0, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getSessionId(), EzErrorCode.SESSION_INVALID);
    }

    private void validateBizDetailRequest(BizWebDetailRequest<String> request) throws EzErrorException {
        AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
    }

    private void validateBizDetailItemRequest(BizWebUpdateItemRequest request) {
        AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getItemId(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getSection(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getValue(), EzErrorCode.ILLEGAL_PARAM);
    }

    private void validateBizUpdateRequest(BizWebUpdateRequest request) {
        AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notNull(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
    }

    private void validateBizUpdateItemRequest(BizWebUpdateItemRequest request) {
        AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getItemId(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getSection(), EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.notBlank(request.getValue(), EzErrorCode.ILLEGAL_PARAM);
    }

    private CoreAuthAdminSession authorizedAdminSession(String sessionId) throws Exception {
        CoreAuthAdminSession session = coreAuthService.adminAuthWebSessionId(sessionId);
        AssertUtil.notNull(session, EzErrorCode.SESSION_INVALID);
        AssertUtil.notBlank(session.getMemberRoles(), EzErrorCode.MEMBER_UNAUTHORIZED);
        List<String> roles = Arrays.asList(session.getMemberRoles().split(","));
        AssertUtil.isTrue(roles.contains(BizMemberRole.ADMIN_ORG.getCode()), EzErrorCode.MEMBER_UNAUTHORIZED);
        return session;
    }

    private void authorizeSuperUserOrAdminMember(String memberRoles) throws EzErrorException {
        AssertUtil.notBlank(memberRoles, EzErrorCode.MEMBER_UNAUTHORIZED);
        List<String> roles = Arrays.asList(memberRoles.split(","));
        boolean isAdminOrSuperUser = roles.contains(BizMemberRole.ADMIN_ORG.getCode()) || roles.contains(BizMemberRole.SUPERUSER.getCode());
        AssertUtil.isTrue(isAdminOrSuperUser, EzErrorCode.MEMBER_UNAUTHORIZED);
    }

    private boolean isSuperAdminCommonUpload(BizUploadScene uploadScene) {
        switch (uploadScene) {
            case ADMIN_APP_BUILD_PACKAGE:
            case ADMIN_APP_ICON:
                return true;
        }
        return false;
    }
}
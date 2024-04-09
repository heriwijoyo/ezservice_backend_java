/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.inner.service;

import id.ezclouds.biz.ezservice.converter.BizMemberConverter;
import id.ezclouds.biz.ezservice.enums.BizMemberRole;
import id.ezclouds.biz.ezservice.model.AppConfig;
import id.ezclouds.biz.ezservice.model.admin.BizApplicationConfig;
import id.ezclouds.biz.ezservice.model.admin.BizOrganization;
import id.ezclouds.biz.ezservice.model.admin.BizOrganizationDetail;
import id.ezclouds.biz.ezservice.model.member.BizGender;
import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.model.member.BizMemberInfo;
import id.ezclouds.biz.ezservice.model.news.BizWebDetailNews;
import id.ezclouds.biz.ezservice.model.news.BizWebSimpleNews;
import id.ezclouds.biz.ezservice.service.apibiz.BizConnectService;
import id.ezclouds.biz.ezservice.service.dataservice.AppConfigService;
import id.ezclouds.biz.ezservice.service.dataservice.AppImageGalleryService;
import id.ezclouds.biz.ezservice.service.dataservice.NewsInnerService;
import id.ezclouds.biz.ezservice.service.dataservice.VideoCardService;
import id.ezclouds.biz.ezservice.service.dataservice.model.BizAppBuildPackage;
import id.ezclouds.biz.ezservice.service.dataservice.model.BizAppConfig;
import id.ezclouds.biz.ezservice.service.dataservice.model.WebImageGallery;
import id.ezclouds.biz.ezservice.service.dataservice.request.AppImageGalleryRequest;
import id.ezclouds.biz.ezservice.service.dataservice.request.NewsCreateRequest;
import id.ezclouds.biz.ezservice.service.dataservice.request.VideoCardCreateRequest;
import id.ezclouds.biz.ezservice.service.result.PageResult;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.RandomUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.model.CoreAuthAppClient;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.MemberStatus;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.constant.CoreConstant;
import id.ezclouds.core.shared.enums.CoreSequenceScene;
import id.ezclouds.core.shared.model.CoreSequenceConfig;
import id.ezclouds.core.shared.repo.dataobject.EzCoreOrganizationDO;
import id.ezclouds.core.shared.service.CoreConfigService;
import id.ezclouds.core.shared.service.CoreOrganizationService;
import id.ezclouds.core.shared.service.CoreSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminInnerService.java, v 0.1 2024‐02‐13 2:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAdminInnerService {

    @Autowired
    private AppImageGalleryService appImageGalleryService;

    @Autowired
    private NewsInnerService newsInnerService;

    @Autowired
    private VideoCardService videoCardService;

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private CoreAuthService coreAuthService;

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private CoreConfigService coreConfigService;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private BizMemberInnerService bizMemberInnerService;

    @Autowired
    private BizConnectService bizConnectService;

    public void createAppBuildPackage(String orgId, String platformId, int versionCode, String versionName) throws EzErrorException {
        BizAppBuildPackage buildPackage = new BizAppBuildPackage();
        buildPackage.setOrgId(orgId);
        buildPackage.setPlatform(platformId);
        buildPackage.setVersionCode(versionCode);
        buildPackage.setVersionName(versionName);
        buildPackage.setStatus(1);
        try {
            appConfigService.createAppBuildPackage(buildPackage);
        } catch (DataIntegrityViolationException integrityException) {
            throw new EzErrorException(EzErrorCode.IDEMPOTENT_ERROR);
        } catch (Exception e) {
            throw new EzErrorException(EzErrorCode.SYSTEM_ERROR);
        }
    }

    public void createAppImageGallery(String orgId, String fileName, Map<String, String> extInfo) {
        AppImageGalleryRequest request = new AppImageGalleryRequest();
        request.setOrgId(orgId);
        request.setImageUrl(fileName);
        if (extInfo != null && !extInfo.isEmpty()) {
            request.setTitle(extInfo.get("TITLE"));
        }
        appImageGalleryService.createImageGallery(request);
    }

    public PageResult<WebImageGallery> getImageGalleryAll(String orgId, int pageNumber, int pageSize, String sortBy, String sort) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy, sort);
        return appImageGalleryService.getImageGalleryAll(orgId, pageRequest);
    }

    public String updateImageGallery(String orgId, String itemId, String section, String value) {
        return appImageGalleryService.updateImageGallery(orgId, itemId, section, value);
    }

    public void validateExtendInfo(Map<String, String> extendInfo, String... extKeys) throws EzErrorException {
        AssertUtil.notNull(extendInfo, EzErrorCode.ILLEGAL_PARAM);
        AssertUtil.isTrue(!extendInfo.isEmpty(), EzErrorCode.ILLEGAL_PARAM);
        for (String extKey : extKeys) {
            AssertUtil.notBlank(extendInfo.get(extKey), EzErrorCode.ILLEGAL_PARAM);
        }
    }

    public void createNews(String orgId, String imageUrl, Map<String, String> extInfo) {
        NewsCreateRequest request = new NewsCreateRequest();
        request.setOrgId(orgId);
        request.setImageUrl(imageUrl);

        if (extInfo != null && !extInfo.isEmpty()) {
            request.setTitle(extInfo.get("TITLE"));
            request.setDescription(extInfo.get("DESCRIPTION"));
            request.setPublishDate(extInfo.get("PUBLISH_DATE"));
            request.setCategory(extInfo.get("CATEGORY"));
            request.setContent(extInfo.get("CONTENT"));
            request.setSource(extInfo.get("SOURCE"));
            request.setSourceUrl(extInfo.get("SOURCE_URL"));
        }
        newsInnerService.createNews(request);
    }

    public void updateNews(String orgId, String imageUrl, Map<String, String> extInfo) {
        BizWebDetailNews detailNews = new BizWebDetailNews();
        detailNews.setNewsId(extInfo.get("NEWS_ID"));
        detailNews.setTitle(extInfo.get("TITLE"));
        detailNews.setDescription(extInfo.get("DESCRIPTION"));
        detailNews.setPublishDate(extInfo.get("PUBLISH_DATE"));
        detailNews.setCategory(extInfo.get("CATEGORY"));
        detailNews.setImageUrl(imageUrl);
        detailNews.setContent(extInfo.get("CONTENT"));
        detailNews.setSource(extInfo.get("SOURCE"));
        detailNews.setSourceUrl(extInfo.get("SOURCE_URL"));
        newsInnerService.updateNews(orgId, detailNews);
    }

    public PageResult<BizWebSimpleNews> getSimpleNews(String orgId, int pageNumber, int pageSize, String sortBy, String sort) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy, sort);
        return newsInnerService.adminGetSimpleNews(orgId, pageRequest);
    }

    public BizWebDetailNews getNewsDetail(String orgId, String newsId) {
        return newsInnerService.adminGetNewsDetail(orgId, newsId);
    }

    public void newsFlagSwitch(String orgId, String newsId, String section, int value) {
        newsInnerService.adminNewsFlagSwitch(orgId, newsId, section, value);
    }

    public void createVideoCard(String orgId, String imageUrl, Map<String, String> extInfo) {
        VideoCardCreateRequest request = new VideoCardCreateRequest();
        request.setOrgId(orgId);
        request.setThumbnail(imageUrl);

        if (extInfo != null && !extInfo.isEmpty()) {
            request.setSection(extInfo.get("SECTION"));
            request.setSectionName(extInfo.get("SECTION_NAME"));
            request.setTitle(extInfo.get("TITLE"));
            request.setDescription(extInfo.get("DESCRIPTION"));
            request.setTargetType(extInfo.get("TARGET_TYPE"));
            request.setTargetUrl(extInfo.get("TARGET_URL"));
        }
        videoCardService.createVideoCard(request);
    }

    public PageResult<BizOrganization> getOrganizationAll(int pageNumber, int pageSize, String sortBy, String sort) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortBy, sort);
        Page<EzCoreOrganizationDO> findResult = coreOrganizationService.getOrganizationAll(pageRequest);
        List<BizOrganization> resultData = findResult
                .getContent()
                .stream()
                .filter(modelDO -> !CoreConstant.SU_ORG_ID.equals(modelDO.getOrgId()))
                .map(this::convert)
                .collect(Collectors.toList());

        PageResult<BizOrganization> pageResult = new PageResult<>();
        composePageResult(pageResult, findResult);
        pageResult.setData(resultData);
        return pageResult;
    }

    public BizOrganizationDetail getOrganizationDetail(String orgId) {
        BizOrganizationDetail detail = new BizOrganizationDetail();
        detail.setBizOrganization(getOrganizationById(orgId));
        detail.setBizApplicationConfig(getAppConfig(orgId));
        detail.setBizAppConfigs(appConfigService.getAppConfigByOrgId(orgId));
        detail.setCoreOrgConfigMap(coreConfigService.getOrgConfigByOrgId(orgId));
        detail.setAdminMembers(getOrgAdminMembers(orgId));
        detail.setBizAppBuildPackages(getAppBuildPackages(orgId));
        return detail;
    }

    public BizOrganization getOrganizationById(String orgId) {
        EzCoreOrganizationDO organizationDO = coreOrganizationService.getOrganizationById(orgId);
        AssertUtil.notNull(organizationDO, EzErrorCode.DATA_NOT_FOUND);
        return convert(organizationDO);
    }

    @Transactional
    public void updateOrganization(BizOrganization organization) {
        EzCoreOrganizationDO organizationDO = convert(organization);
        organizationDO.setModifiedTime(DateUtil.getCurrentFormattedDate());
        coreOrganizationService.saveOrganization(organizationDO);
    }

    @Transactional
    public void createOrganization(BizOrganization org) {
        EzCoreOrganizationDO modelDO = new EzCoreOrganizationDO();
        modelDO.setOrgId(org.getOrgId());
        modelDO.setCode(org.getCode());
        modelDO.setName(org.getName());
        modelDO.setAddress(org.getAddress());
        modelDO.setContactName(org.getContactName());
        modelDO.setContactPhone(org.getContactPhone());
        modelDO.setContactEmail(org.getContactEmail());
        modelDO.setCreatedTime(DateUtil.getCurrentFormattedDate());
        modelDO.setModifiedTime(DateUtil.getCurrentFormattedDate());
        modelDO.setStatus(1);
        coreOrganizationService.saveOrganization(modelDO);

        initiateOrgConfig(modelDO);
    }

    private BizApplicationConfig getAppConfig(String orgId) {
        BizApplicationConfig bizApplicationConfig = new BizApplicationConfig();

        CoreAuthAppClient appClient = coreAuthService.getAppClientByOrgId(orgId);
        if (appClient == null) {
            return bizApplicationConfig;
        }
        bizApplicationConfig.setId(appClient.getId());
        bizApplicationConfig.setOrgId(appClient.getOrgId());
        bizApplicationConfig.setAppId(appClient.getAppId());
        bizApplicationConfig.setClientId(appClient.getClientId());
        bizApplicationConfig.setClientSecret(appClient.getClientSecret());
        bizApplicationConfig.setCreatedTime(appClient.getCreatedTime());
        bizApplicationConfig.setModifiedTime(appClient.getModifiedTime());
        bizApplicationConfig.setStatus(appClient.getStatus());

        return bizApplicationConfig;
    }

    public void saveClientAppConfig(BizApplicationConfig applicationConfig) {
        CoreAuthAppClient coreAuthAppClient = new CoreAuthAppClient();
        coreAuthAppClient.setId(applicationConfig.getId());
        coreAuthAppClient.setOrgId(applicationConfig.getOrgId());
        coreAuthAppClient.setAppId(applicationConfig.getAppId());
        coreAuthAppClient.setClientId(applicationConfig.getClientId());
        coreAuthAppClient.setClientSecret(applicationConfig.getClientSecret());
        coreAuthAppClient.setCreatedTime(applicationConfig.getCreatedTime());
        coreAuthAppClient.setModifiedTime(DateUtil.getCurrentFormattedDate());
        coreAuthAppClient.setStatus(applicationConfig.getStatus());
        coreAuthService.saveAuthAppClient(coreAuthAppClient);
    }

    public void saveBizAppConfigs(String orgId, List<BizAppConfig> bizAppConfigs) {
        for (BizAppConfig bizAppConfig : bizAppConfigs) {
            appConfigService.saveBizAppConfig(orgId, bizAppConfig);
        }
    }

    public void saveCoreOrgConfig(String orgId, Map<String, String> configMap) {
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            coreConfigService.saveCoreOrgConfig(orgId, entry.getKey(), entry.getValue());
        }
    }

    public void adminOrgCreateMember(String orgId, BizMember bizMember) throws Exception {
        BizApplicationConfig bizApplicationConfig = getAppConfig(orgId);
        String orgCode = getOrganizationById(orgId).getCode();
        String appId = bizApplicationConfig.getAppId();

        bizMember.setGender(BizGender.MALE);
        bizMember.setDateOfBirth(DateUtil.getCurrentFormattedDate());
        bizMember.setRoles(BizMemberRole.ADMIN_ORG.getCode());
        bizMember.setPhoneVerified(false);
        bizMember.setEmailVerified(false);
        bizMember.setAddressVerified(false);
        bizMember.setCreatedTime(DateUtil.getCurrentFormattedDate());
        bizMember.setModifiedTime(DateUtil.getCurrentFormattedDate());

        CoreMember coreMember = BizMemberConverter.convert(bizMember);
        coreMember.setSourceId("BACKOFFICE");
        coreMember.setMemberStatus(MemberStatus.ACTIVE);
        BizMemberInfo bizMemberInfo = bizMemberInnerService
                .adminOrgCreateMember(orgId, orgCode, appId, coreMember);

        //generate member password
        String newPassword = RandomUtil.generateNumberCode(6);
        coreAuthService.updateMemberClientPassword(bizMemberInfo.getBizMemberClient().getClientId(), newPassword);

        AppConfig appConfig = appConfigService.getAppConfig(orgId);
        bizConnectService.memberSendPassword(
                bizMemberInfo.getBizMember().getPhone(),
                newPassword,
                appConfig.getAppName(),
                appConfig.getAndroidUpdateUrl()
        );
    }

    private List<BizMember> getOrgAdminMembers(String orgId) {
        return coreMemberService
                .getMemberByOrgIdAndRoles(orgId, BizMemberRole.ADMIN_ORG.getCode())
                .stream()
                .map(coreMember -> BizMemberConverter.convert(coreMember, null))
                .collect(Collectors.toList());
    }

    private List<BizAppBuildPackage> getAppBuildPackages(String orgId) {
        return appConfigService.getAppBuildPackages(orgId);
    }

    private void initiateOrgConfig(EzCoreOrganizationDO organizationDO) {
        String scene = CoreSequenceScene.CORE_MEMBER_ID.getCode();
        String sceneCode = CoreSequenceScene.CORE_MEMBER_ID.getSceneCode();
        CoreSequenceConfig memberSeqConfig = new CoreSequenceConfig();
        memberSeqConfig.setSeqId(organizationDO.getOrgId() +"_"+ scene);
        memberSeqConfig.setOrgId(organizationDO.getOrgId());
        memberSeqConfig.setScene(scene);
        memberSeqConfig.setSceneCode(sceneCode);
        memberSeqConfig.setStepMin(100);
        memberSeqConfig.setStepMax(199);
        memberSeqConfig.setStepValue(100);
        memberSeqConfig.setSeqLength(9);
        memberSeqConfig.setSequence(0);
        coreSequenceService.createSequenceConfig(memberSeqConfig);

        String subOrgscene = CoreSequenceScene.APP_SUB_ORG.getCode();
        String subOrgsceneCode = CoreSequenceScene.APP_SUB_ORG.getSceneCode();
        CoreSequenceConfig subOrgSeqConfig = new CoreSequenceConfig();
        subOrgSeqConfig.setSeqId(organizationDO.getOrgId() +"_"+ subOrgscene);
        subOrgSeqConfig.setOrgId(organizationDO.getOrgId());
        subOrgSeqConfig.setScene(subOrgscene);
        subOrgSeqConfig.setSceneCode(subOrgsceneCode);
        subOrgSeqConfig.setStepMin(100);
        subOrgSeqConfig.setStepMax(100);
        subOrgSeqConfig.setStepValue(100);
        subOrgSeqConfig.setSeqLength(3);
        subOrgSeqConfig.setSequence(0);
        coreSequenceService.createSequenceConfig(subOrgSeqConfig);
    }

    private PageRequest buildPageRequest(int page, int size, String sortBy, String sort) {
        if (StringUtil.isBlank(sortBy)) {
            return PageRequest.of(page - 1, size);
        }

        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (StringUtil.equalsIgnoreCase("DESC", sort)) {
            sortDirection = Sort.Direction.DESC;
        }
        return PageRequest.of(page - 1, size, Sort.by(sortDirection, sortBy));
    }

    private void composePageResult(PageResult pageResult, Page page) {
        pageResult.setPageNumber(page.getPageable().getPageNumber() + 1);
        pageResult.setPageSize(page.getPageable().getPageSize());
        pageResult.setNumberRecord(page.getNumberOfElements());
        pageResult.setTotalPage(page.getTotalPages());
        pageResult.setTotalRecord((int) page.getTotalElements());
        pageResult.setHasNext(page.hasNext());
        pageResult.setHasPrevious(page.hasPrevious());
    }

    private BizOrganization convert(EzCoreOrganizationDO modelDO) {
        if (modelDO == null) { return null; }
        BizOrganization organization = new BizOrganization();
        organization.setOrgId(modelDO.getOrgId());
        organization.setName(modelDO.getName());
        organization.setCode(modelDO.getCode());
        organization.setAddress(modelDO.getAddress());
        organization.setContactName(modelDO.getContactName());
        organization.setContactPhone(modelDO.getContactPhone());
        organization.setContactEmail(modelDO.getContactEmail());
        organization.setExtendConfig(modelDO.getExtendConfig());
        organization.setCreatedTime(modelDO.getCreatedTime());
        organization.setModifiedTime(modelDO.getModifiedTime());
        organization.setStatus(modelDO.getStatus());
        return organization;
    }

    private EzCoreOrganizationDO convert(BizOrganization organization) {
        EzCoreOrganizationDO organizationDO = new EzCoreOrganizationDO();
        organizationDO.setOrgId(organization.getOrgId());
        organizationDO.setName(organization.getName());
        organizationDO.setCode(organization.getCode());
        organizationDO.setAddress(organization.getAddress());
        organizationDO.setContactName(organization.getContactName());
        organizationDO.setContactPhone(organization.getContactPhone());
        organizationDO.setContactEmail(organization.getContactEmail());
        organizationDO.setCreatedTime(organization.getCreatedTime());
        organizationDO.setModifiedTime(organization.getModifiedTime());
        organizationDO.setStatus(organization.getStatus());
        return organizationDO;
    }
}
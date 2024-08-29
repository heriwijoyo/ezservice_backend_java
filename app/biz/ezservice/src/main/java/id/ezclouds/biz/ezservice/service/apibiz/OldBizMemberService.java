/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.config.BizPublicUrlResolver;
import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.biz.ezservice.constant.BizConstant;
import id.ezclouds.biz.ezservice.converter.BizMemberConverter;
import id.ezclouds.biz.ezservice.enums.BizMemberRole;
import id.ezclouds.biz.ezservice.enums.BizUploadScene;
import id.ezclouds.biz.ezservice.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.ezservice.model.member.*;
import id.ezclouds.biz.ezservice.model.profile.MemberProfile;
import id.ezclouds.biz.ezservice.service.app.AppReportService;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberImportRepository;
import id.ezclouds.biz.ezservice.service.inner.service.BizMemberInnerService;
import id.ezclouds.biz.ezservice.service.app.AppProfileService;
import id.ezclouds.biz.ezservice.service.request.BizMemberRegisterRequest;
import id.ezclouds.biz.ezservice.service.request.BizMemberUploadRequest;
import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.common.model.request.BizRequest;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.service.AppSubOrganizationService;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.model.result.BizPageInfo;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.biz.ezservice.util.BizExtendInfoUtil;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import id.ezclouds.core.member.constant.CoreMemberField;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.file.PrivateFileResolver;
import id.ezclouds.core.shared.file.PublicFileResolver;
import id.ezclouds.core.shared.service.CoreFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: OldBizMemberService.java, v 0.1 2023‐12‐31 12:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class OldBizMemberService extends BizBaseService {

    @Autowired
    private BizMemberInnerService bizMemberInnerService;

    @Autowired
    private AppProfileService appProfileService;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private CoreFileService coreFileService;

    @Autowired
    private BizCommonConfigService bizCommonConfigService;

    @Autowired
    private AppReportService appReportService;

    @Autowired
    private AppSubOrganizationService appSubOrganizationService;

    @Autowired
    private BizMemberImportRepository bizMemberImportRepository;

    public BizResult getMemberProfile() {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = authAppMemberSession();

                CoreMember coreMember = coreMemberService.getOptimisticCoreMember(sessionInfo.getMemberId());
                CoreMemberExtension coreMemberExtension = coreMemberService.getPessimisticCoreMemberExtension(sessionInfo.getMemberId());
                BizMember bizMember = BizMemberConverter.convert(coreMember, coreMemberExtension);

                BizPublicUrlResolver publicConfig = bizCommonConfigService.resolvePublicUrl(getOrgCode(), bizMember.getMemberId());
                BizAnnotationProcessor.annotatePublicConfig(bizMember, publicConfig);

                MemberProfile memberProfile = new MemberProfile();
                memberProfile.setAppProfiles(appProfileService.getAppProfile(getOrgId()));
                memberProfile.setBizMember(bizMember);

                bizResult.setSuccess(true);
                bizResult.setObject(memberProfile);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult registerMember(BizMemberRegisterRequest request) throws EzErrorException {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {

            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM, "request (BizMemberRegisterRequest) is null");
                AssertUtil.notBlank(request.getExtendInfo().get(AppConstant.ExtKey.SOURCE_ID), EzErrorCode.ILLEGAL_PARAM, "request.extendInfo.SOURCE_ID is blank");
                AssertUtil.notBlank(request.getRoles(), EzErrorCode.ILLEGAL_PARAM, "request.roles is blank");
                AssertUtil.notBlank(request.getName(), EzErrorCode.ILLEGAL_PARAM, "request.name is blank");
                AssertUtil.notNull(request.getBizGender(), EzErrorCode.ILLEGAL_PARAM, "request.bizGender is null");
                AssertUtil.notBlank(request.getDateOfBirth(), EzErrorCode.ILLEGAL_PARAM, "request.dateOfBirth is blank");
                AssertUtil.notBlank(request.getPhone(), EzErrorCode.ILLEGAL_PARAM, "request.phone is blank");
                AssertUtil.notBlank(request.getAddress(), EzErrorCode.ILLEGAL_PARAM, "request.address is blank");
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = authAppMemberSession();
                AssertUtil.notBlank(sessionInfo.getMemberRoles(), EzErrorCode.UNAUTHORIZED);
                List<String> memberRoles = Arrays.asList(sessionInfo.getMemberRoles().split(","));
                AssertUtil.isTrue(memberRoles.size() > 0, EzErrorCode.UNAUTHORIZED);

                BizMemberRegisterMode bizRegisterMode = request.getRegisterMode();
                request.setRoles("");

                if (bizRegisterMode == BizMemberRegisterMode.BY_RECRUITER) {
                    AssertUtil.isTrue(memberRoles.contains(BizMemberRole.OP_RECRUITER.getCode()), EzErrorCode.UNAUTHORIZED);
                    if (StringUtil.isBlank(request.getSubOrgId())) {
                        CoreMember referrerMember = coreMemberService.getOptimisticCoreMember(sessionInfo.getMemberId());
                        request.setSubOrgId(referrerMember.getSubOrgId());
                        request.setRoles("");
                    }
                }
                if (bizRegisterMode == BizMemberRegisterMode.BY_SUB_ORG_ADMIN) {
                    AssertUtil.isTrue(memberRoles.contains(BizMemberRole.ADMIN_SUB_ORG.getCode()), EzErrorCode.UNAUTHORIZED);
                    request.setRoles("");
                }
                if (bizRegisterMode == BizMemberRegisterMode.BY_ORG_ADMIN) {
                    AssertUtil.isTrue(memberRoles.contains(BizMemberRole.ADMIN_ORG.getCode()), EzErrorCode.UNAUTHORIZED);
                    request.setRoles("OP_RECRUITER");
                    //tmp close member registration from app
                    bizResult.setSuccess(false);
                    bizResult.setErrorMessage("Fitur ini tidak dapat digunakan untuk sementara waktu. Gunakan fitur pada auth admin untuk mendaftarkan anggota baru");
                    return;
                }

                request.setReferrerId(sessionInfo.getMemberId());

                BizMemberInfo bizMemberInfo = bizMemberInnerService.processRegisterMember(request);
                BizMemberRegisterResult result = new BizMemberRegisterResult();
                result.setMemberId(bizMemberInfo.getBizMember().getMemberId());
                result.setMessage(AppConstant.BizMessage.MEMBER_REGISTER_SUCCESS);

                bizResult.setObject(result);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                if (ezErrorCode == EzErrorCode.IDEMPOTENT_ERROR) {
                    return AppConstant.MEMBER_REGISTER_IDEMPOTENT;
                }
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult memberUploadMedia(BizMemberUploadRequest request) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                request.validateMultipartRequest();
                //TODO: add image size and mime type validation
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo memberSession = authAppMemberSession();
                PrivateFileResolver privateFileResolver = coreFileService
                        .resolveMemberFileInfo(getOrgId(), memberSession.getMemberId());

                PublicFileResolver publicFileResolver = coreFileService
                        .resolvePublicFileInfo(getOrgId());

                String fileName = DateUtil.getTimeNowToString() + "." + request.getFileExtension();

                Map<String, String> updateField = new HashMap<>();
                switch (request.getScene()) {
                    case AVATAR:
                        coreFileService.storeFile(
                                request.getMultipartFile().getInputStream(),
                                privateFileResolver.getAvatarPath(fileName));
                        String nickName = request.getExtendInfo().get(BizConstant.ExtKey.NICKNAME);
                        updateField.put(CoreMemberField.AVATAR, fileName);
                        updateField.put(CoreMemberField.NICKNAME, nickName);
                        coreMemberService.updateMemberField(memberSession.getMemberId(), updateField);
                        break;

                    case ID_CARD:
                        coreFileService.storeFile(
                                request.getMultipartFile().getInputStream(),
                                privateFileResolver.getIdCardPath(fileName));
                        updateField.put(CoreMemberField.ID_CARD, fileName);
                        coreMemberService.updateMemberField(memberSession.getMemberId(), updateField);
                        break;

                    case FAMILY_CARD:
                        coreFileService.storeFile(
                                request.getMultipartFile().getInputStream(),
                                privateFileResolver.getFamilyCardPath(fileName));
                        updateField.put(CoreMemberField.FAMILY_CARD, fileName);
                        coreMemberService.updateMemberField(memberSession.getMemberId(), updateField);
                        break;

                    case REPORT_IMAGE:
                        request.getExtendInfo().put("ORG_ID", getOrgId());
                        request.getExtendInfo().put("MEMBER_ID", memberSession.getMemberId());
                        request.getExtendInfo().put("FILE_TYPE", getMemberReportFileType(request.getScene()));
                        request.getExtendInfo().put("FILE_URL", fileName);
                        appReportService.validateExtendInfo(request.getExtendInfo(), "ORG_ID", "MEMBER_ID", "CAPTION", "FILE_TYPE", "FILE_URL");
                        coreFileService.storeFile(
                                request.getMultipartFile().getInputStream(),
                                publicFileResolver.getReportImagePath(fileName));
                        appReportService.storeAppReport(request.getExtendInfo());
                        break;

                    default:
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

    public BizResult getMembers(BizPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo session = authAppMemberSession();
                authorizeAdminOrSubOrgAdmin(session.getMemberRoles());

                BizPageInfo<CoreMember> bizPageInfo = bizMemberInnerService.getMemberPage(getOrgId(), request);
                bizResult.setSuccess(true);
                bizResult.setBizPageInfo(bizPageInfo);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult memberUpdate(BizRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                BizExtendInfoUtil.validateExtendInfo(request.getExtendInfo(), "MEMBER_ID");
            }

            @Override
            public void onBizProcess() throws Exception {
                bizMemberInnerService.memberUpdate(request.getExtendInfo());
                bizResult.setSuccess(true);
                bizResult.setObject("Update Member Success");
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public List<List<String>> getAllMemberData(String orgId) {
        List<List<String>> memberData = new ArrayList<>();

        List<BizSubOrganization> subOrganizations = appSubOrganizationService.getSubOrganizationByOrgId(orgId);
        List<CoreMember> coreMembers = coreMemberService.getAllMembers(orgId);
        List<CoreMemberExtension> coreMemberExtensions = coreMemberService.getAllMemberExtensions(orgId);

        for (CoreMember coreMember : coreMembers) {
            CoreMemberExtension coreExtension = fetchExtension(coreMemberExtensions, coreMember.getMemberId());
            BizMember bizMember = BizMemberConverter.convert(coreMember, coreExtension);
            List<String> rowData = new ArrayList<>();
            rowData.add(fetchSubOrgName(subOrganizations, bizMember.getSubOrganization().getSubOrgId()));
            rowData.add(bizMember.getName());
            rowData.add(bizMember.getIdCardNumber());
            rowData.add(bizMember.getGender().getLabel());
            rowData.add(bizMember.getDateOfBirth());
            rowData.add(bizMember.getPhone());
            rowData.add(bizMember.getEducation());
            rowData.add(bizMember.getOccupation());
            rowData.add(bizMember.getReligion());
            rowData.add(bizMember.getEthnic());
            rowData.add(bizMember.getDistrictName());
            rowData.add(bizMember.getVillageName());
            rowData.add(bizMember.getRukunWarga());
            rowData.add(bizMember.getRukunTetangga());
            rowData.add(bizMember.getTpsNumber());

            memberData.add(rowData);
        }

        return memberData;
    }

    public List<List<String>> getAllImportData(String orgId) {
        List<List<String>> memberData = new ArrayList<>();

        List<BizSubOrganization> subOrganizations = appSubOrganizationService.getSubOrganizationByOrgId(orgId);
        List<BizMemberImportDO> memberImports = bizMemberImportRepository
                .findByOrgIdAndSourceIdNot(orgId, "SYNC_BULK_MEMBER_DATA_REGISTER");

        for (BizMemberImportDO memberImport : memberImports) {
            List<String> rowData = new ArrayList<>();
            rowData.add(fetchSubOrgName(subOrganizations, memberImport.getSubOrgId()));
            rowData.add(memberImport.getName());
            rowData.add(memberImport.getIdCardNumber());
            rowData.add(BizGender.getByCode(memberImport.getGender()).getLabel());
            rowData.add(memberImport.getDateOfBirth());
            rowData.add(memberImport.getPhone());
            rowData.add(memberImport.getEducation());
            rowData.add(memberImport.getOccupation());
            rowData.add(memberImport.getReligion());
            rowData.add(memberImport.getEthnic());
            rowData.add(memberImport.getDistrictName());
            rowData.add(memberImport.getVillageName());
            rowData.add(memberImport.getRukunWarga());
            rowData.add(memberImport.getRukunTetangga());
            rowData.add(memberImport.getTpsNumber());

            memberData.add(rowData);
        }

        return memberData;
    }

    private String getMemberReportFileType(BizUploadScene scene) {
        switch (scene) {
            case REPORT_IMAGE:
                return "IMAGE";
            case REPORT_VIDEO:
                return "VIDEO";
            case REPORT_VOICE:
                return "VOICE";
            default:
                return BizUploadScene.UNKNOWN.getCode();
        }
    }

    private CoreMemberExtension fetchExtension(List<CoreMemberExtension> extensions, String memberId) {
        for (CoreMemberExtension extension : extensions) {
            if (extension.getMemberId().equals(memberId)) {
                return extension;
            }
        }
        return null;
    }

    private String fetchSubOrgName(List<BizSubOrganization> subOrganizations, String subOrgId) {
        if (StringUtil.isBlank(subOrgId)) {
            return "";
        }
        for (BizSubOrganization subOrganization : subOrganizations) {
            if (subOrganization.getSubOrgId().equals(subOrgId)) {
                return subOrganization.getName();
            }
        }
        return "";
    }
}
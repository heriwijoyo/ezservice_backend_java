/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.config.BizPublicConfig;
import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.biz.ezservice.constant.BizConstant;
import id.ezclouds.biz.ezservice.converter.BizMemberConverter;
import id.ezclouds.biz.ezservice.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.model.member.BizMemberInfo;
import id.ezclouds.biz.ezservice.model.profile.MemberProfile;
import id.ezclouds.biz.ezservice.service.inner.service.BizMemberInnerService;
import id.ezclouds.biz.ezservice.service.dataservice.AppProfileService;
import id.ezclouds.biz.ezservice.service.request.BizMemberRegisterRequest;
import id.ezclouds.biz.ezservice.service.request.BizMemberUploadRequest;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.member.MemberFileInfo;
import id.ezclouds.core.shared.service.CoreFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberService.java, v 0.1 2023‐12‐31 12:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberService extends BizBaseService {

    @Autowired
    private BizMemberInnerService bizMemberInnerService;

    @Autowired
    private CoreAuthService coreAuthService;

    @Autowired
    private AppProfileService appProfileService;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private CoreFileService coreFileService;

    @Autowired
    private BizCommonConfigService bizCommonConfigService;

    public BizResult getMemberProfile() {
        final BizResult bizResult = new BizResult();

        if (StringUtil.isBlank(EzAppContextHolder.getContext().getMemberSessionId())) {
            bizResult.setErrorCode(EzErrorCode.SESSION_INVALID);
            return bizResult;
        }

        String sessionId = EzAppContextHolder.getContext().getMemberSessionId();
        String orgId = EzAppContextHolder.getContext().getOrgId();
        int appVersionNo = EzAppContextHolder.getContext().getAppVersionNo();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = coreAuthService.authMemberSession(sessionId);

                CoreMember coreMember = coreMemberService.getOptimisticCoreMember(sessionInfo.getMemberId());
                CoreMemberExtension coreMemberExtension = coreMemberService.getOptimisticCoreMemberExtension(sessionInfo.getMemberId());
                BizMember bizMember = BizMemberConverter.convert(coreMember, coreMemberExtension);

                BizPublicConfig publicConfig = bizCommonConfigService.resolveCommonConfig(getOrgCode(), bizMember.getMemberId());
                BizAnnotationProcessor.annotatePublicConfig(bizMember, publicConfig);

                MemberProfile memberProfile = new MemberProfile();
                memberProfile.setAppProfiles(appProfileService.getAppProfile(orgId));
                memberProfile.setBizMember(bizMember);

                //support older client version
                if (appVersionNo < AppConstant.APP_V2_START_VERSION_NO) {
                    memberProfile.setMemberBase(BizMemberConverter.convert(coreMember));
                }

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
                BizMemberInfo bizMemberInfo = bizMemberInnerService.processRegisterMember(request);
                bizResult.setObject(bizMemberInfo);
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
                CoreAuthMemberSessionInfo memberSession = authMemberSession();
                MemberFileInfo memberFileInfo = coreFileService
                        .resolveMemberFileInfo(getOrgId(), memberSession.getMemberId());

                String fileName = DateUtil.getTimeNowToString() + "." + request.getFileExtension();

                switch (request.getScene()) {
                    case BizConstant.UploadScene.AVATAR:
                        coreFileService.storeFile(
                                request.getMultipartFile().getInputStream(),
                                memberFileInfo.getAvatarPath(fileName));
                        String nickName = request.getExtendInfo().get(BizConstant.ExtKey.NICKNAME);
                        coreMemberService.updateNicknameAndAvatar(memberSession.getMemberId(), nickName, fileName);
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
}
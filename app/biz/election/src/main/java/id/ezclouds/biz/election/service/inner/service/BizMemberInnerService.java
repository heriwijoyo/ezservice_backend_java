/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.inner.service;

import id.ezclouds.biz.election.converter.BizMemberConverter;
import id.ezclouds.biz.election.model.AppConfig;
import id.ezclouds.biz.election.model.member.BizMember;
import id.ezclouds.biz.election.service.app.AppConfigService;
import id.ezclouds.biz.election.service.request.BizMemberRegisterRequest;
import id.ezclouds.biz.election.service.request.BizPageRequest;
import id.ezclouds.biz.election.util.PageRequestUtil;
import id.ezclouds.biz.election.converter.BizMemberClientConverter;
import id.ezclouds.biz.election.model.BizStatus;
import id.ezclouds.biz.election.model.member.BizMemberClient;
import id.ezclouds.biz.election.model.member.BizMemberInfo;
import id.ezclouds.common.facade.biz.election.VoterRegistrationService;
import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.core.CoreSeqSceneEnum;
import id.ezclouds.common.model.core.Organization;
import id.ezclouds.common.util.RandomUtil;
import id.ezclouds.common.model.result.BizPageInfo;
import id.ezclouds.common.util.ShardUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.auth.model.CoreAuthMemberClient;
import id.ezclouds.core.auth.service.LegacyCoreAuthService;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.core.shared.model.CorePageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberInnerService.java, v 0.1 2023‐12‐11 11:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberInnerService {

    private static final String DEFAULT_LOGIN_TYPE = "PHONE";

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private LegacyCoreAuthService legacyCoreAuthService;

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private BizConnectInnerService bizConnectInnerService;

    @Autowired
    private VoterRegistrationService voterRegistrationService;

    @Transactional
    public BizMemberInfo processRegisterMember(BizMemberRegisterRequest request) throws Exception {
        AssertUtil.isTrue(false, EzErrorCode.ACTION_NOT_ALLOWED);
        BizMemberInfo bizMemberInfo = new BizMemberInfo();

        String orgId = EzAppContextHolder.getContext().getOrgId();
        //String orgCode = EzAppContextHolder.getContext().getOrgCode();
        //String appId = EzAppContextHolder.getContext().getAppId();

        BizVoter bizVoter = composeBizVoter(request);
        bizVoter.setOrgId(orgId);
        bizVoter.setSourceId("API");

        String voterId = voterRegistrationService.registerVoter(bizVoter);
        BizMember bizMember = new BizMember();
        bizMember.setMemberId(voterId);
        bizMemberInfo.setBizMember(bizMember);

        /*
        String memberId = coreSequenceService
                .generateSequence(new Organization(orgId, orgCode), CoreSeqSceneEnum.CORE_MEMBER_ID);
        String shard = ShardUtil.getShardId(memberId);

        CoreMember coreMember = BizMemberRequestConverter.getCoreMember(request);
        coreMember.setMemberId(memberId);
        coreMember.setOrgId(orgId);
        coreMember.setShard(shard);
        coreMemberService.store(coreMember);

        CoreMemberExtension memberExtension = BizMemberRequestConverter.getCoreMemberExt(request);
        memberExtension.setMemberId(memberId);
        memberExtension.setOrgId(orgId);
        memberExtension.setShard(shard);
        coreMemberService.store(memberExtension);

        BizMemberInfo bizMemberInfo = new BizMemberInfo();

        CoreMember storedMember = coreMemberService.getOptimisticCoreMember(memberId);
        CoreMemberExtension storedMemberExtension = coreMemberService.getPessimisticCoreMemberExtension(memberId);
        BizMember bizMember = BizMemberConverter.convert(storedMember, storedMemberExtension);

        bizMemberInfo.setBizMember(bizMember);

        BizMemberRegisterMode registerMode = request.getRegisterMode();
        if (registerMode == BizMemberRegisterMode.BY_ORG_ADMIN) {
            CoreAuthMemberClient memberClient = new CoreAuthMemberClient();
            memberClient.setOrgId(orgId);
            memberClient.setShard(shard);
            memberClient.setAppId(appId);
            memberClient.setMemberId(memberId);
            memberClient.setLoginType(DEFAULT_LOGIN_TYPE);
            memberClient.setLoginId(request.getPhone());
            memberClient.setStatus(BizStatus.ACTIVE.getCode());
            legacyCoreAuthService.createMemberClient(memberClient);

            CoreAuthMemberClient storedMemberClient = legacyCoreAuthService.getOptimisticMemberClient(DEFAULT_LOGIN_TYPE, memberId);
            BizMemberClient bizMemberClient = BizMemberClientConverter.convert(storedMemberClient);

            bizMemberInfo.setBizMemberClient(bizMemberClient);

            sendPasswordIfNecessary(orgId, bizMemberInfo.getBizMemberClient().getClientId(), bizMember.getPhone());
        }*/

        return bizMemberInfo;
    }

    private void sendPasswordIfNecessary(String orgId, String clientId, String phone) {
        try {
            String newPassword = RandomUtil.generateNumberCode(6);
            legacyCoreAuthService.updateMemberClientPassword(clientId, newPassword);

            AppConfig appConfig = appConfigService.getAppConfig(orgId);
            bizConnectInnerService.memberSendPassword(
                    orgId,
                    phone,
                    newPassword,
                    appConfig.getAppName(),
                    appConfig.getAndroidUpdateUrl()
            );
        } catch (Exception e) {}
    }

    @Transactional
    public BizMemberInfo adminOrgCreateMember(String orgId, String orgCode, String appId, CoreMember coreMember) throws Exception {
        String memberId = coreSequenceService
                .generateSequence(new Organization(orgId, orgCode), CoreSeqSceneEnum.CORE_MEMBER_ID);
        String shard = ShardUtil.getShardId(memberId);

        coreMember.setMemberId(memberId);
        coreMember.setOrgId(orgId);
        coreMember.setShard(shard);
        coreMemberService.store(coreMember);

        CoreAuthMemberClient memberClient = new CoreAuthMemberClient();
        memberClient.setOrgId(orgId);
        memberClient.setShard(shard);
        memberClient.setAppId(appId);
        memberClient.setMemberId(memberId);
        memberClient.setLoginType(DEFAULT_LOGIN_TYPE);
        memberClient.setLoginId(coreMember.getPhone());
        memberClient.setStatus(BizStatus.ACTIVE.getCode());
        legacyCoreAuthService.createMemberClient(memberClient);

        CoreMember storedMember = coreMemberService.getOptimisticCoreMember(memberId);
        CoreAuthMemberClient storedMemberClient = legacyCoreAuthService.getOptimisticMemberClient(DEFAULT_LOGIN_TYPE, memberId);

        BizMember bizMember = BizMemberConverter.convert(storedMember, null);
        BizMemberClient bizMemberClient = BizMemberClientConverter.convert(storedMemberClient);

        BizMemberInfo bizMemberInfo = new BizMemberInfo();
        bizMemberInfo.setBizMember(bizMember);
        bizMemberInfo.setBizMemberClient(bizMemberClient);
        return bizMemberInfo;
    }

    @Transactional
    public BizMemberInfo createCoreMember(String orgId, String orgCode, String appId, BizMember bizMember) {
        String memberId = coreSequenceService
                .generateSequence(new Organization(orgId, orgCode), CoreSeqSceneEnum.CORE_MEMBER_ID);
        String shard = ShardUtil.getShardId(memberId);

        CoreMember coreMember = BizMemberConverter.convert(bizMember);
        coreMember.setMemberId(memberId);
        coreMember.setShard(shard);
        coreMemberService.store(coreMember);

        bizMember.setMemberId(memberId);
        CoreMemberExtension memberExtension = BizMemberConverter
                .convertExtension(bizMember);
        memberExtension.setOrgId(orgId);
        memberExtension.setMemberId(memberId);
        memberExtension.setShard(shard);
        coreMemberService.store(memberExtension);

        CoreAuthMemberClient memberClient = new CoreAuthMemberClient();
        memberClient.setOrgId(orgId);
        memberClient.setShard(shard);
        memberClient.setAppId(appId);
        memberClient.setMemberId(memberId);
        memberClient.setLoginType(DEFAULT_LOGIN_TYPE);
        memberClient.setLoginId(coreMember.getPhone());
        memberClient.setStatus(BizStatus.ACTIVE.getCode());
        legacyCoreAuthService.createMemberClient(memberClient);

        CoreMember storedMember = coreMemberService.getOptimisticCoreMember(memberId);
        CoreAuthMemberClient storedMemberClient = legacyCoreAuthService.getOptimisticMemberClient(DEFAULT_LOGIN_TYPE, memberId);

        BizMember storedBizMember = BizMemberConverter.convert(storedMember, null);
        BizMemberClient bizMemberClient = BizMemberClientConverter.convert(storedMemberClient);

        BizMemberInfo bizMemberInfo = new BizMemberInfo();
        bizMemberInfo.setBizMember(storedBizMember);
        bizMemberInfo.setBizMemberClient(bizMemberClient);
        return bizMemberInfo;
    }

    public BizPageInfo<CoreMember> getMemberPage(String orgId, BizPageRequest request) {
        request.setSortBy("createdTime");
        request.setSort("DESC");
        PageRequest pageRequest = PageRequestUtil.composePageRequest(request);
        String subOrgId = request.getExtendInfo().get("SUB_ORG_ID");

        CorePageInfo<CoreMember> corePageInfo;
        if (StringUtil.isNotBlank(subOrgId)) {
            corePageInfo = coreMemberService.getMemberByOrgAndSubOrg(orgId, subOrgId, pageRequest);
        } else {
            corePageInfo = coreMemberService.getMemberByOrg(orgId, pageRequest);
        }
        return convert(corePageInfo);
    }

    private BizPageInfo<CoreMember> convert(CorePageInfo<CoreMember> corePageInfo) {
        if (corePageInfo == null) {
            return null;
        }
        BizPageInfo<CoreMember> bizPageInfo = new BizPageInfo<>();
        bizPageInfo.setPageNumber(corePageInfo.getPageNumber());
        bizPageInfo.setPageSize(corePageInfo.getPageSize());
        bizPageInfo.setTotalPage(corePageInfo.getTotalPage());
        bizPageInfo.setNumberRecord(corePageInfo.getNumberRecord());
        bizPageInfo.setTotalRecord(corePageInfo.getTotalRecord());
        bizPageInfo.setHasNext(corePageInfo.isHasNext());
        bizPageInfo.setBizData(corePageInfo.getBizData());
        return bizPageInfo;
    }

    @Transactional
    public void memberUpdate(Map<String, String> extendInfo) {
        String memberId = extendInfo.get("MEMBER_ID");
        CoreMember coreMember = coreMemberService.getOptimisticCoreMember(memberId);
        coreMemberService.updateMemberField(coreMember.getMemberId(), extendInfo);
    }

    private BizVoter composeBizVoter(BizMemberRegisterRequest request) {
        BizVoter voter = new BizVoter();
        voter.setSubOrgId(request.getSubOrgId());
        voter.setReferrerId(request.getReferrerId());
        voter.setFamilySize(0);
        voter.setFamilySizeMale(0);
        voter.setFamilySizeFemale(0);
        voter.setName(request.getName());
        voter.setGender(request.getBizGender().getCode());
        voter.setDateOfBirth(request.getDateOfBirth());
        voter.setPhone(request.getPhone());
        voter.setEducation(request.getEducation());
        voter.setOccupation(request.getOccupation());
        voter.setReligion(request.getReligion());
        voter.setEthnic(request.getEthnic());
        voter.setEmail(request.getEmail());
        voter.setIdCardNumber(request.getIdCardNumber());
        voter.setFamilyCardNumber(request.getFamilyCardNumber());
        voter.setProvinceId(request.getProvinceId());
        voter.setProvinceName(request.getProvinceName());
        voter.setRegencyId(request.getRegencyId());
        voter.setRegencyName(request.getRegencyName());
        voter.setDistrictId(request.getDistrictId());
        voter.setDistrictName(request.getDistrictName());
        voter.setVillageId(request.getVillageId());
        voter.setVillageName(request.getVillageName());
        voter.setNeighbourhood(request.getRukunWarga());
        voter.setSubNeighbourhood(request.getRukunTetangga());
        voter.setPollStationId(request.getTpsNumber());
        return voter;
    }
}
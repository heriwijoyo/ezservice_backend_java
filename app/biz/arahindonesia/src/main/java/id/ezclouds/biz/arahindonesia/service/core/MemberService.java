/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.core;

import id.ezclouds.biz.arahindonesia.converter.BizMemberClientConverter;
import id.ezclouds.biz.arahindonesia.converter.BizMemberConverter;
import id.ezclouds.biz.arahindonesia.model.BizStatus;
import id.ezclouds.biz.arahindonesia.model.member.BizMember;
import id.ezclouds.biz.arahindonesia.model.member.BizMemberClient;
import id.ezclouds.biz.arahindonesia.model.member.BizMemberInfo;
import id.ezclouds.biz.arahindonesia.model.member.MemberBase;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberRegisterRequest;
import id.ezclouds.common.dal.repo.member.AppMemberRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.ShardUtil;
import id.ezclouds.core.auth.model.CoreAuthMemberClient;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.model.MemberStatus;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.enums.CoreSequenceScene;
import id.ezclouds.core.shared.service.CoreSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberService.java, v 0.1 2023‐12‐11 11:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class MemberService {

    private static final String DEFAULT_LOGIN_TYPE = "PHONE";

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private CoreAuthService coreAuthService;

    public MemberBase getMemberById(String memberId, String orgId) {
        return appMemberRepository
                .findByMemberIdAndOrgId(memberId, orgId)
                .stream()
                .findFirst()
                .map(BizMemberConverter::convert)
                .orElse(null);
    }

    @Transactional
    public BizMemberInfo processRegisterMember(BizMemberRegisterRequest request) {
        String orgId = EzAppContextHolder.getContext().getOrgId();
        String orgCode = EzAppContextHolder.getContext().getOrgCode();
        String appId = EzAppContextHolder.getContext().getAppId();

        String memberId = coreSequenceService.generateSequence(orgId, orgCode, CoreSequenceScene.CORE_MEMBER_ID.getCode());
        String shard = ShardUtil.getShardId(memberId);

        CoreMember coreMember = new CoreMember();
        coreMember.setMemberId(memberId);
        coreMember.setOrgId(orgId);
        coreMember.setShard(shard);
        coreMember.setSourceId(request.getSourceId());
        coreMember.setReferrerId(request.getReferrerId());
        coreMember.setRoles(request.getRoles());
        coreMember.setName(request.getName());
        coreMember.setNickname(request.getNickname());
        coreMember.setGender(request.getBizGender().getCode());
        coreMember.setDateOfBirth(request.getDateOfBirth());
        coreMember.setPhone(request.getPhone());
        coreMember.setEmail(request.getEmail());
        coreMember.setAvatarUrl(request.getAvatarUrl());
        coreMember.setAddress(request.getAddress());
        coreMember.setCreatedTime(DateUtil.getCurrentFormattedDate());
        coreMember.setModifiedTime(DateUtil.getCurrentFormattedDate());
        coreMember.setMemberStatus(MemberStatus.ACTIVE);
        coreMemberService.store(coreMember);

        CoreMemberExtension memberExtension = new CoreMemberExtension();
        memberExtension.setMemberId(memberId);
        memberExtension.setOrgId(orgId);
        memberExtension.setShard(shard);
        memberExtension.setIdCardNumber(request.getIdCardNumber());
        memberExtension.setFamilyCardNumber(request.getFamilyCardNumber());
        memberExtension.setProvinceId(request.getProvinceId());
        memberExtension.setProvinceName(request.getProvinceName());
        memberExtension.setRegencyId(request.getRegencyId());
        memberExtension.setRegencyName(request.getRegencyName());
        memberExtension.setDistrictId(request.getDistrictId());
        memberExtension.setDistrictName(request.getDistrictName());
        memberExtension.setVillageId(request.getVillageId());
        memberExtension.setVillageName(request.getVillageName());
        memberExtension.setRukunWarga(request.getRukunWarga());
        memberExtension.setRukunTetangga(request.getRukunTetangga());
        memberExtension.setTpsNumber(request.getTpsNumber());
        coreMemberService.store(memberExtension);

        CoreAuthMemberClient memberClient = new CoreAuthMemberClient();
        memberClient.setOrgId(orgId);
        memberClient.setShard(shard);
        memberClient.setAppId(appId);
        memberClient.setMemberId(memberId);
        memberClient.setLoginType(DEFAULT_LOGIN_TYPE);
        memberClient.setLoginId(request.getPhone());
        memberClient.setStatus(BizStatus.ACTIVE.getCode());
        coreAuthService.createMemberClient(memberClient);

        CoreMember storedMember = coreMemberService.getOptimisticCoreMember(memberId);
        CoreMemberExtension storedMemberExtension = coreMemberService.getPessimisticCoreMemberExtension(memberId);
        CoreAuthMemberClient storedMemberClient = coreAuthService.getOptimisticMemberClient(DEFAULT_LOGIN_TYPE, memberId);

        BizMember bizMember = BizMemberConverter.convert(storedMember, storedMemberExtension);
        BizMemberClient bizMemberClient = BizMemberClientConverter.convert(storedMemberClient);

        BizMemberInfo bizMemberInfo = new BizMemberInfo();
        bizMemberInfo.setBizMember(bizMember);
        bizMemberInfo.setBizMemberClient(bizMemberClient);

        return bizMemberInfo;
    }
}
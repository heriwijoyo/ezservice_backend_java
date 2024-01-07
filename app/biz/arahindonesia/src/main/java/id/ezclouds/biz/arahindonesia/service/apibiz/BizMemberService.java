/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.converter.BizMemberConverter;
import id.ezclouds.biz.arahindonesia.model.member.BizMember;
import id.ezclouds.biz.arahindonesia.model.member.BizMemberInfo;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberRegisterRequest;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.biz.arahindonesia.service.template.BizServiceTemplate;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.model.MemberStatus;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.enums.CoreSequenceScene;
import id.ezclouds.core.shared.enums.CoreUniqueScene;
import id.ezclouds.core.shared.service.CoreSequenceService;
import id.ezclouds.core.shared.service.CoreUniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberService.java, v 0.1 2023‐12‐31 12:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberService {

    @Autowired
    private CoreUniqueService coreUniqueService;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private CoreMemberService coreMemberService;

    public BizResult registerMember(BizMemberRegisterRequest request) throws EzErrorException {
        final BizResult bizResult = new BizResult();
        final String orgId = EzAppContextHolder.getContext().getOrgId();
        final String orgCode = EzAppContextHolder.getContext().getOrgCode();

        BizServiceTemplate.execute(bizResult, new BizServiceTemplate.Handler() {

            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM, "request (BizMemberRegisterRequest) is null");
                AssertUtil.notBlank(request.getSourceId(), EzErrorCode.ILLEGAL_PARAM, "request.sourceId is blank");
                AssertUtil.notBlank(request.getRoles(), EzErrorCode.ILLEGAL_PARAM, "request.roles is blank");
                AssertUtil.notBlank(request.getName(), EzErrorCode.ILLEGAL_PARAM, "request.name is blank");
                AssertUtil.notNull(request.getBizGender(), EzErrorCode.ILLEGAL_PARAM, "request.bizGender is null");
                AssertUtil.notBlank(request.getDateOfBirth(), EzErrorCode.ILLEGAL_PARAM, "request.dateOfBirth is blank");
                AssertUtil.notBlank(request.getPhone(), EzErrorCode.ILLEGAL_PARAM, "request.phone is blank");
                AssertUtil.notBlank(request.getAddress(), EzErrorCode.ILLEGAL_PARAM, "request.address is blank");
            }

            @Override
            public void onBizProcess() throws EzErrorException {
                CoreUniqueScene scene = CoreUniqueScene.CORE_MEMBER_ID;

                boolean uniqueCheckPass = coreUniqueService.insertAndCheck(scene, orgId, request.getPhone());
                AssertUtil.isTrue(uniqueCheckPass, EzErrorCode.IDEMPOTENT_ERROR, "Unique check not pass for scene: ", scene.getCode(), ", uniqueValue: ", request.getPhone());

                bizResult.setObject(processRegisterMember(request));
                bizResult.setSuccess(true);
            }
        });

        return bizResult;
    }

    @Transactional
    private BizMemberInfo processRegisterMember(BizMemberRegisterRequest request) {
        final String orgId = EzAppContextHolder.getContext().getOrgId();
        final String orgCode = EzAppContextHolder.getContext().getOrgCode();

        String memberId = coreSequenceService.generateSequence(orgId, orgCode, CoreSequenceScene.CORE_MEMBER_ID.getCode());
        String shard = coreSequenceService.getShardId(memberId);

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

        CoreMember storedMember = coreMemberService.getOptimisticCoreMember(memberId);
        CoreMemberExtension storedMemberExtension = coreMemberService.getPessimisticCoreMemberExtension(memberId);
        BizMember bizMember = BizMemberConverter.convert(storedMember, storedMemberExtension);

        BizMemberInfo bizMemberInfo = new BizMemberInfo();
        bizMemberInfo.setBizMember(bizMember);

        return bizMemberInfo;
    }

    @Transactional
    public BizResult getMemberSequence() {
        final String orgId = EzAppContextHolder.getContext().getOrgId();
        final String orgCode = EzAppContextHolder.getContext().getOrgCode();

        BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() {

            }

            @Override
            public void onBizProcess() {
                String memberId = coreSequenceService.generateSequence(orgId, orgCode, CoreSequenceScene.CORE_MEMBER_ID.getCode());
                bizResult.setSuccess(true);
                bizResult.setObject(memberId);
            }
        });

        return bizResult;
    }
}
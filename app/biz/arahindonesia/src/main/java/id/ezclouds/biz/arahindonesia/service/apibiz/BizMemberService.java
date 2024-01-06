/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.service.request.BizMemberRegisterRequest;
import id.ezclouds.biz.arahindonesia.service.request.BizRequest;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.biz.arahindonesia.service.template.BizServiceTemplate;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.MemberStatus;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.enums.CoreSequenceScenario;
import id.ezclouds.core.shared.service.CoreSequenceService;
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
    private CoreSequenceService coreSequenceService;

    @Autowired
    private CoreMemberService coreMemberService;

    @Transactional
    public BizResult<String> registerMember(BizMemberRegisterRequest request) {
        final BizResult<String> bizResult = new BizResult<>();
        final String orgId = EzAppContextHolder.getContext().getOrgId();
        final String orgCode = EzAppContextHolder.getContext().getOrgCode();

        BizServiceTemplate.execute(bizResult, new BizServiceTemplate.Handler() {

            @Override
            public void onRequestCheck() {
                AssertUtil.notNull(request, EzErrorCode.PARAM_ILLEGAL, "request (BizMemberRegisterRequest) is null");
                AssertUtil.notBlank(request.getSourceId(), EzErrorCode.PARAM_ILLEGAL, "request.sourceId is blank");
                AssertUtil.notBlank(request.getRoles(), EzErrorCode.PARAM_ILLEGAL, "request.roles is blank");
                AssertUtil.notBlank(request.getName(), EzErrorCode.PARAM_ILLEGAL, "request.name is blank");
                AssertUtil.notNull(request.getBizGender(), EzErrorCode.PARAM_ILLEGAL, "request.bizGender is null");
                AssertUtil.notBlank(request.getDateOfBirth(), EzErrorCode.PARAM_ILLEGAL, "request.dateOfBirth is blank");
                AssertUtil.notBlank(request.getPhone(), EzErrorCode.PARAM_ILLEGAL, "request.phone is blank");
            }

            @Override
            public void onBizProcess() {

                String memberId = coreSequenceService.generateSequence(orgId, orgCode, CoreSequenceScenario.CORE_MEMBER_ID.getCode());
                String shard = memberId.substring(orgId.length(), orgId.length() + 2);

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
                coreMember.setDateOfBirth(request.getNickname());
                coreMember.setEmail(request.getEmail());
                coreMember.setAvatarUrl(request.getAvatarUrl());
                coreMember.setCreatedTime(DateUtil.getCurrentFormattedDate());
                coreMember.setModifiedTime(DateUtil.getCurrentFormattedDate());
                coreMember.setMemberStatus(MemberStatus.ACTIVE);
                coreMemberService.store(coreMember);

                bizResult.setObject(memberId);
                bizResult.setSuccess(true);
            }
        });

        return bizResult;
    }
}
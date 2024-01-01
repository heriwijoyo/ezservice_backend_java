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
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.enums.CoreSequenceScenario;
import id.ezclouds.core.shared.service.CoreSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberRegisterService.java, v 0.1 2023‐12‐31 12:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberRegisterService {

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private CoreMemberService coreMemberService;

    @Transactional
    public void registerMember(BizMemberRegisterRequest request) {
        final String orgId = EzAppContextHolder.getContext().getOrgId();
        final String orgCode = EzAppContextHolder.getContext().getOrgCode();

        BizServiceTemplate.execute(request, new BizServiceTemplate.Handler<String>() {

            @Override
            public void onRequestCheck(BizRequest request) {
                //TODO: bizRequest validation
            }

            @Override
            public BizResult<String> onBizProcess(BizRequest request) {
                BizMemberRegisterRequest registerRequest = (BizMemberRegisterRequest) request;

                String memberId = coreSequenceService.generateSequence(orgId, orgCode, CoreSequenceScenario.CORE_MEMBER_ID.getCode());
                String shard = memberId.substring(orgId.length(), orgId.length() + 2);

                CoreMember coreMember = new CoreMember();
                coreMember.setMemberId(memberId);
                coreMember.setOrgId(orgId);
                coreMember.setShard(shard);
                coreMember.setSourceId(registerRequest.getSourceId());
                coreMember.setReferrerId(registerRequest.getReferrerId());
                coreMember.setRoles(registerRequest.getRoles());
                coreMember.setName(registerRequest.getName());
                coreMember.setNickname(registerRequest.getNickname());
                coreMember.setGender(registerRequest.getBizGender().getCode());
                coreMember.setDateOfBirth(registerRequest.getNickname());
                coreMember.setEmail(registerRequest.getEmail());
                coreMember.setAvatarUrl(registerRequest.getAvatarUrl());
                coreMember.setCreatedTime(DateUtil.getCurrentFormattedDate());
                coreMember.setModifiedTime(DateUtil.getCurrentFormattedDate());

                coreMemberService.store(coreMember);
                return null;
            }
        });
    }
}
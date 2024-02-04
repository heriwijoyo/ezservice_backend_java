/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.inner.service;

import id.ezclouds.biz.arahindonesia.converter.BizMemberClientConverter;
import id.ezclouds.biz.arahindonesia.converter.BizMemberConverter;
import id.ezclouds.biz.arahindonesia.model.BizStatus;
import id.ezclouds.biz.arahindonesia.model.member.BizMember;
import id.ezclouds.biz.arahindonesia.model.member.BizMemberClient;
import id.ezclouds.biz.arahindonesia.model.member.BizMemberInfo;
import id.ezclouds.biz.arahindonesia.service.inner.converter.BizMemberRequestConverter;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberRegisterRequest;
import id.ezclouds.common.util.ShardUtil;
import id.ezclouds.core.auth.model.CoreAuthMemberClient;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.enums.CoreSequenceScene;
import id.ezclouds.core.shared.service.CoreSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    private CoreAuthService coreAuthService;

    @Transactional
    public BizMemberInfo processRegisterMember(BizMemberRegisterRequest request) throws Exception {
        String orgId = EzAppContextHolder.getContext().getOrgId();
        String orgCode = EzAppContextHolder.getContext().getOrgCode();
        String appId = EzAppContextHolder.getContext().getAppId();

        String memberId = coreSequenceService.generateSequence(orgId, orgCode, CoreSequenceScene.CORE_MEMBER_ID.getCode());
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
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.member.dataobject.CoreMemberDO;
import id.ezclouds.core.member.dataobject.CoreMemberExtensionDO;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.model.MemberStatus;
import id.ezclouds.core.member.repo.CoreMemberExtensionRepository;
import id.ezclouds.core.member.repo.CoreMemberRepository;
import id.ezclouds.core.member.util.CoreMemberConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberService.java, v 0.1 2023‐12‐31 7:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreMemberService {

    @Autowired
    private CoreMemberRepository coreMemberRepository;

    @Autowired
    private CoreMemberExtensionRepository coreMemberExtensionRepository;

    public void store(CoreMember coreMember) {
        CoreMemberDO coreMemberDO = CoreMemberConverter.convert(coreMember);
        coreMemberRepository.save(coreMemberDO);
    }

    public void store(CoreMemberExtension coreMemberExtension) {
        CoreMemberExtensionDO extensionDO = CoreMemberConverter.convert(coreMemberExtension);
        extensionDO.setMemberExtensionId(HashUtil.createHash(extensionDO.getMemberId()));

        String currentDate = DateUtil.getCurrentFormattedDate();
        extensionDO.setCreatedTime(currentDate);
        extensionDO.setModifiedTime(currentDate);
        coreMemberExtensionRepository.save(extensionDO);
    }

    public CoreMember getOptimisticCoreMember(String memberId) throws EzErrorException {
        CoreMemberDO coreMemberDO = coreMemberRepository.findById(memberId).orElse(null);
        AssertUtil.notNull(coreMemberDO, EzErrorCode.MEMBER_NOT_FOUND, "Member not found");
        return CoreMemberConverter.convert(coreMemberDO);
    }

    public CoreMemberExtension getOptimisticCoreMemberExtension(String memberId) {
        CoreMemberExtensionDO coreMemberExtensionDO = coreMemberExtensionRepository.findByMemberId(memberId);
        AssertUtil.notNull(coreMemberExtensionDO, EzErrorCode.MEMBER_NOT_FOUND, "Member not found");
        return CoreMemberConverter.convert(coreMemberExtensionDO);
    }

    public CoreMemberExtension getPessimisticCoreMemberExtension(String memberId) {
        CoreMemberExtensionDO memberExtensionDO = coreMemberExtensionRepository.findByMemberId(memberId);
        return CoreMemberConverter.convert(memberExtensionDO);
    }

    @Transactional
    public void verifyPhone(String memberId) {
        CoreMemberDO coreMemberDO = coreMemberRepository
                .findById(memberId)
                .orElse(null);

        if (coreMemberDO != null) {
            coreMemberDO.setPhoneVerified(MemberStatus.ACTIVE.getCode());
            coreMemberRepository.saveAndFlush(coreMemberDO);
        }
    }

    @Transactional
    public void updateAvatar(String memberId, String avatar) {
        CoreMemberDO coreMemberDO = coreMemberRepository
                .findById(memberId)
                .orElse(null);

        if (coreMemberDO != null) {
            coreMemberDO.setAvatarUrl(avatar);
            coreMemberRepository.saveAndFlush(coreMemberDO);
        }
    }
}
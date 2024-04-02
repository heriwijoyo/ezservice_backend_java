/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.member.constant.CoreMemberField;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void updateMemberField(String memberId, Map<String, String> fieldMap) {
        if (StringUtil.isBlank(memberId) || fieldMap == null || fieldMap.isEmpty()) {
            return;
        }
        CoreMemberDO coreMemberDO = coreMemberRepository
                .findById(memberId)
                .orElse(null);

        CoreMemberExtensionDO coreMemberExtensionDO = coreMemberExtensionRepository
                .findByMemberId(memberId);

        updateMemberDO(coreMemberDO, coreMemberExtensionDO, fieldMap);

        if (coreMemberDO != null) {
            coreMemberRepository.saveAndFlush(coreMemberDO);
        }
        if (coreMemberExtensionDO != null) {
            coreMemberExtensionRepository.saveAndFlush(coreMemberExtensionDO);
        }
    }

    public List<CoreMember> getMemberByOrgIdAndRoles(String orgId, String roles) {
        return coreMemberRepository.findByOrgIdAndRolesContains(orgId, roles)
                .stream()
                .map(CoreMemberConverter::convert)
                .collect(Collectors.toList());
    }

    private void updateMemberDO(CoreMemberDO coreMemberDO, CoreMemberExtensionDO extensionDO, Map<String, String> fieldMap) {

        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            switch (entry.getKey()) {
                case CoreMemberField.AVATAR:
                    if (coreMemberDO != null && StringUtil.isNotBlank(entry.getValue())) {
                        coreMemberDO.setAvatarUrl(entry.getValue());
                    }
                    break;

                case CoreMemberField.ID_CARD:
                    if (extensionDO != null && StringUtil.isNotBlank(entry.getValue())) {
                        extensionDO.setIdCardDocUrl(entry.getValue());
                    }
                    break;

                case CoreMemberField.FAMILY_CARD:
                    if (extensionDO != null && StringUtil.isNotBlank(entry.getValue())) {
                        extensionDO.setFamilyCardDocUrl(entry.getValue());
                    }
                    break;
            }
        }
    }
}
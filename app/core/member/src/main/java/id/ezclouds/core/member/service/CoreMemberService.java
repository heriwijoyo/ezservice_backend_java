/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.service.LegacyCoreAuthService;
import id.ezclouds.core.member.constant.CoreMemberField;
import id.ezclouds.core.member.dataobject.CoreGroupCountDO;
import id.ezclouds.core.member.dataobject.EzCoreMemberDO;
import id.ezclouds.core.member.dataobject.EzCoreMemberExtensionDO;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.model.MemberStatus;
import id.ezclouds.core.member.repo.EzCoreMemberExtensionRepository;
import id.ezclouds.core.member.repo.EzCoreMemberRepository;
import id.ezclouds.core.member.util.CoreMemberConverter;
import id.ezclouds.core.shared.model.CorePageInfo;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.core.shared.util.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
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
    private EzCoreMemberRepository ezCoreMemberRepository;

    @Autowired
    private EzCoreMemberExtensionRepository ezCoreMemberExtensionRepository;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private LegacyCoreAuthService legacyCoreAuthService;

    public void store(CoreMember coreMember) {
        EzCoreMemberDO ezCoreMemberDO = CoreMemberConverter.convert(coreMember);
        ezCoreMemberRepository.save(ezCoreMemberDO);
    }

    public void store(CoreMemberExtension coreMemberExtension) {
        EzCoreMemberExtensionDO extensionDO = CoreMemberConverter.convert(coreMemberExtension);
        extensionDO.setMemberExtensionId(HashUtil.createHash(extensionDO.getMemberId()));

        String currentDate = DateUtil.getCurrentFormattedDate();
        extensionDO.setCreatedTime(currentDate);
        extensionDO.setModifiedTime(currentDate);
        ezCoreMemberExtensionRepository.save(extensionDO);
    }

    public CoreMember getOptimisticCoreMember(String memberId) throws EzErrorException {
        EzCoreMemberDO ezCoreMemberDO = ezCoreMemberRepository.findById(memberId).orElse(null);
        AssertUtil.notNull(ezCoreMemberDO, EzErrorCode.MEMBER_NOT_FOUND, "Member not found");
        return CoreMemberConverter.convert(ezCoreMemberDO);
    }

    public CoreMemberExtension getOptimisticCoreMemberExtension(String memberId) {
        EzCoreMemberExtensionDO ezCoreMemberExtensionDO = ezCoreMemberExtensionRepository.findByMemberId(memberId);
        AssertUtil.notNull(ezCoreMemberExtensionDO, EzErrorCode.MEMBER_NOT_FOUND, "Member not found");
        return CoreMemberConverter.convert(ezCoreMemberExtensionDO);
    }

    public CoreMemberExtension getPessimisticCoreMemberExtension(String memberId) {
        EzCoreMemberExtensionDO memberExtensionDO = ezCoreMemberExtensionRepository.findByMemberId(memberId);
        return CoreMemberConverter.convert(memberExtensionDO);
    }

    @Transactional
    public void verifyPhone(String memberId) {
        EzCoreMemberDO ezCoreMemberDO = ezCoreMemberRepository
                .findById(memberId)
                .orElse(null);

        if (ezCoreMemberDO != null) {
            ezCoreMemberDO.setPhoneVerified(MemberStatus.ACTIVE.getCode());
            ezCoreMemberRepository.saveAndFlush(ezCoreMemberDO);
        }
    }

    @Transactional
    public void updateMemberField(String memberId, Map<String, String> fieldMap) {
        if (StringUtil.isBlank(memberId) || fieldMap == null || fieldMap.isEmpty()) {
            return;
        }
        EzCoreMemberDO ezCoreMemberDO = ezCoreMemberRepository
                .findById(memberId)
                .orElse(null);

        EzCoreMemberExtensionDO ezCoreMemberExtensionDO = ezCoreMemberExtensionRepository
                .findByMemberId(memberId);

        updateMemberDO(ezCoreMemberDO, ezCoreMemberExtensionDO, fieldMap);

        if (ezCoreMemberDO != null) {
            ezCoreMemberRepository.saveAndFlush(ezCoreMemberDO);
        }
        if (ezCoreMemberExtensionDO != null) {
            ezCoreMemberExtensionRepository.saveAndFlush(ezCoreMemberExtensionDO);
        }
    }

    public List<CoreMember> getMemberByOrgIdAndRoles(String orgId, String roles) {
        return ezCoreMemberRepository.findByOrgIdAndRolesContains(orgId, roles)
                .stream()
                .map(CoreMemberConverter::convert)
                .collect(Collectors.toList());
    }

    public List<CoreMember> getUniqueMember(String orgId, String phone) {
        return ezCoreMemberRepository
                .findByOrgIdAndPhone(orgId, phone)
                .stream()
                .map(CoreMemberConverter::convert)
                .collect(Collectors.toList());
    }

    public List<String> getAllMemberIds(String orgId) {
        return ezCoreMemberRepository
                .findByOrgId(orgId)
                .stream()
                .map(EzCoreMemberDO::getMemberId)
                .collect(Collectors.toList());
    }

    public List<CoreMember> getAllMembers(String orgId) {
        return ezCoreMemberRepository
                .findByOrgId(orgId)
                .stream()
                .map(CoreMemberConverter::convert)
                .collect(Collectors.toList());
    }

    public Map<String, String> getMemberNamesMap(List<String> memberIds) {
        Map<String, String> memberNamesMap = new HashMap<>();
        List<EzCoreMemberDO> coreMembers = ezCoreMemberRepository
                .findByMemberIdIn(memberIds);
        for (EzCoreMemberDO ezCoreMemberDO : coreMembers) {
            memberNamesMap.put(ezCoreMemberDO.getMemberId(), ezCoreMemberDO.getName());
        }
        return memberNamesMap;
    }

    public List<CoreMemberExtension> getAllMemberExtensions(String orgId) {
        return ezCoreMemberExtensionRepository
                .findByOrgId(orgId)
                .stream()
                .map(CoreMemberConverter::convert)
                .collect(Collectors.toList());
    }

    private void updateMemberDO(EzCoreMemberDO ezCoreMemberDO, EzCoreMemberExtensionDO extensionDO, Map<String, String> fieldMap) {

        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            switch (entry.getKey()) {
                case CoreMemberField.AVATAR:
                    if (ezCoreMemberDO != null && StringUtil.isNotBlank(entry.getValue())) {
                        ezCoreMemberDO.setAvatarUrl(entry.getValue());
                    }
                    break;

                case CoreMemberField.ROLES:
                    if (ezCoreMemberDO != null && StringUtil.isNotBlank(entry.getValue())) {
                        ezCoreMemberDO.setRoles(entry.getValue());
                    }
                    break;

                case CoreMemberField.NICKNAME:
                    if (ezCoreMemberDO != null && StringUtil.isNotBlank(entry.getValue())) {
                        ezCoreMemberDO.setNickname(entry.getValue());
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

    public CorePageInfo<CoreMember> getMemberByOrg(String orgId, PageRequest pageRequest) {
        Page<EzCoreMemberDO> pageResult = ezCoreMemberRepository.findByOrgId(orgId, pageRequest);
        return composePageInfo(pageResult);
    }

    public CorePageInfo<CoreMember> getMemberByOrgAndSubOrg(String orgId, String subOrgId, PageRequest pageRequest) {
        Page<EzCoreMemberDO> pageResult = ezCoreMemberRepository.findByOrgIdAndSubOrgId(orgId, subOrgId, pageRequest);
        return composePageInfo(pageResult);
    }

    private CorePageInfo<CoreMember> composePageInfo(Page<EzCoreMemberDO> pageResult) {
        CorePageInfo<CoreMember> bizPageInfo = new CorePageInfo<>();
        bizPageInfo.setPageNumber(pageResult.getPageable().getPageNumber() + 1);
        bizPageInfo.setPageSize(pageResult.getPageable().getPageSize());
        bizPageInfo.setTotalPage(pageResult.getTotalPages());
        bizPageInfo.setNumberRecord(pageResult.getNumberOfElements());
        bizPageInfo.setTotalRecord((int)pageResult.getTotalElements());
        bizPageInfo.setHasNext(pageResult.hasNext());

        List<CoreMember> bizData = new ArrayList<>();
        pageResult.getContent().forEach(modelDO -> {
            bizData.add(CoreMemberConverter.convert(modelDO));
        });
        bizPageInfo.setBizData(bizData);
        return bizPageInfo;
    }

    public Map<String, Long> getGroupCountBySubOrg(String orgId, String startTime, String endTime) {
        Map<String, Long> result = new HashMap<>();

        List<CoreGroupCountDO> coreGroupCount = ezCoreMemberRepository
                .fetchGroupCountBySubOrg(orgId, startTime, endTime);
        for (CoreGroupCountDO groupCountDO : coreGroupCount) {
            if (StringUtil.isNotBlank(groupCountDO.getGroupValue())) {
                String groupValue = groupCountDO.getGroupValue();
                Long groupCount = groupCountDO.getGroupCount();
                result.put(groupValue, groupCount);
            }
        }

        return result;
    }

    public Map<String, Long> getEmptyGroupCountByReferrerId(String orgId, String startTime) {
        Map<String, Long> result = new HashMap<>();

        List<CoreGroupCountDO> coreGroupCount = ezCoreMemberRepository
                .fetchEmptyGroupCountByReferrerId(orgId, startTime);
        for (CoreGroupCountDO groupCountDO : coreGroupCount) {
            if (StringUtil.isNotBlank(groupCountDO.getGroupValue())) {
                String groupValue = groupCountDO.getGroupValue();
                Long groupCount = groupCountDO.getGroupCount();
                result.put(groupValue, groupCount);
            }
        }

        return result;
    }

    public PageResult<CoreMember> getCoreMembers(String orgId, PageRequest pageRequest) {
        Page<EzCoreMemberDO> findResult = ezCoreMemberRepository
                .findByOrgId(orgId, pageRequest);
        return PageResultUtil.convertFindResult(findResult, input -> input
                    .stream()
                .map(CoreMemberConverter::convert)
                .collect(Collectors.toList()));
    }
}
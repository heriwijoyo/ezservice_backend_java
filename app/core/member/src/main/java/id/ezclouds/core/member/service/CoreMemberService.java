/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.ShardUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.constant.CoreAuthConstant;
import id.ezclouds.core.auth.model.CoreAuthMemberClient;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.member.constant.CoreMemberField;
import id.ezclouds.core.member.dataobject.CoreGroupCountDO;
import id.ezclouds.core.member.dataobject.CoreMemberDO;
import id.ezclouds.core.member.dataobject.CoreMemberExtensionDO;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.model.MemberStatus;
import id.ezclouds.core.member.repo.CoreMemberExtensionRepository;
import id.ezclouds.core.member.repo.CoreMemberRepository;
import id.ezclouds.core.member.util.CoreMemberConverter;
import id.ezclouds.core.shared.converter.CoreModelConverter;
import id.ezclouds.core.shared.enums.CoreSequenceScene;
import id.ezclouds.core.shared.model.CorePageInfo;
import id.ezclouds.core.shared.service.CoreSequenceService;
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
    private CoreMemberRepository coreMemberRepository;

    @Autowired
    private CoreMemberExtensionRepository coreMemberExtensionRepository;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private CoreAuthService coreAuthService;

    public void createOrgAdminMember(String orgId, String orgCode, String appId, CoreMember coreMember) {
        String memberId = coreSequenceService
                .generateSequence(orgId, orgCode, CoreSequenceScene.CORE_MEMBER_ID.getCode());
        String shard = ShardUtil.getShardId(memberId);

        coreMember.setMemberId(memberId);
        coreMember.setShard(shard);
        store(coreMember);

        CoreAuthMemberClient memberClient = new CoreAuthMemberClient();
        memberClient.setOrgId(orgId);
        memberClient.setShard(shard);
        memberClient.setAppId(appId);
        memberClient.setMemberId(memberId);
        memberClient.setLoginType(CoreAuthConstant.DEFAULT_LOGIN_TYPE);
        memberClient.setLoginId(coreMember.getPhone());
        memberClient.setStatus(CoreAuthConstant.Status.ACTIVE);
        coreAuthService.createMemberClient(memberClient);
    }

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

    public List<String> getAllMemberIds(String orgId) {
        return coreMemberRepository
                .findByOrgId(orgId)
                .stream()
                .map(CoreMemberDO::getMemberId)
                .collect(Collectors.toList());
    }

    public List<CoreMember> getAllMembers(String orgId) {
        return coreMemberRepository
                .findByOrgId(orgId)
                .stream()
                .map(CoreMemberConverter::convert)
                .collect(Collectors.toList());
    }

    public Map<String, String> getMemberNamesMap(List<String> memberIds) {
        Map<String, String> memberNamesMap = new HashMap<>();
        List<CoreMemberDO> coreMembers = coreMemberRepository
                .findByMemberIdIn(memberIds);
        for (CoreMemberDO coreMemberDO : coreMembers) {
            memberNamesMap.put(coreMemberDO.getMemberId(), coreMemberDO.getName());
        }
        return memberNamesMap;
    }

    public List<CoreMemberExtension> getAllMemberExtensions(String orgId) {
        return coreMemberExtensionRepository
                .findByOrgId(orgId)
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

                case CoreMemberField.ROLES:
                    if (coreMemberDO != null && StringUtil.isNotBlank(entry.getValue())) {
                        coreMemberDO.setRoles(entry.getValue());
                    }
                    break;

                case CoreMemberField.NICKNAME:
                    if (coreMemberDO != null && StringUtil.isNotBlank(entry.getValue())) {
                        coreMemberDO.setNickname(entry.getValue());
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
        Page<CoreMemberDO> pageResult = coreMemberRepository.findByOrgId(orgId, pageRequest);
        return composePageInfo(pageResult);
    }

    public CorePageInfo<CoreMember> getMemberByOrgAndSubOrg(String orgId, String subOrgId, PageRequest pageRequest) {
        Page<CoreMemberDO> pageResult = coreMemberRepository.findByOrgIdAndSubOrgId(orgId, subOrgId, pageRequest);
        return composePageInfo(pageResult);
    }

    private CorePageInfo<CoreMember> composePageInfo(Page<CoreMemberDO> pageResult) {
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

        List<CoreGroupCountDO> coreGroupCount = coreMemberRepository
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

        List<CoreGroupCountDO> coreGroupCount = coreMemberRepository
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
}
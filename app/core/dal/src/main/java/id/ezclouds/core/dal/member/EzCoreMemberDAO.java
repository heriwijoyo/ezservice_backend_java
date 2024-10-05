/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member;

import id.ezclouds.common.facade.dal.member.CoreMemberDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.common.model.core.member.CoreMemberExtension;
import id.ezclouds.common.model.pagination.SortBy;
import id.ezclouds.core.dal.member.converter.CoreMemberConverter;
import id.ezclouds.core.dal.member.converter.CoreMemberExtensionConverter;
import id.ezclouds.core.dal.member.repo.CoreMemberExtensionRepository;
import id.ezclouds.core.dal.member.repo.CoreMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreMemberDAO.java, v 0.1 2024‐10‐05 1:16 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class EzCoreMemberDAO implements CoreMemberDAO {

    @Autowired
    private CoreMemberRepository coreMemberRepository;

    @Autowired
    private CoreMemberExtensionRepository coreMemberExtensionRepository;

    @Override
    @EzDAOLogger
    public List<CoreMember> getMigrationMembers(String orgId, SortBy sortBy, int limit) {
        CoreMemberConverter memberConverter = new CoreMemberConverter();
        Pageable pageable = PageRequest.of(0, limit, convertSortBy(sortBy));
        List<CoreMember> coreMembers = coreMemberRepository
                .findByOrgIdAndMigrationIdIsNull(orgId, pageable)
                .stream()
                .map(memberConverter::convertQuery)
                .collect(Collectors.toList());

        List<String> memberIds = coreMembers
                .stream()
                .map(CoreMember::getMemberId)
                .collect(Collectors.toList());

        CoreMemberExtensionConverter memberExtensionConverter = new CoreMemberExtensionConverter();
        List<CoreMemberExtension> memberExtensions = coreMemberExtensionRepository
                .findByMemberIdIn(memberIds)
                .stream()
                .map(memberExtensionConverter::convertQuery)
                .collect(Collectors.toList());

        blendMemberExt(coreMembers, memberExtensions);

        return coreMembers;
    }

    @Override
    public CoreMember getAndLock(String memberId) {
        return null;
    }

    @Override
    public void store(CoreMember coreMember) {

    }

    private void blendMemberExt(List<CoreMember> coreMembers, List<CoreMemberExtension> memberExtensions) {
        coreMembers.forEach(coreMember -> {
            for (CoreMemberExtension extension : memberExtensions) {
                if (coreMember.getMemberId().equals(extension.getMemberId())) {
                    coreMember.setMemberExtension(extension);
                }
            }
        });
    }

    private Sort convertSortBy(SortBy sortBy) {
        switch (sortBy) {
            case NEWEST:
                return Sort.by(Sort.Direction.DESC, "createdTime");
            case OLDEST:
                return Sort.by(Sort.Direction.ASC, "createdTime");
        }
        return null;
    }
}
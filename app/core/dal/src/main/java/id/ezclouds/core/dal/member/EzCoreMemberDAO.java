/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member;

import id.ezclouds.common.facade.dal.member.CoreMemberDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.common.model.pagination.SortBy;
import id.ezclouds.core.dal.member.repo.CoreMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreMemberDAO.java, v 0.1 2024‐10‐05 1:16 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class EzCoreMemberDAO implements CoreMemberDAO {

    @Autowired
    private CoreMemberRepository coreMemberRepository;

    @Override
    @EzDAOLogger
    public CoreMember getMembers(SortBy sortBy, int limit) {
        return null;
    }

    @Override
    public CoreMember getAndLock(String memberId) {
        return null;
    }

    @Override
    public void store(CoreMember coreMember) {

    }
}
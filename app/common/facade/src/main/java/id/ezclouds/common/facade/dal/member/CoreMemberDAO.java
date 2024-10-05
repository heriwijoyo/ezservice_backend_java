/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.member;

import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.common.model.pagination.SortBy;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberDAO.java, v 0.1 2024‐10‐05 12:32 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreMemberDAO {

    CoreMember getMembers(SortBy sortBy, int limit);

    CoreMember getAndLock(String memberId);

    void store(CoreMember coreMember);
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.member;

import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.request.BizPageRequest;
import id.ezclouds.common.model.result.PageResult;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberBackOfficeDAO.java, v 0.1 2024‐08‐11 12:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizMemberBackOfficeDAO {

    PageResult<MemberBackOffice> getMemberPage(BizPageRequest bizPageRequest);

    MemberBackOffice getMemberDetail(String memberId);

    List<MemberBackOffice> getByReferrerId(String referrerId);

    void store(MemberBackOffice memberBackOffice);

    void updateRoles(String memberId, String roles);

}
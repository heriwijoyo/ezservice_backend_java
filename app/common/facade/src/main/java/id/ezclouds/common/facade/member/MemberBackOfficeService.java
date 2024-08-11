/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.member;

import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.request.BizPageRequest;
import id.ezclouds.common.model.result.PageResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberBackOfficeService.java, v 0.1 2024‐08‐10 11:41 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface MemberBackOfficeService {
    PageResult<MemberBackOffice> getMemberPage(BizPageRequest bizPageRequest);
    MemberBackOffice getMemberDetail(String memberId);
}
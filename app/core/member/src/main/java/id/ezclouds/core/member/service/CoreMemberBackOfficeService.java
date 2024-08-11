/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.common.facade.dal.member.BizMemberBackOfficeDAO;
import id.ezclouds.common.facade.member.MemberBackOfficeService;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.request.BizPageRequest;
import id.ezclouds.common.model.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberBackOfficeService.java, v 0.1 2024‐08‐11 12:08 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreMemberBackOfficeService implements MemberBackOfficeService {

    @Autowired
    private BizMemberBackOfficeDAO bizMemberBackOfficeDAO;

    @Override
    public PageResult<MemberBackOffice> getMemberPage(BizPageRequest request) {
        return bizMemberBackOfficeDAO.getMemberPage(request);
    }
}
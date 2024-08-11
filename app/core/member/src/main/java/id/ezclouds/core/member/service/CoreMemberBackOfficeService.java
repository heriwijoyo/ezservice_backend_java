/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.common.facade.dal.member.BizMemberBackOfficeDAO;
import id.ezclouds.common.facade.dal.organization.BizSubOrganizationDAO;
import id.ezclouds.common.facade.member.MemberBackOfficeService;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.organization.SubOrganization;
import id.ezclouds.common.model.request.BizPageRequest;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.model.util.PageResultUtil;
import id.ezclouds.core.member.converter.MemberBackOfficeAdjuster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberBackOfficeService.java, v 0.1 2024‐08‐11 12:08 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreMemberBackOfficeService implements MemberBackOfficeService {

    @Autowired
    private BizSubOrganizationDAO bizSubOrganizationDAO;

    @Autowired
    private BizMemberBackOfficeDAO bizMemberBackOfficeDAO;

    @Override
    public PageResult<MemberBackOffice> getMemberPage(BizPageRequest request) {
        List<SubOrganization> subOrganizations = bizSubOrganizationDAO
                .getByOrgId(request.getOrgId());

        PageResult<MemberBackOffice> result = bizMemberBackOfficeDAO.getMemberPage(request);
        PageResultUtil.adjustPageResult(result, new MemberBackOfficeAdjuster(subOrganizations));

        return result;
    }
}
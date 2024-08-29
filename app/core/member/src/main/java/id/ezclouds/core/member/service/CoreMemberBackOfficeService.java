/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.common.facade.dal.member.BizMemberBackOfficeDAO;
import id.ezclouds.common.facade.dal.organization.SubOrganizationDAO;
import id.ezclouds.common.facade.member.MemberBackOfficeService;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.organization.SubOrganization;
import id.ezclouds.common.model.request.BizPageRequest;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.model.util.PageResultUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.member.converter.MemberBackOfficeAdjuster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberBackOfficeService.java, v 0.1 2024‐08‐11 12:08 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreMemberBackOfficeService implements MemberBackOfficeService {

    @Autowired
    private SubOrganizationDAO subOrganizationDAO;

    @Autowired
    private BizMemberBackOfficeDAO bizMemberBackOfficeDAO;

    @Override
    public PageResult<MemberBackOffice> getMemberPage(BizPageRequest request) {
        List<SubOrganization> subOrganizations = subOrganizationDAO
                .getByOrgId(request.getOrgId());

        PageResult<MemberBackOffice> result = bizMemberBackOfficeDAO.getMemberPage(request);
        PageResultUtil.adjustPageResult(result, new MemberBackOfficeAdjuster(subOrganizations));

        return result;
    }

    @Override
    public MemberBackOffice getMemberDetail(String memberId) {
        MemberBackOffice memberBackOffice = bizMemberBackOfficeDAO.getMemberDetail(memberId);
        if (memberBackOffice == null) {
            return null;
        }

        if (StringUtil.isNotBlank(memberBackOffice.getSubOrgId())) {
            SubOrganization subOrganization = subOrganizationDAO
                    .getById(memberBackOffice.getSubOrgId());
            new MemberBackOfficeAdjuster(subOrganization).adjust(memberBackOffice);
        }

        return memberBackOffice;
    }

    @Override
    @Transactional
    public void memberUpdateSubOrganization(MemberBackOffice memberBackOffice, String subOrganizationId) {
        boolean needUpdateRefId = !StringUtil.equals(memberBackOffice.getSubOrgId(), subOrganizationId);

        memberBackOffice.setSubOrgId(subOrganizationId);
        bizMemberBackOfficeDAO.store(memberBackOffice);

        if (needUpdateRefId) {
            List<MemberBackOffice> refMembers = bizMemberBackOfficeDAO
                    .getByReferrerId(memberBackOffice.getMemberId());

            for (MemberBackOffice refMember : refMembers) {
                refMember.setSubOrgId(subOrganizationId);

                bizMemberBackOfficeDAO.store(refMember);
            }
        }
    }
}
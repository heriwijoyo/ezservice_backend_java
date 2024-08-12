/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member;

import id.ezclouds.common.facade.dal.member.BizMemberImportDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.member.BizMemberImport;
import id.ezclouds.core.dal.member.converter.MemberImportStoreConverter;
import id.ezclouds.core.dal.member.converter.MemberImportStoreFailedConverter;
import id.ezclouds.core.dal.member.repo.CoreMemberImportFailedRepository;
import id.ezclouds.core.dal.member.repo.CoreMemberImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberImportDAO.java, v 0.1 2024‐08‐11 6:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreMemberImportDAO implements BizMemberImportDAO {

    @Autowired
    private CoreMemberImportRepository coreMemberImportRepository;

    @Autowired
    private CoreMemberImportFailedRepository coreMemberImportFailedRepository;

    @EzDAOLogger
    @Override
    public long deleteAllImport(String orgId, String subOrgId) {
        return coreMemberImportRepository
                .deleteByOrgIdAndSubOrgId(orgId, subOrgId);
    }

    @Override
    public long deleteAllImportFailed(String orgId, String subOrgId) {
        return coreMemberImportFailedRepository
                .deleteByOrgIdAndSubOrgId(orgId, subOrgId);
    }

    @EzDAOLogger
    @Override
    public void storeMember(BizMemberImport memberImport) {
        MemberImportStoreConverter converter = new MemberImportStoreConverter();
        coreMemberImportRepository
                .saveAndFlush(converter.convert(memberImport));
    }

    @EzDAOLogger
    @Override
    public void storeMemberFailed(BizMemberImport memberImport) {
        MemberImportStoreFailedConverter converter = new MemberImportStoreFailedConverter();
        coreMemberImportFailedRepository
                .saveAndFlush(converter.convert(memberImport));
    }
}
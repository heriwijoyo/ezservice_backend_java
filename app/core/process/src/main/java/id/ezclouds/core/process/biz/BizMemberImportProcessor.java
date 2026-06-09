/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.facade.dal.member.BizMemberImportDAO;
import id.ezclouds.common.model.member.BizMemberImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberImportProcessor.java, v 0.1 2024‐08‐11 5:48 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberImportProcessor {

    @Autowired
    private BizMemberImportDAO bizMemberImportDAO;

    @Transactional
    public long deleteAllImport(String orgId, String subOrgId) {
        return bizMemberImportDAO.deleteAllImport(orgId, subOrgId);
    }

    @Transactional
    public long deleteAllImportFailed(String orgId, String subOrgId) {
        return bizMemberImportDAO.deleteAllImportFailed(orgId, subOrgId);
    }

    @Transactional
    public void storeMember(BizMemberImport memberImport) {
        bizMemberImportDAO.storeMember(memberImport);
    }

    @Transactional
    public void storeMemberFailed(BizMemberImport memberImport) {
        bizMemberImportDAO.storeMemberFailed(memberImport);
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.member;

import id.ezclouds.common.model.member.BizMemberImport;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberImportDAO.java, v 0.1 2024‐08‐11 6:06 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizMemberImportDAO {
    long deleteAllImport(String orgId, String subOrgId);
    long deleteAllImportFailed(String orgId, String subOrgId);
    void storeMember(BizMemberImport memberImport);
    void storeMemberFailed(BizMemberImport memberImport);
}
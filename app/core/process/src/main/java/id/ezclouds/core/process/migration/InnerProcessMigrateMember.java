/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.migration;

import id.ezclouds.common.facade.dal.member.CoreMemberDAO;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.common.model.pagination.SortBy;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: InnerProcessMigrateMember.java, v 0.1 2024‐10‐05 5:02 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class InnerProcessMigrateMember {

    @Autowired
    private CoreMemberDAO coreMemberDAO;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public List<CoreMember> getMigrationMembers(String orgId) {
        return coreMemberDAO.getMigrationMembers(orgId, SortBy.OLDEST, 100);
    }

    public void migrateMember(CoreMember coreMember) {
        String memberRoles = coreMember.getRoles();
        if (StringUtil.isBlank(memberRoles) && Arrays.asList(memberRoles.split(",")).contains(AuthRole.ADMIN_ORG.getCode())) {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    migrateOrgAdmin(coreMember.getMemberId());
                }
            });
        }
    }

    private void migrateOrgAdmin(String memberId) {
        CoreMember coreMember = coreMemberDAO.getAndLock(memberId);
        coreMember.setMigrationId("ADMIN_NOT_MIGRATED");
        coreMemberDAO.store(coreMember);
    }
}
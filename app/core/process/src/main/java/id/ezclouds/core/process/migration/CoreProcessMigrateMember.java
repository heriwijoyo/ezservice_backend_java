/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.migration;

import id.ezclouds.common.facade.dal.member.CoreMemberDAO;
import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.common.model.pagination.SortBy;
import id.ezclouds.core.process.biz.BizAsyncProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreProcessMigrateMember.java, v 0.1 2024‐10‐05 3:36 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreProcessMigrateMember extends BizAsyncProcessor {

    @Autowired
    private CoreMemberDAO coreMemberDAO;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.ORG_INIT_MIGRATE_MEMBER;
    }

    @Override
    protected int maxProcessTime() {
        return 60 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String orgId = (String) request;
        logData.add("IRG_ID="+ orgId);
        int processedMember = 0;

        List<CoreMember> coreMembers = coreMemberDAO.getMigrationMembers(orgId, SortBy.OLDEST, 1000);
        while (coreMembers.size() > 0) {
            for (CoreMember coreMember : coreMembers) {

            }

            //coreMembers = coreMemberDAO.getMigrationMembers(orgId, SortBy.OLDEST, 1000);
        }


        return true;
    }
}
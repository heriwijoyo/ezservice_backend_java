/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.converter.BizMemberConverter;
import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.async.parser.BizMemberUnionConverter;
import id.ezclouds.biz.ezservice.service.core.dataobject.*;
import id.ezclouds.biz.ezservice.service.core.repo.*;
import id.ezclouds.biz.ezservice.service.processor.inner.BizMemberUnionInnerProcessor;
import id.ezclouds.biz.ezservice.service.processor.shared.ProcessorConstant;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.service.CoreMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSyncMemberUnionProcessor.java, v 0.1 2024‐07‐15 1:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope(value = "prototype")
@Async
public class BizSyncMemberUnionProcessor extends BizAsyncProcessor {

    @Autowired
    private AppSubOrganizationRepository appSubOrganizationRepository;

    @Autowired
    private BizMemberUnionInnerProcessor bizMemberUnionInnerProcessor;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private BizMemberImportRepository bizMemberImportRepository;

    @Autowired
    private BizMemberUnionDuplicateRepository bizMemberUnionDuplicateRepository;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.SYNC_MEMBER_UNION;
    }

    @Override
    protected int maxProcessTime() {
        return 10 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        final String orgId = (String) request;
        // prepare necessary data
        List<BizSubOrganizationDO> subOrgs = appSubOrganizationRepository.findByOrgId(orgId);

        // 1. clear all member union by orgId
        long deletedUnion = bizMemberUnionInnerProcessor.deleteAllMemberUnion(orgId);
        long deleteDuplicate = bizMemberUnionInnerProcessor.deleteAllMemberUnionDuplicate(orgId);
        logData.add("DEL_UNION=" + deletedUnion);
        logData.add("DEL_DUPLICATE=" + deleteDuplicate);

        // 2. query all admin member id registered by app
        List<String> membersId = coreMemberService.getAllMemberIds(orgId);
        logData.add("MEMBER_APP_COUNT=" + membersId.size());

        // 3. query admin member detail and try to sync
        int syncSuccessCount = 0;
        int syncFailCount = 0;
        if (membersId.size() > 0) {
            for (String memberId : membersId) {
                BizMemberUnionDO unionDO = null;
                try {
                    CoreMember coreMember = coreMemberService.getOptimisticCoreMember(memberId);
                    String subOrgId = coreMember.getSubOrgId();
                    if (StringUtil.isNotBlank(subOrgId) && ProcessorConstant.getBlacklistSubOrgs(orgId).contains(subOrgId)) {
                        continue;
                    }

                    CoreMemberExtension memberExtension = coreMemberService.getPessimisticCoreMemberExtension(memberId);
                    BizMember bizMember = BizMemberConverter.convert(coreMember, memberExtension);

                    unionDO = BizMemberUnionConverter.convert(bizMember);
                    if (unionDO != null) {
                        unionDO.setOrgId(orgId);
                        unionDO.setSubOrgName(getSubOrgName(subOrgs, unionDO.getSubOrgId()));
                        bizMemberUnionInnerProcessor.storeMemberUnion(unionDO);
                        syncSuccessCount++;
                    }
                } catch (Exception ignored) {
                    tryStoreMemberUnionDuplicate(unionDO);
                    syncFailCount++;
                }
            }
        }
        logData.add("MEMBER_APP_SYNC_SUCCESS=" + syncSuccessCount);
        logData.add("MEMBER_APP_SYNC_FAIL=" + syncFailCount);

        // 4. query from member import and try to sync
        int importSyncSuccessCount = 0;
        int importSyncFailCount = 0;
        List<BizMemberImportDO> memberImports = bizMemberImportRepository.findByOrgId(orgId);
        for (BizMemberImportDO memberImportDO : memberImports) {
            BizMemberUnionDO unionDO = BizMemberUnionConverter.convertMemberImport(memberImportDO);
            unionDO.setOrgId(orgId);
            unionDO.setSubOrgName(getSubOrgName(subOrgs, unionDO.getSubOrgId()));
            unionDO.setRole("L");
            try {
                bizMemberUnionInnerProcessor.storeMemberUnion(unionDO);
                importSyncSuccessCount++;
            } catch (Exception ignored) {
                tryStoreMemberUnionDuplicate(unionDO);
                importSyncFailCount++;
            }
        }
        logData.add("MEMBER_IMPORT_SYNC_TOTAL=" + memberImports.size());
        logData.add("MEMBER_IMPORT_SYNC_SUCCESS=" + importSyncSuccessCount);
        logData.add("MEMBER_IMPORT_SYNC_FAIL=" + importSyncFailCount);

        return true;
    }

    private String getSubOrgName(List<BizSubOrganizationDO> subOrgs, String subOrgId) {
        for (BizSubOrganizationDO subOrganizationDO : subOrgs) {
            if (subOrganizationDO.getSubOrgId().equals(subOrgId)) {
                return subOrganizationDO.getName();
            }
        }
        return "UNDEFINED";
    }

    private void tryStoreMemberUnionDuplicate(BizMemberUnionDO memberUnionDO) {
        try {
            bizMemberUnionInnerProcessor.storeMemberUnionDuplicate(memberUnionDO);
        } catch (Exception ignored) {}
    }
}
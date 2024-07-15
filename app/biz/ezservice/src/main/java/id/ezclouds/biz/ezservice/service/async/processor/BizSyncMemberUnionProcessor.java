/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.processor;

import id.ezclouds.biz.ezservice.converter.BizMemberConverter;
import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.service.async.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.async.parser.BizMemberUnionConverter;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberUnionDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportByAreaDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberImportRepository;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberUnionDuplicateRepository;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberUnionRepository;
import id.ezclouds.biz.ezservice.service.core.repo.BizReportByAreaRepository;
import id.ezclouds.biz.ezservice.service.template.BizProcessTemplate;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.repo.CoreAppDistrictRepository;
import id.ezclouds.core.shared.repo.CoreAppVillageRepository;
import id.ezclouds.core.shared.repo.dataobject.EzCoreAppDistrictDO;
import id.ezclouds.core.shared.repo.dataobject.EzCoreAppVillageDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSyncMemberUnionProcessor.java, v 0.1 2024‐07‐15 1:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Transactional
public class BizSyncMemberUnionProcessor {

    @Autowired
    private AppSubOrganizationRepository appSubOrganizationRepository;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private BizMemberImportRepository bizMemberImportRepository;

    @Autowired
    private BizMemberUnionRepository bizMemberUnionRepository;

    @Autowired
    private BizMemberUnionDuplicateRepository bizMemberUnionDuplicateRepository;

    @Autowired
    private CoreAppDistrictRepository coreAppDistrictRepository;

    @Autowired
    private CoreAppVillageRepository coreAppVillageRepository;

    @Autowired
    private BizReportByAreaRepository bizReportByAreaRepository;

    public void process(String orgId) {

        final List<String> logData = new ArrayList<>();
        logData.add(orgId);

        BizProcessTemplate.execute(BizProcessEvent.SYNC_MEMBER_UNION, new BizProcessTemplate.Handler() {
            @Override
            public boolean onProcess() {
                // prepare necessary data
                List<BizSubOrganizationDO> subOrgs = appSubOrganizationRepository.findByOrgId(orgId);

                // 1. clear all member union by orgId
                long deletedUnion = bizMemberUnionRepository.deleteByOrgId(orgId);
                long deleteDuplicate = bizMemberUnionDuplicateRepository.deleteByOrgId(orgId);
                logData.add("DEL_UNION=" + deletedUnion);
                logData.add("DEL_DUPLICATE=" + deleteDuplicate);

                // 2. query all biz member id registered by app
                List<String> membersId = coreMemberService.getAllMemberIds(orgId);
                logData.add("MEMBER_APP_COUNT=" + membersId.size());

                // 3. query biz member detail and try to sync
                int syncSuccessCount = 0;
                int syncFailCount = 0;
                if (membersId.size() > 0) {
                    for (String memberId : membersId) {
                        BizMemberUnionDO unionDO = null;
                        try {
                            CoreMember coreMember = coreMemberService.getOptimisticCoreMember(memberId);
                            CoreMemberExtension memberExtension = coreMemberService.getPessimisticCoreMemberExtension(memberId);
                            BizMember bizMember = BizMemberConverter.convert(coreMember, memberExtension);

                            unionDO = BizMemberUnionConverter.convert(bizMember);
                            unionDO.setOrgId(orgId);
                            unionDO.setSubOrgName(getSubOrgName(subOrgs, unionDO.getSubOrgId()));
                            bizMemberUnionRepository.saveAndFlush(unionDO);
                            syncSuccessCount++;
                        } catch (Exception e) {
                            e.printStackTrace();
                            tryStoreDuplicate(unionDO);
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
                    try {
                        bizMemberUnionRepository.saveAndFlush(unionDO);
                        importSyncSuccessCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        tryStoreDuplicate(unionDO);
                        importSyncFailCount++;
                    }
                }
                logData.add("MEMBER_IMPORT_SYNC_TOTAL=" + memberImports.size());
                logData.add("MEMBER_IMPORT_SYNC_SUCCESS=" + importSyncSuccessCount);
                logData.add("MEMBER_IMPORT_SYNC_FAIL=" + importSyncFailCount);

                // 5. start generate the report
                // 5.1 fetch all district
                //Map<String, List<EzCoreAppVillageDO>> districtVillageMap = new HashMap<>();
                List<EzCoreAppDistrictDO> districts = coreAppDistrictRepository.findByRegencyId("1802");
                for (EzCoreAppDistrictDO districtDO : districts) {
                    //List<EzCoreAppVillageDO> villages = coreAppVillageRepository.findByDistrictId(districtDO.getId());
                    //districtVillageMap.put(districtDO.getId(), villages);


                    if ("KOTA AGUNG".equals(districtDO.getName())) {
                        BizReportByAreaDO reportByArea = new BizReportByAreaDO();
                        reportByArea.setId(HashUtil.createHash(districtDO.getName()));
                        reportByArea.setSource("APP");
                        reportByArea.setOrgId(orgId);
                        reportByArea.setDistrictName(districtDO.getName());
                        reportByArea.setVillageName("ALL");
                        reportByArea.setTpsNo("ALL");

                        List<BizCustomQueryGroupDO> groupRoles = bizMemberUnionRepository
                                .districtNameFetchRoleGroup(orgId, "APP", districtDO.getName());

                        long strongVoter = 0;
                        long lazyVoter = 0;
                        long otherVoter = 0;
                        long totalVoter = 0;
                        for (BizCustomQueryGroupDO groupRole : groupRoles) {
                            if ("S".equals(groupRole.getGroupName())) {
                                strongVoter = groupRole.getCount1Value();
                            } else if ("L".equals(groupRole.getGroupName())) {
                                strongVoter = groupRole.getCount1Value();
                            } else {
                                otherVoter = groupRole.getCount1Value();
                            }
                        }
                        totalVoter = strongVoter + lazyVoter + otherVoter;
                        reportByArea.setVoterStrong(strongVoter);
                        reportByArea.setVoterLazy(lazyVoter);
                        reportByArea.setVoterOther(otherVoter);
                        reportByArea.setVoterTotal(totalVoter);

                        bizReportByAreaRepository.saveAndFlush(reportByArea);
                    }
                }

                return true;
            }

            @Override
            public List<String> getLogData() {
                return logData;
            }
        });
    }

    private String getSubOrgName(List<BizSubOrganizationDO> subOrgs, String subOrgId) {
        for (BizSubOrganizationDO subOrganizationDO : subOrgs) {
            if (subOrganizationDO.getSubOrgId().equals(subOrgId)) {
                return subOrganizationDO.getName();
            }
        }
        return null;
    }

    private void tryStoreDuplicate(BizMemberUnionDO memberUnionDO) {
        if (memberUnionDO == null) {
            return;
        }
        try {
            bizMemberUnionDuplicateRepository.saveAndFlush(memberUnionDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
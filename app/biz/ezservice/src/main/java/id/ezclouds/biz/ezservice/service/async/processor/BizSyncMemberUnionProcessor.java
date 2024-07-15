/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.converter.BizMemberConverter;
import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.service.async.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.async.parser.BizMemberUnionConverter;
import id.ezclouds.biz.ezservice.service.core.dataobject.*;
import id.ezclouds.biz.ezservice.service.core.repo.*;
import id.ezclouds.biz.ezservice.service.template.BizProcessTemplate;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
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

    @Autowired
    private BizReportBySubOrgRepository bizReportBySubOrgRepository;

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
                            if (unionDO != null) {
                                unionDO.setOrgId(orgId);
                                unionDO.setSubOrgName(getSubOrgName(subOrgs, unionDO.getSubOrgId()));
                                bizMemberUnionRepository.saveAndFlush(unionDO);
                                syncSuccessCount++;
                            }
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
                    unionDO.setRole("L");
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
                // 5.1 delete existing report by area
                long deletedReportArea = bizReportByAreaRepository.deleteByOrgId(orgId);
                logData.add("DEL_REPORT_AREA="+ deletedReportArea);

                String currentTime = DateUtil.getCurrentFormattedDate();

                List<EzCoreAppDistrictDO> districts = coreAppDistrictRepository.findByRegencyId("1802");
                for (EzCoreAppDistrictDO districtDO : districts) {

                    if ("KOTA AGUNG".equals(districtDO.getName())) {
                        generateAreaReport(currentTime, orgId, "APP", districtDO.getName(), "ALL");
                        generateAreaReport(currentTime, orgId, "IMPORT", districtDO.getName(), "ALL");

                        List<EzCoreAppVillageDO> villages = coreAppVillageRepository.findByDistrictId(districtDO.getId());
                        if (villages.size() > 0) {
                            for (EzCoreAppVillageDO village : villages) {
                                generateAreaReport(currentTime, orgId, "APP", districtDO.getName(), village.getName());
                            }
                        }
                    }
                }

                generateSubOrgReport(currentTime, orgId, "APP");
                generateSubOrgReport(currentTime, orgId, "IMPORT");

                return true;
            }

            @Override
            public List<String> getLogData() {
                return logData;
            }
        });
    }

    private void generateSubOrgReport(String currentTime, String orgId, String source) {
        List<BizCustomQueryGroupDO> subOrgGroups = bizMemberUnionRepository
                .fetchGroupSubOrg(orgId, source);
        for (BizCustomQueryGroupDO subOrgGroup : subOrgGroups) {
            BizReportBySubOrgDO report = new BizReportBySubOrgDO();
            report.setId(HashUtil.createHash(currentTime, orgId, source, subOrgGroup.getGroupName()));
            report.setOrgId(orgId);
            report.setSource(source);
            report.setSubOrgName(subOrgGroup.getGroupName());
            report.setVoterTotal(subOrgGroup.getCount1Value());

            List<BizCustomQueryGroupDO> groupRoles = bizMemberUnionRepository
                    .fetchRoleBySubOrgGroup(orgId, source, subOrgGroup.getGroupName());
            for (BizCustomQueryGroupDO groupRole : groupRoles) {
                if ("S".equals(groupRole.getGroupName())) {
                    report.setVoterStrong(groupRole.getCount1Value());
                } else if ("L".equals(groupRole.getGroupName())) {
                    report.setVoterLazy(groupRole.getCount1Value());
                } else {
                    report.setVoterOther(groupRole.getCount1Value());
                }
            }

            List<BizCustomQueryGroupDO> groupGenders = bizMemberUnionRepository
                    .fetchGenderBySubOrgGroup(orgId, source, subOrgGroup.getGroupName());
            long otherGender = 0;
            for (BizCustomQueryGroupDO groupGender : groupGenders) {
                if ("MALE".equals(groupGender.getGroupName())) {
                    report.setGenderMale(groupGender.getCount1Value());
                } else if ("FEMALE".equals(groupGender.getGroupName())) {
                    report.setGenderFemale(groupGender.getCount1Value());
                } else {
                    otherGender += groupGender.getCount1Value();
                }
            }
            report.setGenderOther(otherGender);
            bizReportBySubOrgRepository.saveAndFlush(report);
        }

    }

    private void generateAreaReport(String currentTime, String orgId, String source, String districtName, String villageName) {
        BizReportByAreaDO reportByArea = new BizReportByAreaDO();
        reportByArea.setId(HashUtil.createHash(currentTime, orgId, source, districtName, villageName));
        reportByArea.setSource(source);
        reportByArea.setOrgId(orgId);
        reportByArea.setDistrictName(districtName);
        reportByArea.setVillageName(villageName);

        List<BizCustomQueryGroupDO> groupRoles;
        List<BizCustomQueryGroupDO> groupGenders;
        if ("ALL".equals(villageName)) {
            groupRoles = bizMemberUnionRepository
                    .districtNameFetchRoleGroup(orgId, source, districtName);
            groupGenders = bizMemberUnionRepository
                    .districtNameFetchGenderGroup(orgId, source, districtName);
        } else {
            groupRoles = bizMemberUnionRepository
                    .villageLevelFetchRoleGroup(orgId, source, districtName, villageName);
            groupGenders = bizMemberUnionRepository
                    .villageLevelFetchGenderGroup(orgId, source, districtName, villageName);

            List<BizCustomQueryGroupDO> groupTps = bizMemberUnionRepository
                    .villageLevelFetchTpsGroup(orgId, source, districtName, villageName);
            if (groupTps.size() > 0) {
                Map<String, Long> tpsData = new HashMap<>();
                for (BizCustomQueryGroupDO tpsGroup : groupTps) {
                    String tpsName = "U";
                    if (StringUtil.isNotBlank(tpsGroup.getGroupName())) {
                        tpsName = tpsGroup.getGroupName().length() < 2 ? "0"+ tpsGroup.getGroupName() : tpsGroup.getGroupName();
                    }
                    if (tpsGroup.getCount1Value() > 0) {
                        tpsData.put(tpsName, tpsGroup.getCount1Value());
                    }
                }

                try {
                    reportByArea.setTpsData(new ObjectMapper().writeValueAsString(tpsData));
                } catch (Exception ignored) {}
            }
        }

        long totalVoter = 0;
        for (BizCustomQueryGroupDO groupRole : groupRoles) {
            totalVoter += groupRole.getCount1Value();
            if ("S".equals(groupRole.getGroupName())) {
                reportByArea.setVoterStrong(groupRole.getCount1Value());
            } else if ("L".equals(groupRole.getGroupName())) {
                reportByArea.setVoterLazy(groupRole.getCount1Value());
                totalVoter += groupRole.getCount1Value();
            } else {
                reportByArea.setVoterOther(groupRole.getCount1Value());
            }
        }
        reportByArea.setVoterTotal(totalVoter);

        long otherGender = 0;
        for (BizCustomQueryGroupDO groupGender : groupGenders) {
            if ("MALE".equals(groupGender.getGroupName())) {
                reportByArea.setGenderMale(groupGender.getCount1Value());
            } else if ("FEMALE".equals(groupGender.getGroupName())) {
                reportByArea.setGenderFemale(groupGender.getCount1Value());
            } else {
                otherGender += groupGender.getCount1Value();
            }
        }
        reportByArea.setGenderOther(otherGender);

        bizReportByAreaRepository.saveAndFlush(reportByArea);
    }

    private String getSubOrgName(List<BizSubOrganizationDO> subOrgs, String subOrgId) {
        for (BizSubOrganizationDO subOrganizationDO : subOrgs) {
            if (subOrganizationDO.getSubOrgId().equals(subOrgId)) {
                return subOrganizationDO.getName();
            }
        }
        return "UNDEFINED";
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
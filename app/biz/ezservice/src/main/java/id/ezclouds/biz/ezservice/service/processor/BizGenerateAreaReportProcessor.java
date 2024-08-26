/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportByAreaDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberUnionRepository;
import id.ezclouds.biz.ezservice.service.processor.inner.BizAreaReportInnerProcessor;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.shared.repo.CoreAppDistrictRepository;
import id.ezclouds.core.shared.repo.CoreAppVillageRepository;
import id.ezclouds.core.shared.repo.dataobject.EzCoreAppDistrictDO;
import id.ezclouds.core.shared.repo.dataobject.EzCoreAppVillageDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizGenerateAreaReportProcessor.java, v 0.1 2024‐07‐20 6:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope(value = "prototype")
@Async
public class BizGenerateAreaReportProcessor extends BizAsyncProcessor {

    @Autowired
    private BizAreaReportInnerProcessor bizAreaReportInnerProcessor;

    @Autowired
    private BizMemberUnionRepository bizMemberUnionRepository;

    @Autowired
    private CoreAppDistrictRepository coreAppDistrictRepository;

    @Autowired
    private CoreAppVillageRepository coreAppVillageRepository;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.GENERATE_REPORT_BY_AREA;
    }

    @Override
    protected int maxProcessTime() {
        return 1000 * 60 * 20;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String orgId = (String) request;

        long deletedReportArea = bizAreaReportInnerProcessor.deleteAllReport(orgId);
        logData.add("DEL_REPORT_AREA="+ deletedReportArea);

        String currentTime = DateUtil.getCurrentFormattedDate();

        int totalTps = 0;

        List<EzCoreAppDistrictDO> districts = coreAppDistrictRepository.findByRegencyId("1802");
        logData.add("DISTRICT_TOTAL="+ districts.size());
        int villageTotal = 0;
        for (EzCoreAppDistrictDO districtDO : districts) {
            generateAreaReport(currentTime, orgId, "APP", districtDO.getName(), "ALL");
            generateAreaReport(currentTime, orgId, "IMPORT", districtDO.getName(), "ALL");

            List<EzCoreAppVillageDO> villages = coreAppVillageRepository.findByDistrictId(districtDO.getId());
            villageTotal += villages.size();
            if (villages.size() > 0) {
                for (EzCoreAppVillageDO village : villages) {
                    int tpsCount = generateAreaReport(currentTime, orgId, "APP", districtDO.getName(), village.getName());
                    totalTps += tpsCount;
                }
            }
        }
        logData.add("VILLAGE_TOTAL="+ villageTotal);

        bizAreaReportInnerProcessor.storeTpsCoverage(orgId, totalTps);
        logData.add("TPS_TOTAL="+ totalTps);
        return true;
    }

    private int generateAreaReport(String currentTime, String orgId, String source, String districtName, String villageName) {
        //TODO: move this calculation to proper processor
        int groupTpsTotal = 0;
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
            groupTpsTotal += groupTps.size();
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

        bizAreaReportInnerProcessor.storeBizReport(reportByArea);

        return groupTpsTotal;
    }
}
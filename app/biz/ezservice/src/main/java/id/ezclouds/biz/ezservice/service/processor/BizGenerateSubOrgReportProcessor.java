/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportBySubOrgDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberUnionRepository;
import id.ezclouds.biz.ezservice.service.core.repo.BizReportBySubOrgRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizGenerateSubOrgReportProcessor.java, v 0.1 2024‐07‐21 3:33 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope(value = "prototype")
@Async
public class BizGenerateSubOrgReportProcessor extends BizAsyncProcessor {

    @Autowired
    private BizMemberUnionRepository bizMemberUnionRepository;

    @Autowired
    private BizReportBySubOrgRepository bizReportBySubOrgRepository;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.GENERATE_REPORT_BY_SUB_ORG;
    }

    @Override
    protected int maxProcessTime() {
        return 10 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        final String orgId = (String) request;
        logData.add("ORG_ID="+ orgId);

        clearCurrentReport(orgId, logData);

        String currentTime = DateUtil.getCurrentFormattedDate();
        generateSubOrgReport(currentTime, orgId, "APP", logData);
        generateSubOrgReport(currentTime, orgId, "IMPORT", logData);

        return true;
    }

    @Transactional
    public void clearCurrentReport(String orgId, List<String> logData) {
        long deleted = bizReportBySubOrgRepository.deleteByOrgId(orgId);
        logData.add("DEL_REPORT="+ deleted);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void generateSubOrgReport(String currentTime, String orgId, String source, List<String> logData) {
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
        logData.add("SUBORG_COUNT="+ subOrgGroups.size());
    }
}
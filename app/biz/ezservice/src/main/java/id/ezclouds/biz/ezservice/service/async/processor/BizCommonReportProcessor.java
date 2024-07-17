/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.processor;

import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberImportRepository;
import id.ezclouds.biz.ezservice.service.core.repo.BizReportCustomRepository;
import id.ezclouds.biz.ezservice.service.request.BizAsyncProcessRequest;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCommonReportProcessor.java, v 0.1 2024‐07‐06 2:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizCommonReportProcessor implements BizAsyncProcessor {

    @Autowired
    private BizMemberImportRepository bizMemberImportRepository;

    @Autowired
    private BizReportCustomRepository bizReportCustomRepository;

    @Autowired
    private AppSubOrganizationRepository appSubOrganizationRepository;

    @Override
    public void process(BizAsyncProcessRequest request) {
        //
    }

    /*
    private void reloadReportByGroupType(String groupType, String orgId) {
        List<BizReportCustomDO> currentReports = bizReportCustomRepository
                .findByOrgIdAndGroupType(orgId, groupType);
        for (BizReportCustomDO currentReport : currentReports) {
            bizReportCustomRepository.delete(currentReport);
            bizReportCustomRepository.flush();
        }

        List<BizSubOrganizationDO> subOrgs = new ArrayList<>();
        List<BizReportCustomDO> reportsWithPhone = new ArrayList<>();
        List<BizReportCustomDO> reportsWithIdCard = new ArrayList<>();
        List<BizReportCustomDO> reportsMale = new ArrayList<>();
        List<BizReportCustomDO> reportsFemale = new ArrayList<>();
        List<BizReportCustomDO> reportsGroup = new ArrayList<>();

        if (groupType.equals("SUB_ORG_ID")) {
            reportsWithPhone = bizMemberImportRepository.getPhoneGroupBySubOrg(orgId);
            reportsWithIdCard = bizMemberImportRepository.getIdCardGroupBySubOrg(orgId);
            reportsMale = bizMemberImportRepository.getGenderGroupByIdSubOrg(orgId, "MALE");
            reportsFemale = bizMemberImportRepository.getGenderGroupByIdSubOrg(orgId, "FEMALE");
            subOrgs = appSubOrganizationRepository.findByOrgId(orgId);
            reportsGroup = bizMemberImportRepository.getReportGroupBySubOrg(orgId);
        } else {
            reportsWithPhone = bizMemberImportRepository.getPhoneGroupByDistrict(orgId);
            reportsWithIdCard = bizMemberImportRepository.getIdCardGroupByDistrict(orgId);
            reportsMale = bizMemberImportRepository.getGenderGroupByDistrict(orgId, "MALE");
            reportsFemale = bizMemberImportRepository.getGenderGroupByDistrict(orgId, "FEMALE");
            reportsGroup = bizMemberImportRepository.getReportGroupByDistrict(orgId);
        }


        String currentTime = DateUtil.getCurrentFormattedDate();
        for (BizReportCustomDO reportCustomDO : reportsGroup) {
            reportCustomDO.setId(HashUtil.createHash(reportCustomDO.getGroupId(), currentTime));
            reportCustomDO.setOrgId(orgId);
            reportCustomDO.setGroupType(groupType);
            reportCustomDO.setWithPhone(getCountValue(reportsWithPhone, reportCustomDO.getGroupId()));
            reportCustomDO.setWithIdCard(getCountValue(reportsWithIdCard, reportCustomDO.getGroupId()));
            reportCustomDO.setMale(getCountValue(reportsMale, reportCustomDO.getGroupId()));
            reportCustomDO.setFemale(getCountValue(reportsFemale, reportCustomDO.getGroupId()));
            reportCustomDO.setCreatedTime(currentTime);

            if (groupType.equals("SUB_ORG_ID")) {
                reportCustomDO.setGroupName(getSubOrgName(subOrgs, reportCustomDO.getGroupId()));
            } else {
                reportCustomDO.setGroupName(reportCustomDO.getGroupId());
            }
        }

        storeReports(reportsGroup);
    }

    private String getSubOrgName(List<BizSubOrganizationDO> subOrgs, String subOrgId) {
        for (BizSubOrganizationDO subOrganizationDO : subOrgs) {
            if (subOrganizationDO.getSubOrgId().equals(subOrgId)) {
                return subOrganizationDO.getName();
            }
        }
        return null;
    }

    private long getCountValue(List<BizReportCustomDO> reports, String groupId) {
        for (BizReportCustomDO reportCustom : reports) {
            if (StringUtil.equalsNotNull(reportCustom.getGroupId(), groupId)) {
                return reportCustom.getTotal();
            }
        }
        return 0;
    }

    @Transactional
    public void storeReports(List<BizReportCustomDO> reports) {
        try {
            bizReportCustomRepository.saveAllAndFlush(reports);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     */
}
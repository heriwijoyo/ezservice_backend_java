/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.enums.BizReportByTime;
import id.ezclouds.common.util.TimeSeriesUtil;
import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportTimeSeriesDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberUnionRepository;
import id.ezclouds.biz.ezservice.service.processor.inner.BizTimeSeriesReportInnerProcessor;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.shared.repo.CoreAppDistrictRepository;
import id.ezclouds.core.shared.repo.dataobject.EzCoreAppDistrictDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizGenerateTimeSeriesReportProcessor.java, v 0.1 2024‐07‐21 3:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope(value = "prototype")
@Async
public class BizGenerateTimeSeriesReportProcessor extends BizAsyncProcessor {

    @Autowired
    private BizTimeSeriesReportInnerProcessor bizTimeSeriesReportInnerProcessor;

    @Autowired
    private AppSubOrganizationRepository appSubOrganizationRepository;

    @Autowired
    private CoreAppDistrictRepository coreAppDistrictRepository;

    @Autowired
    private BizMemberUnionRepository bizMemberUnionRepository;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.GENERATE_REPORT_TIME_SERIES;
    }

    @Override
    protected int maxProcessTime() {
        return 10 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        final String orgId = (String) request;
        logData.add("ORG_ID="+ orgId);
        List<BizSubOrganizationDO> subOrgs = appSubOrganizationRepository.findByOrgId(orgId);
        List<EzCoreAppDistrictDO> districts = coreAppDistrictRepository.findByRegencyId("1802");

        long deleted = bizTimeSeriesReportInnerProcessor.deleteAllReport(orgId);
        logData.add("DEL="+ deleted);

        List<String> timePeriods = TimeSeriesUtil.getDailySeriesFrom("2024-06-24", 1);

        for (BizReportByTime bizReportByTime : BizReportByTime.values()) {
            switch (bizReportByTime) {
                case DAILY_SUB_ORG_PERFORMANCE:
                    generateDailySubOrgPerformance(orgId, "APP", timePeriods, subOrgs, bizReportByTime.getId());
                    break;
                case DAILY_AREA_PERFORMANCE:
                    generateDailyDistrictPerformance(orgId, "APP", timePeriods, districts, bizReportByTime.getId());
                    break;
            }
        }

        return true;
    }

    private void generateDailyDistrictPerformance(String orgId, String source, List<String> timePeriods, List<EzCoreAppDistrictDO> districts, String reportId) {
        for (String timePeriod : timePeriods) {
            List<BizCustomQueryGroupDO> groupDates = bizMemberUnionRepository
                    .fetchDateSeriesByDistrictGroup(orgId, source, timePeriod);
            for (EzCoreAppDistrictDO districtDO : districts) {
                String groupValue = districtDO.getName();
                BizReportTimeSeriesDO bizReport = new BizReportTimeSeriesDO();
                bizReport.setId(HashUtil.createHash(orgId, reportId, groupValue, timePeriod));
                bizReport.setOrgId(orgId);
                bizReport.setReportId(reportId);
                bizReport.setGroupValue(groupValue);
                bizReport.setTimeFrame(timePeriod);
                bizReport.setTimeValue(getTimeSeriesValue(groupDates, groupValue));
                bizTimeSeriesReportInnerProcessor.storeReport(bizReport);
            }
        }
    }

    private void generateDailySubOrgPerformance(String orgId, String source, List<String> timePeriods, List<BizSubOrganizationDO> subOrgs, String reportId) {
        for (String timePeriod : timePeriods) {
            List<BizCustomQueryGroupDO> groupDates = bizMemberUnionRepository
                    .fetchDateSeriesBySubOrgGroup(orgId, source, timePeriod);
            for (BizSubOrganizationDO subOrganization : subOrgs) {
                String groupValue = subOrganization.getName();
                BizReportTimeSeriesDO bizReport = new BizReportTimeSeriesDO();
                bizReport.setId(HashUtil.createHash(orgId, reportId, groupValue, timePeriod));
                bizReport.setOrgId(orgId);
                bizReport.setReportId(reportId);
                bizReport.setGroupValue(groupValue);
                bizReport.setTimeFrame(timePeriod);
                bizReport.setTimeValue(getTimeSeriesValue(groupDates, subOrganization.getSubOrgId()));
                bizTimeSeriesReportInnerProcessor.storeReport(bizReport);
            }
        }
    }

    private long getTimeSeriesValue(List<BizCustomQueryGroupDO> groupResult, String groupValue) {
        long value = 0;
        for (BizCustomQueryGroupDO groupDO : groupResult) {
            if (StringUtil.equalsNotNull(groupDO.getGroupName(), groupValue)) {
                return groupDO.getCount1Value();
            }
        }
        return value;
    }
}
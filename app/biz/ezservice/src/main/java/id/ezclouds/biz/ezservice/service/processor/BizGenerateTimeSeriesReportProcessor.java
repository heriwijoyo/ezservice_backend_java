/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.enums.BizReportByTime;
import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportTimeSeriesDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizMemberUnionRepository;
import id.ezclouds.biz.ezservice.service.core.repo.BizReportTimeSeriesRepository;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    private AppSubOrganizationRepository appSubOrganizationRepository;

    @Autowired
    private BizMemberUnionRepository bizMemberUnionRepository;

    @Autowired
    private BizReportTimeSeriesRepository bizReportTimeSeriesRepository;

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
        List<BizSubOrganizationDO> subOrgs = appSubOrganizationRepository.findByOrgId(orgId);

        bizReportTimeSeriesRepository.deleteByOrgId(orgId);
        for (BizReportByTime bizReportByTime : BizReportByTime.values()) {
            switch (bizReportByTime) {
                case DAILY_SUB_ORG_PERFORMANCE:
                    generateDailySubOrgPerformance(logData, orgId, "APP", subOrgs, bizReportByTime.getId());
                    break;
            }
        }

        return true;
    }

    private void generateDailySubOrgPerformance(List<String> logData, String orgId, String source, List<BizSubOrganizationDO> subOrgs, String reportId) {
        Date startDate = DateUtil.parseFormattedDate("2024-06-24 22:00:00", DateUtil.FORMAT_DATETIME_DEFAULT);
        Date endDate = new Date();
        List<String> timePeriods = new ArrayList<>();

        while (startDate.before(endDate)) {
            timePeriods.add(DateUtil.getFormattedDate(startDate, DateUtil.FORMAT_DATE));
            startDate = DateUtil.getDateAfterDays(startDate, 1);
        }
        logData.add("DATE_TOTAL="+ timePeriods.size());

        for (String timePeriod : timePeriods) {
            List<BizCustomQueryGroupDO> groupDates = bizMemberUnionRepository
                    .fetchDateSeriesBySubOrgGroup(orgId, source, timePeriod);
            for (BizSubOrganizationDO subOrganization : subOrgs) {
                String groupValue = subOrganization.getSubOrgId();
                BizReportTimeSeriesDO bizReport = new BizReportTimeSeriesDO();
                bizReport.setId(HashUtil.createHash(orgId, reportId, groupValue, timePeriod));
                bizReport.setOrgId(orgId);
                bizReport.setReportId(reportId);
                bizReport.setGroupValue(groupValue);
                bizReport.setTimeFrame(timePeriod);
                bizReport.setTimeValue(getTimeSeriesValue(groupDates, groupValue));
                bizReportTimeSeriesRepository.saveAndFlush(bizReport);
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
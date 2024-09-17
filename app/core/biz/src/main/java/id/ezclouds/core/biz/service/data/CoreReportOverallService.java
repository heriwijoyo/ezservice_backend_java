/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.biz.report.BizReportOverallService;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportOverallService.java, v 0.1 2024‐09‐18 12:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreReportOverallService implements BizReportOverallService {

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Override
    public List<BizReportOverall> getReportOverall(String orgId) {
        List<String> excludeKeys = new ArrayList<>();
        excludeKeys.add(BizReportOverallKey.TOTAL_SUB_ORGANIZATION.getCode());
        excludeKeys.add(BizReportOverallKey.TOTAL_MEMBER_UNION.getCode());
        excludeKeys.add(BizReportOverallKey.TOTAL_TPS.getCode());
        excludeKeys.add(BizReportOverallKey.MEMBER_TODAY.getCode());
        excludeKeys.add(BizReportOverallKey.MEMBER_YESTERDAY.getCode());
        excludeKeys.add(BizReportOverallKey.REAL_COUNT_VOTER_ALL_COUNT.getCode());
        excludeKeys.add(BizReportOverallKey.REAL_COUNT_VOTER_VERIFIED_COUNT.getCode());
        excludeKeys.add(BizReportOverallKey.VOTER_BASE_CLUSTER_COUNT.getCode());
        excludeKeys.add(BizReportOverallKey.VOTER_BASE_MEMBER_COUNT.getCode());
        excludeKeys.add(BizReportOverallKey.VOTER_BASE_VOTER_COUNT.getCode());
        excludeKeys.add(BizReportOverallKey.VOTER_BASE_VOTE_STATION_COUNT.getCode());

        return bizReportOverallDAO
                .getAllReport(orgId)
                .stream()
                .filter(report -> !excludeKeys.contains(report.getKeyId()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateReportOverall(String orgId, String reportKey, int value) {
        BizReportOverall reportOverall = bizReportOverallDAO
                .getAndLock(orgId, reportKey);
        bizReportOverallDAO.updateValue(reportOverall.getId(), value, DateUtil.getCurrentFormattedDateMillis());
    }
}
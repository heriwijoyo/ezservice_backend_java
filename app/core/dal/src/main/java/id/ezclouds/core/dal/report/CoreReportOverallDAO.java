/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report;

import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.dal.report.converter.BizReportConverter;
import id.ezclouds.core.dal.report.converter.BizReportOverallConverter;
import id.ezclouds.core.dal.report.dataobject.CoreReportOverallDO;
import id.ezclouds.core.dal.report.repo.CoreReportOverallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportOverallDAO.java, v 0.1 2024‐07‐28 7:54 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreReportOverallDAO implements BizReportOverallDAO {

    @Autowired
    private CoreReportOverallRepository coreReportOverallRepository;

    @EzDAOLogger
    @Override
    public void create(String orgId, String keyId) {
        CoreReportOverallDO reportOverallDO = coreReportOverallRepository
                .findByOrgIdAndKeyId(orgId, keyId);
        if (reportOverallDO == null) {
            reportOverallDO = new CoreReportOverallDO();
            reportOverallDO.setId(HashUtil.createHash(orgId, keyId));
            reportOverallDO.setOrgId(orgId);
            reportOverallDO.setKeyId(keyId);
            reportOverallDO.setCount(0);
            reportOverallDO.setUpdatedTime(DateUtil.getCurrentFormattedDateMillis());
            coreReportOverallRepository
                    .saveAndFlush(reportOverallDO);
        }
    }

    @EzDAOLogger
    @Override
    public List<BizReportOverall> getAllReport(String orgId) {
        return coreReportOverallRepository
                .findByOrgId(orgId)
                .stream()
                .map(BizReportConverter::convert)
                .collect(Collectors.toList());
    }

    @EzDAOLogger
    @Override
    public BizReportOverall getAndLock(String orgId, BizReportOverallKey overallKey) {
        String reportId = HashUtil.createHash(orgId, overallKey.getCode());
        return new BizReportOverallConverter().convertQuery(
                coreReportOverallRepository
                        .findAndLockById(reportId)
        );
    }

    @Override
    @EzDAOLogger
    public void store(BizReportOverall reportOverall) {
        coreReportOverallRepository
                .saveAndFlush(
                        new BizReportOverallConverter()
                                .convertStore(reportOverall)
                );
    }

    @EzDAOLogger
    @Override
    public void updateValue(String reportId, int count, String updatedTime) {
        coreReportOverallRepository
                .updateValue(reportId, count, updatedTime);
    }
}
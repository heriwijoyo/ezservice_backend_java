/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateTimeSeriesDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.report.BizReportAccumulateTimeSeries;
import id.ezclouds.common.model.biz.report.BizTimeSeriesScene;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.dal.biz.converter.BizReportAccumulateTimeSeriesConverter;
import id.ezclouds.core.dal.biz.repo.BizReportAccumulateTimeSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizReportAccumulateTimeSeriesDAO.java, v 0.1 2024‐10‐25 8:53 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizReportAccumulateTimeSeriesDAO implements BizReportAccumulateTimeSeriesDAO {

    @Autowired
    private BizReportAccumulateTimeSeriesRepository bizReportAccumulateTimeSeriesRepository;

    @Override
    @EzDAOLogger
    public BizReportAccumulateTimeSeries getAndLock(String orgId, BizTimeSeriesScene scene, String sceneId) {
        String accumulateId = HashUtil.createHash(orgId, scene.getCode(), sceneId);
        return new BizReportAccumulateTimeSeriesConverter()
                .convertQuery(
                        bizReportAccumulateTimeSeriesRepository
                                .findAndLockById(accumulateId)
                );
    }

    @Override
    @EzDAOLogger
    public void store(BizReportAccumulateTimeSeries accumulateTimeSeries) {
        bizReportAccumulateTimeSeriesRepository
                .saveAndFlush(
                        new BizReportAccumulateTimeSeriesConverter()
                                .convertStore(accumulateTimeSeries)
                );
    }
}
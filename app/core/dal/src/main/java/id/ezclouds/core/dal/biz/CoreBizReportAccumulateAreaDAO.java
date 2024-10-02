/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateAreaDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.report.BizReportAccumulateArea;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.dal.biz.converter.BizReportAccumulateAreaConverter;
import id.ezclouds.core.dal.biz.repo.BizReportAccumulateAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizReportAccumulateAreaDAO.java, v 0.1 2024‐10‐03 1:28 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizReportAccumulateAreaDAO implements BizReportAccumulateAreaDAO {

    @Autowired
    private BizReportAccumulateAreaRepository bizReportAccumulateAreaRepository;

    @Override
    @EzDAOLogger
    public void store(BizReportAccumulateArea accumulateArea) {
        bizReportAccumulateAreaRepository
                .saveAndFlush(
                        new BizReportAccumulateAreaConverter()
                                .convertStore(accumulateArea)
                );
    }

    @Override
    @EzDAOLogger
    public BizReportAccumulateArea getAndLock(String orgId, CoreAreaLevel areaLevel, String areaLevelId) {
        String accumulateAreaId = HashUtil.createHash(orgId, areaLevel.getCode(), areaLevelId);
        return new BizReportAccumulateAreaConverter()
                .convertQuery(
                        bizReportAccumulateAreaRepository
                                .findAndLockById(accumulateAreaId)
                );
    }
}
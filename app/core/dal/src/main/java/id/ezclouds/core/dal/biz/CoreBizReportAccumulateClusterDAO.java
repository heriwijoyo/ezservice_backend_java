/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateClusterDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.report.BizReportAccumulateCluster;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.dal.biz.converter.BizReportAccumulateClusterConverter;
import id.ezclouds.core.dal.biz.repo.BizReportAccumulateClusterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizReportAccumulateClusterDAO.java, v 0.1 2024‐10‐21 9:32 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizReportAccumulateClusterDAO implements BizReportAccumulateClusterDAO {

    @Autowired
    private BizReportAccumulateClusterRepository bizReportAccumulateClusterRepository;

    @Override
    @EzDAOLogger
    public BizReportAccumulateCluster getAndLock(String orgId, String clusterId) {
        String accumulateId = HashUtil.createHash(orgId, clusterId);
        return new BizReportAccumulateClusterConverter()
                .convertQuery(
                        bizReportAccumulateClusterRepository
                                .findAndLockById(accumulateId)
                );
    }

    @Override
    @EzDAOLogger
    public void store(BizReportAccumulateCluster accumulateCluster) {
        if (StringUtil.isBlank(accumulateCluster.getAccumulateClusterId())) {
            String orgId = accumulateCluster.getOrgId();
            String clusterId = accumulateCluster.getClusterId();
            String accumulateId = HashUtil.createHash(orgId, clusterId);
            accumulateCluster.setAccumulateClusterId(accumulateId);
        }

        bizReportAccumulateClusterRepository
                .saveAndFlush(
                        new BizReportAccumulateClusterConverter()
                                .convertStore(accumulateCluster)
                );
    }
}
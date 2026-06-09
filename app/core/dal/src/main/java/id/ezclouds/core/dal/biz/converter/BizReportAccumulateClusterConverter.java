/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.report.BizReportAccumulateCluster;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.BizReportAccumulateClusterDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateClusterConverter.java, v 0.1 2024‐10‐21 11:41 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportAccumulateClusterConverter extends CommonDOModelConverter<BizReportAccumulateClusterDO, BizReportAccumulateCluster> {

    @Override
    protected BizReportAccumulateCluster safeConvertQuery(BizReportAccumulateClusterDO dataObject) {
        BizReportAccumulateCluster accumulateCluster = new BizReportAccumulateCluster();
        accumulateCluster.setAccumulateClusterId(dataObject.getAccumulateClusterId());
        accumulateCluster.setOrgId(dataObject.getOrgId());
        accumulateCluster.setClusterId(dataObject.getClusterId());
        accumulateCluster.setClusterName(dataObject.getClusterName());
        accumulateCluster.setVoterCount(dataObject.getVoterCount());
        accumulateCluster.setVoterMaleCount(dataObject.getVoterMaleCount());
        accumulateCluster.setVoterFemaleCount(dataObject.getVoterFemaleCount());
        accumulateCluster.setVoterExtraCount(dataObject.getVoterExtraCount());
        accumulateCluster.setVoterExtraMaleCount(dataObject.getVoterExtraMaleCount());
        accumulateCluster.setVoterExtraFemaleCount(dataObject.getVoterExtraFemaleCount());
        accumulateCluster.setModifiedTime(dataObject.getModifiedTime());
        return accumulateCluster;
    }

    @Override
    protected BizReportAccumulateClusterDO safeConvertStore(BizReportAccumulateCluster model) {
        BizReportAccumulateClusterDO accumulateClusterDO = new BizReportAccumulateClusterDO();
        accumulateClusterDO.setAccumulateClusterId(model.getAccumulateClusterId());
        accumulateClusterDO.setOrgId(model.getOrgId());
        accumulateClusterDO.setClusterId(model.getClusterId());
        accumulateClusterDO.setClusterName(model.getClusterName());
        accumulateClusterDO.setVoterCount(model.getVoterCount());
        accumulateClusterDO.setVoterMaleCount(model.getVoterMaleCount());
        accumulateClusterDO.setVoterFemaleCount(model.getVoterFemaleCount());
        accumulateClusterDO.setVoterExtraCount(model.getVoterExtraCount());
        accumulateClusterDO.setVoterExtraMaleCount(model.getVoterExtraMaleCount());
        accumulateClusterDO.setVoterExtraFemaleCount(model.getVoterExtraFemaleCount());
        accumulateClusterDO.setModifiedTime(model.getModifiedTime());
        return accumulateClusterDO;
    }
}
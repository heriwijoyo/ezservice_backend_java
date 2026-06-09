/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.converter;

import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.report.BizReportRealCount;
import id.ezclouds.core.dal.report.dataobject.CoreReportRealCountDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportRealCountConverter.java, v 0.1 2024‐09‐15 11:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreReportRealCountConverter extends CommonDOModelConverter<CoreReportRealCountDO, BizReportRealCount> {

    @Override
    protected BizReportRealCount safeConvertQuery(CoreReportRealCountDO dataObject) {
        BizReportRealCount realCount = new BizReportRealCount();
        realCount.setId(dataObject.getId());
        realCount.setOrgId(dataObject.getOrgId());
        realCount.setScene(dataObject.getScene());
        realCount.setSceneId(dataObject.getSceneId());
        realCount.setSceneParent(dataObject.getSceneParent());
        realCount.setUpdatedTime(dataObject.getUpdatedTime());
        realCount.setSort(dataObject.getSort());
        realCount.setCountA(dataObject.getCountA());
        realCount.setCountB(dataObject.getCountB());
        realCount.setCountC(dataObject.getCountC());
        realCount.setCountD(dataObject.getCountD());
        realCount.setCountE(dataObject.getCountE());
        realCount.setCountF(dataObject.getCountF());
        realCount.setCountG(dataObject.getCountG());
        realCount.setCountH(dataObject.getCountH());
        realCount.setCountI(dataObject.getCountI());
        realCount.setCountJ(dataObject.getCountJ());
        return realCount;
    }

    @Override
    protected CoreReportRealCountDO safeConvertStore(BizReportRealCount model) {
        CoreReportRealCountDO realCountDO = new CoreReportRealCountDO();
        realCountDO.setId(model.getId());
        realCountDO.setOrgId(model.getOrgId());
        realCountDO.setScene(model.getScene());
        realCountDO.setSceneId(model.getSceneId());
        realCountDO.setSceneParent(model.getSceneParent());
        realCountDO.setUpdatedTime(model.getUpdatedTime());
        realCountDO.setSort(model.getSort());
        realCountDO.setCountA(model.getCountA());
        realCountDO.setCountB(model.getCountB());
        realCountDO.setCountC(model.getCountC());
        realCountDO.setCountD(model.getCountD());
        realCountDO.setCountE(model.getCountE());
        realCountDO.setCountF(model.getCountF());
        realCountDO.setCountG(model.getCountG());
        realCountDO.setCountH(model.getCountH());
        realCountDO.setCountI(model.getCountI());
        realCountDO.setCountJ(model.getCountJ());
        return realCountDO;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.report.BizReportAccumulateArea;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.BizReportAccumulateAreaDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateAreaConverter.java, v 0.1 2024‐10‐03 1:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportAccumulateAreaConverter extends CommonDOModelConverter<BizReportAccumulateAreaDO, BizReportAccumulateArea> {

    @Override
    protected BizReportAccumulateArea safeConvertQuery(BizReportAccumulateAreaDO dataObject) {
        BizReportAccumulateArea accumulateArea = new BizReportAccumulateArea();
        accumulateArea.setAccumulateAreaId(dataObject.getAccumulateAreaId());
        accumulateArea.setOrgId(dataObject.getOrgId());
        accumulateArea.setAreaLevel(CoreAreaLevel.getByCode(dataObject.getAreaLevel()));
        accumulateArea.setProvinceId(dataObject.getProvinceId());
        accumulateArea.setProvinceName(dataObject.getProvinceName());
        accumulateArea.setRegencyId(dataObject.getRegencyId());
        accumulateArea.setRegencyName(dataObject.getRegencyName());
        accumulateArea.setDistrictId(dataObject.getDistrictId());
        accumulateArea.setDistrictName(dataObject.getDistrictName());
        accumulateArea.setVillageId(dataObject.getVillageId());
        accumulateArea.setVillageName(dataObject.getVillageName());
        accumulateArea.setVoterMaleCount(dataObject.getVoterMaleCount());
        accumulateArea.setVoterFemaleCount(dataObject.getVoterFemaleCount());
        accumulateArea.setVoterExtraCount(dataObject.getVoterExtraCount());
        accumulateArea.setModifiedTime(dataObject.getModifiedTime());
        return accumulateArea;
    }

    @Override
    protected BizReportAccumulateAreaDO safeConvertStore(BizReportAccumulateArea model) {
        BizReportAccumulateAreaDO accumulateAreaDO = new BizReportAccumulateAreaDO();
        accumulateAreaDO.setAccumulateAreaId(model.getAccumulateAreaId());
        accumulateAreaDO.setOrgId(model.getOrgId());
        accumulateAreaDO.setAreaLevel(model.getAreaLevel().getCode());
        accumulateAreaDO.setProvinceId(model.getProvinceId());
        accumulateAreaDO.setProvinceName(model.getProvinceName());
        accumulateAreaDO.setRegencyId(model.getRegencyId());
        accumulateAreaDO.setRegencyName(model.getRegencyName());
        accumulateAreaDO.setDistrictId(model.getDistrictId());
        accumulateAreaDO.setDistrictName(model.getDistrictName());
        accumulateAreaDO.setVillageId(model.getVillageId());
        accumulateAreaDO.setVillageName(model.getVillageName());
        accumulateAreaDO.setVoterMaleCount(model.getVoterMaleCount());
        accumulateAreaDO.setVoterFemaleCount(model.getVoterFemaleCount());
        accumulateAreaDO.setVoterExtraCount(model.getVoterExtraCount());
        accumulateAreaDO.setModifiedTime(model.getModifiedTime());
        return accumulateAreaDO;
    }
}
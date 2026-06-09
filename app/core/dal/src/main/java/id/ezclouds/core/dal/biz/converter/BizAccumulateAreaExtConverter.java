/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.report.BizAccumulateAreaExt;
import id.ezclouds.common.model.biz.report.BizAccumulateKey;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.BizAccumulateAreaExtDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAccumulateAreaExtConverter.java, v 0.1 2024‐10‐03 2:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAccumulateAreaExtConverter extends CommonDOModelConverter<BizAccumulateAreaExtDO, BizAccumulateAreaExt> {

    @Override
    protected BizAccumulateAreaExt safeConvertQuery(BizAccumulateAreaExtDO dataObject) {
        BizAccumulateAreaExt accumulateAreaExt = new BizAccumulateAreaExt();
        accumulateAreaExt.setAccumulateAreaExtId(dataObject.getAccumulateAreaExtId());
        accumulateAreaExt.setAccumulateAreaId(dataObject.getAccumulateAreaId());
        accumulateAreaExt.setOrgId(dataObject.getOrgId());
        accumulateAreaExt.setAreaLevel(CoreAreaLevel.getByCode(dataObject.getAreaLevel()));
        accumulateAreaExt.setAccumulateKey(BizAccumulateKey.getByCode(dataObject.getAccumulateKey()));
        accumulateAreaExt.setAccumulateVariable(dataObject.getAccumulateVariable());
        accumulateAreaExt.setAccumulateCount(dataObject.getAccumulateCount());
        accumulateAreaExt.setModifiedTime(dataObject.getModifiedTime());
        return accumulateAreaExt;
    }

    @Override
    protected BizAccumulateAreaExtDO safeConvertStore(BizAccumulateAreaExt model) {
        BizAccumulateAreaExtDO accumulateAreaExtDO = new BizAccumulateAreaExtDO();
        accumulateAreaExtDO.setAccumulateAreaExtId(model.getAccumulateAreaExtId());
        accumulateAreaExtDO.setAccumulateAreaId(model.getAccumulateAreaId());
        accumulateAreaExtDO.setOrgId(model.getOrgId());
        accumulateAreaExtDO.setAreaLevel(model.getAreaLevel().getCode());
        accumulateAreaExtDO.setAccumulateKey(model.getAccumulateKey().getCode());
        accumulateAreaExtDO.setAccumulateVariable(model.getAccumulateVariable());
        accumulateAreaExtDO.setAccumulateCount(model.getAccumulateCount());
        accumulateAreaExtDO.setModifiedTime(model.getModifiedTime());
        return accumulateAreaExtDO;
    }
}
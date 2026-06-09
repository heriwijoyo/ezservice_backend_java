/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.BizUnique;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.EzBizUniqueDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizUniqueConverter.java, v 0.1 2024‐08‐31 3:28 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizUniqueConverter extends CommonDOModelConverter<EzBizUniqueDO, BizUnique> {

    @Override
    protected BizUnique safeConvertQuery(EzBizUniqueDO dataObject) {
        BizUnique bizUnique = new BizUnique();
        bizUnique.setBizUniqueId(dataObject.getBizUniqueId());
        bizUnique.setOrgId(dataObject.getOrgId());
        bizUnique.setBizScenario(dataObject.getBizScenario());
        bizUnique.setParamValue(dataObject.getParamValue());
        return bizUnique;
    }

    @Override
    protected EzBizUniqueDO safeConvertStore(BizUnique model) {
        EzBizUniqueDO uniqueDO = new EzBizUniqueDO();
        uniqueDO.setBizUniqueId(model.getBizUniqueId());
        uniqueDO.setOrgId(model.getOrgId());
        uniqueDO.setBizScenario(model.getBizScenario());
        uniqueDO.setParamValue(model.getParamValue());
        uniqueDO.setCreatedTime(model.getCreatedTime());
        return uniqueDO;
    }
}
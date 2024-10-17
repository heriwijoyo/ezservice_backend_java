/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.data.BizRealCountDataMap;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.EzRealCountDataMapDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizRealCountDataMapConverter.java, v 0.1 2024‐09‐17 12:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizRealCountDataMapConverter extends CommonDOModelConverter<EzRealCountDataMapDO, BizRealCountDataMap> {

    @Override
    protected BizRealCountDataMap safeConvertQuery(EzRealCountDataMapDO dataObject) {
        BizRealCountDataMap dataMap = new BizRealCountDataMap();
        dataMap.setMapId(dataObject.getMapId());
        dataMap.setOrgId(dataObject.getOrgId());
        dataMap.setScene(dataObject.getScene());
        dataMap.setMapKey(dataObject.getMapKey());
        dataMap.setMapValue(dataObject.getMapValue());
        return dataMap;
    }

    @Override
    protected EzRealCountDataMapDO safeConvertStore(BizRealCountDataMap model) {
        return null;
    }
}
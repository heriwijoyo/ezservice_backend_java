/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.data.BizMasterData;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.EzMasterDataDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMasterDataConverter.java, v 0.1 2024‐09‐04 8:54 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMasterDataConverter extends CommonDOModelConverter<EzMasterDataDO, BizMasterData> {

    @Override
    protected BizMasterData safeConvertQuery(EzMasterDataDO dataObject) {
        return null;
    }

    @Override
    protected EzMasterDataDO safeConvertStore(BizMasterData model) {
        EzMasterDataDO dataDO = new EzMasterDataDO();
        dataDO.setBizMasterId(model.getBizMasterId());
        dataDO.setOrgId(model.getOrgId());
        dataDO.setScene(model.getScene());
        dataDO.setDataId(model.getDataId());
        dataDO.setDataName(model.getDataName());
        dataDO.setNumberValue1(model.getNumberValue1());
        dataDO.setNumberValue2(model.getNumberValue2());
        dataDO.setNumberValue3(model.getNumberValue3());
        dataDO.setNumberValue4(model.getNumberValue4());
        dataDO.setNumberValue5(model.getNumberValue5());
        dataDO.setCharValue1(model.getCharValue1());
        dataDO.setCharValue2(model.getCharValue2());
        dataDO.setCharValue3(model.getCharValue3());
        dataDO.setCharValue4(model.getCharValue4());
        dataDO.setCharValue5(model.getCharValue5());
        return dataDO;
    }
}
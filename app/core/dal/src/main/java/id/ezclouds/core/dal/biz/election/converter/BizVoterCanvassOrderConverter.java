/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.converter;

import id.ezclouds.common.model.biz.election.BizCanvassOrder;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.election.dataobject.BizVoterCanvassOrderDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterCanvassOrderConverter.java, v 0.1 2024‐09‐26 12:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizVoterCanvassOrderConverter extends CommonDOModelConverter<BizVoterCanvassOrderDO, BizCanvassOrder> {

    @Override
    protected BizCanvassOrder safeConvertQuery(BizVoterCanvassOrderDO dataObject) {
        return null;
    }

    @Override
    protected BizVoterCanvassOrderDO safeConvertStore(BizCanvassOrder model) {
        return null;
    }
}
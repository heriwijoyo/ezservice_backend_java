/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.broker.converter;

import id.ezclouds.common.model.broker.BrokerMessage;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.broker.dataobject.EzBrokerMessageDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerMessageConverter.java, v 0.1 2024‐08‐31 11:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BrokerMessageConverter extends CommonDOModelConverter<EzBrokerMessageDO, BrokerMessage> {

    @Override
    protected BrokerMessage safeConvertQuery(EzBrokerMessageDO dataObject) {
        return null;
    }

    @Override
    protected EzBrokerMessageDO safeConvertStore(BrokerMessage model) {
        EzBrokerMessageDO messageDO = new EzBrokerMessageDO();
        messageDO.setOrgId(model.getOrgId());
        messageDO.setSource(model.getSource());
        messageDO.setTopic(model.getTopic());
        messageDO.setEvent(model.getEvent());
        return messageDO;
    }
}
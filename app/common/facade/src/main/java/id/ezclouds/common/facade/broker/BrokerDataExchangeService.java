/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.broker;

import id.ezclouds.common.model.broker.BrokerDataTopic;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerDataExchangeService.java, v 0.1 2024‐09‐09 12:02 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BrokerDataExchangeService {

    void subscribe(BrokerDataTopic topic, BrokerDataEvent brokerDataEvent);
    void emitEvent(BrokerDataTopic topic, Object payload);
}
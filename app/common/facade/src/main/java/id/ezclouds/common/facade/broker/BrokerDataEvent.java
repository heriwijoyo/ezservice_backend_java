/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.broker;

import id.ezclouds.common.model.broker.BrokerDataTopic;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerDataEvent.java, v 0.1 2024‐09‐09 12:16 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BrokerDataEvent {

    void onDataEvent(BrokerDataTopic topic, Object payload);
}
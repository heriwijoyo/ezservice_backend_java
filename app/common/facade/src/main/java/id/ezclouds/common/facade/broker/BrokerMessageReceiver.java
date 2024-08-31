/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.broker;

import id.ezclouds.common.model.broker.BrokerMessageData;
import id.ezclouds.common.model.result.MessageReceiveResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerMessageReceiver.java, v 0.1 2024‐08‐31 12:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BrokerMessageReceiver {

    MessageReceiveResult process(String topic, String event, BrokerMessageData data);

}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.broker;

import id.ezclouds.common.model.broker.BrokerMessageData;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerMessageDataConvertService.java, v 0.1 2024‐08‐31 12:17 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BrokerMessageDataConvertService {

    String convertPayload(BrokerMessageData messageData);

    BrokerMessageData parsePayload(String topic, String event, String payload);

}
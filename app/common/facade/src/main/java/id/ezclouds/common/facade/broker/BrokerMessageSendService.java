/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.broker;

import id.ezclouds.common.model.broker.BrokerMessage;
import id.ezclouds.common.model.result.MessageSendResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerMessageSendService.java, v 0.1 2024‐08‐31 12:27 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BrokerMessageSendService {

    MessageSendResult send(BrokerMessage message);

}
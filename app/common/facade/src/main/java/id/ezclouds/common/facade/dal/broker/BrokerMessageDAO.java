/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.broker;

import id.ezclouds.common.model.broker.BrokerMessage;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerMessageDAO.java, v 0.1 2024‐08‐31 9:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BrokerMessageDAO {

    String storeMessage(BrokerMessage message);
}
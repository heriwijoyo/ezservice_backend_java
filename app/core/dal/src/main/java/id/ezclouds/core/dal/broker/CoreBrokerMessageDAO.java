/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.broker;

import id.ezclouds.common.facade.dal.broker.BrokerMessageDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.broker.BrokerMessage;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBrokerMessageDAO.java, v 0.1 2024‐08‐31 10:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBrokerMessageDAO implements BrokerMessageDAO {

    @EzDAOLogger
    @Override
    public String storeMessage(BrokerMessage message) {
        return null;
    }
}
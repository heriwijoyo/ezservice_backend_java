/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.service.inner;

import id.ezclouds.common.facade.broker.BrokerMessageReceiver;
import id.ezclouds.common.model.broker.BrokerMessage;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ProcessDispatchService.java, v 0.1 2024‐08‐31 10:08 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Async
@Service
public class ProcessDispatchService {

    public void process(String subscriberId, BrokerMessage message) {

        try {
            BeanFacadeUtil
                    .getBeanWithQualifier(BrokerMessageReceiver.class, subscriberId)
                    .process(message.getTopic(), message.getEvent(), message.getPayload());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
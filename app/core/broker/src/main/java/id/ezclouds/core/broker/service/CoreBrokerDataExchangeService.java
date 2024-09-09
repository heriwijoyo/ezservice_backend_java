/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.service;

import id.ezclouds.common.facade.broker.BrokerDataEvent;
import id.ezclouds.common.facade.broker.BrokerDataExchangeService;
import id.ezclouds.common.model.broker.BrokerDataTopic;
import id.ezclouds.core.broker.model.BrokerDataSubscriber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBrokerDataExchangeService.java, v 0.1 2024‐09‐09 12:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBrokerDataExchangeService implements BrokerDataExchangeService {

    private List<BrokerDataSubscriber> subscribers = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void subscribe(BrokerDataTopic topic, BrokerDataEvent event) {
        BrokerDataSubscriber subscriber = new BrokerDataSubscriber();
        subscriber.setTopic(topic);
        subscriber.setEvent(event);

        subscribers.add(subscriber);
    }

    @Override
    public void emitEvent(BrokerDataTopic topic, Object payload) {
        for (BrokerDataSubscriber subscriber : subscribers) {
            if (subscriber.getTopic() == topic) {
                subscriber.getEvent().onDataEvent(topic, payload);
            }
        }
    }
}
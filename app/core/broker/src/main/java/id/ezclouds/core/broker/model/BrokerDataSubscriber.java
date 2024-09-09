/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.model;

import id.ezclouds.common.facade.broker.BrokerDataEvent;
import id.ezclouds.common.model.broker.BrokerDataTopic;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerDataSubscriber.java, v 0.1 2024‐09‐09 12:24 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BrokerDataSubscriber {

    private BrokerDataTopic topic;
    private BrokerDataEvent event;

    public BrokerDataTopic getTopic() {
        return topic;
    }

    public void setTopic(BrokerDataTopic topic) {
        this.topic = topic;
    }

    public BrokerDataEvent getEvent() {
        return event;
    }

    public void setEvent(BrokerDataEvent event) {
        this.event = event;
    }
}
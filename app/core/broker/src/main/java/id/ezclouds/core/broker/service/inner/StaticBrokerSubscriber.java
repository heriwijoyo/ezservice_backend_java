/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.service.inner;

import id.ezclouds.common.model.broker.MsgSubConstant;
import id.ezclouds.core.broker.model.BrokerTopicEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: StaticBrokerSubscriber.java, v 0.1 2024‐08‐31 9:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class StaticBrokerSubscriber {

    public static List<String> getSubscribers(BrokerTopicEvent topicEvent) {
        List<String> subscribers = new ArrayList<>();

        switch (topicEvent) {
            case MEMBER_CLIENT_APP_LOGIN:
                subscribers.add(MsgSubConstant.MEMBER_APP_CLIENT_LOGIN);
                break;
            case CORE_MEMBER_REGISTER:
                break;
        }
        return subscribers;
    }

}
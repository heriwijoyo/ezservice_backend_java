/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.broker.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerTopicEvent.java, v 0.1 2024‐08‐31 9:23 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BrokerTopicEvent {

    BIZ_SURVEY_SUBMIT("BIZ_SURVEY", "SUBMIT"),

    CORE_AUTHORIZATION("CORE_AUTHORIZATION", "APP_CLIENT_LOGIN"),
    CORE_MEMBER_REGISTER("CORE_MEMBER", "REGISTER"),

    UNKNOWN("UNKNOWN", "UNKNOWN"),
    ;

    private final String topic;
    private final String event;

    BrokerTopicEvent(String topic, String event) {
        this.topic = topic;
        this.event = event;
    }

    public static BrokerTopicEvent getByTopicAndEvent(String topic, String event) {
        for (BrokerTopicEvent topicEvent : values()) {
            if (topicEvent.topic.equals(topic) && topicEvent.event.equals(event)) {
                return topicEvent;
            }
        }
        return UNKNOWN;
    }
}
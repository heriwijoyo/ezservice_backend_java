/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.broker;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BrokerMessage.java, v 0.1 2024‐08‐31 12:36 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BrokerMessage {

    private String orgId;
    private String source;
    private String topic;
    private String event;
    private String createdTime;
    private BrokerMessageData payload;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public BrokerMessageData getPayload() {
        return payload;
    }

    public void setPayload(BrokerMessageData payload) {
        this.payload = payload;
    }
}
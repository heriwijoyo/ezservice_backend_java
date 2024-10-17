/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.broker.event;

import id.ezclouds.common.model.broker.topic.EzCoreTopic;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCommonEvent.java, v 0.1 2024‐10‐02 12:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzCommonEvent {

    private EzCoreTopic coreTopic;
    private String orgId;
    private Object payload;

    public EzCommonEvent(EzCoreTopic coreTopic, String orgId, Object payload) {
        this.coreTopic = coreTopic;
        this.orgId = orgId;
        this.payload = payload;
    }

    public EzCoreTopic getCoreTopic() {
        return coreTopic;
    }

    public String getOrgId() {
        return orgId;
    }

    public Object getPayload() {
        return payload;
    }
}
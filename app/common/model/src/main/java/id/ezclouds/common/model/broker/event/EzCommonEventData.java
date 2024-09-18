/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.broker.event;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCommonEventData.java, v 0.1 2024‐09‐18 11:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzCommonEventData {

    private String orgId;
    private EzCommonEvent event;
    private Object payload;

    public EzCommonEventData(String orgId, EzCommonEvent event, Object payload) {
        this.orgId = orgId;
        this.event = event;
        this.payload = payload;
    }

    public String getOrgId() {
        return orgId;
    }

    public EzCommonEvent getEvent() {
        return event;
    }

    public Object getPayload() {
        return payload;
    }
}
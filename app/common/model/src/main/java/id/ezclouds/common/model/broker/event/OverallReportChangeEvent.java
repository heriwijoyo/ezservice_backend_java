/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.broker.event;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: OverallReportChangeEvent.java, v 0.1 2024‐09‐22 11:27 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class OverallReportChangeEvent {

    private String orgId;

    public OverallReportChangeEvent(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }
}
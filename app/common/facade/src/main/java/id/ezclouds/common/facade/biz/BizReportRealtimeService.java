/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportRealtimeService.java, v 0.1 2024‐09‐09 9:45 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportRealtimeService {

    void accumulateValue(String orgId, String reportKey, int addValue);
}
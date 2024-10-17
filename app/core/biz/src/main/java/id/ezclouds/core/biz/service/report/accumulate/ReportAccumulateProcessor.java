/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report.accumulate;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ReportAccumulateProcessor.java, v 0.1 2024‐10‐02 3:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface ReportAccumulateProcessor {

    void process(String orgId, Object payload, ReportAccumulateProcessHandler handler);
}
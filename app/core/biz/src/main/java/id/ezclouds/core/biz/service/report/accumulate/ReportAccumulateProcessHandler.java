/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report.accumulate;

import id.ezclouds.common.model.process.ProcessStatus;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ReportAccumulateProcessHandler.java, v 0.1 2024‐10‐02 3:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface ReportAccumulateProcessHandler {

    void onFinished(ProcessStatus status);
}
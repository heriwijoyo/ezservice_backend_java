/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report.accumulate;

import id.ezclouds.common.model.process.ProcessStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateVoterRegister.java, v 0.1 2024‐10‐02 3:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Qualifier("bizReportAccumulateVoterRegister")
public class BizReportAccumulateVoterRegister implements ReportAccumulateProcessor {

    @Override
    public void process(String orgId, Object payload, ReportAccumulateProcessHandler handler) {


        handler.onFinished(ProcessStatus.EXCEPTION);
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report.accumulate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateClusterRegister.java, v 0.1 2024‐10‐23 5:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope("prototype")
@Qualifier("bizReportAccumulateClusterRegister")
public class BizReportAccumulateClusterRegister implements ReportAccumulateProcessor {

    @Override
    public void process(String orgId, Object payload, ReportAccumulateProcessHandler handler) {
        
    }
}
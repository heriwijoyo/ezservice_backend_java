/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.service.async.event.BizProcessEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizGenerateAreaReportProcessor.java, v 0.1 2024‐07‐20 6:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope(value = "prototype")
@Async
public class BizGenerateAreaReportProcessor extends BizAsyncProcessor {

    @Override
    public BizProcessEvent getProcessEvent() {
        return null;
    }

    @Override
    int maxProcessTime() {
        return 0;
    }

    @Override
    boolean onProcess(Object request, List<String> logData) {
        return false;
    }
}
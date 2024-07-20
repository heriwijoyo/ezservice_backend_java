/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor.sample;

import id.ezclouds.biz.ezservice.service.async.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.processor.BizAsyncProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSampleOneProcessor.java, v 0.1 2024‐07‐21 1:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope(value = "prototype")
@Async
public class BizSampleOneProcessor extends BizAsyncProcessor {

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.SAMPLE_ONE;
    }

    @Override
    protected int maxProcessTime() {
        return 5000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        System.out.println("onProcess : SAMPLE_ONE");
        try {
            Thread.sleep(3000);
        } catch (Exception ignored) {}
        return true;
    }
}
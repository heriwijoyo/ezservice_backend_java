/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.service.processor.shared.BizThreadSharedResource;
import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.template.BizProcessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSchedulerMinuteProcessor.java, v 0.1 2024‐07‐18 3:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Async
public class BizSchedulerMinuteProcessor {

    @Autowired
    private BizThreadSharedResource bizThreadSharedResource;

    public void process() {
        final List<String> logData = new ArrayList<>();

        BizProcessTemplate.execute(BizProcessEvent.SCHEDULER_MINUTE, new BizProcessTemplate.Handler() {
            @Override
            public void doStart(BizProcessEvent processEvent) {
            }

            @Override
            public boolean doProcess(BizProcessEvent processEvent) {
                bizThreadSharedResource.startProcess(processEvent.getEventCode());
                logData.add("I_AM_DUMMY_PROCESS");
                return true;
            }

            @Override
            public void doFinish(BizProcessEvent processEvent) {
                bizThreadSharedResource.stopProcess();
            }

            @Override
            public List<String> getLogData() {
                return logData;
            }
        });
    }
}
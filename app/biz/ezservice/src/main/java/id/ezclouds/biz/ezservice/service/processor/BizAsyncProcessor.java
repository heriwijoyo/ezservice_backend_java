/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.service.processor.shared.BizThreadSharedResource;
import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.template.BizProcessTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAsyncProcessor.java, v 0.1 2024‐07‐20 4:01 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class BizAsyncProcessor implements BizProcessor {

    private final int ON_FINISH_BY_PROCESS = 0;
    private final int ON_FINISH_BY_TIMEOUT = 1;

    @Autowired
    private BizThreadSharedResource bizThreadSharedResource;
    private BizProcessor.FinishHandler finishHandler;

    private Timer timer;
    private boolean isFinishedCalled = false;

    protected abstract int maxProcessTime();
    protected abstract boolean onProcess(Object request, List<String> logData);

    @Override
    public void process(Object request) {
        final List<String> logData = new ArrayList<>();
        BizProcessTemplate.execute(getProcessEvent(), new BizProcessTemplate.Handler() {
            @Override
            public void doStart(BizProcessEvent processEvent) {
                onStart(processEvent);
            }

            @Override
            public boolean doProcess(BizProcessEvent processEvent) {
                return onProcess(request, logData);
            }

            @Override
            public void doFinish(BizProcessEvent processEvent) {
                onFinish(ON_FINISH_BY_PROCESS, processEvent);
            }

            @Override
            public List<String> getLogData() {
                return logData;
            }
        });
    }

    @Override
    public void setFinishHandler(FinishHandler finishHandler) {
        this.finishHandler = finishHandler;
    }

    private void onFinish(int finishMode, BizProcessEvent processEvent) {
        if (finishMode == ON_FINISH_BY_PROCESS && timer != null) {
            timer.cancel();
            timer = null;
        }
        bizThreadSharedResource.stopProcess();
        if (!isFinishedCalled) {
            if (finishHandler != null) {
                finishHandler.onFinish(processEvent);
            }
            isFinishedCalled = true;
        }
    }

    private void onStart(BizProcessEvent processEvent) {
        bizThreadSharedResource.startProcess(getProcessEvent().getEventCode());

        isFinishedCalled = false;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onFinish(ON_FINISH_BY_TIMEOUT, processEvent);
            }
        }, maxProcessTime());
    }
}
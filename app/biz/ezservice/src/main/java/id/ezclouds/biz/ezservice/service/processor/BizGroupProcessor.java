/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.service.async.event.BizProcessEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizGroupProcessor.java, v 0.1 2024‐07‐20 8:55 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizGroupProcessor implements BizProcessor.FinishHandler {

    private int buildSeq = 0;
    private int execSeq = 0;
    private boolean isSeqProcess = false;

    private Object request;

    private Map<Integer, BizProcessor> processorMap = new HashMap<>();

    public void addProcessor(BizProcessor bizProcessor) {
        processorMap.put(buildSeq, bizProcessor);
        buildSeq++;
    }

    public void processInSequence(Object request) {
        isSeqProcess = true;
        this.request = request;
        executeProcessor(request);
    }

    public void processAllAsync(Object request) {
        for (Map.Entry<Integer, BizProcessor> entry : processorMap.entrySet()) {
            entry.getValue().process(request);
        }
    }

    @Override
    public void onFinish(BizProcessEvent processEvent) {
        if (isSeqProcess) {
            execSeq++;
            executeProcessor(request);
        }
    }

    private void executeProcessor(Object request) {
        BizProcessor bizProcessor = processorMap.get(execSeq);
        if (bizProcessor != null) {
            bizProcessor.setFinishHandler(this);
            bizProcessor.process(request);
        }
    }
}
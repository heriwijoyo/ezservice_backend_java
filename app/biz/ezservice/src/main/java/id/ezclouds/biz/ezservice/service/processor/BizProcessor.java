/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessor.java, v 0.1 2024‐07‐20 3:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizProcessor {

    BizProcessEvent getProcessEvent();
    void process(Object request);
    void setFinishHandler(FinishHandler finishHandler);

    interface FinishHandler {
        void onFinish(BizProcessEvent processEvent);
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.core.process.model.BizProcessEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessor.java, v 0.1 2024‐07‐28 4:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizProcessor {

    BizProcessEvent getProcessEvent();
    void process(Object request);
    void setFinishHandler(FinishHandler finishHandler);

    interface FinishHandler {
        void onFinish(BizProcessEvent event);
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.util.thread;

import id.ezclouds.common.util.StringUtil;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizThreadSharedResource.java, v 0.1 2024‐07‐18 2:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class BizThreadSharedResource {

    private volatile boolean onProcess = false;
    private volatile String processEvent = StringUtil.EMPTY;

    public boolean isOnProcess() {
        return onProcess;
    }

    public String getProcessEvent() {
        return processEvent;
    }

    public void startProcess(String processEvent) {
        this.onProcess = true;
        this.processEvent = processEvent;
    }

    public void stopProcess() {
        this.onProcess = false;
        this.processEvent = StringUtil.EMPTY;
    }
}
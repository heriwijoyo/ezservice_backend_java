/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process;

import id.ezclouds.common.facade.process.AsyncProcessExecutor;
import id.ezclouds.common.model.process.ProcessName;
import id.ezclouds.core.process.biz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAsyncProcessExecutor.java, v 0.1 2024‐08‐19 8:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Async
@Service
@Scope("prototype")
public class CoreAsyncProcessExecutor implements AsyncProcessExecutor {

    @Autowired
    private Map<ProcessName, BizAsyncProcessor> bizAsyncProcessorMap;

    @Override
    public void execute(ProcessName processName, String param) {
        bizAsyncProcessorMap
                .get(processName)
                .process(param);
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.template;

import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreProcessTemplate.java, v 0.1 2024‐07‐28 5:03 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreProcessTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.ASYNC_PROCESS);


    public interface Handler {
        void doStart(BizProcessEvent processEvent);
        boolean doProcess(BizProcessEvent processEvent);
        void doFinish(BizProcessEvent processEvent);
        List<String> getLogData();
    }
}
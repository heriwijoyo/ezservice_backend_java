/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.core.SpringContextConfig;
import id.ezclouds.core.shared.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ProcessorFactory.java, v 0.1 2023‐12‐09 7:42 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizProcessorFactory {

    public static BizProcessor getBizProcessor(EzAppEvent ezAppEvent) {
        if (ezAppEvent instanceof ApiEvent) {
            return SpringContextConfig.getBean(ApiBizProcessor.class);
        }
        return null;
    }
}
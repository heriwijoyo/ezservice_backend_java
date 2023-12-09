/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.core.EzAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ProcessorFactory.java, v 0.1 2023‐12‐09 7:42 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Configuration
public class BizProcessorFactory {

    @Autowired
    private BizProcessor apiBizProcessor;

    public BizProcessor getBizProcessor(EzAppEvent ezAppEvent) {
        if (ezAppEvent instanceof ApiEvent) {
            return apiBizProcessor;
        }

        return null;
    }
}
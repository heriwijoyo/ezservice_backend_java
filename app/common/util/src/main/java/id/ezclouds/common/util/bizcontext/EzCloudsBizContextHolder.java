/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.bizcontext;

import id.ezclouds.common.util.eventcode.EventCodeEnum;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCloudsBizContext.java, v 0.1 2023‐06‐19 2:56 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class EzCloudsBizContextHolder {

    private static ThreadLocal<EzCloudsBizContext> contextLocal = new ThreadLocal<>();

    public static EzCloudsBizContext getContext() {
        return contextLocal.get();
    }

    public static void init(EventCodeEnum eventCodeEnum) {
        if (getContext() != null) {
            getContext().setEventCodeEnum(eventCodeEnum);
        } else {
            EzCloudsBizContext context = new EzCloudsBizContext();
            context.setEventCodeEnum(eventCodeEnum);
            contextLocal.set(context);
        }
    }
}
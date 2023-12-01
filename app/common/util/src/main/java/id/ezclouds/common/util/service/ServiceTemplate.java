/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.service;

import id.ezclouds.common.util.eventcode.EventCodeEnum;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ServiceTemplate.java, v 0.1 2023‐06‐19 1:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ServiceTemplate {

    public static void execute(EventCodeEnum eventCodeEnum, ServiceTemplateCallback callback) {

        try {
            callback.checkParameter();

            callback.process();
        } catch (Exception e) {

        } finally {

            callback.composeLog();
        }
    }
}
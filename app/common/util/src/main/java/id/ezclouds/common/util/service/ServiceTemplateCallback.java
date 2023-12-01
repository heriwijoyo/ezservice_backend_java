/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util.service;

import id.ezclouds.common.util.log.DigestLog;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ServiceTemplateCallback.java, v 0.1 2023‐06‐19 1:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public interface ServiceTemplateCallback {

    void checkParameter();

    void process();

    DigestLog composeLog();
}
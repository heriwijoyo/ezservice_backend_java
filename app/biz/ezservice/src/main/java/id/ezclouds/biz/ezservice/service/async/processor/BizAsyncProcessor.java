/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.processor;

import id.ezclouds.biz.ezservice.service.request.BizAsyncProcessRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAsyncProcessor.java, v 0.1 2024‐07‐06 2:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizAsyncProcessor {

    void process(BizAsyncProcessRequest request);
}
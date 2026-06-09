/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.model.biz.data.SmartDataSource;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SmartDataProcessor.java, v 0.1 2024‐09‐06 11:44 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface SmartDataProcessor {

    SmartDataSource syncData(SmartDataSource data);
}
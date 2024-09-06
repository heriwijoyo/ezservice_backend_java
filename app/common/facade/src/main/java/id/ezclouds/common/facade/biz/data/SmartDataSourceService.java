/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.data;

import id.ezclouds.common.model.biz.data.SmartDataSource;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SmartDataSourceService.java, v 0.1 2024‐09‐06 12:09 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface SmartDataSourceService {

    SmartDataSource syncData(SmartDataSource data);

}
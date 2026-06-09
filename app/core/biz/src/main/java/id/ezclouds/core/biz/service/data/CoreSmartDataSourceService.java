/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.biz.data.SmartDataSourceService;
import id.ezclouds.common.model.biz.data.SmartDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSmartDataSourceService.java, v 0.1 2024‐09‐06 11:39 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreSmartDataSourceService implements SmartDataSourceService {

    @Autowired
    private SmartDataProcessor smartDataProcessor;

    @Override
    public SmartDataSource syncData(SmartDataSource data) {

        return smartDataProcessor.syncData(data);
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webpage;

import id.ezclouds.common.facade.biz.data.SmartDataSourceService;
import id.ezclouds.common.model.biz.data.SmartDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPageDataSourceController.java, v 0.1 2024‐09‐05 11:47 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@RestController
public class WebPageDataSourceController {

    @Autowired
    private SmartDataSourceService smartDataSourceService;

    @PostMapping(value = "/pageDataSource")
    private SmartDataSource bindSmartDataSource(SmartDataSource data) {

        return smartDataSourceService.syncData(data);
    }
}
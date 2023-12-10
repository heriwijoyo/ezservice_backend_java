/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.data;

import id.ezclouds.biz.arahindonesia.converter.ModelConverter;
import id.ezclouds.biz.arahindonesia.model.AppConfig;
import id.ezclouds.common.dal.AppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppConfigService.java, v 0.1 2023‐12‐09 11:48 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppConfigService {

    @Autowired
    private AppConfigRepository appConfigRepository;

    @Cacheable("app_configs")
    public List<AppConfig> getAppConfigs() {
        return appConfigRepository
                .findAll()
                .stream()
                .map(ModelConverter::convert)
                .collect(Collectors.toList());
    }
}
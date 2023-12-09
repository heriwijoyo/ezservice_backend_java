/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CacheService.java, v 0.1 2023‐12‐07 2:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CacheService {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private AppClientService appClientService;

    @Autowired
    private AppConfigService appConfigService;

    @EventListener(ApplicationReadyEvent.class)
    public List<String> refreshAllCache() {
        List<String> cacheNames = new ArrayList<>();

        cacheManager.getCacheNames().stream()
                .forEach((cacheName) -> {
                    cacheNames.add(cacheName);
                    cacheManager.getCache(cacheName).clear();
                });

        organizationService.getOrganizations();
        appClientService.getAppClients();
        appConfigService.getAppConfigs();

        return cacheNames;
    }
}
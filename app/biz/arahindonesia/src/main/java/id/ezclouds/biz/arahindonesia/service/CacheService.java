/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CacheService.java, v 0.1 2023‐12‐07 2:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@Service
public class CacheService {

    @Autowired
    private CacheManager cacheManager;

    public void refreshAllCache() {
        cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.core.CoreCacheService;
import id.ezclouds.common.facade.core.CoreOrganizationService;
import id.ezclouds.common.model.core.CoreCacheKeyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreCacheService.java, v 0.1 2024‐09‐26 2:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreCacheService implements CoreCacheService {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Override
    public void initCaches() {
        for (CoreCacheKeyEnum cacheKeyEnum : CoreCacheKeyEnum.values()) {
            switch (cacheKeyEnum) {
                case ORGANIZATIONS:
                    coreOrganizationService.getOrganizations();
                    break;
            }
        }

        for (String cachedKey : cacheManager.getCacheNames()) {
            System.out.println("INIT_CACHE: "+ cachedKey);
        }
    }

    @Override
    public void invalidateCache(String cacheKey) {
        Cache cache = cacheManager.getCache(cacheKey);
        if (cache != null) {
            cache.clear();
        }
    }
}
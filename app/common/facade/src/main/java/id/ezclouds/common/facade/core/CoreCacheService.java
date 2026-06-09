/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.core;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreCacheService.java, v 0.1 2024‐09‐26 2:09 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreCacheService {

    void initCaches();

    void invalidateCache(String cacheKey);
}
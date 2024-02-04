/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.core;

import id.ezclouds.biz.arahindonesia.service.dataservice.*;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.shared.service.CoreConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCacheService.java, v 0.1 2023‐12‐07 2:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizCacheService {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private BizOrganizationService bizOrganizationService;

    @Autowired
    private AppSubOrganizationService appSubOrganizationService;

    @Autowired
    private CoreAuthService coreAuthService;

    @Autowired
    private CoreConfigService coreConfigService;

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private ImageSlideService imageSlideService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CandidateProfileItemService candidateProfileItemService;

    @Autowired
    private VideoCardService videoCardService;

    @Autowired
    private CandidateBioService candidateBioService;

    @Autowired
    private AppProfileService appProfileService;

    @EventListener(ApplicationReadyEvent.class)
    public List<String> refreshAllCache() {
        List<String> cacheNames = new ArrayList<>();

        cacheManager.getCacheNames()
                .forEach((cacheName) -> {
                    cacheNames.add(cacheName);
                    if (cacheManager.getCache(cacheName) != null) {
                        cacheManager.getCache(cacheName).clear();
                    }
                });

        bizOrganizationService.getActiveOrganizations();
        coreAuthService.getActiveAppClients();
        appSubOrganizationService.getAllSubOrganization();
        coreConfigService.getCoreConfigs();

        /*
        appConfigService.getAppConfigs();
        imageSlideService.getImageSlideHome();
        imageSlideService.getHomePosterImage();
        imageSlideService.getPortfolioImage();
        newsService.getHighlightedNews();
        candidateProfileItemService.getCandidateProfileItems();
        videoCardService.getAllVideoCards();
        candidateBioService.getActiveCandidateBios();
        appProfileService.getAllAppProfile();
         */

        cacheManager
                .getCacheNames()
                .forEach(cacheName -> {
                    System.out.println("refreshed cache: " + cacheName);
                });

        return cacheNames;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core;

import id.ezclouds.biz.ezservice.service.dataservice.*;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.service.AppSubOrganizationService;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.shared.service.CoreAdminService;
import id.ezclouds.core.shared.service.CoreConfigService;
import id.ezclouds.core.shared.service.CoreFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAppStartupService.java, v 0.1 2023‐12‐07 2:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAppStartupService {

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
    private AppImageGalleryService appImageGalleryService;

    @Autowired
    private NewsInnerService newsInnerService;

    @Autowired
    private CandidateProfileItemService candidateProfileItemService;

    @Autowired
    private VideoCardService videoCardService;

    @Autowired
    private CandidateBioService candidateBioService;

    @Autowired
    private AppProfileService appProfileService;

    @Autowired
    private CoreAdminService coreAdminService;

    @Autowired
    private CoreFileService coreFileService;

    @EventListener(ApplicationReadyEvent.class)
    public void onEzAppStartup() {
        refreshAllCache();

        bizOrganizationService
                .getActiveOrganizations()
                .forEach(org -> {
                    coreFileService.initPublicFileDirectory(org.getOrgId());
                });
    }

    public List<String> refreshAllCache() {
        List<String> cacheNames = new ArrayList<>();

        cacheManager.getCacheNames()
                .forEach((cacheName) -> {
                    cacheNames.add(cacheName);
                    if (cacheManager.getCache(cacheName) != null) {
                        cacheManager.getCache(cacheName).clear();
                    }
                });

        appImageGalleryService.getImageGalleryAllActive();
        bizOrganizationService.getActiveOrganizations();
        coreAuthService.getActiveAppClients();
        appSubOrganizationService.getAllSubOrganization();
        coreConfigService.getCoreConfigs();
        appConfigService.getAppConfigs();
        appConfigService.getMessageTemplates();
        newsInnerService.getHighlightedNews();
        candidateProfileItemService.getCandidateProfileItems();
        videoCardService.getAllVideoCards();
        candidateBioService.getActiveCandidateBios();
        appProfileService.getAllAppProfile();
        coreAdminService.getAdminBOPermissionAllActive();
        coreAdminService.getAdminBoMenuAllActive();

        cacheManager
                .getCacheNames()
                .forEach(cacheName -> {
                    System.out.println("refreshed cache: " + cacheName);
                });

        return cacheNames;
    }
}
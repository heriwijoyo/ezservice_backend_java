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
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAppCacheService.java, v 0.1 2023‐12‐07 2:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAppCacheService {

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

    @Autowired
    private AppSurveyDataService appSurveyDataService;

    public List<String> refreshAllCaches() {
        return refreshAllCaches(false);
    }

    public List<String> refreshAllCaches(boolean fromStartup) {
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
        coreConfigService.getCoreOrgConfigs();
        appConfigService.getAppConfigAllActive();
        appConfigService.getMessageTemplates();
        newsInnerService.getHighlightedNews();
        candidateProfileItemService.getCandidateProfileItems();
        candidateBioService.getActiveCandidateBios();
        videoCardService.getAllVideoCards();
        appProfileService.getAllAppProfile();
        coreAdminService.getAdminBOPermissionAllActive();
        coreAdminService.getAdminBoMenuAllActive();
        appSurveyDataService.getTopSurveyAllOrg();

        cacheManager
                .getCacheNames()
                .forEach(cacheName -> {
                    if (fromStartup) {
                        System.out.println("Refreshed Cache: " + cacheName);
                    }
                });

        return cacheNames;
    }

    public void reloadCacheItem(String cacheKey) {
        BizCacheEnum bizCacheEnum = BizCacheEnum.getByCode(cacheKey);
        if (bizCacheEnum == BizCacheEnum.UNKNOWN) {
            return;
        }

        if (cacheManager.getCache(cacheKey) != null) {
            cacheManager.getCache(cacheKey).clear();
        }

        switch (bizCacheEnum) {
            case NEWS_HIGHLIGHT:
                newsInnerService.getHighlightedNews();
                break;
            case APP_IMAGE_GALLERY_ALL:
                appImageGalleryService.getImageGalleryAllActive();
                break;
            case VIDEO_CARD_GALLERY_ALL:
                videoCardService.getAllVideoCards();
                break;
            case CANDIDATE_PROFILE:
            case CANDIDATE_BIOGRAPHY:
                candidateProfileItemService.getCandidateProfileItems();
                candidateBioService.getActiveCandidateBios();
                break;
        }
    }
}
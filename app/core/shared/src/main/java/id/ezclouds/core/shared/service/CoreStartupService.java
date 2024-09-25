/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.core.CoreCacheService;
import id.ezclouds.common.facade.core.CoreOrganizationService;
import id.ezclouds.common.facade.file.CoreFileService;
import id.ezclouds.common.model.constant.OrgConstant;
import id.ezclouds.common.model.core.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreStartupService.java, v 0.1 2024‐02‐27 11:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreStartupService {

    @Autowired
    private CoreCacheService coreCacheService;

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Autowired
    private CoreFileService coreFileService;

    @EventListener(ApplicationReadyEvent.class)
    public void onEzAppStartup() {

        // init all caches first
        coreCacheService.initCaches();

        // then init all organization public directory
        for (Organization organization : coreOrganizationService.getOrganizations()) {
            if (!OrgConstant.ORG_ID_SU.equals(organization.getOrgId())) {
                coreFileService.initPublicFileDirectory(organization.getOrgId());
            }
        }
    }

}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core;

import id.ezclouds.biz.ezservice.service.app.BizOrganizationService;
import id.ezclouds.common.facade.file.CoreFileService;
import id.ezclouds.core.shared.constant.CoreConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAppStartupService.java, v 0.1 2024‐02‐27 11:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAppStartupService {

    @Autowired
    private BizOrganizationService bizOrganizationService;

    @Autowired
    private CoreFileService coreFileService;

    @Autowired
    private BizAppCacheService bizAppCacheService;

    @EventListener(ApplicationReadyEvent.class)
    public void onEzAppStartup() {
        bizOrganizationService
                .getActiveOrganizations()
                .forEach(org -> {
                    if (!CoreConstant.SU_ORG_ID.equals(org.getOrgId())) {
                        coreFileService.initPublicFileDirectory(org.getOrgId());
                    }
                });

        bizAppCacheService.refreshAllCaches(true);
    }

}
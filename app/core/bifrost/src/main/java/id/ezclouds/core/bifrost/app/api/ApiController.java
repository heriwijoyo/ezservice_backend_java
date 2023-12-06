/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.arahindonesia.service.CacheService;
import id.ezclouds.biz.arahindonesia.service.OrganizationService;
import id.ezclouds.common.dal.OrganizationRepository;
import id.ezclouds.common.dal.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiController.java, v 0.1 2023‐12‐03 11:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private CacheService cacheService;

    @GetMapping("/test")
    public String test() {
        return "API test";
    }

    @GetMapping("/organizations")
    public List<Organization> getOrganization() {
        return organizationService.getOrganizations();
    }



    @GetMapping("refresh_cache")
    public String refreshAllCache() {
        cacheService.refreshAllCache();

        organizationService.getOrganizations();
        return "All caches refreshed";
    }
}
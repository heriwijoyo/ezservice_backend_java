/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.core.CoreCacheService;
import id.ezclouds.common.facade.core.CoreOrganizationService;
import id.ezclouds.common.facade.dal.core.CoreOrganizationDAO;
import id.ezclouds.common.model.core.CoreCacheKey;
import id.ezclouds.common.model.core.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: NewCoreOrganizationService.java, v 0.1 2024‐09‐23 1:53 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class NewCoreOrganizationService implements CoreOrganizationService {

    @Autowired
    private CoreOrganizationDAO coreOrganizationDAO;

    @Autowired
    private CoreCacheService coreCacheService;

    @Override
    public Organization getById(String orgId) {
        return getOrganizations()
                .stream()
                .filter(organization -> organization.getOrgId().equals(orgId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Organization getById(String orgId, boolean forceReload) {
        if (forceReload) {
            coreCacheService
                    .invalidateCache(CoreCacheKey.ORGANIZATIONS);
        }
        return getOrganizations()
                .stream()
                .filter(organization -> organization.getOrgId().equals(orgId))
                .findFirst()
                .orElse(null);
    }

    @Override
    @Cacheable(CoreCacheKey.ORGANIZATIONS)
    public List<Organization> getOrganizations() {
        return coreOrganizationDAO.getOrganizations();
    }
}
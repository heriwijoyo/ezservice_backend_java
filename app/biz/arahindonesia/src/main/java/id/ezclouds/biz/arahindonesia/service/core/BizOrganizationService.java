/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.core;

import id.ezclouds.core.shared.model.CoreOrganization;
import id.ezclouds.core.shared.service.CoreOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizOrganizationService.java, v 0.1 2024‐01‐28 6:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizOrganizationService {

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Cacheable("organizations")
    public List<CoreOrganization> getActiveOrganizations() {
        return coreOrganizationService.getActiveOrganizations();
    }
}
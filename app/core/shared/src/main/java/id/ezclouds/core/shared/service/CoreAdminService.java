/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.shared.converter.CoreModelConverter;
import id.ezclouds.core.shared.model.CoreAdminBOMenu;
import id.ezclouds.core.shared.model.CoreAdminBOPermission;
import id.ezclouds.core.shared.repo.EzCoreAdminBOMenuRepository;
import id.ezclouds.core.shared.repo.EzCoreAdminBOPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminService.java, v 0.1 2024‐02‐11 9:56 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAdminService {

    @Autowired
    private EzCoreAdminBOPermissionRepository ezCoreAdminBOPermissionRepository;

    @Autowired
    private EzCoreAdminBOMenuRepository ezCoreAdminBOMenuRepository;


    public List<CoreAdminBOPermission> getPermissionByRoles(String orgId, List<String> roles) {
        if (StringUtil.isBlank(orgId) || roles == null || roles.isEmpty()) {
            return new ArrayList<>();
        }

        return getAdminBOPermissionAllActive()
                .stream()
                .filter(permission -> orgId.equals(permission.getOrgId()) && roles.contains(permission.getRoleName()))
                .collect(Collectors.toList());
    }

    public List<CoreAdminBOMenu> getBOMenuByPermission(String orgId, List<String> permissions) {
        if (StringUtil.isBlank(orgId) || permissions == null || permissions.isEmpty()) {
            return new ArrayList<>();
        }

        return getAdminBoMenuAllActive()
                .stream()
                .filter(menu -> orgId.equals(menu.getOrgId()) && permissions.contains(menu.getPermissionMain()))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "coreAdminBOPermission")
    public List<CoreAdminBOPermission> getAdminBOPermissionAllActive() {
        return ezCoreAdminBOPermissionRepository
                .findAllActive()
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "coreAdminBOMenu")
    public List<CoreAdminBOMenu> getAdminBoMenuAllActive() {
        return ezCoreAdminBOMenuRepository
                .findAllActive()
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }
}
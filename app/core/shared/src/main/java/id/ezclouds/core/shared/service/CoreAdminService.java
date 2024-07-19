/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.shared.converter.CoreModelConverter;
import id.ezclouds.core.shared.model.BoMenuComparator;
import id.ezclouds.core.shared.model.CoreAdminBOMenu;
import id.ezclouds.core.shared.model.CoreAdminBOPermission;
import id.ezclouds.core.shared.model.CoreAdminDashboard;
import id.ezclouds.core.shared.repo.EzCoreAdminBOMenuRepository;
import id.ezclouds.core.shared.repo.EzCoreAdminBOPermissionRepository;
import id.ezclouds.core.shared.repo.EzCoreAdminDashboardRepository;
import id.ezclouds.core.shared.repo.dataobject.EzCoreAdminBOMenuDO;
import id.ezclouds.core.shared.repo.dataobject.EzCoreAdminBOPermissionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private EzCoreAdminDashboardRepository ezCoreAdminDashboardRepository;

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
                .sorted(new BoMenuComparator())
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

    public List<CoreAdminDashboard> getAdminDashboardAllActive(String orgId) {
        return ezCoreAdminDashboardRepository
                .findByOrgIdActive(orgId)
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAllBOMenuAndPermission(String ordId) {
        List<EzCoreAdminBOMenuDO> menus = ezCoreAdminBOMenuRepository
                .findByOrgId(ordId);
        for (EzCoreAdminBOMenuDO menu : menus) {
            ezCoreAdminBOMenuRepository
                    .delete(menu);
        }
        ezCoreAdminBOMenuRepository.flush();

        List<EzCoreAdminBOPermissionDO> permissions = ezCoreAdminBOPermissionRepository
                .findByOrgId(ordId);
        for (EzCoreAdminBOPermissionDO permission : permissions) {
            ezCoreAdminBOPermissionRepository
                    .delete(permission);
        }
        ezCoreAdminBOPermissionRepository.flush();
    }

    @Transactional
    public void initiateBOMenuAndPermission(String orgId) {
        String initRole = "ADMIN_ORG";
        List<String> initMenus = Arrays.asList(
                "APP_IMAGE_GALLERY,Manage Gallery,appgallery.htm,image",
                "APP_NEWS,Manage News,news.htm,library_books",
                "APP_EVENTS,Manage Events,events.htm,calendar_month",
                "APP_VIDEO_CARD,Manage Video Card,videocard.htm,smart_display",
                "APP_PROFILE,Candidate Profile,profile.htm,assignment_ind",
                "APP_DOCUMENTS,Documents,documents.htm,picture_as_pdf",
                "DATA_UPLOAD,Data Upload,dataUpload.htm,upload_file",
                "WHATSAPP_LOG,Whatsapp Logs,whatsapp.htm,sms"
        );
        for (int i = 0; i < initMenus.size(); i++) {
            String[] menuSection = initMenus.get(i).split(",");
            EzCoreAdminBOMenuDO boMenuDO = new EzCoreAdminBOMenuDO();
            boMenuDO.setId(HashUtil.createHash(orgId, menuSection[0]));
            boMenuDO.setOrgId(orgId);
            boMenuDO.setPermissionMain(menuSection[0]);
            boMenuDO.setMenuName(menuSection[1]);
            boMenuDO.setMenuUrl(menuSection[2]);
            boMenuDO.setMenuIcon(menuSection[3]);
            boMenuDO.setSorting(i);
            boMenuDO.setStatus(1);
            ezCoreAdminBOMenuRepository.saveAndFlush(boMenuDO);
        }

        List<String> initPermission = Arrays.asList(
                "APP_IMAGE_GALLERY",
                "APP_NEWS",
                "APP_EVENTS",
                "APP_VIDEO_CARD",
                "APP_PROFILE",
                "APP_DOCUMENTS",
                "DATA_UPLOAD",
                "WHATSAPP_LOG"
        );
        for (String permission : initPermission) {
            EzCoreAdminBOPermissionDO permissionDO = new EzCoreAdminBOPermissionDO();
            permissionDO.setId(HashUtil.createHash(orgId, permission));
            permissionDO.setOrgId(orgId);
            permissionDO.setRoleName(initRole);
            permissionDO.setPermissionMain(permission);
            permissionDO.setPermissionSub(permission + "-ALL");
            permissionDO.setStatus(1);
            ezCoreAdminBOPermissionRepository.saveAndFlush(permissionDO);
        }
    }
}
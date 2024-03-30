/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.converter;

import id.ezclouds.core.shared.enums.CoreAreaLevel;
import id.ezclouds.core.shared.model.*;
import id.ezclouds.core.shared.repo.dataobject.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreModelConverter.java, v 0.1 2024‐01‐28 6:23 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreModelConverter {

    public static CoreOrganization convert(EzCoreOrganizationDO organizationDO) {
        if (organizationDO == null) { return null; }
        CoreOrganization coreOrganization = new CoreOrganization();
        coreOrganization.setOrgId(organizationDO.getOrgId());
        coreOrganization.setName(organizationDO.getName());
        coreOrganization.setCode(organizationDO.getCode());
        return coreOrganization;
    }

    public static CoreConfig convert(EzCoreConfigDO configDO) {
        if (configDO == null) { return null; }
        CoreConfig coreConfig = new CoreConfig();
        coreConfig.setConfigId(configDO.getConfigId());
        coreConfig.setConfigKey(configDO.getConfigKey());
        coreConfig.setConfigValue(configDO.getConfigValue());
        return coreConfig;
    }

    public static CoreOrgConfig convert(EzCoreOrgConfigDO coreConfigDO) {
        if (coreConfigDO == null) { return null; }
        CoreOrgConfig coreOrgConfig = new CoreOrgConfig();
        coreOrgConfig.setConfigId(coreConfigDO.getConfigId());
        coreOrgConfig.setOrgId(coreConfigDO.getOrgId());
        coreOrgConfig.setConfigKey(coreConfigDO.getConfigKey());
        coreOrgConfig.setConfigValue(coreConfigDO.getConfigValue());
        return coreOrgConfig;
    }

    public static CoreAdminBOMenu convert(EzCoreAdminBOMenuDO menuDO) {
        if (menuDO == null) { return null; }
        CoreAdminBOMenu menu = new CoreAdminBOMenu();
        menu.setOrgId(menuDO.getOrgId());
        menu.setPermissionMain(menuDO.getPermissionMain());
        menu.setMenuName(menuDO.getMenuName());
        menu.setMenuUrl(menuDO.getMenuUrl());
        menu.setMenuIcon(menuDO.getMenuIcon());
        menu.setSorting(menuDO.getSorting());
        return menu;
    }

    public static CoreAdminBOPermission convert(EzCoreAdminBOPermissionDO permissionDO) {
        if (permissionDO == null) { return null; }
        CoreAdminBOPermission permission = new CoreAdminBOPermission();
        permission.setOrgId(permissionDO.getOrgId());
        permission.setRoleName(permissionDO.getRoleName());
        permission.setPermissionMain(permissionDO.getPermissionMain());
        permission.setPermissionSub(permissionDO.getPermissionSub());
        return permission;
    }

    public static CoreAdminDashboard convert(EzCoreAdminDashboardDO dashboardDO) {
        if (dashboardDO == null) { return null; }
        CoreAdminDashboard dashboard = new CoreAdminDashboard();
        dashboard.setOrgId(dashboardDO.getOrgId());
        dashboard.setKeyName(dashboardDO.getKeyName());
        dashboard.setDisplayName(dashboardDO.getDisplayName());
        dashboard.setIcon(dashboardDO.getIcon());
        dashboard.setUrl(dashboardDO.getUrl());
        dashboard.setCountValue(dashboardDO.getCountValue());
        dashboard.setCountLabel(dashboardDO.getCountLabel());
        dashboard.setLastUpdate(dashboardDO.getLastUpdate());
        dashboard.setSorting(dashboardDO.getSorting());
        return dashboard;
    }

    public static CoreArea convert(EzCoreAppProvinceDO provinceDO) {
        if (provinceDO == null) { return null; }
        CoreArea coreArea = new CoreArea();
        coreArea.setId(provinceDO.getId());
        coreArea.setName(provinceDO.getName());
        coreArea.setAreaLevel(CoreAreaLevel.PROVINCE);
        return coreArea;
    }

    public static CoreArea convert(EzCoreAppRegencyDO regencyDO) {
        if (regencyDO == null) { return null; }
        CoreArea coreArea = new CoreArea();
        coreArea.setId(regencyDO.getId());
        coreArea.setName(regencyDO.getName());
        coreArea.setAreaLevel(CoreAreaLevel.REGENCY);
        return coreArea;
    }

    public static CoreArea convert(EzCoreAppDistrictDO districtDO) {
        if (districtDO == null) { return null; }
        CoreArea coreArea = new CoreArea();
        coreArea.setId(districtDO.getId());
        coreArea.setName(districtDO.getName());
        coreArea.setAreaLevel(CoreAreaLevel.DISTRICT);
        return coreArea;
    }

    public static CoreArea convert(EzCoreAppVillageDO villageDO) {
        if (villageDO == null) { return null; }
        CoreArea coreArea = new CoreArea();
        coreArea.setId(villageDO.getId());
        coreArea.setName(villageDO.getName());
        coreArea.setAreaLevel(CoreAreaLevel.VILLAGE);
        return coreArea;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.converter;

import id.ezclouds.common.model.admin.CoreAdminBOPermission;
import id.ezclouds.common.model.core.CoreOrganization;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.core.shared.model.*;
import id.ezclouds.core.shared.repo.dataobject.*;

import java.util.HashMap;
import java.util.Map;

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
        if (StringUtil.isNotBlank(organizationDO.getExtendConfig())) {
            Map<String, String> extendConfigMap = new HashMap<>();
            String[] extendConfigs = organizationDO.getExtendConfig().split(",");
            for (String extendConfig : extendConfigs) {
                String[] configs = extendConfig.split("=");
                extendConfigMap.put(configs[0], configs[1]);
            }
            coreOrganization.setExtendInfo(extendConfigMap);
        }
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

    public static LegacyCoreArea convert(EzCoreAppProvinceDO provinceDO) {
        if (provinceDO == null) { return null; }
        LegacyCoreArea legacyCoreArea = new LegacyCoreArea();
        legacyCoreArea.setId(provinceDO.getId());
        legacyCoreArea.setName(provinceDO.getName());
        legacyCoreArea.setAreaLevel(CoreAreaLevel.PROVINCE);
        return legacyCoreArea;
    }

    public static LegacyCoreArea convert(EzCoreAppRegencyDO regencyDO) {
        if (regencyDO == null) { return null; }
        LegacyCoreArea legacyCoreArea = new LegacyCoreArea();
        legacyCoreArea.setId(regencyDO.getId());
        legacyCoreArea.setName(regencyDO.getName());
        legacyCoreArea.setAreaLevel(CoreAreaLevel.REGENCY);
        return legacyCoreArea;
    }

    public static LegacyCoreArea convert(EzCoreAppDistrictDO districtDO) {
        if (districtDO == null) { return null; }
        LegacyCoreArea legacyCoreArea = new LegacyCoreArea();
        legacyCoreArea.setId(districtDO.getId());
        legacyCoreArea.setName(districtDO.getName());
        legacyCoreArea.setAreaLevel(CoreAreaLevel.DISTRICT);
        return legacyCoreArea;
    }

    public static LegacyCoreArea convert(EzCoreAppVillageDO villageDO) {
        if (villageDO == null) { return null; }
        LegacyCoreArea legacyCoreArea = new LegacyCoreArea();
        legacyCoreArea.setId(villageDO.getId());
        legacyCoreArea.setName(villageDO.getName());
        legacyCoreArea.setAreaLevel(CoreAreaLevel.VILLAGE);
        return legacyCoreArea;
    }
}
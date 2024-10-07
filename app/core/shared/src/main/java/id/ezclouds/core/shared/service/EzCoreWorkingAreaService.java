/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.area.CoreAreaService;
import id.ezclouds.common.facade.area.CoreWorkingAreaService;
import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.area.CoreAreaRecursive;
import id.ezclouds.common.model.config.CoreOrgConfigType;
import id.ezclouds.common.model.util.CoreAreaUtil;
import id.ezclouds.common.facade.area.CoreAreaScanListener;
import id.ezclouds.common.model.area.AreaInitConfig;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreWorkingAreaService.java, v 0.1 2024‐09‐16 7:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope(value = "prototype")
public class EzCoreWorkingAreaService implements CoreWorkingAreaService {

    @Autowired
    private CoreConfigService coreConfigService;

    @Autowired
    private CoreAreaService coreAreaService;

    private CoreAreaScanListener coreAreaScanListener;

    private Map<String, CoreArea> parentsMap = new HashMap<>();

    @Override
    public void scanWorkingAreaRecursive(String orgId, CoreAreaLevel targetLevel, CoreAreaScanListener listener) {
        coreAreaScanListener = listener;
        AreaInitConfig areaInitConfig = getAreaInitConfig(orgId, targetLevel);
        for (CoreArea rootArea : areaInitConfig.getRootAreas()) {
            recursiveLoadAndExecute(rootArea, areaInitConfig.getTargetLevel());
        }
    }

    @Override
    public List<CoreArea> fetchCoreAreas(String orgId, CoreAreaLevel targetLevel) {
        final List<CoreArea> coreAreas = new ArrayList<>();
        scanWorkingAreaRecursive(orgId, targetLevel, areaRecursive -> coreAreas.add(areaRecursive.getCurrentArea()));
        return coreAreas;
    }

    @Override
    public Map<String, CoreArea> allParentMap() {
        return parentsMap;
    }

    private AreaInitConfig getAreaInitConfig(String orgId, CoreAreaLevel targetLevel) {
        String areaRootLevel = coreConfigService
                .getOrgConfig(orgId, CoreOrgConfigType.CORE_AREA_ROOT_LEVEL)
                .getConfigValue();
        CoreAreaLevel rootLevel = CoreAreaLevel.getByCode(areaRootLevel);

        String areaRootIds = coreConfigService
                .getOrgConfig(orgId, CoreOrgConfigType.CORE_AREA_ROOT_IDS)
                .getConfigValue();
        List<String> rootIds = Arrays.asList(areaRootIds.split(","));

        String areaRootNames = coreConfigService
                .getOrgConfig(orgId, CoreOrgConfigType.CORE_AREA_ROOT_NAMES)
                .getConfigValue();
        List<String> rootNames = Arrays.asList(areaRootNames.split(","));

        AreaInitConfig initConfig = new AreaInitConfig();
        initConfig.setTargetLevel(targetLevel);

        for (int i = 0; i < rootIds.size(); i++) {
            CoreArea rootArea = CoreAreaUtil
                    .buildCoreArea(rootLevel, rootIds.get(i), rootNames.get(i));
            initConfig
                    .getRootAreas()
                    .add(rootArea);
        }
        return initConfig;
    }

    private void recursiveLoadAndExecute(CoreArea currentArea, CoreAreaLevel targetLevel) {
        if (currentArea.getAreaLevel() == targetLevel) {
            if (coreAreaScanListener != null) {
                CoreAreaRecursive recursiveCurrent = new CoreAreaRecursive(currentArea);
                recursiveLoadParents(recursiveCurrent);

                coreAreaScanListener.areaOnTargetLevel(recursiveCurrent);
            }
        } else {
            parentsMap.putIfAbsent(currentArea.getAreaId(), currentArea);

            List<CoreArea> childs = coreAreaService.getChildArea(currentArea);
            for (CoreArea childArea : childs) {
                recursiveLoadAndExecute(childArea, targetLevel);
            }
        }
    }

    private void recursiveLoadParents(CoreAreaRecursive recursiveCurrent) {
        CoreArea parentArea = fetchParentArea(recursiveCurrent.getCurrentArea().getParentId());
        if (parentArea != null) {
            CoreAreaRecursive recursiveParent = new CoreAreaRecursive(parentArea);
            recursiveCurrent.setParentAreaRecursive(recursiveParent);

            recursiveLoadParents(recursiveParent);
        }
    }

    private CoreArea fetchParentArea(String coreAreaId) {
        if (StringUtil.isBlank(coreAreaId)) {
            return null;
        }
        return parentsMap.get(coreAreaId);
    }
}
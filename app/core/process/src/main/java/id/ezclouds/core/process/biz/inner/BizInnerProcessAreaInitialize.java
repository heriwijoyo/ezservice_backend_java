/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz.inner;

import id.ezclouds.common.facade.area.CoreAreaService;
import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.config.CoreOrgConfigType;
import id.ezclouds.common.model.util.CoreAreaUtil;
import id.ezclouds.common.facade.area.CoreAreaScanListener;
import id.ezclouds.common.model.area.AreaInitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizInnerProcessAreaInitialize.java, v 0.1 2024‐09‐16 7:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope(value = "prototype")
public class BizInnerProcessAreaInitialize {

    @Autowired
    private CoreConfigService coreConfigService;

    @Autowired
    private CoreAreaService coreAreaService;

    private CoreAreaScanListener coreAreaScanListener;

    public AreaInitConfig getAreaInitConfig(String orgId, String targetLevel) {
        CoreAreaLevel targetAreaLevel = CoreAreaLevel.getByCode(targetLevel);
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
        initConfig.setTargetLevel(targetAreaLevel);

        for (int i = 0; i < rootIds.size(); i++) {
            CoreArea rootArea = CoreAreaUtil
                    .buildCoreArea(rootLevel, rootIds.get(i), rootNames.get(i));
            initConfig
                    .getRootAreas()
                    .add(rootArea);
        }
        return initConfig;
    }

    public void setCoreAreaScanListener(CoreAreaScanListener coreAreaScanListener) {
        this.coreAreaScanListener = coreAreaScanListener;
    }

    public void startInitArea(AreaInitConfig initConfig) {
        for (CoreArea rootArea : initConfig.getRootAreas()) {
            recursiveLoadAndExecute(rootArea, initConfig.getTargetLevel());
        }
    }

    public void recursiveLoadAndExecute(CoreArea currentArea, CoreAreaLevel targetLevel) {
        if (currentArea.getAreaLevel() == targetLevel) {
            if (coreAreaScanListener != null) {
                coreAreaScanListener.areaOnTargetLevel(currentArea);
            }
        } else {
            List<CoreArea> childs = coreAreaService.getChildArea(currentArea);
            for (CoreArea childArea : childs) {
                recursiveLoadAndExecute(childArea, targetLevel);
            }
        }
    }
}
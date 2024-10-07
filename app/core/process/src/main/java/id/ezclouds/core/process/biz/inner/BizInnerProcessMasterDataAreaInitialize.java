/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz.inner;

import id.ezclouds.common.facade.dal.biz.BizMasterDataDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.CoreAreaRecursive;
import id.ezclouds.common.model.biz.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizInnerProcessMasterDataAreaInitialize.java, v 0.1 2024‐09‐04 8:07 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizInnerProcessMasterDataAreaInitialize {

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Autowired
    private BizMasterDataDAO bizMasterDataDAO;

    @Transactional
    public void initOverall(String orgId) {
        for (BizMasterDataOverall masterOverall : BizMasterDataOverall.values()) {
            OverallMasterData overallMasterData = new OverallMasterData();
            overallMasterData.setOverallKey(masterOverall.getCode());
            overallMasterData.setOverallName(masterOverall.getDescription());
            overallMasterData.setValueCount(0);

            BizMasterData masterData = new BizMasterData();
            masterData.setOrgId(orgId);
            masterData.setScene(BizMasterDataScene.OVERALL_STATIC.getCode());
            bizObjectMapperService.parseFromSource(masterData, overallMasterData);

            bizMasterDataDAO.storeOrUpdate(masterData);
        }
    }

    @Transactional
    public void init(String orgId, String scene, CoreAreaRecursive areaRecursive) {
        BizMasterData bizMasterData = buildMasterData(orgId, scene, areaRecursive);
        bizMasterDataDAO.storeOrUpdate(bizMasterData);
    }

    private BizMasterData buildMasterData(String orgId, String scene, CoreAreaRecursive recursiveCurrent) {
        BizMasterData masterData = new BizMasterData();
        masterData.setOrgId(orgId);
        masterData.setScene(scene);

        switch (recursiveCurrent.getCurrentArea().getAreaLevel()) {
            case VILLAGE:
                VillageMasterData villageMasterData = new VillageMasterData();
                recursiveUpdateVillage(villageMasterData, recursiveCurrent);
                bizObjectMapperService.parseFromSource(masterData, villageMasterData);
                break;
        }

        return masterData;
    }

    private void recursiveUpdateVillage(VillageMasterData villageMasterData, CoreAreaRecursive areaRecursive) {
        CoreArea currentArea = areaRecursive.getCurrentArea();
        String areaId = currentArea.getAreaId();
        switch (currentArea.getAreaLevel()) {
            case VILLAGE:
                villageMasterData.setVillageId(areaId);
                villageMasterData.setVillageName(currentArea.getName());
                break;
            case DISTRICT:
                villageMasterData.setDistrictId(areaId);
                break;
            case REGENCY:
                villageMasterData.setRegencyId(areaId);
                break;
            case PROVINCE:
                villageMasterData.setProvinceId(areaId);
                break;
        }

        CoreAreaRecursive recursiveParent = areaRecursive.getParentAreaRecursive();
        if (recursiveParent != null) {
            recursiveUpdateVillage(villageMasterData, recursiveParent);
        }
    }
}
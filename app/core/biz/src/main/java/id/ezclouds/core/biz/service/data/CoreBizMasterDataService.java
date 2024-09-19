/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.biz.data.BizMasterDataService;
import id.ezclouds.common.facade.dal.biz.BizMasterDataDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.biz.data.BizMasterData;
import id.ezclouds.common.model.biz.data.BizMasterDataQueryParam;
import id.ezclouds.common.model.biz.data.BizMasterDataScene;
import id.ezclouds.common.model.biz.data.VillageMasterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizMasterDataService.java, v 0.1 2024‐09‐19 12:32 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBizMasterDataService implements BizMasterDataService {

    @Autowired
    private BizMasterDataDAO bizMasterDataDAO;

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Override
    public List<VillageMasterData> getVillageMasterData(String orgId, String districtId) {
        List<VillageMasterData> villageMasterData = new ArrayList<>();

        BizMasterDataQueryParam queryParam = new BizMasterDataQueryParam();
        queryParam.setOrgId(orgId);
        queryParam.setDistrictId(districtId);
        queryParam.setScene(BizMasterDataScene.AREA_VILLAGE_STATIC);

        List<BizMasterData> masterDataList = bizMasterDataDAO.getBizMasterData(queryParam);
        for (BizMasterData masterData : masterDataList) {
            VillageMasterData vMasterData = new VillageMasterData();
            vMasterData.setBizMasterId(masterData.getBizMasterId());
            bizObjectMapperService.parseFromSource(vMasterData, masterData);

            villageMasterData.add(vMasterData);
        }

        return villageMasterData;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.biz.data.BizMasterDataService;
import id.ezclouds.common.model.biz.data.BizMasterDataScene;
import id.ezclouds.common.model.biz.data.VillageMasterData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizMasterDataService.java, v 0.1 2024‐09‐19 12:32 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBizMasterDataService implements BizMasterDataService {

    @Override
    public List<VillageMasterData> getVillageMasterData(BizMasterDataScene scene, String orgId, String districtId) {
        return null;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.data;

import id.ezclouds.common.model.biz.data.OverallMasterData;
import id.ezclouds.common.model.biz.data.VillageMasterData;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMasterDataService.java, v 0.1 2024‐09‐19 3:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizMasterDataService {

    List<OverallMasterData> getOverallMasterData(String orgId);

    List<VillageMasterData> getVillageMasterData(String orgId, String districtId);

    VillageMasterData getVillageMasterDataById(String orgId, String villageId);

    OverallMasterData updateOverallMasterData(OverallMasterData masterData);

    void updateVillageMasterData(VillageMasterData masterData);
}
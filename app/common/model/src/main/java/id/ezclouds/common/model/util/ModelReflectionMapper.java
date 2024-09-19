/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ModelReflectionMapper.java, v 0.1 2024‐09‐04 7:33 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ModelReflectionMapper {

    private static final Map<String, Map<String, String>> PARSER_MAP_ALL;

    static {
        PARSER_MAP_ALL = new HashMap<>();

        Map<String, String> villageMasterDataStoreMap = new HashMap<>();
        villageMasterDataStoreMap.put("dataId", "villageId");
        villageMasterDataStoreMap.put("dataName", "villageName");
        villageMasterDataStoreMap.put("districtId", "districtId");
        villageMasterDataStoreMap.put("numberValue1", "voterMale");
        villageMasterDataStoreMap.put("numberValue2", "voterFemale");
        villageMasterDataStoreMap.put("numberValue3", "voterTotal");
        villageMasterDataStoreMap.put("numberValue4", "pollStationTotal");
        villageMasterDataStoreMap.put("numberValue5", "INT_0");
        PARSER_MAP_ALL.put("BizMasterData_FROM_VillageMasterData", villageMasterDataStoreMap);

        Map<String, String> villageMasterDataQueryMap = new HashMap<>();
        villageMasterDataQueryMap.put("villageId", "dataId");
        villageMasterDataQueryMap.put("villageName", "dataName");
        villageMasterDataQueryMap.put("districtId", "districtId");
        villageMasterDataQueryMap.put("voterMale", "numberValue1");
        villageMasterDataQueryMap.put("voterFemale", "numberValue2");
        villageMasterDataQueryMap.put("voterTotal", "numberValue3");
        villageMasterDataQueryMap.put("pollStationTotal", "numberValue4");
        PARSER_MAP_ALL.put("VillageMasterData_FROM_BizMasterData", villageMasterDataQueryMap);
    }

    public static Map<String, String> getParserMap(Object source, Object output) {
        String sourceName = source.getClass().getSimpleName();
        String outputName = output.getClass().getSimpleName();
        String mappingKey = outputName +"_FROM_"+ sourceName;

        return PARSER_MAP_ALL.get(mappingKey);
    }
}
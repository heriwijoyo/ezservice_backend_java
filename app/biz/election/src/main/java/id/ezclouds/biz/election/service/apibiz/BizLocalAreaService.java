/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.apibiz;

import id.ezclouds.biz.election.service.request.BizLocalAreaRequest;
import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.model.config.CoreOrgConfigType;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.util.CollectionUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.core.shared.model.LegacyCoreArea;
import id.ezclouds.core.shared.service.LegacyCoreAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizLocalAreaService.java, v 0.1 2024‐02‐18 7:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizLocalAreaService extends BizBaseService {

    @Autowired
    private CoreConfigService coreConfigService;

    @Autowired
    private LegacyCoreAreaService legacyCoreAreaService;

    public BizResult getLocalArea(BizLocalAreaRequest request) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAreaLevel coreAreaLevel = CoreAreaLevel.getByCode(request.getAreaLevel());
                List<String> areaIds = request.getAreaIds();
                List<String> parentIds = request.getParentIds();
                if (coreAreaLevel == null) {
                    String areaLevel = coreConfigService
                            .getOrgConfig(getOrgId(), CoreOrgConfigType.CORE_AREA_ROOT_LEVEL)
                            .getConfigValue();
                    coreAreaLevel = CoreAreaLevel.getByCode(areaLevel);

                    String rootIds = coreConfigService
                            .getOrgConfig(getOrgId(), CoreOrgConfigType.CORE_AREA_ROOT_IDS)
                            .getConfigValue();
                    areaIds = Arrays.asList(rootIds.split(","));
                }

                List<LegacyCoreArea> legacyCoreAreas = new ArrayList<>();
                switch (coreAreaLevel) {
                    case PROVINCE:
                        if (CollectionUtil.isNotEmpty(areaIds)) {
                            legacyCoreAreas = legacyCoreAreaService.getProvinceByIds(areaIds);
                        } else {
                            legacyCoreAreas = legacyCoreAreaService.getAllProvince();
                        }
                        break;

                    case REGENCY:
                        if (CollectionUtil.isNotEmpty(areaIds)) {
                            legacyCoreAreas = legacyCoreAreaService.getRegencyByIds(areaIds);
                        }
                        else if (CollectionUtil.isNotEmpty(parentIds)) {
                            legacyCoreAreas = legacyCoreAreaService.getRegencyByProvinceIds(parentIds);
                        }
                        break;

                    case DISTRICT:
                        if (CollectionUtil.isNotEmpty(areaIds)) {
                            legacyCoreAreas = legacyCoreAreaService.getDistrictByIds(areaIds);
                        }
                        else if (CollectionUtil.isNotEmpty(parentIds)) {
                            legacyCoreAreas = legacyCoreAreaService.getDistrictByRegencyIds(parentIds);
                        }
                        break;

                    case VILLAGE:
                        if (CollectionUtil.isNotEmpty(areaIds)) {
                            legacyCoreAreas = legacyCoreAreaService.getVillageByIds(areaIds);
                        }
                        else if (CollectionUtil.isNotEmpty(parentIds)) {
                            legacyCoreAreas = legacyCoreAreaService.getVillageByDistrictIds(parentIds);
                        }
                        break;
                }

                bizResult.setSuccess(true);
                bizResult.setObject(legacyCoreAreas);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }
}
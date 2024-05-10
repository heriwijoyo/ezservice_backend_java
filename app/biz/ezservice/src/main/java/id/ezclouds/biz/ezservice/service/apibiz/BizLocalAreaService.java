/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.service.request.BizLocalAreaRequest;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.CollectionUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.shared.enums.CoreAreaLevel;
import id.ezclouds.core.shared.model.CoreArea;
import id.ezclouds.core.shared.service.CoreAreaService;
import id.ezclouds.core.shared.service.CoreConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private CoreAreaService coreAreaService;

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
                    coreAreaLevel = CoreAreaLevel.getByCode(coreConfigService.getCoreAreaLevelRoot(getOrgId()));
                    areaIds = coreConfigService.getCoreAreaRootIds(getOrgId());
                }

                List<CoreArea> coreAreas = new ArrayList<>();
                switch (coreAreaLevel) {
                    case PROVINCE:
                        if (CollectionUtil.isNotEmpty(areaIds)) {
                            coreAreas = coreAreaService.getProvinceByIds(areaIds);
                        } else {
                            coreAreas = coreAreaService.getAllProvince();
                        }
                        break;

                    case REGENCY:
                        if (CollectionUtil.isNotEmpty(areaIds)) {
                            coreAreas = coreAreaService.getRegencyByIds(areaIds);
                        }
                        else if (CollectionUtil.isNotEmpty(parentIds)) {
                            coreAreas = coreAreaService.getRegencyByProvinceIds(parentIds);
                        }
                        break;

                    case DISTRICT:
                        if (CollectionUtil.isNotEmpty(areaIds)) {
                            coreAreas = coreAreaService.getDistrictByIds(areaIds);
                        }
                        else if (CollectionUtil.isNotEmpty(parentIds)) {
                            coreAreas = coreAreaService.getDistrictByRegencyIds(parentIds);
                        }
                        break;

                    case VILLAGE:
                        if (CollectionUtil.isNotEmpty(areaIds)) {
                            coreAreas = coreAreaService.getVillageByIds(areaIds);
                        }
                        else if (CollectionUtil.isNotEmpty(parentIds)) {
                            coreAreas = coreAreaService.getVillageByDistrictIds(parentIds);
                        }
                        break;
                }

                bizResult.setSuccess(true);
                bizResult.setObject(coreAreas);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }
}
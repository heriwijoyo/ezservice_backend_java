/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.area.CoreWorkingAreaService;
import id.ezclouds.common.facade.biz.report.BizReportAccumulateAreaService;
import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.facade.dal.biz.report.BizAccumulateAreaExtDAO;
import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateAreaDAO;
import id.ezclouds.common.model.area.AreaInitConfig;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.report.BizAccumulateKey;
import id.ezclouds.common.model.biz.report.BizReportAccumulateArea;
import id.ezclouds.common.model.biz.report.BizReportArea;
import id.ezclouds.common.model.biz.report.BizReportAreaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportAccumulateAreaService.java, v 0.1 2024‐10‐14 12:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreReportAccumulateAreaService implements BizReportAccumulateAreaService {

    @Autowired
    private CoreWorkingAreaService coreWorkingAreaService;

    @Autowired
    private CoreConfigService coreConfigService;

    @Autowired
    private BizReportAccumulateAreaDAO bizReportAccumulateAreaDAO;

    @Autowired
    private BizAccumulateAreaExtDAO bizAccumulateAreaExtDAO;

    @Override
    public List<BizReportAccumulateArea> getAccumulateAreaByParentId(String orgId, CoreAreaLevel areaLevel, String parentId) {
        return bizReportAccumulateAreaDAO.getByParentId(orgId, areaLevel, parentId);
    }

    @Override
    public BizReportArea getReportArea(String orgId) {
        AreaInitConfig areaInitConfig = coreWorkingAreaService.getAreaInitConfig(orgId);
        return getReportArea(orgId, areaInitConfig.getRootAreas().get(0));
    }

    @Override
    public BizReportArea getReportArea(String orgId, CoreArea coreArea) {
        CoreAreaLevel childLevel = getChildLevel(coreArea.getAreaLevel());
        if (childLevel == null) {
            return null;
        }

        List<BizReportAccumulateArea> accumulateAreas = bizReportAccumulateAreaDAO
                .getByParentId(orgId, childLevel, coreArea.getAreaId());

        return composeBizReportArea(accumulateAreas, childLevel);
    }

    @Override
    public BizReportArea getReportAreaPollStation(String orgId, String villageId) {
        BizReportArea reportArea = new BizReportArea();
        reportArea.setAreaLevel("POLL_STATION");

        BizReportAccumulateArea accumulateArea = bizReportAccumulateAreaDAO
                .getPollStationReportArea(orgId, villageId);
        if (accumulateArea == null) {
            reportArea.setAreaData(new ArrayList<>());
            return reportArea;
        }

        reportArea.setAreaData(
                bizAccumulateAreaExtDAO
                        .getVillageAreaExt(accumulateArea.getAccumulateAreaId(), BizAccumulateKey.POLL_STATION_ID.getCode())
                        .stream()
                        .map(accumulateAreaExt -> {
                            BizReportAreaData areaData = new BizReportAreaData();
                            areaData.setDataName(accumulateAreaExt.getAccumulateVariable());
                            areaData.setVoterCount(accumulateAreaExt.getAccumulateCount());
                            return areaData;
                        })
                        .collect(Collectors.toList())
        );
        return reportArea;
    }

    private BizReportArea composeBizReportArea(List<BizReportAccumulateArea> accumulateAreas, CoreAreaLevel areaLevel) {
        BizReportArea bizReportArea = new BizReportArea();
        bizReportArea.setAreaLevel(areaLevel.getCode());
        bizReportArea.setAreaData(
                accumulateAreas
                        .stream()
                        .map(accumulateArea -> {
                            BizReportAreaData areaData = new BizReportAreaData();
                            areaData.setDataId(getAreaId(accumulateArea));
                            areaData.setDataName(getAreaName(accumulateArea));
                            areaData.setVoterCount(accumulateArea.getVoterCount());
                            areaData.setVoterMaleCount(accumulateArea.getVoterMaleCount());
                            areaData.setVoterFemaleCount(accumulateArea.getVoterFemaleCount());
                            return areaData;
                        })
                        .collect(Collectors.toList())
        );
        return bizReportArea;
    }

    private String getAreaId(BizReportAccumulateArea accumulateArea) {
        switch (accumulateArea.getAreaLevel()) {
            case PROVINCE:
                return accumulateArea.getProvinceId();
            case REGENCY:
                return accumulateArea.getRegencyId();
            case DISTRICT:
                return accumulateArea.getDistrictId();
            case VILLAGE:
                return accumulateArea.getVillageId();
        }
        return null;
    }

    private String getAreaName(BizReportAccumulateArea accumulateArea) {
        switch (accumulateArea.getAreaLevel()) {
            case PROVINCE:
                return accumulateArea.getProvinceName();
            case REGENCY:
                return accumulateArea.getRegencyName();
            case DISTRICT:
                return accumulateArea.getDistrictName();
            case VILLAGE:
                return accumulateArea.getVillageName();
        }
        return null;
    }

    private CoreAreaLevel getChildLevel(CoreAreaLevel areaLevel) {
        switch (areaLevel) {
            case PROVINCE:
                return CoreAreaLevel.REGENCY;
            case REGENCY:
                return CoreAreaLevel.DISTRICT;
            case DISTRICT:
                return CoreAreaLevel.VILLAGE;
        }
        return null;
    }
}
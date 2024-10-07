/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizMasterDataDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.data.BizMasterData;
import id.ezclouds.common.model.biz.data.BizMasterDataQueryParam;
import id.ezclouds.common.model.biz.data.OverallMasterData;
import id.ezclouds.common.model.biz.data.VillageMasterData;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.dal.biz.converter.BizMasterDataConverter;
import id.ezclouds.core.dal.biz.dataobject.EzMasterDataDO;
import id.ezclouds.core.dal.biz.repo.EzMasterDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMasterDataDAO.java, v 0.1 2024‐09‐04 8:52 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreMasterDataDAO implements BizMasterDataDAO {

    @Autowired
    private EzMasterDataRepository ezMasterDataRepository;

    @EzDAOLogger
    @Override
    public void storeOrUpdate(BizMasterData masterData) {
        String bizMasterId = HashUtil.createHash(masterData.getOrgId(), masterData.getScene(), masterData.getDataId());
        EzMasterDataDO dataDO = ezMasterDataRepository
                .findAndLockById(bizMasterId);

        if (dataDO != null) {
            dataDO.setProvinceId(masterData.getProvinceId());
            dataDO.setRegencyId(masterData.getRegencyId());
            dataDO.setDistrictId(masterData.getDistrictId());
        }
        else {
            dataDO = new BizMasterDataConverter().convertStore(masterData);
            dataDO.setBizMasterId(bizMasterId);
            dataDO.setNumberValue1(0);
            dataDO.setNumberValue2(0);
            dataDO.setNumberValue3(0);
            dataDO.setNumberValue4(0);
            dataDO.setNumberValue5(0);
        }
        ezMasterDataRepository
                .saveAndFlush(dataDO);
    }

    @EzDAOLogger
    @Override
    public List<BizMasterData> getBizMasterData(BizMasterDataQueryParam param) {
        BizMasterDataConverter converter = new BizMasterDataConverter();
        switch (param.getScene()) {
            case AREA_VILLAGE_STATIC:
                return ezMasterDataRepository
                        .findByOrgIdAndSceneAndDistrictId(param.getOrgId(), param.getScene().getCode(), param.getDistrictId())
                        .stream()
                        .map(converter::convertQuery)
                        .collect(Collectors.toList());

            case OVERALL_STATIC:
                return ezMasterDataRepository
                        .findByOrgIdAndScene(param.getOrgId(), param.getScene().getCode())
                        .stream()
                        .map(converter::convertQuery)
                        .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @EzDAOLogger
    @Override
    public BizMasterData updateData(OverallMasterData overallMasterData) {
        EzMasterDataDO masterDataDO = ezMasterDataRepository
                .findAndLockById(overallMasterData.getBizMasterId());
        masterDataDO.setNumberValue1(overallMasterData.getValueCount());
        ezMasterDataRepository.saveAndFlush(masterDataDO);
        return new BizMasterDataConverter().convertQuery(masterDataDO);
    }

    @EzDAOLogger
    @Override
    public void updateData(VillageMasterData villageMasterData) {
        EzMasterDataDO masterDataDO = ezMasterDataRepository
                .findAndLockById(villageMasterData.getBizMasterId());

        if (villageMasterData.getVoterTotal() != null) {
            masterDataDO.setNumberValue1(villageMasterData.getVoterTotal());
        }
        if (villageMasterData.getVoterMale() != null) {
            masterDataDO.setNumberValue2(villageMasterData.getVoterMale());
        }
        if (villageMasterData.getVoterFemale() != null) {
            masterDataDO.setNumberValue3(villageMasterData.getVoterFemale());
        }
        if (villageMasterData.getPollStationTotal() != null) {
            masterDataDO.setNumberValue4(villageMasterData.getPollStationTotal());
        }

        ezMasterDataRepository.saveAndFlush(masterDataDO);
    }
}
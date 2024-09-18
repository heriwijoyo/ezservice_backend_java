/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizMasterDataDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.data.BizMasterData;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.dal.biz.converter.BizMasterDataConverter;
import id.ezclouds.core.dal.biz.dataobject.EzMasterDataDO;
import id.ezclouds.core.dal.biz.repo.EzMasterDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                .findById(bizMasterId)
                .orElse(null);

        if (dataDO == null) {
            dataDO = new BizMasterDataConverter().convertStore(masterData);
            dataDO.setBizMasterId(bizMasterId);
        }
        ezMasterDataRepository
                .saveAndFlush(dataDO);
    }
}
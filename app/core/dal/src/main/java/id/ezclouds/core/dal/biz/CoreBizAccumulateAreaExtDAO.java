/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.report.BizAccumulateAreaExtDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.report.BizAccumulateAreaExt;
import id.ezclouds.common.model.biz.report.BizAccumulateKey;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.dal.biz.converter.BizAccumulateAreaExtConverter;
import id.ezclouds.core.dal.biz.repo.BizAccumulateAreaExtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizAccumulateAreaExtDAO.java, v 0.1 2024‐10‐03 2:47 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizAccumulateAreaExtDAO implements BizAccumulateAreaExtDAO {

    @Autowired
    private BizAccumulateAreaExtRepository bizAccumulateAreaExtRepository;

    @Override
    @EzDAOLogger
    public void store(BizAccumulateAreaExt accumulateAreaExt) {
        if (StringUtil.isBlank(accumulateAreaExt.getAccumulateAreaExtId())) {
            accumulateAreaExt.generateId();
        }
        bizAccumulateAreaExtRepository
                .saveAndFlush(
                        new BizAccumulateAreaExtConverter()
                                .convertStore(accumulateAreaExt)
                );
    }

    @Override
    @EzDAOLogger
    public List<BizAccumulateAreaExt> getVillageAreaExt(String accumulateId, String accumulateKey) {
        BizAccumulateAreaExtConverter converter = new BizAccumulateAreaExtConverter();
        return bizAccumulateAreaExtRepository
                .findByAccumulateAreaIdAndAccumulateKey(accumulateId, accumulateKey)
                .stream()
                .map(converter::convertQuery)
                .collect(Collectors.toList());
    }

    @Override
    @EzDAOLogger
    public BizAccumulateAreaExt getAndLock(String accumulateAreaId, String orgId, String accumulateKey, String accumulateVariable) {
        String accumulateExtId = HashUtil.createHash(accumulateAreaId, orgId, accumulateKey, accumulateVariable);
        return new BizAccumulateAreaExtConverter()
                .convertQuery(
                        bizAccumulateAreaExtRepository
                                .findAndLockById(accumulateExtId)
                );
    }

    @Override
    @EzDAOLogger
    public int getCountPollStation(String orgId) {
        return Math.toIntExact(
                bizAccumulateAreaExtRepository
                        .countByOrgIdAndAreaLevelAndAccumulateKey(orgId, CoreAreaLevel.VILLAGE.getCode(), BizAccumulateKey.POLL_STATION_ID.getCode())
        );
    }
}
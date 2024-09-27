/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election;

import id.ezclouds.common.facade.dal.biz.election.BizCanvassRecordDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.election.BizCanvassRecord;
import id.ezclouds.core.dal.biz.election.converter.BizCanvassRecordConverter;
import id.ezclouds.core.dal.biz.election.repo.BizCanvassRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizCanvassRecordDAO.java, v 0.1 2024‐09‐26 12:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizCanvassRecordDAO implements BizCanvassRecordDAO {

    @Autowired
    private BizCanvassRecordRepository bizCanvassRecordRepository;

    @Override
    @EzDAOLogger
    public void store(BizCanvassRecord canvassOrder) {
        bizCanvassRecordRepository
                .saveAndFlush(
                        new BizCanvassRecordConverter()
                                .convertStore(canvassOrder)
                );
    }

    @Override
    @EzDAOLogger
    public BizCanvassRecord getCanvassRecord(String canvassOrderId) {
        return new BizCanvassRecordConverter()
                .convertQuery(
                        bizCanvassRecordRepository
                                .findById(canvassOrderId)
                                .orElse(null)
                );
    }
}
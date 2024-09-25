/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election;

import id.ezclouds.common.facade.dal.biz.election.BizVoterCanvassOrderDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.election.BizCanvassOrder;
import id.ezclouds.core.dal.biz.election.converter.BizVoterCanvassOrderConverter;
import id.ezclouds.core.dal.biz.election.repo.BizVoterCanvassOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizVoterCanvassOrderDAO.java, v 0.1 2024‐09‐26 12:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizVoterCanvassOrderDAO implements BizVoterCanvassOrderDAO {

    @Autowired
    private BizVoterCanvassOrderRepository bizVoterCanvassOrderRepository;

    @Override
    @EzDAOLogger
    public void store(BizCanvassOrder canvassOrder) {
        bizVoterCanvassOrderRepository
                .saveAndFlush(
                        new BizVoterCanvassOrderConverter()
                                .convertStore(canvassOrder)
                );
    }

    @Override
    @EzDAOLogger
    public BizCanvassOrder getCanvassOrder(String canvassOrderId) {
        return new BizVoterCanvassOrderConverter()
                .convertQuery(
                        bizVoterCanvassOrderRepository
                                .findById(canvassOrderId)
                                .orElse(null)
                );
    }
}
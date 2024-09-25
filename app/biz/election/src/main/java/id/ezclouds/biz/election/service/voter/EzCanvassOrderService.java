/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.voter;

import id.ezclouds.common.facade.biz.election.CanvassOrderService;
import id.ezclouds.common.facade.core.CoreOrganizationService;
import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.facade.dal.biz.election.BizVoterCanvassOrderDAO;
import id.ezclouds.common.model.biz.election.BizCanvassOrder;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.core.BizSeqScene;
import id.ezclouds.common.model.core.Organization;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.ShardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCanvassOrderService.java, v 0.1 2024‐09‐24 12:30 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCanvassOrderService implements CanvassOrderService {

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private BizVoterCanvassOrderDAO bizVoterCanvassOrderDAO;

    @Override
    public BizCanvassOrder createCanvassOrder(BizVoter bizVoter) {

        Organization organization = coreOrganizationService
                .getById(bizVoter.getOrgId());

        String canvassOrderId = coreSequenceService
                .generateSequence(organization, BizSeqScene.BIZ_VOTER_CANVASS);

        BizCanvassOrder canvassOrder = new BizCanvassOrder();
        canvassOrder.setCanvassOrderId(canvassOrderId);
        canvassOrder.setOrgId(bizVoter.getOrgId());
        canvassOrder.setShard(ShardUtil.getShardId(bizVoter.getVoterId()));
        canvassOrder.setVoterId(bizVoter.getVoterId());
        canvassOrder.setReferrerId(bizVoter.getReferrerId());
        canvassOrder.setCreatedTime(DateUtil.getCurrentFormattedDate());
        canvassOrder.setModifiedTime(DateUtil.getCurrentFormattedDate());
        bizVoterCanvassOrderDAO.store(canvassOrder);

        return canvassOrder;
    }
}
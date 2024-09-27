/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.voter;

import id.ezclouds.common.facade.biz.election.CanvassRecordService;
import id.ezclouds.common.facade.core.CoreOrganizationService;
import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.facade.dal.biz.election.BizCanvassRecordDAO;
import id.ezclouds.common.model.biz.election.BizCanvassRecord;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.core.BizSeqScene;
import id.ezclouds.common.model.core.Organization;
import id.ezclouds.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCanvassRecordService.java, v 0.1 2024‐09‐24 12:30 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCanvassRecordService implements CanvassRecordService {

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private BizCanvassRecordDAO bizCanvassRecordDAO;

    @Override
    public BizCanvassRecord createCanvassOrder(BizVoter bizVoter) {

        Organization organization = coreOrganizationService
                .getById(bizVoter.getOrgId());

        String canvassOrderId = coreSequenceService
                .generateSequence(organization, BizSeqScene.BIZ_VOTER_CANVASS);

        BizCanvassRecord canvassOrder = new BizCanvassRecord();
        canvassOrder.setCanvassOrderId(canvassOrderId);
        canvassOrder.setOrgId(bizVoter.getOrgId());
        canvassOrder.setVoterId(bizVoter.getVoterId());
        canvassOrder.setReferrerId(bizVoter.getReferrerId());
        canvassOrder.setCreatedTime(DateUtil.getCurrentFormattedDate());
        canvassOrder.setModifiedTime(DateUtil.getCurrentFormattedDate());
        bizCanvassRecordDAO.store(canvassOrder);

        return canvassOrder;
    }
}
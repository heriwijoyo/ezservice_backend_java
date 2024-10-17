/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.voter;

import id.ezclouds.common.facade.biz.election.CanvassRecordService;
import id.ezclouds.common.facade.core.CoreBizSequenceService;
import id.ezclouds.common.facade.core.CoreOrganizationService;
import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.facade.dal.biz.election.BizCanvassRecordDAO;
import id.ezclouds.common.model.biz.election.BizCanvassRecord;
import id.ezclouds.common.model.core.CoreSeqSceneEnum;
import id.ezclouds.common.model.core.Organization;
import id.ezclouds.common.model.request.biz.election.CanvassRecordCreateRequest;
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
    private CoreBizSequenceService coreBizSequenceService;

    @Autowired
    private BizCanvassRecordDAO bizCanvassRecordDAO;

    @Override
    public BizCanvassRecord createCanvassRecord(CanvassRecordCreateRequest request) {

        Organization organization = coreOrganizationService
                .getById(request.getOrgId());

        String canvassOrderId = coreSequenceService
                .generateSequence(organization, CoreSeqSceneEnum.BIZ_VOTER_CANVASS);


        return null;
    }
}
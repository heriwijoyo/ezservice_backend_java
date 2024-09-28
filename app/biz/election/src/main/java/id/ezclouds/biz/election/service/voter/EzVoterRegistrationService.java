/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.voter;

import id.ezclouds.common.facade.biz.election.VoterRegistrationService;
import id.ezclouds.common.facade.core.CoreOrganizationService;
import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.facade.dal.biz.election.BizVoterDAO;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.core.CoreSeqSceneEnum;
import id.ezclouds.common.model.core.Organization;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzVoterRegistrationService.java, v 0.1 2024‐09‐23 10:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzVoterRegistrationService implements VoterRegistrationService {

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private BizVoterDAO bizVoterDAO;

    @Override
    public String registerVoter(BizVoter bizVoter) {
        BizVoter existBizVoter = bizVoterDAO.getByIdCardNumber(bizVoter.getOrgId(), bizVoter.getIdCardNumber());
        AssertUtil.isNull(existBizVoter, EzErrorCode.IDEMPOTENT_ERROR);

        Organization organization = coreOrganizationService.getById(bizVoter.getOrgId());
        String voterId = coreSequenceService.generateSequence(organization, CoreSeqSceneEnum.BIZ_VOTER_ID);

        bizVoter.setVoterId(voterId);
        bizVoterDAO.store(bizVoter);

        return voterId;
    }
}
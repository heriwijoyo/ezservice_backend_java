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
import id.ezclouds.common.model.core.BizSeqScene;
import id.ezclouds.common.model.core.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterRegistrationService.java, v 0.1 2024‐09‐23 10:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizVoterRegistrationService implements VoterRegistrationService {

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private BizVoterDAO bizVoterDAO;

    @Override
    public String registerVoter(BizVoter bizVoter) {
        Organization organization = coreOrganizationService.getById(bizVoter.getOrgId());
        String voterId = coreSequenceService.generateSequence(organization, BizSeqScene.BIZ_VOTER_ID);

        bizVoter.setVoterId(voterId);
        bizVoterDAO.store(bizVoter);

        return voterId;
    }
}
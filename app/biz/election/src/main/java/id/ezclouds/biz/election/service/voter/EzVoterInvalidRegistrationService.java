/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.voter;

import id.ezclouds.common.facade.biz.election.VoterInvalidRegistrationService;
import id.ezclouds.common.facade.broker.CoreEventPublisherService;
import id.ezclouds.common.facade.core.CoreOrganizationService;
import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.facade.dal.biz.election.BizVoterInvalidDAO;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.biz.election.BizVoterInvalid;
import id.ezclouds.common.model.broker.event.EzCommonEvent;
import id.ezclouds.common.model.broker.topic.EzCoreTopic;
import id.ezclouds.common.model.core.sequence.CoreSeqSceneEnum;
import id.ezclouds.common.model.core.organization.Organization;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.ShardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzVoterInvalidRegistrationService.java, v 0.1 2024‐10‐06 10:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzVoterInvalidRegistrationService implements VoterInvalidRegistrationService {

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private BizVoterInvalidDAO bizVoterInvalidDAO;

    @Autowired
    private CoreEventPublisherService coreEventPublisherService;

    @Override
    public String registerVoterInvalid(BizVoter bizVoter, String invalidCode, String invalidMessage) {
        Organization organization = coreOrganizationService.getById(bizVoter.getOrgId());
        String voterId = coreSequenceService.generateSequence(organization, CoreSeqSceneEnum.BIZ_VOTER_ID);

        BizVoterInvalid bizVoterInvalid = new BizVoterInvalid();
        BeanCopier
                .create(BizVoter.class, BizVoterInvalid.class, false)
                .copy(bizVoter, bizVoterInvalid, null);

        bizVoterInvalid.setInvalidCode(invalidCode);
        bizVoterInvalid.setInvalidMessage(invalidMessage);
        bizVoterInvalid.setVoterId(voterId);
        bizVoterInvalid.setShard(ShardUtil.getShardId(voterId));
        bizVoterInvalid.setCreatedTime(DateUtil.getCurrentFormattedDate());
        bizVoterInvalid.setModifiedTime(DateUtil.getCurrentFormattedDate());
        bizVoterInvalidDAO.store(bizVoterInvalid);

        coreEventPublisherService.publish(new EzCommonEvent(EzCoreTopic.ELECTION_VOTER_REGISTER_INVALID, bizVoter.getOrgId(), bizVoterInvalid));

        return voterId;
    }
}
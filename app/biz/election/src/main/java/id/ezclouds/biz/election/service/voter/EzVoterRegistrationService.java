/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.voter;

import id.ezclouds.common.facade.biz.election.VoterRegistrationService;
import id.ezclouds.common.facade.broker.CoreEventPublisherService;
import id.ezclouds.common.facade.core.CoreBizValidationService;
import id.ezclouds.common.facade.core.CoreOrganizationService;
import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.facade.dal.biz.election.BizVoterDAO;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.broker.event.EzCommonEvent;
import id.ezclouds.common.model.broker.topic.EzCoreTopic;
import id.ezclouds.common.model.core.BizValidationScene;
import id.ezclouds.common.model.core.CoreSeqSceneEnum;
import id.ezclouds.common.model.core.Organization;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.ShardUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzVoterRegistrationService.java, v 0.1 2024‐09‐23 10:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzVoterRegistrationService implements VoterRegistrationService {

    @Autowired
    private CoreBizValidationService coreBizValidationService;

    @Autowired
    private CoreOrganizationService coreOrganizationService;

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Autowired
    private BizVoterDAO bizVoterDAO;

    @Autowired
    private CoreEventPublisherService coreEventPublisherService;

    @Override
    public String registerVoter(BizVoter bizVoter) {
        coreBizValidationService.validate(bizVoter.getOrgId(), BizValidationScene.BIZ_VOTER_REGISTER, bizVoter);

        BizVoter existBizVoter = bizVoterDAO.getByIdCardNumber(bizVoter.getOrgId(), bizVoter.getIdCardNumber());
        AssertUtil.isNull(existBizVoter, EzErrorCode.IDEMPOTENT_ERROR);

        Organization organization = coreOrganizationService.getById(bizVoter.getOrgId());
        String voterId = coreSequenceService.generateSequence(organization, CoreSeqSceneEnum.BIZ_VOTER_ID);

        bizVoter.setVoterId(voterId);
        bizVoter.setShard(ShardUtil.getShardId(voterId));
        bizVoter.setCreatedTime(DateUtil.getCurrentFormattedDate());
        bizVoter.setModifiedTime(DateUtil.getCurrentFormattedDate());
        bizVoterDAO.store(bizVoter);

        publishVoterCreated(bizVoter);

        return voterId;
    }

    private void publishVoterCreated(BizVoter bizVoter) {
        BizVoter copyBizVoter = new BizVoter();
        BeanCopier
                .create(BizVoter.class, BizVoter.class, false)
                .copy(bizVoter, copyBizVoter, null);

        EzCommonEvent ezCommonEvent = new EzCommonEvent(
                EzCoreTopic.ELECTION_VOTER_REGISTER,
                bizVoter.getOrgId(),
                copyBizVoter
        );
        coreEventPublisherService.publish(ezCommonEvent);
    }
}
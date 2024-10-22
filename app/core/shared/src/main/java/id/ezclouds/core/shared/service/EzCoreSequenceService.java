/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.common.facade.dal.core.CoreSequenceDAO;
import id.ezclouds.common.model.core.sequence.BizSeqInitConfig;
import id.ezclouds.common.model.core.sequence.CoreSeqSceneEnum;
import id.ezclouds.common.model.core.sequence.CoreSequence;
import id.ezclouds.common.model.core.organization.Organization;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.shared.util.CoreSeqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreSequenceService.java, v 0.1 2023‐12‐30 3:47 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreSequenceService implements CoreSequenceService {

    @Autowired
    private CoreSequenceDAO coreSequenceDAO;

    @Override
    public void initSequenceConfig(String orgId) {
        for (BizSeqInitConfig seqInitConfig : BizSeqInitConfig.values()) {
            CoreSequence initSequence = seqInitConfig.toCoreSequence(orgId);

            CoreSequence existSequence = coreSequenceDAO
                    .getSequence(orgId, initSequence.getSeqScene());

            if (existSequence == null) {
                coreSequenceDAO.store(initSequence);
            }
        }
    }

    @Override
    public String generateSequence(Organization organization, CoreSeqSceneEnum seqScene) {
        CoreSequence sequence = coreSequenceDAO.lockSequence(organization.getOrgId(), seqScene);
        AssertUtil.notNull(sequence, EzErrorCode.CORE_SEQUENCE_ERROR);
        int nextStep;
        int nextSequence = sequence.getSequence();
        if (sequence.getStepValue() >= sequence.getStepMax()) {
            nextStep = sequence.getStepMin();
            nextSequence++;
        } else {
            nextStep = sequence.getStepValue() + 1;
        }

        sequence.setStepValue(nextStep);
        sequence.setSequence(nextSequence);
        coreSequenceDAO.store(sequence);

        String shard = String.valueOf(nextStep).substring(1);
        String sceneCode = sequence.getSeqScene().getSceneCode();
        String seqStr = CoreSeqUtil.composeSeqCode(nextSequence, sequence.getSeqLength());

        return organization.getOrgCode() + shard + sceneCode + seqStr;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core;

import id.ezclouds.common.util.HashUtil;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSeqInitConfig.java, v 0.1 2024‐09‐22 5:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizSeqInitConfig {

    CORE_MEMBER_ID(BizSeqScene.CORE_MEMBER_ID, 100, 199, 100, 9),
    BIZ_SUB_ORG(BizSeqScene.BIZ_SUB_ORG, 100, 100, 100, 3),
    BIZ_VOTER_ID(BizSeqScene.BIZ_VOTER_ID, 100, 99, 100, 9),
    BIZ_VOTER_CANVASS(BizSeqScene.BIZ_VOTER_CANVASS, 100, 100, 100, 3),

    ;

    private final CoreSeqScene seqScene;
    private final int stepMin;
    private final int stepMax;
    private final int stepValue;
    private final int seqLength;

    BizSeqInitConfig(CoreSeqScene seqScene, int stepMin, int stepMax, int stepValue, int seqLength) {
        this.seqScene = seqScene;
        this.stepMin = stepMin;
        this.stepMax = stepMax;
        this.stepValue = stepValue;
        this.seqLength = seqLength;
    }

    public CoreSequence toCoreSequence(String orgId) {
        CoreSequence coreSequence = new CoreSequence();
        coreSequence.setSequenceId(HashUtil.createHash(orgId, seqScene.getScene()));
        coreSequence.setOrgId(orgId);
        coreSequence.setSeqScene(seqScene);
        coreSequence.setStepMin(stepMin);
        coreSequence.setStepMax(stepMax);
        coreSequence.setStepMax(stepValue);
        coreSequence.setSeqLength(seqLength);
        coreSequence.setSequence(0);
        return coreSequence;
    }
}
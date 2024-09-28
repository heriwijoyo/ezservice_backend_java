/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.enums;

import id.ezclouds.common.model.core.CoreBizSeqScene;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizElectionSeqScene.java, v 0.1 2024‐09‐28 3:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizElectionSeqScene implements CoreBizSeqScene {

    BIZ_CANVASS_RECORD("BIZ_CANVASS_RECORD", 4),

    ;

    private final String code;
    private final int seqLength;

    private String orgId;
    private String sceneId;

    BizElectionSeqScene(String code, int seqLength) {
        this.code = code;
        this.seqLength = seqLength;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int seqLength() {
        return seqLength;
    }

    @Override
    public String getOrgId() {
        return orgId;
    }

    @Override
    public String getSceneId() {
        return sceneId;
    }
}
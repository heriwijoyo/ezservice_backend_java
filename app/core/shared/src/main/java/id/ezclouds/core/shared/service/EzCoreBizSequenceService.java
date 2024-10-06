/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.core.CoreBizSequenceService;
import id.ezclouds.common.facade.dal.core.CoreBizSequenceDAO;
import id.ezclouds.common.model.core.CoreBizSeqScene;
import id.ezclouds.common.model.core.CoreBizSequence;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.core.shared.util.CoreSeqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreBizSequenceService.java, v 0.1 2024‐09‐28 1:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreBizSequenceService implements CoreBizSequenceService {

    @Autowired
    private CoreBizSequenceDAO coreBizSequenceDAO;

    @Override
    @Transactional
    public String generateSequence(CoreBizSeqScene bizSeqScene) {
        CoreBizSequence bizSequence = coreBizSequenceDAO
                .lockBizSequence(bizSeqScene.getOrgId(), bizSeqScene.getCode(), bizSeqScene.getSceneId());

        if (bizSequence == null) {
            String bizSeqId = CoreSeqUtil.composeBizSeqId(
                    bizSequence.getOrgId(),
                    bizSeqScene.getCode(),
                    bizSeqScene.getSceneId()
            );

            bizSequence = new CoreBizSequence();
            bizSequence.setBizSeqId(bizSeqId);
            bizSequence.setOrgId(bizSeqScene.getOrgId());
            bizSequence.setSeqScene(bizSeqScene.getCode());
            bizSequence.setSeqSceneId(bizSeqScene.getSceneId());
            bizSequence.setSequence(0);
        }

        int updateSeq = bizSequence.getSequence() + 1;
        String seqCode = CoreSeqUtil.composeSeqCode(updateSeq, bizSeqScene.seqLength());

        bizSequence.setSequence(updateSeq);
        bizSequence.setModifiedTime(DateUtil.getCurrentFormattedDateMillis());
        coreBizSequenceDAO.store(bizSequence);

        return seqCode;
    }
}